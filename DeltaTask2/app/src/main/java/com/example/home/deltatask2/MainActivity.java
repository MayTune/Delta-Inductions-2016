package com.example.home.deltatask2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.speech.RecognizerIntent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int width=1600, height=2000;
    private float x=width/2,y=height/2,vx=1,vy=1,r=30;
    private Canvas c;
    private Paint paint;
    private ImageView imageview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        speakButton = (Button) findViewById(R.id.btn_speak);
        speakButton.setOnClickListener(this);

        voiceinputbuttons();

        Bitmap b = Bitmap.createBitmap(width,
                height, Bitmap.Config.ARGB_8888);
        c = new Canvas(b);
        c.drawColor(Color.WHITE);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        c.drawCircle(x, y, r, paint);

        imageview = (ImageView) findViewById(R.id.imageView);
        imageview.setImageBitmap(b);

        }


    void up(){
        paint.setColor(Color.WHITE);
        c.drawCircle(x,y,r,paint);
            y = y - vy;
            if ((y - r) == 0) {
                y = height - r;
            }
            paint.setColor(Color.RED);
        c.drawCircle(x, y, r, paint);
            imageview.invalidate();
        }



    void down(){
        paint.setColor(Color.WHITE);
        c.drawCircle(x,y,r,paint);
        y=y+vy;
        if(y+r>=height)
        {
            y=r;
        }
        paint.setColor(Color.RED);
        c.drawCircle(x, y, r, paint);
        imageview.invalidate();
    }

    void left(){
        paint.setColor(Color.WHITE);
        c.drawCircle(x,y,r,paint);
        x=x-vx;
        if(x-r<=0)
        {
            x=width-r;
        }
        paint.setColor(Color.RED);
        c.drawCircle(x, y, r, paint);
        imageview.invalidate();
    }


    void right()
    {
        paint.setColor(Color.WHITE);
        c.drawCircle(x,y,r,paint);
        x=x+vx;
        if(x+r>=width)
        {
            x=r;
        }
        paint.setColor(Color.RED);
        c.drawCircle(x,y,r,paint);
        imageview.invalidate();
    }


    public ListView mList;
    public Button speakButton;
    public static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;



    public void voiceinputbuttons() {
        speakButton = (Button) findViewById(R.id.btn_speak);
        mList = (ListView) findViewById(R.id.list);
    }

    public void startVoiceRecognitionActivity() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Speech recognition demo");
        startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);
    }

    public void onClick(View v) {
        // TODO Auto-generated method stub
        startVoiceRecognitionActivity();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK) {
            // Fill the list view with the strings the recognizer thought it
            // could have heard
            ArrayList matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            mList.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, matches));

            if (matches.contains("up")) {
                for (int i = 0; i < 10; i++) {
                    up();
                }
            }
                else
                if(matches.contains("down") ){
                    for (int i = 0; i < 10; i++) {
                        down();
                    }

            }
                else
                if(matches.contains("left") ){
                    for (int i = 0; i < 10; i++) {
                        left();
                    }

                }
                else
                if(matches.contains("right") ){
                    for (int i = 0; i < 10; i++) {
                        right();
                    }

                }
            else if(matches.contains("increase")){
                    if(r<width/2){
                        r=r+10;
                    }
                    paint.setColor(Color.RED);
                    c.drawCircle(x, y, r, paint);
                }
                else if(matches.contains("decrease")){
                    if(r>=20){
                        r=r-10;
                    }
                    paint.setColor(Color.RED);
                    c.drawCircle(x, y, r, paint);
                }


        }
    }
}







