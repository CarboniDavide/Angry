package ch.cpnv.angrywirds.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import ch.cpnv.angrywirds.AngryWirds;
import ch.cpnv.angrywirds.GameActivity;

/**
 * Created by Davide.CARBONI on 07.05.2018.
 */

public final class Wasp extends MovingObject{

    Random rand = new Random();

    public static  final  int OFFSET_X = GameActivity.WORLD_WIDTH/2;
    public static  final  int OFFSET_Y = GameActivity.WORLD_HEIGHT/2;
    public static  final  int QUANTUM_X = GameActivity.WORLD_WIDTH/10;;
    public static  final  int QUANTUM_Y = GameActivity.WORLD_HEIGHT/20;

    private float rel_X;
    private float rel_Y;

    private float max_X;
    private float min_X;
    private float max_Y;
    private float min_Y;
    private float oldSpeed_X;
    private float oldSpeed_Y;

    public Wasp(Vector2 position, Vector2 dimension, String spritePath, Vector2 speed){
        super(position, dimension, spritePath, speed);
    }

    @Override
    public void accelerate(float dt) {

        rel_X = sprite.getX() - OFFSET_X;
        max_X = 20 -  (rel_X/(QUANTUM_X));
        min_X = -20 - (rel_X/(QUANTUM_X));

        rel_Y = sprite.getY() - OFFSET_Y;
        max_Y = 20 - (rel_Y /(QUANTUM_Y));
        min_Y = -20 - (rel_Y /(QUANTUM_Y));

        if ((sprite.getX() < 100) || (sprite.getX() > 1500))
            speed.x = 0;

        if ( (sprite.getY() < 100) || (sprite.getY() > 800)  )
            speed.y = 0;

        float number_X =(float)((Math.random() * ((max_X - min_X) + 1) ) + min_X);
        float number_Y =(float)((Math.random() * ((max_Y - min_Y) + 1) ) + min_Y);
        // max = 30; min = -10;

        speed.x += number_X ;
        speed.y += number_Y ;

        oldSpeed_X = speed.x;
        oldSpeed_Y = speed.y;

        speed.x = speed.x * ((rand.nextInt(50)) == 25 ? -1 : 1) ;
        speed.y = speed.y * ((rand.nextInt(50)) == 25 ? -1 : 1) ;

        if (speed.x != oldSpeed_X)
            speed.x = speed.x/(rand.nextInt(50) + 1);

        if (speed.y != oldSpeed_Y)
            speed.y = speed.y/(rand.nextInt(50) + 1);
    }

    @Override
    public void refresh()
    {
        this.accelerate(1f);
        this.move(0.08f);
    }
}
