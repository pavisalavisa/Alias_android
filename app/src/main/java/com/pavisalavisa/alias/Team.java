package com.pavisalavisa.alias;

/**
 * Created by krist on 6.1.2018..
 */

public class Team {
    private String teamName;
    private String playerOne;
    private String playerTwo;
    private int points;

    public Team(String teamName, String playerOne, String playerTwo) {
        this.teamName = teamName;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        points=0;
    }

    public void addPoints(int newPoints){
        if(newPoints<0){
            return;
        }
        points+=newPoints;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getPlayerOne() {
        return playerOne;
    }

    public String getPlayerTwo() {
        return playerTwo;
    }

    public int getPoints() {
        return points;
    }
}
