package com.pavisalavisa.alias;

import android.os.CountDownTimer;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by krist on 6.1.2018..
 */

public class Game {
    private static Game currentGame;
    private LinkedList<Team> teams=new LinkedList<Team>();
    private CountDownTimer timer;
    private GameRules rules;
    private Team currentTeamPlaying;



    private Game(){
        rules=GameRules.fastGame;
    }

    public static Game getCurrentGame(){
        if(currentGame==null) {
            currentGame = new Game();
        }
        return currentGame;
    }

    public static void endGame(){
        currentGame=null;
    }

    public static void resetPoints(){
        for (Team x:currentGame.teams) {
            x.resetPoints();
        }
    }

    public Boolean addTeam(String teamName,String playerOne,String playerTwo){
        if(teamName.length()==0||playerOne.length()==0||playerTwo.length()==0){
            return false;
        }
        Team tempTeam=new Team(teamName,playerOne,playerTwo);
        currentGame.teams.add(tempTeam);
        if(teams.size()==1){
            currentTeamPlaying=tempTeam;
        }
        return true;
    }
    public Team getCurrentTeamPlaying() {
        return currentTeamPlaying;
    }

    public void setRules(int duration,int threshold){
        currentGame.rules=new GameRules(duration,threshold);
    }
    public void setRules(GameRules rules)
    {
        currentGame.rules=rules;
    }

    public Team getLastAddedTeam(){
        return currentGame.teams.getLast();
    }

    public Boolean hasTeams()
    {
        return !teams.isEmpty();
    }

    public Iterator<Team> getTeamIterator()
    {
        return teams.listIterator();
    }

    public void nextRound(){
        currentTeamPlaying.nextRound();
        if(teams.indexOf(currentTeamPlaying)==teams.size()-1) {
            currentTeamPlaying = teams.getFirst();
        }
        else{
            currentTeamPlaying=teams.get(teams.indexOf(currentTeamPlaying)+1);
        }
    }
    public int getPointThreshold(){
        return rules.getPointThreshold();
    }

    /**
     *
     * @return duration of the game in seconds
     */
    public int getGameDuration(){
        return rules.getRoundDurationInSeconds();
    }

    public Team getWinningTeam(){
        Iterator<Team> iter=getTeamIterator();
        Team winningTeam=iter.next();
        Team temporaryTeam;
        while(iter.hasNext()){
            temporaryTeam=iter.next();
            if(temporaryTeam.getPoints()>winningTeam.getPoints()){
                winningTeam=temporaryTeam;
            }
        }
        return winningTeam;
    }

}
