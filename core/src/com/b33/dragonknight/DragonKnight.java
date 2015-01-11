package com.b33.dragonknight;

import com.b33.dragonknight.model.Player;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class DragonKnight extends Game implements InputProcessor {

    private OrthographicCamera camera;
	private SpriteBatch batch;
    private TiledMap map;
    private TiledMapRenderer tiledMapRenderer;
    private MapObjects objects;
    private ShapeRenderer shapeRenderer;
    private Stage scene;
    private Stage hud;

    private Player player;

    private static DragonKnight dragonKnight;
	
	@Override
	public void create () {
        camera = new OrthographicCamera();
        scene = new Stage(new StretchViewport(1280, 720, camera));
        player = new Player();
        //hud = new Stage(new StretchViewport(1280, 720, camera));


		batch = new SpriteBatch();
        map = new TmxMapLoader().load("data/dk_level_1.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map, batch);
        objects = map.getLayers().get("objects").getObjects();

        shapeRenderer = new ShapeRenderer();

        Gdx.input.setInputProcessor(this);
        dragonKnight = this;
	}

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
    }

    @Override
	public void render () {
        super.render();
		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || (Gdx.input.isTouched(0) && Gdx.input.getX(0) < scene.getViewport().getScreenWidth()/2)) {
            player.setVelocity(-Player.SPEED, 0f);
            player.setPlayerFacing(Player.FACE.LEFT);
        } else
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || (Gdx.input.isTouched(0) && Gdx.input.getX(0) > scene.getViewport().getScreenWidth()/2)) {
            player.setVelocity(Player.SPEED, 0f);
            player.setPlayerFacing(Player.FACE.RIGHT);
        } else {
            player.setVelocity(0f, 0f);
        }


        camera.position.x = player.getX();
        if(camera.position.x < scene.getViewport().getWorldWidth()/2)
            camera.position.x = scene.getViewport().getWorldWidth()/2;

        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        batch.begin();
        player.draw(batch);
        batch.end();

        shapeRenderer.setProjectionMatrix(camera.combined);
        for(MapObject object : objects) {
            if(object instanceof  RectangleMapObject) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();
                TiledMapTile tile = map.getTileSets().getTile(15);
                batch.begin();
                batch.draw(tile.getTextureRegion(), rect.x, rect.y);
                batch.end();
            }
            //RectangleMapObject rectangleMapObject = (RectangleMapObject) object;
            //batch.draw(rectangleMapObject.getRectangle().get);
        }

	}

    @Override
    public void resize(int width, int height) {
        scene.getViewport().update(width, height, true);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public static DragonKnight getInstance() {
        return dragonKnight;
    }

    public Stage getScene() {
        return scene;
    }
}
