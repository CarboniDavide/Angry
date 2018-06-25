package ch.cpnv.angrywirds.models;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import ch.cpnv.angrywirds.Activities.GameOver;
import ch.cpnv.angrywirds.Activities.Play;
import ch.cpnv.angrywirds.AngryWirds;
import ch.cpnv.angrywirds.GameActivity;

/**
 * Created by Davide.CARBONI on 07.05.2018.
 */

public final class Bird extends MovingObject {
    private Boolean isLanched = false;
    private ArrayList<PhysicalObject> scene;

    public static final float DELTA_AC = 0.1f;
    public static final float DELTA_MV = 0.05f;

    private float dt_accelerate = 0f;
    private float dt_move = 0f;

    private float dtx = 0;

    public Bird(Vector2 position, Vector2 dimension, String spritePath, Vector2 speed){
        super(position, dimension, spritePath, speed);
    }

    @Override
    public void accelerate(float dt) {
        /*
        if (isCollsion)
        {
            dtx = dtx+2;
            speed.x = (100/(dtx/20)+1)*(float)Math.cos((0.14)*dtx);
            speed.y = -MovingObject.G;
        }
        else*/
        speed.y += MovingObject.G * 0.1f;

    }

    public void setSpeed(Vector2 newSpeed)
    {
        this.speed = newSpeed;
    }

    public Boolean getisLanched() {
        return isLanched;
    }

    public void Launch(Vector2 speed)
    {
        this.speed = speed;
        dt_accelerate = 0.1f;
        dt_move = DELTA_MV;
        isLanched = true;
    }

    public void Stop(){
        dt_accelerate = 0;
        dt_move = 0;
        isLanched = false;
    }

    public void Reset(){
        Stop();
    }



    @Override
    public void refresh(){
        accelerate(this.dt_accelerate);
        move(this.dt_move);
    }
}
