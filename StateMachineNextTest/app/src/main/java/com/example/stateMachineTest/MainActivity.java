package com.example.stateMachineTest;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;

import app.avare.statemachinelib.StateMachine;

public class MainActivity extends AppCompatActivity {

    private StateMachine stateMachine;
    private static final String TAG = "STATE_MACHINE_TEST";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout layout = (LinearLayout) findViewById(R.id.main);

        stateMachine = new StateMachine();
        
        Button button = new Button(this);
        button.setText("TestStates");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "-");
                testMicState();
                testCamState();
                Log.d(TAG, "-");
            }
        });
        layout.addView(button);

    }

    //status blocked, blockStatus hard
    private void testCamState() {
        Log.d(TAG, "1" + stateMachine.getCameraState().toString()); // BLOCKED
        stateMachine.nextCameraState();
        Log.d(TAG, "2" + stateMachine.getCameraState().toString()); // BLACK_PICTURE
        stateMachine.nextCameraState();
        Log.d(TAG, "3" +  stateMachine.getCameraState().toString()); // NEUTRAL_PICTURE
    }

    //status blocked, blockStatus hard
    private void testMicState() {
        Log.d(TAG, "1" + stateMachine.getMicrophoneState().toString()); // BLOCKED
        stateMachine.nextMicrophoneState();
        Log.d(TAG, "2" + stateMachine.getMicrophoneState().toString()); // NO_SOUND
        stateMachine.nextMicrophoneState();
        Log.d(TAG, "3" +  stateMachine.getMicrophoneState().toString()); // NEUTRAL_SOUND
        
    }
}
