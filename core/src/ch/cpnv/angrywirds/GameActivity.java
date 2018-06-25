package ch.cpnv.angrywirds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ch.cpnv.angrywirds.AngryWirds;

public abstract class GameActivity{
    public static  final  int WORLD_WIDTH = 1600;
    public static  final  int WORLD_HEIGHT = 900;

    protected OrthographicCamera camera;
    protected SpriteBatch batch;

    protected GameActivity()
    {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
    }

    protected abstract void handleInput();
    public abstract void update();
    public abstract void render();
    public abstract void dispose();
    public abstract void InitInputProcessor();

}
