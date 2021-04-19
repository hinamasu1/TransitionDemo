package jp.co.informatix.transitiondemo;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

public class TransitionDemo extends Application {

    private static final String _tag = TransitionDemo.class.getSimpleName();

    @Override
    public void onCreate() {
        Log.d(_tag, "onCreate()");

        super.onCreate();

        Intent intent = new Intent(this, TransitionService.class);
        startService(intent);
    }
}
