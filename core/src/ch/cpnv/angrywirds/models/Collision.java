package ch.cpnv.angrywirds.models;

import java.util.ArrayList;

public class Collision {

    ArrayList<PhysicalObject> touchedObjects;
    public Boolean isCollsion;

    public Collision(){
        touchedObjects = new ArrayList<PhysicalObject>();
        this.isCollsion = false;
    }

    // Check collision for this object with more objects
    public Boolean checkCollisions(PhysicalObject object, ArrayList<PhysicalObject> objects){
        touchedObjects.clear();
        for (PhysicalObject o : objects)
            if (checkCollision(object, o))
            {
                object.touchedObjects.add(o);
                this.touchedObjects.add(o);
                object.haveCollsion = true;
                this.isCollsion = true;
            }
        return  object.haveCollsion;
    }

    // Check collsion with another object
    public Boolean checkCollision(PhysicalObject object1, PhysicalObject object2){
        if (object1.sprite.getBoundingRectangle().overlaps(object2.getSprite().getBoundingRectangle())) return true;
        return false;
    }

    public Boolean contains(String className) {
        for (PhysicalObject o: touchedObjects)
            if (o.IsClass(className)) return true;
        return false;
    }
}
