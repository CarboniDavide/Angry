package ch.cpnv.angrywirds.Activities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ch.cpnv.angrywirds.AngryWirds;
import ch.cpnv.angrywirds.GameActivity;
import ch.cpnv.angrywirds.Providers.VoxData;
import ch.cpnv.angrywirds.Vocabulary.Language;
import ch.cpnv.angrywirds.models.Button;
import ch.cpnv.angrywirds.models.ConvertXY;

public class GameSettings extends GameActivity implements InputProcessor {

    public static final String BACKGROUND_IMAGE = "language.jpg";
    private static final String SPRITE_BUTTON_FR = "flag_fr.png";
    private static final String SPRITE_BUTTON_EN = "flag_en.png";
    private static final String SPRITE_BUTTON_DE = "flag_de.png";
    private static final String SPRITE_BUTTON_SP = "flag_sp.png";

    Button fr, en, de, sp;
    Texture background;
    Vector2 touch;
    float midx;
    float midy;
    String returnWith = "";

    /**
     * Create a new setting window
     */
    public GameSettings(){
        super();
        midx = (GameActivity.WORLD_WIDTH/2)-100; //800
        midy = (GameActivity.WORLD_HEIGHT/2)-100; //300
        fr = new Button(new Vector2(((GameActivity.WORLD_WIDTH/2)-100)-midx/2,((GameActivity.WORLD_HEIGHT/2)-100)-midy/2), new Vector2(200,200), SPRITE_BUTTON_FR);
        en = new Button(new Vector2(((GameActivity.WORLD_WIDTH/2)-100)-midx/2,((GameActivity.WORLD_HEIGHT/2)-100)+midy/2), new Vector2(200,200), SPRITE_BUTTON_EN);
        sp = new Button(new Vector2(((GameActivity.WORLD_WIDTH/2)-100)+midx/2,((GameActivity.WORLD_HEIGHT/2)-100)-midy/2), new Vector2(200,200), SPRITE_BUTTON_SP);
        de = new Button(new Vector2(((GameActivity.WORLD_WIDTH/2)-100)+midx/2,((GameActivity.WORLD_HEIGHT/2)-100)+midy/2), new Vector2(200,200), SPRITE_BUTTON_DE);
        background = new Texture(BACKGROUND_IMAGE);
        Gdx.input.setInputProcessor(this);
    }

    /**
     *
     * Create a new window and after when exit create a new gameActivity
     *
     */
    public GameSettings(String returnWith){
        super();
        midx = (GameActivity.WORLD_WIDTH/2)-100; //800
        midy = (GameActivity.WORLD_HEIGHT/2)-100; //300
        fr = new Button(new Vector2(((GameActivity.WORLD_WIDTH/2)-100)-midx/2,((GameActivity.WORLD_HEIGHT/2)-100)-midy/2), new Vector2(200,200), SPRITE_BUTTON_FR);
        en = new Button(new Vector2(((GameActivity.WORLD_WIDTH/2)-100)-midx/2,((GameActivity.WORLD_HEIGHT/2)-100)+midy/2), new Vector2(200,200), SPRITE_BUTTON_EN);
        sp = new Button(new Vector2(((GameActivity.WORLD_WIDTH/2)-100)+midx/2,((GameActivity.WORLD_HEIGHT/2)-100)-midy/2), new Vector2(200,200), SPRITE_BUTTON_SP);
        de = new Button(new Vector2(((GameActivity.WORLD_WIDTH/2)-100)+midx/2,((GameActivity.WORLD_HEIGHT/2)-100)+midy/2), new Vector2(200,200), SPRITE_BUTTON_DE);
        background = new Texture(BACKGROUND_IMAGE);
        this.returnWith = returnWith;
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
            fr.draw(batch);
            en.draw(batch);
            sp.draw(batch);
            de.draw(batch);
        batch.end();

    }

    @Override
    public void dispose() {

    }

    @Override
    public void InitInputProcessor() {
        Gdx.input.setInputProcessor(this);
    }


    // Input Processor

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

        // Si on clique sur le français, on set la langue en français
        if (fr.checkIfClicked(touch.x,touch.y)){
            AngryWirds.LangueId = VoxData.getLangueId(Language.LANGUE_FR);
        }

        // Si on clique sur l'allemand, on set la langue en allemand
        if (de.checkIfClicked(touch.x,touch.y)){
            AngryWirds.LangueId = VoxData.getLangueId(Language.LANGUE_DE);

        }

        // Si on clique sur l'anglais, on set la langue en anglais
        if (en.checkIfClicked(touch.x,touch.y)){
            AngryWirds.LangueId = VoxData.getLangueId(Language.LANGUE_EN);
        }

        // Si on clique sur l'espagnol, on set la langue en espagnol
        if (sp.checkIfClicked(touch.x,touch.y)){
            AngryWirds.LangueId = VoxData.getLangueId(Language.LANGUE_ES);
        }

        // Check if it possible to use the voc
        if (!VoxData.checkIF_vocExsist_for_langue(AngryWirds.LangueId))
            AngryWirds.LangueId = 1; // defaull value

        // Exit and put in paramter the activity to show when exit
        AngryWirds.gameActivityManager.exitSetting(returnWith);
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
