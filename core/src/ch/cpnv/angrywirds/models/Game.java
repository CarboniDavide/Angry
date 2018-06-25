package ch.cpnv.angrywirds.models;

import com.badlogic.gdx.math.Vector2;

import ch.cpnv.angrywirds.Activities.GameOver;
import ch.cpnv.angrywirds.Activities.GameOverScore;
import ch.cpnv.angrywirds.Activities.Play;
import ch.cpnv.angrywirds.AngryWirds;
import ch.cpnv.angrywirds.DataStore.GameScore;

public class Game {

    private Bird bird;
    private Scenery scenery;
    private Score score;
    private String word;
    private Boolean isRunning;
    private int idWord;
    private GameTimer gameTimer;


    private enum ObjectNames {
        Block, Pig, Tnt, Wasp, Clock;
    }

    public Game(Bird bird, Scenery scenery, Score score, VocabularyGame vocabularyGame, GameTimer gameTimer){
        this.bird = bird;
        this.scenery = scenery;
        this.score = score;
        this.idWord = vocabularyGame.idWordToFind;
        this.isRunning = false;
        this.gameTimer = gameTimer;
    }

    public void refresh(){
        if (bird.isOutOfScreen()) {
            reset();
            return;
        }

        if (score.getScore()<=0){
            score.setScore(0);
            exit();
        }


        if ((bird.checkCollisions(scenery.scene)) && isRunning){
            //isRunning = false;
            stop();
            for (PhysicalObject e: bird.touchedObjects) {
                ObjectNames objectNames = ObjectNames.valueOf(e.GetClassName()); // surround with try/catch
                switch (objectNames) {
                    case Wasp:
                        exit();
                        break;
                    case Pig:
                        verifyPigWord((Pig)e);
                        break;
                    case Tnt:
                        this.score.sum(-20);
                        break;
                    case Block:
                        this.score.sum(-5);
                        break;
                    case Clock:
                        this.gameTimer.addTime(+100);
                        scenery.removeClock();
                        this.score.sum(+15);
                        break;
                }
            }
            reset();
            return;
        }
    }

    public void stop(){
        isRunning = false;
        bird.Stop();
    }

    public void start(Vector2 speed){
        isRunning = true;
        bird.Launch(speed);
    }

    public void reset(){
        this.bird.Reset();
        this.bird.setSpeed(new Vector2(0,0));
        this.bird.setPosition(new Vector2(Play.BIRD_POSITION.x, Play.BIRD_POSITION.y));
    }

    public void exit(){
        AngryWirds.gameActivityManager.push(new GameOverScore(new GameScore(score.getScore(),gameTimer.getTimeToFinish())));
        AngryWirds.score = 50;
        AngryWirds.timer = 120;
    }

    private void verifyPigWord(Pig pig){
        if (pig.getIdWord() == idWord) {
            AngryWirds.score = score.getScore() + 50;
            AngryWirds.timer = gameTimer.getTimeToFinish() + 120;
            AngryWirds.gameActivityManager.pop();
            AngryWirds.gameActivityManager.push(new Play());
        }
        else
            this.score.sum(-5);

    }

}
