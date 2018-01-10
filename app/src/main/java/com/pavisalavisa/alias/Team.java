package com.pavisalavisa.alias;

/**
 * Created by krist on 6.1.2018..
 */

public class Team {
    private String teamName;
    private String playerOne;
    private String playerTwo;
    private int points;
    private int playerReading;

    public Team(String teamName, String playerOne, String playerTwo) {
        this.teamName = teamName;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        playerReading=1;
        points=0;
    }

    /**
     * Add points to cumulative points of given team.
     * Cumulative points cannot be less than zero.
     * @param newPoints
     */
    public void addPoints(int newPoints){
        if(points<=0 && newPoints<0){
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
    public String getPlayerReading() {
        if(playerReading==1){
            return playerOne;
        }
        return playerTwo;
    }
    public String getPlayerGuessing(){
        if(playerReading==1){
            return playerTwo;
        }
        return playerOne;
    }
}
