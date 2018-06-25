package ch.cpnv.angrywirds.Activities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import ch.cpnv.angrywirds.AngryWirds;
import ch.cpnv.angrywirds.DataStore.GameScore;
import ch.cpnv.angrywirds.DataStore.Store;
import ch.cpnv.angrywirds.GameActivity;
import ch.cpnv.angrywirds.Providers.VoxData;
import ch.cpnv.angrywirds.Vocabulary.AssignementsData;
import ch.cpnv.angrywirds.models.Button;
import ch.cpnv.angrywirds.models.ConvertXY;
import ch.cpnv.angrywirds.models.ShowBestScore;

public class Assignements extends GameActivity implements InputProcessor{

    public static final String BACKGROUND_IMAGE = "blackBackground.jpg";
    public static final String BUTTON_IMAGE = "button.png";
    public static final String BUTTON_IMAGE_NO = "buttonNO.png";

    public static final float START_X = 700;
    public static final float START_Y = 700;
    public static final float STEP_Y = 150;

    public static final Vector2 BUTTON_POSITION = new Vector2(START_X,START_Y);
    public static final Vector2 BUTTON_DIMENSION = new Vector2(500,100);

    Texture background;
    Vector2 touch;
    Button play,exit;
    ShowBestScore showBestScore;
    Boolean isReady = false;
    Button btn1, btn2, btn3;

    public Assignements(){
        super();
        background = new Texture(BACKGROUND_IMAGE);
        Gdx.input.setInputProcessor(this);
        //create Assignements Buttons
        // Check if the vocabulary has already play

        if (VoxData.assignements.get(0).getResult() == null)
            btn1 = new Button(new Vector2(600,600), new Vector2(400,100), BUTTON_IMAGE_NO, VoxData.assignements.get(0).getTitle());
        else
            btn1 = new Button(new Vector2(600,600), new Vector2(400,100), BUTTON_IMAGE, VoxData.assignements.get(0).getTitle());


        if (VoxData.assignements.get(1).getResult() == null)
            btn2 = new Button(new Vector2(600,400), new Vector2(400,100), BUTTON_IMAGE_NO, VoxData.assignements.get(1).getTitle());
        else
            btn2 = new Button(new Vector2(600,400), new Vector2(400,100), BUTTON_IMAGE, VoxData.assignements.get(1).getTitle());

        if (VoxData.assignements.get(2).getResult() == null)
            btn3 = new Button(new Vector2(600,200), new Vector2(400,100), BUTTON_IMAGE_NO, VoxData.assignements.get(2).getTitle());
        else
            btn3 = new Button(new Vector2(600,200), new Vector2(400,100), BUTTON_IMAGE, VoxData.assignements.get(2).getTitle());

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
            btn1.draw(batch);
            btn2.draw(batch);
            btn3.draw(batch);
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


            if (btn1.checkIfClicked(touch.x, touch.y)){
                AngryWirds.assignementId = VoxData.assignements.get(0).getId();
                AngryWirds.vocId = VoxData.assignements.get(0).getVocId();
                AngryWirds.gameActivityManager.push(new Play());
            }
            if (btn2.checkIfClicked(touch.x, touch.y)){
                AngryWirds.assignementId = VoxData.assignements.get(1).getId();
                AngryWirds.vocId = VoxData.assignements.get(1).getVocId();
                AngryWirds.gameActivityManager.push(new Play());
            }
            if (btn3.checkIfClicked(touch.x, touch.y)){
                AngryWirds.assignementId = VoxData.assignements.get(2).getId();
                AngryWirds.vocId = VoxData.assignements.get(2).getVocId();
                AngryWirds.gameActivityManager.push(new Play());
            }
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
