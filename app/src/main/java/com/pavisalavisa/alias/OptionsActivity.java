package com.pavisalavisa.alias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

public class OptionsActivity extends AppCompatActivity {
    private SeekBar roundDurationSeekBar;
    private SeekBar pointThresholdSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        wireUpViews();
    }

    private void wireUpViews(){
        roundDurationSeekBar=(SeekBar)findViewById(R.id.round_duration_seekbar);
        pointThresholdSeekBar=(SeekBar)findViewById(R.id.point_threshold_seekbar);

        roundDurationSeekBar.setMax(120);
        roundDurationSeekBar.setOnSeekBarChangeListener(new SeekBarListener());
        pointThresholdSeekBar.setOnSeekBarChangeListener(new SeekBarListener());
    }

    public void cancelOptions(View view){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void confirmOptions(View view){
        Game currentGame=Game.getCurrentGame();

        int roundDuration=roundDurationSeekBar.getProgress();
        int pointThreshold=pointThresholdSeekBar.getProgress();
        currentGame.setRules(new GameRules(roundDuration,pointThreshold));
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    private class SeekBarListener implements SeekBar.OnSeekBarChangeListener{

        private int progress=0;
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            this.progress=progress;
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            Toast.makeText(getApplicationContext(),Integer.toString(progress),Toast.LENGTH_SHORT).show();

        }
    }
}
