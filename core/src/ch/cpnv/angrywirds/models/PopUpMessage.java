package ch.cpnv.angrywirds.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class PopUpMessage {

    private static final String POPUP_IMAGE = "popUp.png";

    protected Sprite sprite;
    private BitmapFont font;
    private String text="";

    public PopUpMessage(Vector2 position, Vector2 dimension, String text) {
        this.sprite = new Sprite(new Texture(POPUP_IMAGE));
        this.sprite.setBounds(position.x,position.y,dimension.x, dimension.y);
        font = new BitmapFont();
        font.setColor(0.0f, 0.0f, 0.0f, 1.0f);
        font.getData().setScale(2,2);
        this.text = text;
    }

    public void draw(SpriteBatch batch){
        sprite.draw(batch);
        font.setColor(Color.BLACK);
        font.draw(batch, text, fontPositionXinsideButton(), fontPositionYinsideButton());
    }

    private float fontPositionXinsideButton() {
        float offsetX = (this.sprite.getWidth()/2) - (font.getScaleX()*text.length()*font.getSpaceWidth());
        return this.sprite.getX()+offsetX;
    }

    private float fontPositionYinsideButton() {
        float offsetY = (this.sprite.getHeight()/2) - ((font.getScaleY()*font.getXHeight())/2);
        return this.sprite.getY()+offsetY +(font.getScaleY()*font.getXHeight()-5);
    }
}
