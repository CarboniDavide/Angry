package ch.cpnv.angrywirds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;

import ch.cpnv.angrywirds.Activities.Play;
import ch.cpnv.angrywirds.Activities.Welcome;
import ch.cpnv.angrywirds.Providers.VoxData;
import ch.cpnv.angrywirds.Vocabulary.AssignementsData;
import ch.cpnv.angrywirds.Vocabulary.Language;

public class AngryWirds extends Game{

    static public GameActivityManager gameActivityManager = new GameActivityManager();
    static public int LangueId = VoxData.getLangueId(Language.LANGUE_FR); // defualt fr

    public static int score = 50; // initial score
    public static int timer = 120; // initial score

    public static int assignementId = -1; // initial assignement
    public static int vocId = -1; // initial assignement

    //MyToken
    public static String token = "*1FEFAA1CAACF99FB0F2FFA96078809BAD9925550";

    @Override
    public void create () {
        gameActivityManager.push(new Welcome());
    }

    @Override
    public void render () {
      gameActivityManager.handleInput();
      gameActivityManager.update();
      gameActivityManager.render();
    }

    @Override
    public void dispose () {
    }
}
