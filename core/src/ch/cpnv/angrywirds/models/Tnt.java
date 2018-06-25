package ch.cpnv.angrywirds.models;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Davide.CARBONI on 07.05.2018.
 */

public final class Tnt extends PhysicalObject {
    private final int negativePoints = 25;

    public Tnt(Vector2 position, Vector2 dimension, String spritePath){
        super(position, dimension, spritePath);
    }

}
