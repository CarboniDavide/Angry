package ch.cpnv.angrywirds.DataStore;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.ArrayList;

public abstract class Store {

    public static final String FILE_NAME = "score.txt";
    public static FileHandle  file = Gdx.files.local(FILE_NAME);
    public static final int DATA_MAX_LENGHT = 5;
    public static BestScore bestScore= new BestScore();

    public static void read()
    {
        // create file if dont exists
        if (!file.exists()) createFile();

        String data = file.readString();
        String[] dataSplit = data.split(";");

        //Convert all data into integer
        ArrayList<GameScore> dataTable = new ArrayList<GameScore>();
        int i=0;
        while(i < (DATA_MAX_LENGHT*2)){
            dataTable.add(new GameScore(Integer.parseInt(dataSplit[i]),Integer.parseInt(dataSplit[i+1])));
            i=i+2;
        }
        //store all data into bestscore
        bestScore.setBestScores(dataTable);
    }

    public static void write()
    {
        // delete old file before to store new data
        deleteFile();
        // Read all data and convert in String before erite in to file
        for (int i=0; i < DATA_MAX_LENGHT; i++){
            file.writeString(String.valueOf(bestScore.getBestScores().get(i).getScore())+";",true);
            file.writeString(String.valueOf(bestScore.getBestScores().get(i).getTime())+";",true);
        }
    }

    /**
     * Create new file
     */
    private static void createFile(){
        for (int i=0; i < DATA_MAX_LENGHT *2; i++)
            file.writeString("0;",true);
    }

    /**
     * Delete file
     */
    private static void deleteFile(){
        Gdx.files.local(FILE_NAME).delete();
    }
}
