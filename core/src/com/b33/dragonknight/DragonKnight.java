package com.b33.dragonknight;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class DragonKnight extends ApplicationAdapter implements InputProcessor {

    private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
    private TiledMap map;
    private Viewport viewport;
    private TiledMapRenderer tiledMapRenderer;
    private Sprite sprite;
	
	@Override
	public void create () {
        camera = new OrthographicCamera();
        viewport = new StretchViewport(1280, 720, camera);


		batch = new SpriteBatch();
        map = new TmxMapLoader().load("data/level1.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);


        texture = new Texture(Gdx.files.internal("data/player.png"));

        sprite = new Sprite(texture);
        sprite.setPosition(100, 50);

        Gdx.input.setInputProcessor(this);
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

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || (Gdx.input.isTouched(0) && Gdx.input.getX(0) < viewport.getScreenWidth()/2)) {
            camera.translate(-10.0f, 0f);
            sprite.translate(-10.0f, 0f);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || (Gdx.input.isTouched(0) && Gdx.input.getX(0) > viewport.getScreenWidth()/2)) {
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
        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);
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
