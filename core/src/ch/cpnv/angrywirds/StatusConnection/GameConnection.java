package ch.cpnv.angrywirds.StatusConnection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;

import javax.swing.Popup;

import ch.cpnv.angrywirds.Activities.GameOver;
import ch.cpnv.angrywirds.AngryWirds;
import ch.cpnv.angrywirds.GameActivity;
import ch.cpnv.angrywirds.Providers.VoxData;
import ch.cpnv.angrywirds.models.Button;
import ch.cpnv.angrywirds.models.PopUpMessage;

/**
 *
 * Check the status connection and show a message with the result
 *
 */

public class GameConnection {
    public static  final  int TIME_MAX_VALUE = 60;

    private static final String BUTTON_PLAY_IMAGE = "play2.png";
    public static final Vector2 BUTTON_PLAY_DIMENSION = new Vector2(200,200);
    public static final Vector2 BUTTON_PLAY_POSITION = new Vector2((GameActivity.WORLD_WIDTH/2)- (BUTTON_PLAY_DIMENSION.x/2),(GameActivity.WORLD_HEIGHT/2)-(BUTTON_PLAY_DIMENSION.y/2));

    public static final Vector2 POPUP_DIMENSION = new Vector2(400,80);
    public static final Vector2 POPUP_POSITION = new Vector2((GameActivity.WORLD_WIDTH/2)- (POPUP_DIMENSION.x/2),(GameActivity.WORLD_HEIGHT/2)-(POPUP_DIMENSION.y/2));

    int initialDelay = 0;  // start after 1 seconds
    int period = 1;        // repeat every 1 seconds
    int timeToFinish;
    com.badlogic.gdx.utils.Timer timer;
    Timer.Task taskStart = new Timer.Task() {public void run() { dec();}};
    PopUpMessage popup;

    Button play;

    public GameConnection(){
        timer = new com.badlogic.gdx.utils.Timer();
        timer.clear();
        timer.scheduleTask(taskStart,initialDelay,period);
        timer.start();
        this.timeToFinish = TIME_MAX_VALUE;
        popup = new PopUpMessage(POPUP_POSITION,POPUP_DIMENSION,"CONNECTION ...");
        play = new Button(BUTTON_PLAY_POSITION,BUTTON_PLAY_DIMENSION,BUTTON_PLAY_IMAGE);
    }

    public void refresh(){
        // Check connection until connection is ready
        if ((VoxData.status != VoxData.Status.ready) && (timeToFinish != 0))
                    VoxData.load();

        if (timeToFinish == 0) // check timer
            popup = new PopUpMessage(POPUP_POSITION, POPUP_DIMENSION, "NO CONNECTION");
        else {
            if (VoxData.status == VoxData.Status.cancelled)
                popup = new PopUpMessage(POPUP_POSITION, POPUP_DIMENSION, "CONNECTION CANCELLED");
            if (VoxData.status == VoxData.Status.error)
                popup = new PopUpMessage(POPUP_POSITION, POPUP_DIMENSION, "CONNECTION ERROR");
        }
    }

    public void draw(SpriteBatch batch){
        popup.draw(batch);
    }

    public void dec() {
        this.timeToFinish -= 1;
        if (this.timeToFinish == 0) {
            timer.stop();
            timer.clear();
        }
    }

}
