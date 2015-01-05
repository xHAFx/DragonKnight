package com.b33.dragonknight;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class DragonKnight extends ApplicationAdapter implements GestureDetector.GestureListener {

    private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
    private TiledMap map;
    private Viewport viewport;
    private TiledMapRenderer tiledMapRenderer;
    private Sprite sprite;
	
	@Override
	public void create () {
        camera = new OrthographicCamera(1280, 720);
        viewport = new ScreenViewport(camera);


		batch = new SpriteBatch();
        map = new TmxMapLoader().load("data/level1.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);


        texture = new Texture(Gdx.files.internal("data/player.png"));

        sprite = new Sprite(texture);
        //sprite.setOrigin(400,200);
        sprite.setPosition((camera.viewportWidth/2 - sprite.getWidth()/2), 50);

        Gdx.input.setInputProcessor(new GestureDetector((GestureDetector.GestureListener) this));
	}

    @Override
    public void dispose() {
        super.dispose();
        texture.dispose();
    }

    @Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if(camera.position.x >= camera.viewportWidth/2 + 10f) {
                camera.translate(-10.0f, 0f);
                sprite.translate(-10.0f, 0f);
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                camera.translate(10.0f, 0f);
                sprite.translate(10.0f, 0f);
        }
        batch.setProjectionMatrix(camera.combined);
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        batch.begin();
        sprite.draw(batch);
        batch.end();
	}

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(viewport.getScreenWidth()/2f, viewport.getScreenHeight()/2f, 0);
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }
}
