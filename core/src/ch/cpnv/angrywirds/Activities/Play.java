package ch.cpnv.angrywirds.Activities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ch.cpnv.angrywirds.AngryWirds;
import ch.cpnv.angrywirds.GameActivity;
import ch.cpnv.angrywirds.Providers.VoxData;
import ch.cpnv.angrywirds.models.ArrowsDirection;
import ch.cpnv.angrywirds.models.Bird;
import ch.cpnv.angrywirds.models.Button;
import ch.cpnv.angrywirds.models.ConvertXY;
import ch.cpnv.angrywirds.models.Distance;
import ch.cpnv.angrywirds.models.Game;
import ch.cpnv.angrywirds.models.GameTimer;
import ch.cpnv.angrywirds.models.Message;
import ch.cpnv.angrywirds.models.PhysicalObject;
import ch.cpnv.angrywirds.models.Pig;
import ch.cpnv.angrywirds.models.Scenery;
import ch.cpnv.angrywirds.models.Score;
import ch.cpnv.angrywirds.models.VocabularyGame;
import ch.cpnv.angrywirds.models.Wasp;

public class Play extends GameActivity implements InputProcessor{

    public static  final  int FLOW_HEIGHT = 125;

    public static final int IMAGE_WITH = 80;
    public static final int IMAGE_HEIGHT = 80;

    public static final int START_SCENE_X = 500;
    public static final int START_SCENE_Y = 500;

    public static final float STRONG_FACTOR_X = 3.5f;
    public static final float STRONG_FACTOR_Y = 3.5f;

    public static final String BACKGROUND_IMAGE = "background.jpg";
    public static final String WASP_SRITE = "wasp.png";
    public static final String PIG_SRITE = "pig.png";
    public static final String TNT_SRITE = "tnt.png";
    public static final String BLOK_SRITE = "block.png";
    public static final String BIRD_SRITE = "bird.png";
    public static final String SLINGSHOT_SRITE_FRONT = "slingshotFront.png";
    public static final String SLINGSHOT_SRITE_BACK = "slingshotBack.png";
    public static final String PANEL_SRITE = "panel.png";
    public static final String CLOCK_SRITE = "clock.png";

    public static final String MESSAGE_SRITE = "bubble.png";
    public static final String ARROW_SRITE = "arrow.png";

    public static final Vector2 SLINGSHOT_POSITION = new Vector2(200, FLOW_HEIGHT);
    public static final Vector2 SLINGSHOT_DIMENSION = new Vector2(80,140);
    public static final Vector2 SLINGSHOT_FOCUS_POSITION = new Vector2(SLINGSHOT_POSITION.x + 30,SLINGSHOT_POSITION.y +120);

    public static final Vector2 WASP_POSITION = new Vector2(GameActivity.WORLD_WIDTH/2,GameActivity.WORLD_HEIGHT/2);
    public static final Vector2 WASP_DIMENSION = new Vector2(IMAGE_WITH,IMAGE_HEIGHT);
    public static final Vector2 WASP_SPEED = new Vector2(1,1);

    public static final Vector2 BIRD_POSITION = new Vector2(SLINGSHOT_FOCUS_POSITION.x - (IMAGE_WITH/2),SLINGSHOT_FOCUS_POSITION.y  - (IMAGE_HEIGHT/2));
    public static final Vector2 BIRD_DIMENSION = new Vector2(IMAGE_WITH,IMAGE_HEIGHT);
    public static final Vector2 BIRD_SPEED = new Vector2(0,0);

    public static final Vector2 PANEL_POSITION = new Vector2(50,GameActivity.WORLD_HEIGHT - 240);
    public static final Vector2 PANEL_DIMENSION = new Vector2(350,240);

    // Buttons

    public static final String BUTTON_EXIT_IMAGE = "exit2.png";
    public static final Vector2 BUTTON_EXIT_POSITION = new Vector2(GameActivity.WORLD_WIDTH - 140,15);
    public static final Vector2 BUTTON_EXIT_DIMENSION = new Vector2(100,80);

    public static final String BUTTON_SETTING_IMAGE = "setting.png";
    public static final Vector2 BUTTON_SETTING_POSITION = new Vector2(20,20);
    public static final Vector2 BUTTON_SETTING_DIMENSION = new Vector2(70,70);

    public static final String BUTTON_RESET_IMAGE = "new.png";
    public static final Vector2 BUTTON_RESET_POSITION = new Vector2(GameActivity.WORLD_WIDTH - 100,GameActivity.WORLD_HEIGHT - 100);
    public static final Vector2 BUTTON_RESET_DIMENSION = new Vector2(80,80);

