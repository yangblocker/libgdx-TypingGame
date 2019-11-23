package com.blocker.typinggame.res;

/**
 * Created by yangwen on 2019-11-20
 */
public interface Res {
    float FIX_WORLD_WIDTH = 800;
    float FIX_WORLD_HEIGHT = 480;

    // 单词的宽度
    int WORD_WIDTH = 50;

    String BITMAP_FONT_ANTON_REGULAR_PATH = "font/Anton-Regular.ttf";

    String BITMAP_FONT_ATRAMENT_MEDIUM_PATH = "font/Atrament-Medium.woff.ttf";

    public interface Audios {
        // 背景音乐
        String BACKGROUND_MUSIC_PATH = "audio/background_music.mp3";
        // 击中音乐
        String HIT_MUSIC_PATH = "audio/hit.mp3";
    }

    public interface WordCharacter {
        Character[] WORD_CHARACTER = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    }
}
