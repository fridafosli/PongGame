package com.frida.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.frida.game.PongGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = PongGame.WIDTH;
		config.height = PongGame.HEIGHT;
		config.title = PongGame.TITLE;
		new LwjglApplication(new PongGame(), config);
	}
}
