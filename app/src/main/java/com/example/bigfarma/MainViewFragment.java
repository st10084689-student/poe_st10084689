package com.example.bigfarma;

import android.content.Intent;
import android.os.Bundle;

import androidx.compose.foundation.interaction.DragInteraction;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class MainViewFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //TODO add category icons

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView categoryRecView;

    private RelativeLayout newTask;

    private boolean IsRunning;

    private Button StartButton,StopButton;

    private TextView timerTextView;

    private long startTime;

    public MainViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainViewFragment newInstance(String param1, String param2) {
        MainViewFragment fragment = new MainViewFragment();
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
        View view = inflater.inflate(R.layout.fragment_main_view, container, false);
        innit(view);
        return(view);
    }
//    public void ToList(View view){
//        Intent toList = new Intent(view.getContext(), list_tasks.class);
//        startActivity(toList);
//    }

    public void innit(View view){
        categoryRecView = view.findViewById(R.id.categoryRecyclerFragView);
        newTask = view.findViewById(R.id.NewCard);
        timerTextView = view.findViewById(R.id.Timer);
        StartButton = view.findViewById(R.id.StartTime);
        StopButton = view.findViewById(R.id.EndTime);
        IsRunning = false;
        startTime = 0;


        newTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), NewTask.class);
                startActivity(intent);


            }
        });

        StartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!IsRunning) {
                    startTimer();
                }
            }
        });
        StopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (IsRunning) {
                    stopTimer();
                }
            }
        });
        timerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTime=0;
                timerTextView.setText("00:00:000");
            }
        });

        CategoryRecyclerAdapter adapter = new CategoryRecyclerAdapter(view.getContext());

        categoryRecView.setAdapter(adapter);
        categoryRecView.setLayoutManager(new LinearLayoutManager(view.getContext(),categoryRecView.HORIZONTAL,false));
        Utility util = new Utility();
        ArrayList<Category> categories = new ArrayList<>();
        categories = util.getAllCategories();
        adapter.setCategories(categories);
    }



    private void startTimer() {
        IsRunning = true;
        startTime = System.currentTimeMillis();

        // Start updating the UI
        updateTimerUI();
    }

    private void stopTimer() {
        IsRunning = false;
    }

    private void updateTimerUI() {


        // Calculate the elapsed time
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTime;

        // Convert the elapsed time to minutes, seconds, and milliseconds
        int minutes = (int) (elapsedTime / 60000);
        int seconds = (int) (elapsedTime % 60000 / 1000);
        int milliseconds = (int) (elapsedTime % 1000);

        // Update the timer TextView
        String timeString = String.format(Locale.getDefault(), "%02d:%02d:%03d", minutes, seconds, milliseconds);
        timerTextView.setText(timeString);

        // If the timer is still running, schedule the next UI update after a small delay
        if (IsRunning) {
            timerTextView.postDelayed(this::updateTimerUI, 10);
        }

    }


}