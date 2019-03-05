package android.tictactoe.game.utils;

import android.support.annotation.NonNull;
import android.tictactoe.game.data.enums.Player;
import android.util.Log;

/**
 * Created by varun.am on 05/03/19
 */
public class Board {
    
    private static final String TAG = Board.class.getSimpleName();
    private int boxes[][] = {
            {2, 3, 4},
            {5, 6, 7},
            {8, 9, 10},
    };
    private OnGameCompletedCallbacks onGameCompletedCallbacks;
    
    public Board(@NonNull OnGameCompletedCallbacks onGameCompletedCallbacks) {
        this.onGameCompletedCallbacks = onGameCompletedCallbacks;
    }
    
    public void setBoxFilled(int x, int y, Player player) {
        
        if (player == Player.PLAYER_1)
            boxes[x][y] = 1;
        else
            boxes[x][y] = 0;
        
        printCurrentBoardStatus();
        checkIfGameCompleted(player);
    }
    
    private void checkIfGameCompleted(Player player) {
        //Horizontal --- rows
        for (int i = 0; i < 3; i++) {
            if (boxes[i][0] == boxes[i][1] && boxes[i][0] == boxes[i][2]) {
                onGameCompletedCallbacks.onGameWonByPlayer(player, "Won at " + (i + 1) + " row");
                break;
            }
        }
        
        //Vertical --- columns
        for (int i = 0; i < 3; i++) {
            if (boxes[0][i] == boxes[1][i] && boxes[0][i] == boxes[2][i]) {
                onGameCompletedCallbacks.onGameWonByPlayer(player, "Won at " + (i + 1) + " column");
                break;
            }
        }
        
        //First diagonal
        if (boxes[0][0] == boxes[1][1] && boxes[0][0] == boxes[2][2]) {
            onGameCompletedCallbacks.onGameWonByPlayer(player, "Won at First Diagonal");
        }
        
        //Second diagonal
        if (boxes[0][2] == boxes[1][1] && boxes[0][2] == boxes[2][0]) {
            onGameCompletedCallbacks.onGameWonByPlayer(player, "Won at Second Diagonal");
        }
    }
    
    private void printCurrentBoardStatus() {
        StringBuilder boxesString = new StringBuilder();
        for (int i = 0; i < boxes.length; i++) {
            for (int j = 0; j < boxes.length; j++) {
                boxesString.append(boxes[i][j]).append(" ");
            }
            boxesString.append("\n");
        }
        Log.d(TAG, "Current board status: \n" + boxesString.toString());
    }
    
    public int getBoxStatus(int x, int y) {
        return boxes[x][y];
    }
}
