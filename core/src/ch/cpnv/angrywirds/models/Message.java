package ch.cpnv.angrywirds.models;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import ch.cpnv.angrywirds.AngryWirds;

public class Message{

    public static  final  int MESSAGE_OFFSET_X = 80;
    public static  final  int MESSAGE_OFFSET_Y = 80;

    ArrayList<PhysicalObject> messages;

    public void addMessage(PhysicalObject o)
    {
        PhysicalObject msg = new PhysicalObject(new Vector2(o.getSprite().getX()- MESSAGE_OFFSET_X, o.getSprite().getY()+ MESSAGE_OFFSET_Y), new Vector2(150, 150), "bubble.png");
        messages.add(msg);
    }

    public void removeMessage()
    {
        if (messages.size() != 0)
            messages.remove(0);
    }

    public Message(){
        messages = new ArrayList<PhysicalObject>();
    }

    public void draw(SpriteBatch batch){
        for (PhysicalObject p:messages)
            p.draw(batch);
    }
}