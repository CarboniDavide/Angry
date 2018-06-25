package ch.cpnv.angrywirds.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import ch.cpnv.angrywirds.Activities.Play;
import ch.cpnv.angrywirds.AngryWirds;
import ch.cpnv.angrywirds.GameActivity;

public class Scenery {
    ArrayList<PhysicalObject> scene = new ArrayList<PhysicalObject>();
    PhysicalObject visibility;

    private enum ObjectNames {
        Bird, Block, Pig, Tnt, Wasp, Clock;;
    }

    public void Scenery(){

    }

    // Generate a new Scenery
    public void Make()
    {
        MakeObject("Block",5);
        MakeObject("Tnt",5);
        MakeObject("Block",5);
        MakeObject("Pig",4);
        MakeObject("Clock",1);
    }

    public void AddScene(PhysicalObject o)
    {
        scene.add(o);
    }

    public ArrayList<PhysicalObject> getScene() {
        return scene;
    }

    // Create a new Object
    private PhysicalObject CreateObject(String className, Vector2 position, Vector2 dimension)
    {
        ObjectNames objectNames = ObjectNames.valueOf(className); // surround with try/catch

        switch (objectNames){
            case Bird: return new Bird(position,dimension, Play.BIRD_SRITE,null);
            case Block: return new Block(position,dimension,Play.BLOK_SRITE);
            case Pig: return new Pig(position,dimension,Play.PIG_SRITE,"");
            case Tnt: return new Tnt(position,dimension,Play.TNT_SRITE);
            case Wasp: return new Wasp(position,dimension,Play.WASP_SRITE,null);
            case Clock: return new Clock(position,dimension,Play.CLOCK_SRITE);
            default: return null;
        }
    }

    // Add a new Object in the scenery if possible
    public void AddElement(PhysicalObject object) throws Exception{
        if (CanInsert(object))
                scene.add(object);
        else
            throw new Exception("No place");
    }

    // Create a new elemnts in the scenary
    private  void MakeObject(String name, int quantity)
    {
        int q = quantity;
        while (q != 0) {
            try {
                AddElement(CreateObject( // Create a new object with random position in the top of the screen
                        name,
                        new Vector2((float)((Math.random() * (((GameActivity.WORLD_WIDTH - Play.IMAGE_WITH) - Play.START_SCENE_X) + 1) ) + Play.START_SCENE_X) , GameActivity.WORLD_HEIGHT - 100),
                        new Vector2(Play.IMAGE_WITH, Play.IMAGE_HEIGHT)));
                q--;
            } catch (Exception e) {
            }
        }
    }

    // Verify if the elements can be insert in the scene
    private Boolean CanInsert(PhysicalObject elm)
    {
        // Translate the object from the top of the screen to the floor
        // If no collision as detected then put the object in the floor
        // If collision was detected then verify if the object can stay on the collided object:
        //   - collision width one object: -> verify balance
        //   - collision width two object: -> Ok put object on the two objects
        //   - collision Pig:Pig -> not possible
        //   - collision with the polygon of visibility: ePig have visibility ? If yes put the Pig, else make new position for the Pig

        Boolean isFinished = false;

        while (!isFinished)
        {
            if (elm.checkCollisions(scene))
            {
                // Collision for the Pig
                if (elm.IsClass("Pig")) {
                    // Two Pig can't be in the same stack
                    if (elm.isTouchedBy("Pig")) return false;

                    // Check the visibility fear for the Pig
                    if (!isPigVisible(elm)) return false;
                }

                // Max Height
                if (elm.getSprite().getY() > Play.START_SCENE_Y) return false;

                // check if the object can be have balance
                if (elm.getTouchedObject().size() == 1) {
                    float marg = elm.getSprite().getX() - elm.getTouchedObject().get(0).getSprite().getX();
                    if (Math.abs(marg) <= (elm.getTouchedObject().get(0).getSprite().getWidth()/3))
                        return true;
                    else
                        return false;
                }

                // Check if the object can be surrended by two object
                if (elm.getTouchedObject().size() == 2) {
                    float marg = elm.getTouchedObject().get(1).getSprite().getX() + elm.getTouchedObject().get(1).getSprite().getWidth() - elm.getTouchedObject().get(0).getSprite().getX();
                    if (Math.abs(marg) <= (elm.getTouchedObject().get(0).getSprite().getWidth()/1.2f))
                        return true;
                    else
                        return false;
                }
            }

            // Control 3: The Floor
            if (elm.getSprite().getY() == Play.FLOW_HEIGHT)
            {
                if (elm.IsClass("Pig")) return isPigVisible(elm);

                return true;
            }

            //Nothing touhed!!
            // Translate object in the bottom for the next control
            elm.setPosition(new Vector2(elm.getSprite().getX(),elm.getSprite().getY()-1));
        }
        return true;
    }

    // draw all elements in the scenery
    public void draw(SpriteBatch batch){
        for (PhysicalObject p:scene) {
            p.draw(batch);
            if (p.IsClass("Pig")) ((Pig)p).ShowMessage(batch);
        }
    }

    private Boolean isPigVisible(PhysicalObject o)
    {
        visibility = new PhysicalObject(new Vector2(o.getSprite().getX()+50,o.getSprite().getY()+50), new Vector2(200,10), Play.BLOK_SRITE);
        visibility.getSprite().setOrigin(5,0);
        visibility.getSprite().setRotation(115);
        if (visibility.checkCollisions(scene)) return false;
        return true;
    }

    public void removeClock()
    {
        for(int i=0; i < scene.size(); i++)
            if(scene.get(i).IsClass("Clock")) {
                scene.remove(i);
                return;
            }

    }

    public ArrayList<Pig> getPigs(){
        ArrayList<Pig> tmp = new ArrayList<Pig>();
        for(PhysicalObject p:scene)
            if(p.IsClass("Pig")) tmp.add((Pig)p);
        return tmp;
    }

    public ArrayList<Tnt> getTnts(){
        ArrayList<Tnt> tmp = new ArrayList<Tnt>();
        for(PhysicalObject p:scene)
            if(p.IsClass("Tnt")) tmp.add((Tnt)p);
        return tmp;
    }

    public ArrayList<Block> getBlocks(){
        ArrayList<Block> tmp = new ArrayList<Block>();
        for(PhysicalObject p:scene)
            if(p.IsClass("Block")) tmp.add((Block)p);
        return tmp;
    }



}
