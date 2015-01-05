package com.b33.dragonknight.desktop;

import com.b33.dragonknight.DragonKnight;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "DragonKnight";
        config.width = 1600;
        config.height = 900;

		new LwjglApplication(new DragonKnight(), config);
	}
}
