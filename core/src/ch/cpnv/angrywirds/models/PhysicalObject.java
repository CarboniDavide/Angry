package ch.cpnv.angrywirds.models;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import ch.cpnv.angrywirds.Activities.Play;
import ch.cpnv.angrywirds.GameActivity;

/**
 * Created by Davide.CARBONI on 03.05.2018.
 */

public class PhysicalObject extends Button {
    Boolean haveCollsion = false;
    ArrayList<PhysicalObject> touchedObjects;

    public Boolean getCollsion() {
        return haveCollsion;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Vector2 getPosition(){
        return new Vector2(sprite.getX(),sprite.getY());
    }

    public Vector2 getDimension(){
        return new Vector2(sprite.getWidth(), sprite.getHeight());
    }

    public void setPosition(Vector2 position){
        this.sprite.setPosition(position.x,position.y);
    }

    public void setDimension(Vector2 dimension){
        this.sprite.setSize(dimension.x, dimension.y);
    }

    public void changeBound(Vector2 position, Vector2 dimension){
        setPosition(position);
        setDimension(dimension);
    }

    public PhysicalObject(Vector2 position, Vector2 dimension, String spritePath) {
        super(position,dimension,spritePath);
        this.touchedObjects = new ArrayList<PhysicalObject>();
    }

    @Override
    public String toString(){
        return getClass().getSimpleName() + " at + ("+ sprite.getX() +" "+ sprite.getY() + ")";
    }

    public String GetClassName(){
        return this.getClass().getSimpleName();
    }

    public Boolean IsClass(String className) {
        if (this.GetClassName().equals(className)) return true;
        return false;
    }

    // Check collision for this object with more objects
    public Boolean checkCollisions(ArrayList<PhysicalObject> objects){
        this.haveCollsion = false;
        this.touchedObjects.clear();
        for (PhysicalObject o : objects)
            if (checkCollision(o))
            {
                touchedObjects.add(o);
                haveCollsion = true;
            }
        return  haveCollsion;
    }

    // Check collsion with another object
    public Boolean checkCollision(PhysicalObject object){
        if (this.sprite.getBoundingRectangle().overlaps(object.getSprite().getBoundingRectangle())) return true;
        return false;
    }

    // Get all touched objects
    public ArrayList<PhysicalObject> getTouchedObject() {
        return this.touchedObjects;
    }

    //Check if the object if out of screen
    public Boolean isOutOfScreen() {
        if ((this.sprite.getX() > (GameActivity.WORLD_WIDTH + this.sprite.getWidth())) ||
                (this.sprite.getX() < (0 - this.sprite.getWidth())) ||
                (this.sprite.getY() < (0 - this.sprite.getHeight()))) {
            return true;
        }
        return false;
    }

    public Boolean isTouchedBy(String className) {
        for (PhysicalObject o: touchedObjects)
            if (o.IsClass(className)) return true;
        return false;
    }
}
