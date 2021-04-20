package jp.co.informatix.transitiondemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.io.Serializable;
import java.lang.ref.WeakReference;

public class LevelOneActivity extends AppCompatActivity implements Serializable {

    public static final String _tag = LevelOneActivity.class.getSimpleName();
    private BroadcastReceiver _receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (action.equals(TransitionAction._destryActivity)) {
                String activity = intent.getStringExtra("activity");

                if (activity.equals(_tag)) {
                    finish();
                }
            }
        }
    };

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(_tag, "onCreate()");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_one);;

        Button level2 = findViewById(R.id.button_level2);
        level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(TransitionAction._levelchanged);
                intent.putExtra("level", 2);
                sendBroadcast(intent);
            }
        });

        Button level3 = findViewById(R.id.button_level3);
        level3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(TransitionAction._levelchanged);
                intent.putExtra("level", 3);
                sendBroadcast(intent);
            }
        });

        Button transition = findViewById(R.id.button_transition);
        transition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(TransitionAction._levelchanged);
                intent.putExtra("level", 0);
                sendBroadcast(intent);
            }
        });

        Intent intent = new Intent();
        intent.setAction(TransitionAction._activityCreated);
        intent.putExtra("activity", _tag);
        sendBroadcast(intent);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(TransitionAction._destryActivity);
        registerReceiver(_receiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        Log.d(_tag, "onDestroy()");

        super.onDestroy();

        Intent intent = new Intent();
        intent.setAction(TransitionAction._activityDestroyed);
        intent.putExtra("activity", _tag);
        sendBroadcast(intent);

        unregisterReceiver(_receiver);
    }
}