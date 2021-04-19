package jp.co.informatix.transitiondemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class TransitionActivity extends AppCompatActivity {

    public static final String _tag = TransitionActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(_tag, "onCreate()");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);

        Button level1 = findViewById(R.id.button_level1);
        level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(TransitionAction._levelchanged);
                intent.putExtra("level", 1);
                sendBroadcast(intent);
            }
        });

        Button kill1 = findViewById(R.id.button_kill1);
        kill1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(TransitionAction._destryActivity);
                intent.putExtra("activity", LevelOneActivity._tag);
                sendBroadcast(intent);
            }
        });

        Intent intent = new Intent();
        intent.setAction(TransitionAction._activityCreated);
        intent.putExtra("activity", _tag);
        sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        Log.d(_tag, "onDestroy()");

        super.onDestroy();

        Intent intent = new Intent();
        intent.setAction(TransitionAction._activityDestroyed);
        intent.putExtra("activity", _tag);
        sendBroadcast(intent);
    }
}