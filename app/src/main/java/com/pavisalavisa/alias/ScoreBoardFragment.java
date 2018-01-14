package com.pavisalavisa.alias;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Iterator;


public class ScoreBoardFragment extends DialogFragment {
    private View dialogFragmentView;
    private TableLayout table;
    private static boolean backgroundFlag;
    public ScoreBoardFragment() {
        // Required empty public constructor
    }


   @Override
   public Dialog onCreateDialog(Bundle savedInstanceState){
       AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
       Context context=builder.getContext();
       LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       dialogFragmentView=inflater.inflate(R.layout.fragment_score_board,null);
       builder.setView(dialogFragmentView);
       this.setCancelable(false);
       wireUpViews();
       return builder.create();
    }

    private void wireUpViews(){
        wireUpButtons();
        wireUpTable();
    }

    private void wireUpButtons(){
        Button okButton=(Button)dialogFragmentView.findViewById(R.id.score_ok_button);
        okButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                ScoreBoardFragment.this.dismiss();
                notifyParent();
            }
        });
    }

    private void wireUpTable(){
        Game game=Game.getCurrentGame();
        table=(TableLayout)dialogFragmentView.findViewById(R.id.players_score_table);
            if(game.hasTeams()){
                Iterator<Team> iterator=game.getTeamIterator();
                while(iterator.hasNext()){
                    TableRow tr=new TableRow(getActivity().getApplicationContext());
                    addItemsToRow(tr,iterator.next());
                    table.addView(tr);
                    setAlternatingBackground(tr);
                }
            }
    }

    private void addItemsToRow(TableRow tr, Team team){
        attachTextViewToRow(tr,team.getTeamName(),2);
        attachTextViewToRow(tr,team.getPlayerOne(),2);
        attachTextViewToRow(tr,team.getPlayerTwo(),2);
        attachTextViewToRow(tr,Integer.toString(team.getPoints()),1);
    }

    private void attachTextViewToRow(TableRow tr,String text,int weight){
        TextView myText=makeTextView(text,weight);
        if(weight==1){
            myText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }
        tr.addView(myText);

    }

    private TextView makeTextView(String text,int weight){
        TextView teamName=new TextView(getActivity().getApplicationContext());
        teamName.setText(text);
        teamName.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
        teamName.setLayoutParams(new TableRow.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT, (float)weight));
        return teamName;
    }

    private void setAlternatingBackground(TableRow tableRow){
        if(backgroundFlag) {
            tableRow.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }
        backgroundFlag=!backgroundFlag;


    }



    private void notifyParent(){
        ((gameActivity)getActivity()).nextRound();
    }

}
