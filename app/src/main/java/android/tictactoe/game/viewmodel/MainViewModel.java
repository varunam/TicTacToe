package android.tictactoe.game.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.tictactoe.game.data.enums.Player;

/**
 * Created by varun.am on 05/03/19
 */
public class MainViewModel extends AndroidViewModel {
    
    private MutableLiveData<Player> winner = new MutableLiveData<>();
    
    public MainViewModel(@NonNull Application application) {
        super(application);
    }
    
    public MutableLiveData<Player> getWinner() {
        return winner;
    }
    
    public void setWinner(Player player) {
        this.winner.postValue(player);
    }
    
    
}
