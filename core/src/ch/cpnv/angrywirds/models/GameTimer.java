package ch.cpnv.angrywirds.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import ch.cpnv.angrywirds.Activities.GameOver;
import ch.cpnv.angrywirds.Activities.Play;
import ch.cpnv.angrywirds.AngryWirds;
import ch.cpnv.angrywirds.GameActivity;

public class GameTimer {

    //

    private BitmapFont text;

    public static  final  float TIMER_X = (GameActivity.WORLD_WIDTH/2) - 100;
    public static  final  float TIMER_Y = GameActivity.WORLD_HEIGHT - 50;
    public static  final  int TIME_MAX_VALUE = 120;

    int initialDelay = 1;  // start after 1 seconds
    int period = 1;        // repeat every 1 seconds
    int timeToFinish;
    com.badlogic.gdx.utils.Timer timer;
    Task taskStart = new Task() {public void run() { dec();}};

    public int getTimeToFinish() {
        return timeToFinish;
    }

    public void setTimeToFinish(int timeToFinish) {
        this.timeToFinish = timeToFinish;
    }

    public GameTimer(int initialTime){
        text = new BitmapFont();
        text.setColor(0.0f, 0.0f, 0.0f, 1.0f);
        text.getData().setScale(2,2);
        timer = new com.badlogic.gdx.utils.Timer();
        timer.clear();
        timer.scheduleTask(taskStart,initialDelay,period);
        timer.start();
        this.timeToFinish = initialTime;
    }

    public void refresh(){
        if (this.timeToFinish == 0){ // Time out
            AngryWirds.gameActivityManager.push(new GameOver());
        }
    }

    public void addTime(int value){
        this.timeToFinish = timeToFinish + value;
    }

    public void draw(SpriteBatch batch){
        text.setColor(Color.BLACK);
        text.draw(batch, "TIME: " + String.valueOf(this.timeToFinish) + " s", TIMER_X, TIMER_Y);
    }

    public void dec() {
        this.timeToFinish -= 1;
    }

}
