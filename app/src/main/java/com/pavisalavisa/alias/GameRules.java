package com.pavisalavisa.alias;

/**
 * Created by krist on 6.1.2018..
 */

public class GameRules {
    private int roundDurationInSeconds;


    private int pointThreshold;

    public static GameRules fastGame=new GameRules(30,25);
    public static GameRules longGame=new GameRules(60,50);
    public static GameRules ultraLongGame=new GameRules(60,100);

    public static final int rightAnswerPoints=1;
    public static final int wrongAnswerPoints=-1;

    public GameRules(int roundDurationInSeconds, int pointThreshold) {
        this.roundDurationInSeconds = roundDurationInSeconds;
        this.pointThreshold = pointThreshold;
    }

    public int getRoundDurationInSeconds(){
        return roundDurationInSeconds;
    }


    public int getPointThreshold() {
        return pointThreshold;
    }
}
