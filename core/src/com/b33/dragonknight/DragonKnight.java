package com.b33.dragonknight;

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
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class DragonKnight extends Game implements InputProcessor {

    private final static int WALK_ROWS = 2;
    private final static int WALK_COLS = 4;

    private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture walkSheet;
    private TextureRegion[] walkFrames;
    private TextureRegion currentFrame;
    private Animation walkAnimation;
    private TiledMap map;
    private TiledMapRenderer tiledMapRenderer;
    private Stage scene;
    private float stateTime;
    private Stage hud;
	
	@Override
	public void create () {
        camera = new OrthographicCamera();
        scene = new Stage(new StretchViewport(1280, 720, camera));

        walkSheet = new Texture(Gdx.files.internal("data/player_walk_sheet.png"));
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/WALK_COLS, walkSheet.getHeight()/WALK_ROWS);
        walkFrames = new TextureRegion[WALK_ROWS * WALK_COLS];
        int index = 0;
        for(int i = 0; i < WALK_ROWS; i++) {
            for(int j = 0; j < WALK_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }
        walkAnimation = new Animation(0.125f, walkFrames);
        stateTime = 0f;
        //hud = new Stage(new StretchViewport(1280, 720, camera));


		batch = new SpriteBatch();
        map = new TmxMapLoader().load("data/level1.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map, batch);

        Gdx.input.setInputProcessor(this);
	}

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
    }

    @Override
	public void render () {
        super.render();
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || (Gdx.input.isTouched(0) && Gdx.input.getX(0) < scene.getViewport().getScreenWidth()/2)) {
            camera.translate(-10.0f, 0f);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || (Gdx.input.isTouched(0) && Gdx.input.getX(0) > scene.getViewport().getScreenWidth()/2)) {
            camera.translate(10.0f, 0f);
        }

        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = walkAnimation.getKeyFrame(stateTime, true);
        batch.begin();
        batch.draw(currentFrame, 50, 50);
        batch.end();

        batch.setProjectionMatrix(camera.combined);


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
}
