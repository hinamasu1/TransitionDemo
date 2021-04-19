package jp.co.informatix.transitiondemo;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class TransitionService extends Service {

    private final String _tag = getClass().getSimpleName();
    private boolean _levelOneActivity = false;
    private boolean _levelTwoActivity = false;
    private boolean _levelThreeActivity = false;
    private boolean _transitionActivity = false;
    private BroadcastReceiver _receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (action.equals(TransitionAction._levelchanged)) {
                Integer level = intent.getIntExtra("level", -1);
                TransitionDemo app = (TransitionDemo)getApplication();

                if (level == 0) {
                    Intent i = new Intent(getApplicationContext(), TransitionActivity.class);
                    if (_transitionActivity == false) {
                        i.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    } else {
                        i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | FLAG_ACTIVITY_NEW_TASK);
                    }
                    startActivity(i);
                } else if (level == 1) {
                    Intent i = new Intent(getApplicationContext(), LevelOneActivity.class);
                    if (_levelOneActivity == false) {
                        i.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    } else {
                        i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | FLAG_ACTIVITY_NEW_TASK);
                    }
                    startActivity(i);
                } else if (level == 2) {
                    Intent i = new Intent(getApplicationContext(), LevelTwoActivity.class);
                    if (_levelTwoActivity == false) {
                        i.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    } else {
                        i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | FLAG_ACTIVITY_NEW_TASK);
                    }
                    startActivity(i);
                } else if (level == 3) {
                    Intent i = new Intent(getApplicationContext(), LevelThreeActivity.class);
                    if (_levelThreeActivity == false) {
                        i.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    } else {
                        i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | FLAG_ACTIVITY_NEW_TASK);
                    }
                    startActivity(i);
                }
            } else if (action.equals(TransitionAction._activityCreated)) {
                String activity = intent.getStringExtra("activity");
                if (activity.equals(LevelOneActivity._tag)) {
                    _levelOneActivity = true;
                } else if (activity.equals(LevelTwoActivity._tag)) {
                    _levelTwoActivity = true;
                } else if (activity.equals(LevelThreeActivity._tag)) {
                    _levelThreeActivity = true;
                }
            } else if (action.equals(TransitionAction._activityDestroyed)) {
                String activity = intent.getStringExtra("activity");
                if (activity.equals(LevelOneActivity._tag)) {
                    _levelOneActivity = false;
                } else if (activity.equals(LevelTwoActivity._tag)) {
                    _levelTwoActivity = false;
                } else if (activity.equals(LevelThreeActivity._tag)) {
                    _levelThreeActivity = false;
                }
            } else if (action.equals(TransitionAction._destryActivity)) {
            }
        }
    };

    public TransitionService() {
    }

    @Override
    public void onCreate() {
        Log.d(_tag, "onCreate()");

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(TransitionAction._levelchanged);
        intentFilter.addAction(TransitionAction._activityCreated);
        intentFilter.addAction(TransitionAction._activityDestroyed);
        registerReceiver(_receiver, intentFilter);

        Intent intent = new Intent(getApplicationContext(), LevelOneActivity.class);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}