    Texture background;
    Bird bird;
    Wasp wasp;
    Scenery scenery;
    PhysicalObject slingshotFront,slingshotBack, panel;
    Vector2 touch;
    Vector2 diff,strong;
    Button btnReset, btnExit, btnSetting;
    ArrowsDirection arrows;
    Score score;
    Game game;
    GameTimer gameTimer;
    VocabularyGame vocabularyGame;

    public void Init(){
        diff = new Vector2(0,0);
        strong = new Vector2(0,0);
        score = new Score(AngryWirds.score);
        background = new Texture(BACKGROUND_IMAGE);
        slingshotFront = new PhysicalObject(SLINGSHOT_POSITION, SLINGSHOT_DIMENSION,SLINGSHOT_SRITE_FRONT);
        slingshotBack = new PhysicalObject(SLINGSHOT_POSITION, SLINGSHOT_DIMENSION,SLINGSHOT_SRITE_BACK);
        bird = new Bird(BIRD_POSITION, BIRD_DIMENSION,  BIRD_SRITE, BIRD_SPEED);
        wasp = new Wasp(WASP_POSITION,WASP_DIMENSION, WASP_SRITE, WASP_SPEED);
        panel = new PhysicalObject(PANEL_POSITION,PANEL_DIMENSION,PANEL_SRITE);

        btnReset = new Button(BUTTON_RESET_POSITION,BUTTON_RESET_DIMENSION,BUTTON_RESET_IMAGE);
        btnExit = new Button(BUTTON_EXIT_POSITION,BUTTON_EXIT_DIMENSION,BUTTON_EXIT_IMAGE);
        btnSetting = new Button(BUTTON_SETTING_POSITION,BUTTON_SETTING_DIMENSION,BUTTON_SETTING_IMAGE);

        scenery = new Scenery();
        scenery.Make();
        arrows = new ArrowsDirection(bird);
        // Add Wasp to the scene
        scenery.AddScene(wasp);

        // Create new Vocabulary game for this play
        vocabularyGame = new VocabularyGame(scenery.getPigs());
        // Create a New Timer for this game
        gameTimer = new GameTimer(AngryWirds.timer);

        game = new Game(bird,scenery,score,vocabularyGame, gameTimer);
    }

    public Play()
    {
        super();
        Init();
        Gdx.input.setInputProcessor(this);
    }


    @Override
    protected void handleInput() {

    }

    @Override
    public void update() {
        game.refresh();
        wasp.refresh();
        bird.refresh();
        arrows.refresh(strong);
        gameTimer.refresh();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
            batch.draw(background, 0, 0, GameActivity.WORLD_WIDTH,GameActivity.WORLD_HEIGHT);
            scenery.draw(batch);
            slingshotBack.draw(batch);
            bird.draw(batch);
            slingshotFront.draw(batch);
            panel.draw(batch);
            wasp.draw(batch);
            arrows.draw(batch);
            btnReset.draw(batch);
            btnExit.draw(batch);
            btnSetting.draw(batch);
            score.draw(batch);
            gameTimer.draw(batch);
            vocabularyGame.draw(batch);
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

        if (btnReset.checkIfClicked(touch.x,touch.y)) Init();
        if (btnExit.checkIfClicked(touch.x,touch.y)) Gdx.app.exit();
        if (bird.checkIfClicked(touch.x,touch.y))  diff = new Distance(touch,bird.getPosition());
        if (btnSetting.checkIfClicked(touch.x,touch.y)){ AngryWirds.gameActivityManager.push(new GameSettings("Play")); }

        for(Pig o : scenery.getPigs())
            if (o.checkIfClicked(touch.x,touch.y))
                o.EnableMessage();

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        touch =  new ConvertXY(new Vector2(screenX,screenY), camera);
        if (bird.checkIfClicked(touch.x,touch.y)) {
            game.start(new Vector2(strong.x*STRONG_FACTOR_X,strong.y* STRONG_FACTOR_Y));
            strong = new Vector2(0,0);
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        touch =  new ConvertXY(new Vector2(screenX,screenY), camera);
        if (bird.checkIfClicked(touch.x,touch.y)) {
            bird.setPosition(new Vector2(touch.x - diff.x, touch.y - diff.y));
            strong = new Distance(SLINGSHOT_FOCUS_POSITION, new Vector2(bird.getPosition().x + (bird.getDimension().x/2), bird.getPosition().y + (bird.getDimension().y/2) )    );
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) { return false; }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
