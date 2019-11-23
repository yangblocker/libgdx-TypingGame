package com.blocker.typinggame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.blocker.typinggame.act.WordGroup;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by yangwen on 2019-11-20
 */
public class MainScreen extends ScreenAdapter {

    TypingGame game;
    private List<WordGroup> wordList;
    Random random;
    Music bgMusic;
    Sound hitSound;
    private OrthographicCamera camera;

    long lastDropTime;
    int hitedCounter = 0;

    public MainScreen(TypingGame game) {
        this.game = game;
        init();
    }

    private void init() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Res.FIX_WORLD_WIDTH, Res.FIX_WORLD_HEIGHT);

        bgMusic = game.assetManager.get(Res.Audios.BACKGROUND_MUSIC_PATH, Music.class);
        bgMusic.setLooping(true);
        bgMusic.play();
        hitSound = game.assetManager.get(Res.Audios.HIT_MUSIC_PATH, Sound.class);

        random = new Random();
        wordList = new LinkedList<>();
        spawnWordDrop();
    }

    private void spawnWordDrop() {
        WordGroup group = new WordGroup();
        group.setWordX(MathUtils.random(Res.WORD_WIDTH / 2, (int) Res.FIX_WORLD_WIDTH - Res.WORD_WIDTH));
        group.setWordY((int) Res.FIX_WORLD_HEIGHT);
        group.setPixmap(getRandomPixmap());
        group.setValue(getRandomCharacter());
        Texture texture = new Texture(group.getPixmap());
        texture.draw(getRandomPixmap(), group.getWordX(), group.getWordY());
        group.setTexture(texture);
        wordList.add(group);
        lastDropTime = TimeUtils.nanoTime();
    }

    public Character getRandomCharacter() {
        int len = Res.WordCharacter.WORD_CHARACTER.length - 1;
        int index = 0 + (int) (Math.random() * (len - 0 + 1));
        return Res.WordCharacter.WORD_CHARACTER[index];
    }

    public Pixmap getRandomPixmap() {
        Pixmap pixmap = new Pixmap(Res.WORD_WIDTH, Res.WORD_WIDTH, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.YELLOW);
        return pixmap;
    }

    public static String randomHexStr(int len) {
        try {
            StringBuffer result = new StringBuffer();
            for (int i = 0; i < len; i++) {
                //随机生成0-15的数值并转换成16进制
                result.append(Integer.toHexString(new Random().nextInt(16)));
            }
            return result.toString().toUpperCase();
        } catch (Exception e) {
            System.out.println("获取16进制字符串异常，返回默认...");
            return "00CCCC";
        }
    }

    public static Color randomColor() {
        int color = Integer.valueOf(randomHexStr(6), 16);
        return new Color(color);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.bitmapFont.setColor(Color.RED);
        game.bitmapFont.getData().setScale(0.6f);
        game.bitmapFont.draw(game.batch, "Hited: " + hitedCounter, 10, Res.FIX_WORLD_HEIGHT - 20);

        game.bitmapFont.setColor(Color.YELLOW);
        game.bitmapFont.getData().setScale(1.0f);
        for (WordGroup group : wordList) {
            game.batch.draw(group.getTexture(), group.getWordX(), group.getWordY());
            game.bitmapFont.draw(game.batch, String.valueOf(group.getValue()), group.getWordX(), group.getWordY());
        }
        game.batch.end();

        Gdx.input.setInputProcessor(new KeyboardProcessEvent());

        if (TimeUtils.nanoTime() - lastDropTime > 1000000000) {
            spawnWordDrop();
        }

        Iterator<WordGroup> iter = wordList.iterator();
        while (iter.hasNext()) {
            WordGroup wordGroup = iter.next();
            wordGroup.setWordY((int) (wordGroup.getWordY() - 200 * Gdx.graphics.getDeltaTime()));
            if (wordGroup.getWordY() < 0) {
                iter.remove();
                wordGroup.getTexture().dispose();
                wordGroup.getPixmap().dispose();
            }
        }
    }

    class KeyboardProcessEvent extends InputAdapter {

        public static final String TAG = "KeyboardProcessEvent";

        @Override
        public boolean keyDown(int keycode) {
//            Gdx.app.log(TAG, "keycode: " + Input.Keys.toString(keycode));
            WordGroup word = null;
            if (!wordList.isEmpty()) {
                word = wordList.get(0);
            }
//            Gdx.app.log(TAG, "word: " + word.getValue());
            if (word != null && Input.Keys.toString(keycode).equalsIgnoreCase(word.getValue().toString())) {
//                Gdx.app.log(TAG, "hited....");
                // 击中了
                hitedCounter++;
                wordList.remove(word);
                word.getTexture().dispose();
                word.getPixmap().dispose();
                hitSound.play();
            } else if (keycode == Input.Keys.ESCAPE) {
                // 退出游戏
            }
            return true;
        }
    }

    @Override
    public void dispose() {
        bgMusic.dispose();
        hitSound.dispose();
    }
}
