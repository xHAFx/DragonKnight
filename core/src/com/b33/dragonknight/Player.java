package com.b33.dragonknight;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Brendan on 08/01/2015.
 */
public class Player extends Sprite {

    /** The movement velocity **/
    private Vector2 velocity = new Vector2();

    private float speed = 60 * 2;

    private float gravity = 60 * 1.0f;

    public Player(Sprite sprite) {
        super(sprite);

    }

    @Override
    public void draw(Batch batch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(batch);
    }

    public void update(float delta) {
        //velocity.y -= gravity * delta;

        setX(getX() + velocity.x * delta);
    }
}
