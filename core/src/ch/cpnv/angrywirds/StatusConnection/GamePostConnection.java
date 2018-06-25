/**
 * Carboni Davide
 *
 * check teh status connection for the post
 * and show a popUpmessage for the connection status
 *
 */

package ch.cpnv.angrywirds.StatusConnection;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;

import ch.cpnv.angrywirds.Activities.Assignements;
import ch.cpnv.angrywirds.AngryWirds;
import ch.cpnv.angrywirds.DataStore.GameScore;
import ch.cpnv.angrywirds.GameActivity;
import ch.cpnv.angrywirds.Providers.PostAssignmentsDatas;
import ch.cpnv.angrywirds.Providers.VoxData;
import ch.cpnv.angrywirds.models.Button;
import ch.cpnv.angrywirds.models.PopUpMessage;

public class GamePostConnection {

    public static final Vector2 POPUP_DIMENSION = new Vector2(500,80);
    public static final Vector2 POPUP_POSITION = new Vector2(0,(GameActivity.WORLD_HEIGHT - 100));

    PopUpMessage popup;
    private Assignements assignements;
    private GameScore gameScore;
    private PostAssignmentsDatas postAssignmentsDatas;

    public GamePostConnection(GameScore gameScore, Assignements assignements){
        popup = new PopUpMessage(POPUP_POSITION,POPUP_DIMENSION,"connect to server...");
        this.assignements = assignements;
        this.gameScore = gameScore;
        //Preapere the data
        postAssignmentsDatas = new PostAssignmentsDatas(AngryWirds.assignementId,AngryWirds.token,gameScore.getScore());
    }

    // Check the status connection
    // Show a popUp message for the status connection
    public void refresh(){
        if (VoxData.statusPost != VoxData.Status.ready){
            try {
                // Post data to the server
                VoxData.submitResults(postAssignmentsDatas.id,postAssignmentsDatas.token,postAssignmentsDatas.score);
            }catch(Exception exception)
            {
                // show error message
                popup = new PopUpMessage(POPUP_POSITION, POPUP_DIMENSION, "..fatal error");
            }

        }
        else {
            if (VoxData.statusPost == VoxData.Status.cancelled)
                popup = new PopUpMessage(POPUP_POSITION, POPUP_DIMENSION, "connection aborted");
            if (VoxData.statusPost == VoxData.Status.error)
                popup = new PopUpMessage(POPUP_POSITION, POPUP_DIMENSION, "connection error");
        }
    }

    public void draw(SpriteBatch batch){
        popup.draw(batch);
    }
}
