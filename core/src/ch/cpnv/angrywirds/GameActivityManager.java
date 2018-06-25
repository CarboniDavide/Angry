package ch.cpnv.angrywirds;

import java.util.Stack;

import ch.cpnv.angrywirds.Activities.Play;

public class GameActivityManager {

    private Stack<GameActivity> mGameActivities;

    public GameActivityManager() {
        mGameActivities = new Stack<GameActivity>();
    }

    public void push(GameActivity gameActivity) {
        mGameActivities.push(gameActivity);
    }

    public void pop() {
        mGameActivities.pop();
    }

    public void set(GameActivity gameActivity) {
        mGameActivities.pop();
        mGameActivities.push(gameActivity);
    }

    public void rePlay(GameActivity gameActivity) {
        // Replay the Game
        mGameActivities.pop();
        mGameActivities.pop();
        mGameActivities.pop();
        mGameActivities.push(gameActivity);
    }

    public void exitSetting(String with) {
        if (with.equals("")) //Restore the old screen after exit settings
        {
            mGameActivities.pop();
        }
        if (with.equals("Play")){                  //Exit setting and push a new activity
            mGameActivities.pop();
            mGameActivities.pop();
            mGameActivities.push(new Play());
        }
        //restore the current Input Processor
        mGameActivities.peek().InitInputProcessor();
    }

    public void handleInput() {
        mGameActivities.peek().handleInput();
    }

    public void update() {
        mGameActivities.peek().update();
    }

    public void render() {
        mGameActivities.peek().render();
    }
}
