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

    public Team getCurrentTeamPlaying() {
        return currentTeamPlaying;
    }

    public void setCurrentTeamPlaying(Team currentTeamPlaying) {
        this.currentTeamPlaying = currentTeamPlaying;
    }

    private Game(){

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

    public Boolean addTeam(String teamName,String playerOne,String playerTwo){
        if(teamName.length()==0||playerOne.length()==0||playerTwo.length()==0){
            return false;
        }
        Team tempTeam=new Team(teamName,playerOne,playerTwo);
        currentGame.teams.add(tempTeam);
        currentTeamPlaying=tempTeam;
        return true;
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
        return teams.iterator();
    }


}
