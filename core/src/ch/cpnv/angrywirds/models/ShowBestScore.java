package ch.cpnv.angrywirds.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ch.cpnv.angrywirds.DataStore.BestScore;
import ch.cpnv.angrywirds.DataStore.GameScore;
import ch.cpnv.angrywirds.GameActivity;

public class ShowBestScore {
    private int score;
    private BitmapFont text;
    private BestScore bestScore;
    private  GameScore gameScore;

    public static  final  float START_X = 500;
    public static  final  float START_Y = 600;
    public static  final  float STEP_Y = 90;

    public int getScore() {
        return score;
    }

    public ShowBestScore(BestScore bestScore, GameScore score){
        this.bestScore = bestScore;
        text = new BitmapFont();
        text.setColor(0.0f, 0.0f, 0.0f, 1.0f);
        text.getData().setScale(3,3);
        this.gameScore = score;
    }

    public void draw(SpriteBatch batch){
        for(int i=0; i<bestScore.getBestScores().size(); i++) {
            text.setColor(Color.YELLOW);
            text.draw(batch, "Your score : " + String.valueOf(gameScore.getScore()), START_X, START_Y + 190);
            text.draw(batch,"Best score ...", START_X,START_Y  + 100);
            text.draw(batch,
                    String.valueOf(i+1)
                    + " SCORE :  " + String.valueOf(bestScore.getBestScores().get(i).getScore())
                    + " - TIME :  " + String.valueOf(bestScore.getBestScores().get(i).getTime()),
                    START_X, START_Y - (STEP_Y*i));
        }
    }
}
