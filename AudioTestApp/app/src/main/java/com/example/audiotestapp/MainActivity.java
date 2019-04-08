package com.example.audiotestapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends Activity{

    //Parameter um Berechtigungen zu erfragen
    private static final int REQUEST_AUDIO_PERMISSION = 5000;
    private static final String permissionArray[] = {Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};

    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;

    private static String FILE_NAME;

    private TextView textView;

    //For Debug Logging
    private static final String LOG_TAG = "AudioTest";

    private boolean isRecording = false;
    private boolean permissionGranted = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //intitalize file name
        FILE_NAME = getExternalCacheDir().getAbsolutePath() + "/audioTest";

        //ask user for permission
        ActivityCompat.requestPermissions(this, permissionArray, REQUEST_AUDIO_PERMISSION);

        LinearLayout l = (LinearLayout) findViewById(R.id.main);

        textView = new TextView(this);
        l.addView(textView);

        PlayButton playButton = new PlayButton(this);
        l.addView(playButton);

        RecordButton recordButton = new RecordButton(this);
        l.addView(recordButton);
    }

    @Override
    public void onStop() {
        super.onStop();

        if(mediaRecorder != null) {
            mediaRecorder.release();
            mediaRecorder.stop();
        }

        if(mediaRecorder != null) {
            mediaRecorder.release();
            mediaRecorder.stop();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_AUDIO_PERMISSION) {
            permissionGranted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
        }
    }



    private void stopRecord() {
        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder = null;
        isRecording = false;
        textView.setText("Recorder ready");
    }

    private void record() {
        isRecording = true;
        textView.setText("is recording");
        mediaRecorder.start();
        Log.d(LOG_TAG, "Record aufgerufen.");
    }


    private boolean initMediaRecorder() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mediaRecorder.setOutputFile(FILE_NAME);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            textView.setText("Media Recorder konnte nicht initialisiert werden" + "\n" + FILE_NAME +   "\n"+ e.getMessage());
            e.printStackTrace();
            Log.d(LOG_TAG, "prepare failed");
            return false;
        }
        textView.setText("Recorder ready");
        return true;
    }



    private void playAudio() {
        //MediaPlayer schließen und neu anlegen.
        if(mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }

        Log.d(LOG_TAG, "PlayAudio aufgerufen");
        mediaPlayer = new MediaPlayer();
        try {

            mediaPlayer.setDataSource(FILE_NAME);
        } catch (IOException e) {
            Log.d(LOG_TAG, "setDataSource failed");
        }
        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            Log.d(LOG_TAG, "prepare failed");
        }
        mediaPlayer.start();
    }

    private class RecordButton extends android.support.v7.widget.AppCompatButton {

        OnClickListener click = new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG, "RecordButton geklickt");
                if(!permissionGranted) {
                    return;
                }
                if(isRecording) {
                    stopRecord();
                } else {
                    if(initMediaRecorder()) {
                        record();
                    }

                }
            }
        };


        public RecordButton(Context context) {
            super(context);
            setText("Record");
            setOnClickListener(click);
            Log.d(LOG_TAG, "RecordButton angelegt");
        }
    }

    private class PlayButton extends android.support.v7.widget.AppCompatButton {

        OnClickListener click = new OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio();
            }
        };


        public PlayButton(Context context) {
            super(context);
            setText("Play");
            setOnClickListener(click);
            Log.d(LOG_TAG, "PlayButton angelegt");
        }
    }


}

