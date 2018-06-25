package ch.cpnv.angrywirds.models;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Button {

    protected Sprite sprite;
    private BitmapFont font;
    private String text="";
    private Boolean isTextType;

    public Button(Vector2 position, Vector2 dimension, String spritePath) {
        this.sprite = new Sprite(new Texture(spritePath));
        this.sprite.setBounds(position.x,position.y,dimension.x, dimension.y);
        this.isTextType = false;
    }

    public Button(Vector2 position, Vector2 dimension, String spritePath, String text) {
        this.sprite = new Sprite(new Texture(spritePath));
        this.sprite.setBounds(position.x,position.y,dimension.x, dimension.y);
        font = new BitmapFont();
        font.setColor(0.0f, 0.0f, 0.0f, 1.0f);
        font.getData().setScale(2,2);
        this.isTextType = true;
        this.text = text;
    }

    public Boolean checkIfClicked (float ix, float iy) {
        if(sprite.getBoundingRectangle().contains(new Vector2(ix,iy))) return true;
        /*
        if (ix > sprite.getX() && ix < sprite.getX() + sprite.getWidth())
            if (iy > sprite.getY() && iy < sprite.getY() + sprite.getHeight()) return true;*/

        return false;
    }

    public void draw(SpriteBatch batch){
        sprite.draw(batch);
        if (isTextType) {
            font.setColor(Color.BLACK);
            font.draw(batch, text, fontPositionXinsideButton(), fontPositionYinsideButton());
        }
    }

    private float fontPositionXinsideButton() {
        float offsetX = (this.sprite.getWidth()/2) - (font.getScaleX()*text.length()*font.getSpaceWidth());
        return this.sprite.getX()+offsetX;
    }

    private float fontPositionYinsideButton() {
        float offsetY = (this.sprite.getHeight()/2) - ((font.getScaleY()*font.getXHeight())/2);
        return this.sprite.getY()+offsetY +(font.getScaleY()*font.getXHeight()) -5 ;
    }
}