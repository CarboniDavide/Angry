package ch.cpnv.angrywirds.DataStore;

public class GameScore {
    private int score;
    private  int time;

    public GameScore(int score, int time){
        this.score = score;
        this.time = time;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
