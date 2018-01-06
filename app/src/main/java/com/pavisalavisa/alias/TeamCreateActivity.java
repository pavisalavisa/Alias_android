package com.pavisalavisa.alias;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TeamCreateActivity extends AppCompatActivity {
    private Game game;
    DialogFragment dialog;
    TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_create);
        game=Game.getCurrentGame();
        table=(TableLayout)findViewById(R.id.players_table);
    }

    public void addNewTeam(View view){
        //TODO: dodati funkcionalnost timovac
        popDialog();
    }
    private void popDialog(){
        dialog=new NewTeamFragment();
        dialog.show(getFragmentManager(),"newTeamDialog");
    }

    /**
     * Called by the dialog fragment
     */
    public void addNewRow(){
        TableRow tr=new TableRow(this);
        addItemsToRow(tr,game.getLastAddedTeam());
        table.addView(tr);
    }

    private void addItemsToRow(TableRow tr, Team team){
        //TODO: popravi font i izgled ovog sta se ubacuje
        TextView teamName=new TextView(this);
        teamName.setText(team.getTeamName());
        tr.addView(teamName);

        TextView playerOneName=new TextView(this);
        playerOneName.setText(team.getPlayerOne());
        tr.addView(playerOneName);

        TextView playerTwoName=new TextView(this);
        playerTwoName.setText(team.getPlayerTwo());
        tr.addView(playerTwoName);
    }


}
