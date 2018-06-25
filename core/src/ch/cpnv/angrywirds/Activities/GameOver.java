package ch.cpnv.angrywirds.Activities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ch.cpnv.angrywirds.AngryWirds;
import ch.cpnv.angrywirds.GameActivity;
import ch.cpnv.angrywirds.Providers.VoxData;
import ch.cpnv.angrywirds.models.Button;
import ch.cpnv.angrywirds.models.ConvertXY;

public class GameOver extends GameActivity implements InputProcessor{

    private static final String SPRITE_BUTTON_PLAY = "new.png";
    private static final String SPRITE_BUTTON_EXIT = "exit.png";
    public static final String BACKGROUND_IMAGE = "gameover.png";

    Texture background;
    Vector2 touch;
    Button play,exit;

    public GameOver(){
        super();
        background = new Texture(BACKGROUND_IMAGE);
        play = new Button(new Vector2((GameActivity.WORLD_WIDTH/2)-200,50), new Vector2(100,100), SPRITE_BUTTON_PLAY);
        exit = new Button(new Vector2((GameActivity.WORLD_WIDTH/2)+100,50), new Vector2(100,100), SPRITE_BUTTON_EXIT);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        batch.begin();
            batch.draw(background, 0, 0, GameActivity.WORLD_WIDTH,GameActivity.WORLD_HEIGHT);
            play.draw(batch);
            exit.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {

    }

    @Override
    public void InitInputProcessor() {
        Gdx.input.setInputProcessor(this);
    }

    // Imput Processor

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch = new ConvertXY(new Vector2(screenX,screenY), camera);

        if (play.checkIfClicked(touch.x,touch.y))AngryWirds.gameActivityManager.rePlay(new Welcome());
        if (exit.checkIfClicked(touch.x,touch.y))Gdx.app.exit();
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
