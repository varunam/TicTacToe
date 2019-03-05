package android.tictactoe.game.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.tictactoe.game.R;
import android.tictactoe.game.data.enums.Player;
import android.tictactoe.game.utils.Board;
import android.tictactoe.game.utils.OnGameCompletedCallbacks;
import android.tictactoe.game.viewmodel.MainViewModel;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnGameCompletedCallbacks {
    
    private MainViewModel mainViewModel;
    private Button b00, b01, b02, b10, b11, b12, b20, b21, b22;
    private TextView title, winnerTextView;
    
    private Board board;
    private Player currentPlayer;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        init();
    }
    
    private void init() {
        //init board
        board = new Board(this);
        
        title = findViewById(R.id.title_text_id);
        winnerTextView = findViewById(R.id.winner_text_id);
        
        currentPlayer = Player.PLAYER_1;
        showTurnNow(Player.PLAYER_1);
        
        initBoardButtons();
        resetBoardButtons();
        
        //init VM
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getWinner().observe(this, winnerObserver);
        
    }
    
    private void resetBoardButtons() {
        b00.setText("");
        b01.setText("");
        b02.setText("");
        b10.setText("");
        b11.setText("");
        b12.setText("");
        b20.setText("");
        b21.setText("");
        b22.setText("");
    }
    
    private void initBoardButtons() {
        b00 = findViewById(R.id.button00);
        b01 = findViewById(R.id.button01);
        b02 = findViewById(R.id.button02);
        b10 = findViewById(R.id.button10);
        b11 = findViewById(R.id.button11);
        b12 = findViewById(R.id.button12);
        b20 = findViewById(R.id.button20);
        b21 = findViewById(R.id.button21);
        b22 = findViewById(R.id.button22);
        
        b00.setOnClickListener(this);
        b01.setOnClickListener(this);
        b02.setOnClickListener(this);
        b10.setOnClickListener(this);
        b11.setOnClickListener(this);
        b12.setOnClickListener(this);
        b20.setOnClickListener(this);
        b21.setOnClickListener(this);
        b22.setOnClickListener(this);
    }
    
    private Observer<Player> winnerObserver = new Observer<Player>() {
        @Override
        public void onChanged(@Nullable Player player) {
            //display winner
        }
    };
    
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button00:
                board.setBoxFilled(0, 0, currentPlayer);
                fillBox(b00, currentPlayer);
                switchPlayer();
                b00.setEnabled(false);
                break;
            case R.id.button01:
                board.setBoxFilled(0, 1, currentPlayer);
                fillBox(b01, currentPlayer);
                switchPlayer();
                b01.setEnabled(false);
                break;
            case R.id.button02:
                board.setBoxFilled(0, 2, currentPlayer);
                fillBox(b02, currentPlayer);
                switchPlayer();
                b02.setEnabled(false);
                break;
            case R.id.button10:
                board.setBoxFilled(1, 0, currentPlayer);
                fillBox(b10, currentPlayer);
                switchPlayer();
                b10.setEnabled(false);
                break;
            case R.id.button11:
                board.setBoxFilled(1, 1, currentPlayer);
                fillBox(b11, currentPlayer);
                switchPlayer();
                b11.setEnabled(false);
                break;
            case R.id.button12:
                board.setBoxFilled(1, 2, currentPlayer);
                fillBox(b12, currentPlayer);
                switchPlayer();
                b12.setEnabled(false);
                break;
            case R.id.button20:
                board.setBoxFilled(2, 0, currentPlayer);
                fillBox(b20, currentPlayer);
                switchPlayer();
                b20.setEnabled(false);
                break;
            case R.id.button21:
                board.setBoxFilled(2, 1, currentPlayer);
                fillBox(b21, currentPlayer);
                switchPlayer();
                b21.setEnabled(false);
                break;
            case R.id.button22:
                board.setBoxFilled(2, 2, currentPlayer);
                fillBox(b22, currentPlayer);
                b22.setEnabled(false);
                break;
        }
    }
    
    private void fillBox(Button boxPlayed, Player currentPlayer) {
        if (currentPlayer == Player.PLAYER_1) {
            boxPlayed.setText("X");
        } else {
            boxPlayed.setText("O");
        }
    }
    
    private void switchPlayer() {
        if (currentPlayer == Player.PLAYER_1) {
            currentPlayer = Player.PLAYER_2;
        } else
            currentPlayer = Player.PLAYER_1;
        
        showTurnNow(currentPlayer);
    }
    
    private void showTurnNow(Player player) {
        String playersTurnText = String.format(getResources().getString(R.string.players_turn_now), getPlayerText(player));
        title.setText(playersTurnText);
    }
    
    private String getPlayerText(Player player) {
        String playerText;
        if (player == Player.PLAYER_1)
            playerText = getResources().getString(R.string.player_one);
        else
            playerText = getResources().getString(R.string.player_two);
        return playerText;
    }
    
    @Override
    public void onGameOver() {
        String gameOverText = "GAME OVER!";
        winnerTextView.setText(gameOverText);
        title.setVisibility(View.GONE);
    }
    
    @Override
    public void onGameWonByPlayer(Player winner, String message) {
        String winnerText = getPlayerText(winner) + " won the game";
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        winnerTextView.setText(winnerText);
        title.setVisibility(View.GONE);
        disableGame();
    }
    
    private void disableGame() {
        b00.setEnabled(false);
        b01.setEnabled(false);
        b02.setEnabled(false);
        b10.setEnabled(false);
        b11.setEnabled(false);
        b12.setEnabled(false);
        b21.setEnabled(false);
        b22.setEnabled(false);
        b20.setEnabled(false);
        b00.setTextColor(getResources().getColor(R.color.secondaryTextColor));
        b01.setTextColor(getResources().getColor(R.color.secondaryTextColor));
        b02.setTextColor(getResources().getColor(R.color.secondaryTextColor));
        b20.setTextColor(getResources().getColor(R.color.secondaryTextColor));
        b21.setTextColor(getResources().getColor(R.color.secondaryTextColor));
        b22.setTextColor(getResources().getColor(R.color.secondaryTextColor));
        b10.setTextColor(getResources().getColor(R.color.secondaryTextColor));
        b11.setTextColor(getResources().getColor(R.color.secondaryTextColor));
        b12.setTextColor(getResources().getColor(R.color.secondaryTextColor));
    }
}

