package com.pavisalavisa.alias;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;



public class ScoreBoardFragment extends DialogFragment {
    private View dialogFragmentView;

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

    }

}
