package com.pavisalavisa.alias;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.app.DialogFragment;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class GameStartFragment extends DialogFragment {
    private View dialogFragmentView;

    public GameStartFragment() {
        // Required empty public constructor
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        Context context=builder.getContext();
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dialogFragmentView=inflater.inflate(R.layout.fragment_game_start,null);
        builder.setView(dialogFragmentView);
        wireUpViews();
        this.setCancelable(false);
        return builder.create();
    }
    private void wireUpViews(){
        wireUpButtons();
        wireUpTexts();
    }
    private void wireUpButtons(){
        Button goButton=(Button)dialogFragmentView.findViewById(R.id.go_button);
        goButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                GameStartFragment.this.dismiss();
                notifyParent();
            }
        });
    }

    private void wireUpTexts(){
        Team currentTeam=Game.getCurrentGame().getCurrentTeamPlaying();
        TextView team=(TextView)dialogFragmentView.findViewById(R.id.team_name_text_view);
        TextView readingPlayer=(TextView)dialogFragmentView.findViewById(R.id.current_player_reading);
        TextView guessingPlayer=(TextView)dialogFragmentView.findViewById(R.id.current_player_guessing);

        team.setText(currentTeam.getTeamName());
        team.append(" "+currentTeam.getPoints());
        readingPlayer.setText(currentTeam.getPlayerReading());
        guessingPlayer.setText(currentTeam.getPlayerGuessing());
    }

    private void notifyParent(){
        ( (gameActivity)this.getActivity()).startRound();
    }

}
