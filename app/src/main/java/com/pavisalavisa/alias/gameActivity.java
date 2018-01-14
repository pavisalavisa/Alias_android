package com.pavisalavisa.alias;

import android.app.DialogFragment;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.Random;

public class gameActivity extends AppCompatActivity {
    private DialogFragment dialogFragment;
    private TextView wordToGuess;
    private MyCountdownTimer timer;
    private Game currentGame;
    private Team currentTeamPlaying;
    private int millisUntilFInished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        currentGame = Game.getCurrentGame();
        wireUpViews();
        popStartDialog();
    }

    @Override
    protected void onPause() {
        super.onPause();
        pauseTimer();
    }
    @Override
    protected void onStop(){
        super.onStop();
        pauseTimer();
    }

    @Override
   protected void onRestart(){
       super.onRestart();
       timer=new MyCountdownTimer(millisUntilFInished,1000);
       timer.start();
   }

    public void wrongAnswerButtonClicked(View view) {
        currentTeamPlaying.addPoints(GameRules.wrongAnswerPoints);
        nextWord();
    }

    public void rightAnswerButtonClicked(View view) {
        currentTeamPlaying.addPoints(GameRules.rightAnswerPoints);
        nextWord();
    }

    private void nextWord() {
        new GetWordFromDatabaseTask().execute();
    }

    private void wireUpViews() {
        wordToGuess = (TextView) findViewById(R.id.word_to_guess);
    }

    private void popStartDialog() {
        dialogFragment = new GameStartFragment();
        dialogFragment.show(getFragmentManager(), "GameStartFragment");
    }

    //Called by the GameStartFragment
    protected void startRound() {
        currentTeamPlaying = currentGame.getCurrentTeamPlaying();
        startTimer(currentGame.getGameDuration());
        nextWord();
    }

    private void startTimer(int timeInSeconds) {
        //+1000 because it floors the value of seconds and it appears that
        //it doesn't start from timeInSeconds but timeInSeconds-1 instead
        final int timeInMillis = timeInSeconds * 1000 + 1000;
        timer = new MyCountdownTimer(timeInMillis, 1000);
        timer.start();
    }

    public void pauseTimer() {
        millisUntilFInished = timer.getMilisUntilFinished();
        timer.pause();
    }

    public void resumeTimer() {
        timer = new MyCountdownTimer(millisUntilFInished, 1000);
        timer.start();
    }


    private class MyCountdownTimer extends CountDownTimer {
        int milisUntilFinished;

        public MyCountdownTimer(long milis, int step) {
            super(milis, step);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            setCountdownText(millisUntilFinished);
            this.milisUntilFinished = (int) millisUntilFinished;
        }

        @Override
        public void onFinish() {
            TextView countdownText = (TextView) findViewById(R.id.countdown);
            countdownText.setText(R.string.time_up);
            showScore();
        }

        public void pause() {
            timer.cancel();
        }

        public void resume() {

            this.start();
        }

        public int getMilisUntilFinished() {
            return milisUntilFinished;
        }

        private void setCountdownText(long millisUntilFinished) {
            int seconds = (int) (millisUntilFinished / 1000) % 60;
            int minutes = (int) millisUntilFinished / 1000 / 60;
            TextView countdownText = (TextView) findViewById(R.id.countdown);
            if (minutes == 0) {
                countdownText.setText("00:" + seconds);
                return;
            }
            if (seconds == 0) {
                countdownText.setText(minutes + ":00");
                return;
            }
            countdownText.setText(minutes + ":" + seconds);
        }
    }

    private class GetWordFromDatabaseTask extends AsyncTask<String, Void, Boolean> {
        private String wordFromDB;

        @Override
        protected Boolean doInBackground(String... params) {
            try {
                SQLiteOpenHelper aliasDatabaseHelper = new AliasDatabaseHelper(gameActivity.this);
                SQLiteDatabase db = aliasDatabaseHelper.getReadableDatabase();
                System.out.println(db.getVersion());
                int randomID = ((AliasDatabaseHelper) aliasDatabaseHelper).getRandomRow();
                System.out.println("Random number is" + randomID);
                System.out.println("The size od DB is " + DatabaseUtils.queryNumEntries(db, "ITEMS"));
                Cursor cursor = db.query("ITEMS",
                        new String[]{"WORD"},
                        "_id = ?",
                        new String[]{Integer.toString(randomID)},
                        null, null, null);
                cursor.moveToFirst();
                wordFromDB = cursor.getString(0);
                cursor.close();
            } catch (SQLiteException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            wordToGuess.setText(wordFromDB);
        }
    }

    private void showScore() {
        popScoreDialog();
    }

    private void popScoreDialog() {
        dialogFragment = new ScoreBoardFragment();
        dialogFragment.show(getFragmentManager(), "ScoreBoardFragment");
    }

    //Called by the ScoreBoardFragment
    void nextRound() {
        if (someoneWon()) {
            declareWinner();
            return;
        }
        currentGame.nextRound();
        popStartDialog();
    }

    private Boolean someoneWon() {
        Iterator<Team> iter = currentGame.getTeamIterator();
        while (iter.hasNext()) {
            if (iter.next().getPoints() >= currentGame.getPointThreshold()) {
                return true;
            }
        }
        return false;
    }

    private void declareWinner() {
        /*
        Decalre winner, show scoreboard and ask if they wanna play again.
         */
        Team winningTeam = currentGame.getWinningTeam();
        popWinnerDialog();
    }

    private void popWinnerDialog() {
        dialogFragment = new WinnerDialogFragment();
        dialogFragment.show(getFragmentManager(), "WinnerFragment");
    }
}




