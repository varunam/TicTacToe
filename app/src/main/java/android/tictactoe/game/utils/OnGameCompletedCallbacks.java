package android.tictactoe.game.utils;

import android.tictactoe.game.data.enums.Player;

/**
 * Created by varun.am on 05/03/19
 */
public interface OnGameCompletedCallbacks {
    public void onGameOver();
    public void onGameWonByPlayer(Player winner, String message);
}
