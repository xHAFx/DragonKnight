package com.b33.dragonknight.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Brendan on 08/01/2015.
 */
public class Player extends Sprite {

    public enum FACE {LEFT, RIGHT}

    private final static int WALK_ROWS = 2;
    private final static int WALK_COLS = 4;

    /** The movement velocity **/
    private static final Vector2 VELOCITY = new Vector2();

    /** Player movement speed **/
    public static final float SPEED = 80 * 2;

    /** Default player gravity **/
    private static final float GRAVITY = 60 * 1.0f;

    private Texture walkSheet;
    private TextureRegion[] walkFrames;
    private TextureRegion currentFrame;
    private Animation walkAnimation;

    private FACE playerFacing;

    private float stateTime;

    public Player() {
        super();
        init();

        stateTime = 0f;
    }

    public void setVelocity(float x, float y) {
        this.VELOCITY.set(x, y);
    }

    public void setPlayerFacing(FACE direction) {
        this.playerFacing = direction;
    }

    @Override
    public void draw(Batch batch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(batch);
    }

    public void init() {
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
        currentFrame = walkAnimation.getKeyFrame(0);

        this.set(new Sprite(walkSheet));
        this.setRegion(currentFrame);
        this.setSize(128f, 64f);
        this.setY(50f);
        this.playerFacing = FACE.RIGHT;
    }

    public void update(float delta) {
        stateTime += delta;

        /** Is player moving? **/

        if(this.VELOCITY.x > 0f || this.VELOCITY.x < 0f) {
            currentFrame = walkAnimation.getKeyFrame(stateTime, true);
        } else {
            currentFrame = walkAnimation.getKeyFrame(0);
        }


        this.setRegion(currentFrame);
        setX((getX() + VELOCITY.x * delta));
        if(getX() < 0)
            setX(0);

        if(playerFacing == FACE.LEFT) {
            //this.setOrigin(this.getWidth()/4, this.getHeight()/2);
            this.flip(true, false);
        }


    }
}
