package ch.cpnv.angrywirds.DataStore;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;

public class BestScore {

    private ArrayList<GameScore> bestScores;

    public BestScore(){
        this.bestScores = new ArrayList<GameScore>();
    }

    public ArrayList<GameScore> getBestScores() {
        return bestScores;
    }

    public void setBestScores(ArrayList<GameScore> bestScores) {
        this.bestScores = bestScores;
    }

    public void newBestScore(GameScore gameScore){

        //get the new best score position
        int pos = getPosition(gameScore.getScore());

        if (pos == -1) return; //no new best score

        for(int i = bestScores.size() -1 ; i > pos; i--){
            //Translatae all values
            bestScores.set(i,bestScores.get(i-1));
        }
        bestScores.set(pos,gameScore);
        Gdx.app.log("","");
    }

    private int getPosition(int score){
        for(int i=0; i < bestScores.size(); i++){
            if (bestScores.get(i).getScore() <= score)
                return i;
        }
        return -1;
    }
}
