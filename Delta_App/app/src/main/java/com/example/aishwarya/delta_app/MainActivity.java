package com.example.aishwarya.delta_app;

        import android.content.SharedPreferences;
        import android.graphics.Color;
        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.util.DisplayMetrics;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.LinearLayout;
        import android.widget.RelativeLayout;
        import android.widget.TextView;
        import android.content.Context;



public class MainActivity extends ActionBarActivity {

    private static final String COUNTER1="COUNTER";
    private static final String BGCOLOR1="BGCOLOR";
    private static final String PREF="file";

    int counter=0;
    int bgColor=Color.CYAN;
    private RelativeLayout myLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.portraitview);

        SharedPreferences sharedpref=getSharedPreferences(PREF,Context.MODE_PRIVATE);
        counter=sharedpref.getInt(COUNTER1,counter);
        bgColor=sharedpref.getInt(BGCOLOR1,bgColor);
       Button resetButton = (Button) findViewById(R.id.resetButton);
        resetButton.setOnClickListener(
                new Button.OnClickListener() {

                    public void onClick(View v) {
                        counter = 0;
                        TextView viewText = (TextView) findViewById(R.id.viewText);
                        viewText.setText("No of clicks=" + String.valueOf(counter));
                    }

                }
        );

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(
                new Button.OnClickListener() {

                    public void onClick(View v) {
                        RelativeLayout myLayout = (RelativeLayout) findViewById(R.id.myLayout);
                        myLayout.setBackgroundColor(bgColor);
                        TextView viewText = (TextView) findViewById(R.id.viewText);
                        viewText.setText("No of clicks=" + String.valueOf(counter));
                        counter = counter + 1;
                        if (counter == 1) {
                            viewText.setText("No of clicks=" + String.valueOf(counter));
                        } else {
                            viewText.setText("No of clicks=" + String.valueOf(counter));
                        }

                        if (bgColor == Color.CYAN) {
                            bgColor = Color.BLUE;
                        } else if (bgColor == Color.BLUE) {
                            bgColor = Color.RED;
                        } else if (bgColor == Color.RED) {
                            bgColor = Color.GREEN;
                        } else if (bgColor == Color.GREEN) {
                            bgColor = Color.YELLOW;
                        } else if (bgColor == Color.YELLOW) {
                            bgColor = Color.GRAY;
                        } else if (bgColor == Color.GRAY) {
                            bgColor = Color.CYAN;
                        }


                    }

                }
        );


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt(COUNTER1, counter);
        outState.putInt(BGCOLOR1,bgColor);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        counter=savedInstanceState.getInt(COUNTER1);
        bgColor=savedInstanceState.getInt(BGCOLOR1);

    }

    protected void onStop()
    {
        super.onStop();
        SharedPreferences sharedpref=getSharedPreferences(PREF,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedpref.edit();
        editor.putInt(COUNTER1,counter);
        editor.putInt(BGCOLOR1,bgColor);
        editor.apply();
    }



}
