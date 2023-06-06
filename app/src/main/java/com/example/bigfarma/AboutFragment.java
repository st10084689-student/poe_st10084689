package com.example.bigfarma;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AboutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AboutFragment extends Fragment {

    private EditText MinimumDailyGoals, MaximumDailyGoals;

    private AppCompatButton MaxInsertButton,MinInsertButton;

    private Goal goals;

    private int maximum;
    private int minimum;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String TAG = "AboutFragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AboutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AboutFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AboutFragment newInstance(String param1, String param2) {
        AboutFragment fragment = new AboutFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_about, container, false);
        innit(view);
        return view;
    }

    public void innit(View view){

        MinimumDailyGoals= view.findViewById(R.id.setMinGoals);
        MaximumDailyGoals= view.findViewById(R.id.setMaxGoals);
        MaxInsertButton= view.findViewById(R.id.insertMaxButton);
        MinInsertButton= view.findViewById(R.id.insertMinButton);
        goals = new Goal();


        MaxInsertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maximum = Integer.parseInt(MaximumDailyGoals.getText().toString());
                Log.d(TAG, "onClick: "+maximum);
                if(maximum>=minimum||maximum==0) {
                    goals.setMaximumGoal(maximum);
                    Toast.makeText(view.getContext(),"Coolbeans\uD83E\uDED8",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(view.getContext(),"The maximum cant be less than the minimum",Toast.LENGTH_SHORT).show();
                }
            }
        });
        MinInsertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ");
                minimum = Integer.parseInt(MinimumDailyGoals.getText().toString());
                if(minimum<=maximum) {
                    goals.setMinimumGoal(minimum);
                    Toast.makeText(view.getContext(),"Coolbeans\uD83E\uDED8" ,Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(view.getContext(),"The maximum cant be less than the minimum",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}