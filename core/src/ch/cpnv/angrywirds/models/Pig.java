package ch.cpnv.angrywirds.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import java.util.TimerTask;

import ch.cpnv.angrywirds.Activities.Play;
import ch.cpnv.angrywirds.AngryWirds;

/**
 * Created by Davide.CARBONI on 07.05.2018.
 */

public final class Pig extends PhysicalObject {
    public static  final  int MESSAGE_OFFSET_X = 180;
    public static  final  int MESSAGE_OFFSET_Y = 80;

    private String word;
    private int idWord;

    private PhysicalObject message;
    private BitmapFont text;
    public Boolean isOpened = false;

    int initialDelay = 3; // start after 1 seconds
    int period = 0;        // repeat every 1 seconds
    Timer timer;
    Task taskStart = new Task() {public void run() { DisableMessage();}};


    public Pig(Vector2 position, Vector2 dimension, String spritePath, String word){
        super(position, dimension, spritePath);
        this.word = "nothing";
        text = new BitmapFont();
        text.setColor(0.0f, 0.0f, 0.0f, 1.0f);
        text.getData().setScale(2,2);

        timer = new Timer();
    }

    public static String getMyClass(){
        return Pig.class.getSimpleName();
    }

    public String getWord() {return word;}

    public void setWord(String value)
    {
        this.word = value;
    }

    public int getIdWord() {
        return idWord;
    }

    public void setIdWord(int idWord) {
        this.idWord = idWord;
    }

    public void EnableMessage()
    {
        timer.clear();
        timer.scheduleTask(taskStart,initialDelay,period);
        isOpened = true;
        timer.start();
    }

    public void DisableMessage()
    {
        isOpened = false;
        timer.clear();
    }

    public void ShowMessage(SpriteBatch batch)
    {
        if (!isOpened) return;
        message = new PhysicalObject(new Vector2(getSprite().getX()- MESSAGE_OFFSET_X, getSprite().getY()+ MESSAGE_OFFSET_Y), new Vector2(250, 150), Play.MESSAGE_SRITE);
        message.draw(batch);
        text.setColor(Color.BLACK);
        text.draw(batch, word, message.getSprite().getX()+ 40,message.getSprite().getY() + 100);

    }
}
