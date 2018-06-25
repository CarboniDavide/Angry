package ch.cpnv.angrywirds.models;

import com.badlogic.gdx.math.Vector2;

public class Block extends PhysicalObject{

    public Block(Vector2 position, Vector2 dimension, String spritePath){
        super(position, dimension, spritePath);
    }
}
