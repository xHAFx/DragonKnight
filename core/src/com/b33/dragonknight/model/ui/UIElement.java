package com.b33.dragonknight.model.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Stephen on 1/10/2015.
 */
public abstract class UIElement {

    public abstract Sprite getSprite();

    public abstract void actionInvoked();

}
