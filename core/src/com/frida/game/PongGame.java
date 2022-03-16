package com.frida.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.frida.game.states.GameStateManager;
import com.frida.game.states.MenuState;
import com.frida.game.states.PlayState;

public class PongGame extends ApplicationAdapter {
	//forutsetter Pixel 3 API 30 og at toppen er tiltet mot venstre
	public static final int WIDTH = 2030;
	public static final int HEIGHT = 1080;

	public static final String TITLE = "PongGame";

	private GameStateManager gsm;
	private SpriteBatch batch;

	private OrthographicCamera camera;
	private ExtendViewport viewport;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		gsm.push(new PlayState(gsm, 0, 0));
		gsm.push(new MenuState(gsm, 0, 0));
		camera = new OrthographicCamera();
		viewport = new ExtendViewport(2030, 1080, camera);
	}

	@Override
	public void render () {
		/*ScreenUtils.clear(1, 0, 0, 1);*/
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
		resize(WIDTH, HEIGHT);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height, true);

		batch.setProjectionMatrix(camera.combined);
	}
}
