package com.example.andre.howfast;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.SubMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;


public class MYActivity extends AppCompatActivity {
    private Handler mHandler = new Handler();
    private long startTime;
    private long stopTime;
    private long elapsedTime;
    private final int REFRESH_RATE = 100;
    private boolean startButton = true;
    private String hours, minutes, seconds, milliseconds;
    private long secs, mins, hrs, msecs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getResources().getText(R.string.app_name));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);


        SubMenu langMenu = menu.addSubMenu(0, 200, 2, "EN-ES-PT").setIcon(android.R.drawable.ic_menu_rotate);
        langMenu.add(1, 201, 0, "English");
        langMenu.add(1, 202, 0, "Español");
        langMenu.add(1, 203, 0, "Português");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (item.getItemId()) {

            case 201:

                Locale locale = new Locale("En");
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                Toast.makeText(this, "Language set to english !", Toast.LENGTH_LONG).show();
                break;

            case 202:

                Locale locale2 = new Locale("es");
                Locale.setDefault(locale2);
                Configuration config2 = new Configuration();
                config2.locale = locale2;
                getBaseContext().getResources().updateConfiguration(config2, getBaseContext().getResources().getDisplayMetrics());
                Toast.makeText(this, "El idioma configurado en español ! ", Toast.LENGTH_LONG).show();
                break;

            case 203:

                Locale locale3 = new Locale("pt");
                Locale.setDefault(locale3);
                Configuration config3 = new Configuration();
                config3.locale = locale3;
                getBaseContext().getResources().updateConfiguration(config3, getBaseContext().getResources().getDisplayMetrics());
                Toast.makeText(this, "O idioma foi mudado para português ! ", Toast.LENGTH_LONG).show();
                break;

        }
        if (id > 200 && id < 204) restartActivity();// API 11: recreate();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void restartActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);

    }


    public void saveTime(View view) {
        Button tiny = (Button)findViewById(R.id.button);

        if (!startButton) {
            elapsedTime = System.currentTimeMillis() - startTime;
            updateTimer(elapsedTime);
            tiny.setBackground(getResources().getDrawable(R.drawable.panicbutton));
            startButton = true;
        } else {
            startTime = System.currentTimeMillis();
            tiny.setBackground(getResources().getDrawable(R.drawable.pressed));
            startButton = false;
        }
    }

    private void updateTimer(float time) {
        secs = (long) (time / 1000);
        mins = (long) ((time / 1000) / 60);
        hrs = (long) (((time / 1000) / 60) / 60);

    /* Convert the seconds to String
     * and format to ensure it has
     * a leading zero when required
     */
        secs = secs % 60;
        seconds = String.valueOf(secs);
        if (secs == 0) {
            seconds = "00";
        }
        if (secs < 10 && secs > 0) {
            seconds = "0" + seconds;
        }

    /* Convert the minutes to String and format the String */

        mins = mins % 60;
        minutes = String.valueOf(mins);
        if (mins == 0) {
            minutes = "00";
        }
        if (mins < 10 && mins > 0) {
            minutes = "0" + minutes;
        }

    /* Although we are not using milliseconds on the timer in this example
     * I included the code in the event that you wanted to include it on your own
     */
        milliseconds = String.valueOf((long) time);
        if (milliseconds.length() == 2) {
            milliseconds = "0" + milliseconds;
        }
        if (milliseconds.length() <= 1) {
            milliseconds = "00";
        }
        milliseconds = milliseconds.substring(milliseconds.length() - 3, milliseconds.length());


    /* Setting the timer text to the elapsed time */
        TextView textView = (TextView)findViewById(R.id.textView);
        textView.setTextSize(40);
        textView.setText((!minutes.equals("00")?(minutes+":"):" " ) + (!seconds.equals("00")?(seconds+":"):" ") + milliseconds + "ms");
    }
}
