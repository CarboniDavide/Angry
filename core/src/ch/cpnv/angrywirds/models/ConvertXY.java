package ch.cpnv.angrywirds.models;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class ConvertXY extends Vector2{

    public ConvertXY(Vector2 position, OrthographicCamera camera)
    {
        this.x = camera.unproject(new Vector3(position.x,position.y,0)).x;
        this.y = camera.unproject(new Vector3(position.x,position.y,0)).y;
    }
}
