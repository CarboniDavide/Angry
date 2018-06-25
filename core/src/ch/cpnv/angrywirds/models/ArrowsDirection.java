package ch.cpnv.angrywirds.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import ch.cpnv.angrywirds.Activities.Play;
import ch.cpnv.angrywirds.AngryWirds;

public class ArrowsDirection {

    private ArrayList<Sprite> arrows;
    private int arrowsQuantity;
    private Bird bird;

    public static final int ARROW_OFFSET_X = 120;

    public static final int ARROW_WIDTH = 20;
    public static final int ARROW_HEIGHT = 20;

    public void refresh(Vector2 dist){
        if (bird.getisLanched()) {
            arrows.clear(); return;
        }
        float mod = (float) Math.sqrt( (dist.x*dist.x) + (dist.y*dist.y));
        arrowsQuantity = (int) mod/30;
        if (arrowsQuantity > arrows.size()) AddArrows();
        if (arrowsQuantity < arrows.size()) RemoveArrows();
        rotate(dist);
    }

    private void AddArrows()
    {
        for (int i=0; i < arrowsQuantity - arrows.size(); i++)
            arrows.add(CreateNewArrow());
    }

    private void RemoveArrows()
    {
        for (int i=0; i < arrows.size() - arrowsQuantity; i++)
            arrows.remove(arrows.size()-1);
    }

    private void rotate(Vector2 dist)
    {
        float add = 0;
        if ((dist.x < 0) && (dist.y > 0))
            add = 180;
        if ((dist.x < 0) && (dist.y < 0))
            add = -180;
        float degrees = (float) Math.toDegrees ((float) Math.atan(dist.y/dist.x));
        for(Sprite arrow: arrows)
            arrow.setRotation(degrees+add);
            
    }

    private Sprite CreateNewArrow()
    {
        Sprite tmp;
        float offset = arrows.size() * 50;
        tmp = new Sprite(new Texture(Play.ARROW_SRITE));
        tmp.setBounds(Play.SLINGSHOT_FOCUS_POSITION.x + ARROW_OFFSET_X + offset,Play.SLINGSHOT_FOCUS_POSITION.y,ARROW_WIDTH, ARROW_HEIGHT);
        tmp.setOrigin(- ARROW_OFFSET_X - offset , 0);
        return tmp;
    }

    public void draw(SpriteBatch batch){
        for (Sprite arrow: arrows)
            arrow.draw(batch);
    }

    public ArrowsDirection(Bird bird)
    {
        this.bird = bird;
        arrows = new ArrayList<Sprite>();
    }
}
