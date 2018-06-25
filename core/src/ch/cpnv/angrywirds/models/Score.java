package ch.cpnv.angrywirds.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ch.cpnv.angrywirds.Activities.Play;
import ch.cpnv.angrywirds.AngryWirds;
import ch.cpnv.angrywirds.GameActivity;

public class Score {
    private int score;
    private BitmapFont text;

    public static  final  float SCORE_X = (GameActivity.WORLD_WIDTH/2) - 100;
    public static  final  float SCORE_Y = GameActivity.WORLD_HEIGHT - 100;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Score(int initilScore){
        this.score = initilScore;

        text = new BitmapFont();
        text.setColor(0.0f, 0.0f, 0.0f, 1.0f);
        text.getData().setScale(2,2);
    }

    public void draw(SpriteBatch batch){
        text.setColor(Color.BLACK);
        text.draw(batch, "SCORE: " + String.valueOf(score), SCORE_X, SCORE_Y);
    }

    public void sum(int value){
        this.score = score + value;
    }

    public void substract(int value){
        this.score = score - value;
    }
}
