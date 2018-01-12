package com.pavisalavisa.alias;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class WinnerDialogFragment extends DialogFragment {
    private View dialogFragmentView;
    private TextView winner;
    private Button newTeamButton;
    private Button sameTeamsButton;


    public WinnerDialogFragment(){}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        Context context=builder.getContext();
        LayoutInflater infalter=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dialogFragmentView=infalter.inflate(R.layout.fragment_winner_dialog,null);
        builder.setView(dialogFragmentView);
        this.setCancelable(false);
        wireUpViews();
        return builder.create();
    }

    private void wireUpViews(){
        winner=(TextView)dialogFragmentView.findViewById(R.id.winner_text);
        winner.setText(Game.getCurrentGame().getWinningTeam().getTeamName()+" has won!");
        wireUpButtons();
    }

    private void wireUpButtons(){
        newTeamButton=(Button)dialogFragmentView.findViewById(R.id.different_teams_button);
        sameTeamsButton=(Button)dialogFragmentView.findViewById(R.id.same_teams_button);

        newTeamButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Game.endGame();
                WinnerDialogFragment.this.dismiss();
                Intent intent=new Intent(WinnerDialogFragment.this.getActivity(),TeamCreateActivity.class);
                WinnerDialogFragment.this.startActivity(intent);
            }
        });

        sameTeamsButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Game.resetPoints();
                WinnerDialogFragment.this.dismiss();
                Intent intent=new Intent(WinnerDialogFragment.this.getActivity(),TeamCreateActivity.class);
                WinnerDialogFragment.this.startActivity(intent);
            }
        });

    }
}