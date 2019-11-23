package com.blocker.typinggame.entity;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by yangwen on 2019-11-20
 */
public class Word {

    Texture texture;
    Character value;
    Pixmap pixmap;
    int wordX;
    int wordY;


    public Character getValue() {
        return value;
    }

    public void setValue(Character value) {
        this.value = value;
    }

    public Pixmap getPixmap() {
        return pixmap;
    }

    public void setPixmap(Pixmap pixmap) {
        this.pixmap = pixmap;
    }

    public int getWordX() {
        return wordX;
    }

    public void setWordX(int wordX) {
        this.wordX = wordX;
    }

    public int getWordY() {
        return wordY;
    }

    public void setWordY(int wordY) {
        this.wordY = wordY;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }
}
