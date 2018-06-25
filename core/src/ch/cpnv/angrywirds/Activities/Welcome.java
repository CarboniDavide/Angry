package ch.cpnv.angrywirds.Activities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ch.cpnv.angrywirds.AngryWirds;
import ch.cpnv.angrywirds.GameActivity;
import ch.cpnv.angrywirds.StatusConnection.GameConnection;
import ch.cpnv.angrywirds.Providers.VoxData;
import ch.cpnv.angrywirds.models.Button;
import ch.cpnv.angrywirds.models.ConvertXY;

public class Welcome extends GameActivity implements InputProcessor{

    private static final String BUTTON_PLAY_IMAGE = "play2.png";
    public static final Vector2 BUTTON_PLAY_DIMENSION = new Vector2(200,200);
    public static final Vector2 BUTTON_PLAY_POSITION = new Vector2((GameActivity.WORLD_WIDTH/2)- (BUTTON_PLAY_DIMENSION.x/2),(GameActivity.WORLD_HEIGHT/2)-(BUTTON_PLAY_DIMENSION.y/2));

    public static final String BACKGROUND_IMAGE = "intro.jpg";

    public static final String BUTTON_SETTING_IMAGE = "setting.png";
    public static final Vector2 BUTTON_SETTING_POSITION = new Vector2(20,20);
    public static final Vector2 BUTTON_SETTING_DIMENSION = new Vector2(70,70);

    Button play;
    Texture background;
    Vector2 touch;
    Button btnSetting;
    GameConnection gameConnection;

    public Welcome(){
        super();
        play = new Button(BUTTON_PLAY_POSITION,BUTTON_PLAY_DIMENSION,BUTTON_PLAY_IMAGE);
        btnSetting = new Button(BUTTON_SETTING_POSITION,BUTTON_SETTING_DIMENSION,BUTTON_SETTING_IMAGE);
        background = new Texture(BACKGROUND_IMAGE);
        Gdx.input.setInputProcessor(this);
        // New Connection
        gameConnection = new GameConnection();
    }


    @Override
    protected void handleInput() {

    }

    @Override
    public void update() {
        gameConnection.refresh();
    }

    @Override
    public void render() {
        batch.begin();
            batch.draw(background, 0, 0, GameActivity.WORLD_WIDTH,GameActivity.WORLD_HEIGHT);
            if (VoxData.status == VoxData.Status.ready)
                play.draw(batch);
            else
                gameConnection.draw(batch);
            btnSetting.draw(batch);
        batch.end();

    }

    @Override
    public void dispose() {

    }

    @Override
    public void InitInputProcessor() {
        Gdx.input.setInputProcessor(this);
    }

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
        if (play.checkIfClicked(touch.x,touch.y)){ AngryWirds.gameActivityManager.push(new Assignements()); }
        if (btnSetting.checkIfClicked(touch.x,touch.y)){ AngryWirds.gameActivityManager.push(new GameSettings()); }
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
