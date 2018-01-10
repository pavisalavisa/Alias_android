package com.pavisalavisa.alias;

import android.app.DialogFragment;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class gameActivity extends AppCompatActivity {
    private DialogFragment dialogFragment;
    private TextView timer;
    private TextView wordToGuess;

    private Game currentGame;
    private Team currentTeamPlaying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        currentGame=Game.getCurrentGame();
        wireUpViews();
        popStartDialog();
    }
    /*
    TODO: Implementacija countdown timera
     */


    public void wrongAnswerButtonClicked(View view){
        currentTeamPlaying.addPoints(GameRules.wrongAnswerPoints);
        nextWord();
    }

    public void rightAnswerButtonClicked(View view){
        currentTeamPlaying.addPoints(GameRules.rightAnswerPoints);
        nextWord();
    }

    private void nextWord(){

    }

    private void wireUpViews(){
        timer=(TextView)findViewById(R.id.countdown);
        wordToGuess=(TextView)findViewById(R.id.word_to_guess);
    }

    private void popStartDialog(){
        dialogFragment=new GameStartFragment();
        dialogFragment.show(getFragmentManager(),"GameStartFragment");
    }

    protected void startRound(){
        startTimer(5);//TODO timer must be controlled by game rules
        nextWord();
    }

    private void startTimer(int timeInSeocnds){
       final int timeInMilis=timeInSeocnds*1000+1000;
       CountDownTimer timer= new CountDownTimer(timeInMilis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                setCountdownText(millisUntilFinished);

            }

            @Override
            public void onFinish() {
            }
            private void setCountdownText(long millisUntilFinished){
                int seconds=(int)(millisUntilFinished/1000)%60;
                int minutes=(int)millisUntilFinished/1000/60;
                TextView countdownText=(TextView)findViewById(R.id.countdown);
                if(minutes==0){
                    countdownText.setText("00:"+seconds);
                    return;
                }
                if(seconds==0){
                    countdownText.setText(minutes+":00");
                    return;
                }

                countdownText.setText(minutes+":"+seconds);
            }
        };
        timer.start();
    }

}
