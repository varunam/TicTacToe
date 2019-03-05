package android.tictactoe.game.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by varun.am on 05/03/19
 */
public class ThisApplication extends Application {
    
    private static Context appContext;
    
    public static Context getAppContext() {
        return appContext;
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }
    
}
