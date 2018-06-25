package ch.cpnv.angrywirds.models;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Davide.CARBONI on 07.05.2018.
 */

public abstract class MovingObject extends  PhysicalObject{

    public final static  float G = -100f;
    protected Vector2 speed;

    public MovingObject(Vector2 position, Vector2 dimension, String spritePath, Vector2 speed){
        super(position, dimension, spritePath);
        this.speed = speed;
    }

    public  abstract  void accelerate(float dt);

    public  abstract  void refresh();

    public final void move(float dt)
    {
        this.sprite.translate(speed.x * dt, speed.y *dt);
    }

    @Override
    public String toString()
    {
        return getClass().getSimpleName()+" at (" + sprite.getX() + "," + sprite.getX() + "), moving at (" + speed.x  + "," + speed.y +")";
    }
}
