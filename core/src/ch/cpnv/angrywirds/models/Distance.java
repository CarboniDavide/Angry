package ch.cpnv.angrywirds.models;

import com.badlogic.gdx.math.Vector2;

public class Distance extends Vector2{

    public Distance(Vector2 startPoint, Vector2 endPoint)
    {
        this.x = startPoint.x - endPoint.x;
        this.y = startPoint.y - endPoint.y;
    }

}
