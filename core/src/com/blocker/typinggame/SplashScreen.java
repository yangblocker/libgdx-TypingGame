package com.blocker.typinggame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by yangwen on 2019-11-20
 */
public class SplashScreen extends ScreenAdapter {

    private TypingGame typingGame;
    private OrthographicCamera camera;

    public SplashScreen(TypingGame typingGame) {
        this.typingGame = typingGame;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Res.FIX_WORLD_WIDTH, Res.FIX_WORLD_HEIGHT);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        typingGame.batch.setProjectionMatrix(camera.combined);
        typingGame.batch.begin();
        String welcomeMsg = "Welcome to TypingGame!!";
//        Gdx.app.log(SplashScreen.class.getSimpleName(), "bitmapFont.getScaleX: " + typingGame.bitmapFont.getData().xHeight);
        float start = Res.FIX_WORLD_WIDTH - welcomeMsg.length() * typingGame.bitmapFont.getData().xHeight;

        typingGame.bitmapFont.draw(typingGame.batch, "Welcome to TypingGame!!", start, 150);
        typingGame.bitmapFont.draw(typingGame.batch, "Tap to begin!!", start, 200);
        typingGame.batch.end();
        if (Gdx.input.isTouched()) {
            typingGame.setScreen(new MainScreen(typingGame));
            dispose();
        }
    }
}
