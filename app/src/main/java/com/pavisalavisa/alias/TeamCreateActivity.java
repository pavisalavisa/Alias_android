package com.pavisalavisa.alias;

import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;

public class TeamCreateActivity extends AppCompatActivity {
    private Game game;
    private DialogFragment dialog;
    private TableLayout table;
    static Boolean backgroundFlag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_create);
        game=Game.getCurrentGame();
        table=(TableLayout)findViewById(R.id.players_table);
        buildTable();
    }

    private void buildTable(){
        if(game.hasTeams()){
            Iterator<Team> iterator=game.getTeamIterator();
            while(iterator.hasNext()){
                TableRow tr=new TableRow(this);
                addItemsToRow(tr,iterator.next());
                table.addView(tr);
                setAlternatingBackground(tr);
            }
        }
    }
    public void addNewTeam(View view){
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
        setAlternatingBackground(tr);
    }

    private void addItemsToRow(TableRow tr, Team team){
        attachTextViewToRow(tr,team.getTeamName(),2);
        attachTextViewToRow(tr,team.getPlayerOne(),1);
        attachTextViewToRow(tr,team.getPlayerTwo(),1);
    }

    private void attachTextViewToRow(TableRow tr,String text,int weight){
        TextView myText=makeTextView(text,weight);
        if(weight==1){
            myText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }
        tr.addView(myText);

    }

    private TextView makeTextView(String text,int weight){
        TextView teamName=new TextView(this);
        teamName.setText(text);
        teamName.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
        teamName.setLayoutParams(new TableRow.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT, (float)weight));
        return teamName;
    }

    private void setAlternatingBackground(TableRow tableRow){
        if(backgroundFlag) {
                tableRow.setBackgroundColor(Color.parseColor("#add8e6"));
            }
        backgroundFlag=!backgroundFlag;


    }

    public void startClicked(View view){
        if(!game.hasTeams()){
            Toast toast= Toast.makeText(TeamCreateActivity.this,"Must have at least 1 team",Toast.LENGTH_SHORT);
            return;
        }
        Intent intent=new Intent(TeamCreateActivity.this,gameActivity.class);
        startActivity(intent);
    }



}
