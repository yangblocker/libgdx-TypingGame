package com.blocker.typinggame;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.MathUtils;
import com.blocker.typinggame.res.Res;
import com.blocker.typinggame.screen.SplashScreen;

public class TypingGame extends Game {
	public SpriteBatch batch;
	public BitmapFont bitmapFont;

	FreeTypeFontGenerator generator;

	public AssetManager assetManager;

	@Override
	public void create () {

		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		batch = new SpriteBatch();

		int index = MathUtils.random(0, 1);
		String fontTypePath;
		if (index == 0) {
			fontTypePath = Res.BITMAP_FONT_ATRAMENT_MEDIUM_PATH;
		} else {
			fontTypePath = Res.BITMAP_FONT_ANTON_REGULAR_PATH;
		}
		generator = new FreeTypeFontGenerator(Gdx.files.internal(fontTypePath));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.color = Color.GOLDENROD;
		parameter.size = 40;
		bitmapFont = generator.generateFont(parameter);

		assetManager = new AssetManager();
		assetManager.load(Res.Audios.BACKGROUND_MUSIC_PATH, Music.class);
		assetManager.load(Res.Audios.HIT_MUSIC_PATH, Sound.class);
		assetManager.finishLoading();
		setScreen(new SplashScreen(this));

	}

	@Override
	public void dispose () {
		batch.dispose();
		bitmapFont.dispose();
		generator.dispose();
		assetManager.dispose();
	}
}
