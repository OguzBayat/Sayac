package com.oguzbayat.sayac;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    Button button;
    int count;
    SharedPreferences preferences,ayarlar;
    RelativeLayout bg;
    Boolean sesDurumu,titresim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        button = (Button) findViewById(R.id.button);
        bg = (RelativeLayout) findViewById(R.id.content_main);
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ayarlar = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        final MediaPlayer ses = MediaPlayer.create(getApplicationContext(),R.raw.cartoon180);
        final Vibrator titre = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        ayarlariYükle();

        count = preferences.getInt("count_tut",0);
        String sayac = Integer.toString(count);
        button.setText(sayac);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sesDurumu){
                    ses.start();
                }
                if(titresim){
                    titre.vibrate(250);
                }
                count ++;
                String sayac = Integer.toString(count);
                button.setText(sayac);
            }
        });


    }

    private void ayarlariYükle() {
        String pos = ayarlar.getString("arkaplan","3");
        switch (Integer.valueOf(pos)){

            case 0:
                bg.setBackgroundResource(R.drawable.back);
                break;
            case 1:
                bg.setBackgroundResource(R.drawable.back);
                break;
            case 2:
                bg.setBackgroundResource(R.drawable.back);
                break;
            case 3:
                bg.setBackgroundColor(Color.RED);
                break;

        }
        sesDurumu = ayarlar.getBoolean("ses",false);
        titresim = ayarlar.getBoolean("titresim",false);

        ayarlar.registerOnSharedPreferenceChangeListener(MainActivity.this);
    }

    @Override
    protected void onPause() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("count_tut",count);
        editor.commit();
        super.onPause();
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
            Intent intent = new Intent(getApplicationContext(),Ayarlar.class);
            startActivity(intent);
            return true;
        }

        else if (id == R.id.action_settings2){
            count = 0;
            String sayac = Integer.toString(count);
            button.setText(sayac);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        ayarlariYükle();
    }
}
