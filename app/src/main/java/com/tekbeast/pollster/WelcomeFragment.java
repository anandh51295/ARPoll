package com.tekbeast.pollster;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeFragment extends Fragment {

    private TextView label;
    private Button logout;
    private Button add,vote;
    OnLogoutListener logoutListener;
    public interface OnLogoutListener{
        public void LogoutPerformed();
    }


    public WelcomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);
        label=view.findViewById(R.id.txt_name_info);
        add=view.findViewById(R.id.btn_question);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(),Question.class);
                startActivity(intent);
            }
        });
        vote=view.findViewById(R.id.btn_vote);
        vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Votes.class);
                startActivity(intent);
            }
        });

        label.setText("Welcome "+login.prefConfig.readName());
        logout=view.findViewById(R.id.bn_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            logoutListener.LogoutPerformed();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity)context;
        logoutListener=(OnLogoutListener)activity;

    }

}
