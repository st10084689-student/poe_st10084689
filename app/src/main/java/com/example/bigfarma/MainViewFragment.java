package com.example.bigfarma;

import android.content.Intent;
import android.os.Bundle;

import androidx.compose.foundation.interaction.DragInteraction;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

import okhttp3.internal.Util;

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

    private RelativeLayout newTask,UpcomingCard;

    private boolean IsRunning;

    private Button StartButton,StopButton;

    private TextView timerTextView,TitleCardText,DateCardText;

    private ImageView categoryView, image;

    private ArrayList<Task> tasks = new ArrayList<>();
    private ArrayList<Category> categories = new ArrayList<>();

    private  Utility util ;

    private long startTime;

    private SimpleDateFormat DateFormat;

    private LayoutInflater inflater;

    private static final String TAG = "MainViewFragment";

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
        util = new Utility();
        innit(view);
        SetCardView();
        return(view);
    }
//    public void ToList(View view){
//        Intent toList = new Intent(view.getContext(), list_tasks.class);
//        startActivity(toList);
//    }

    public void innit(View view){
        Utility util = new Utility();
        tasks = util.getNewTask();
        categories = util.getAllCategories();
        categoryRecView = view.findViewById(R.id.categoryRecyclerFragView);
        newTask = view.findViewById(R.id.NewCard);
        timerTextView = view.findViewById(R.id.Timer);
        StartButton = view.findViewById(R.id.StartTime);
        StopButton = view.findViewById(R.id.EndTime);
        IsRunning = false;
        startTime = 0;
        DateCardText = view.findViewById(R.id.Date);
        TitleCardText = view.findViewById(R.id.titleCard);
        categoryView = view.findViewById(R.id.categoryImage);
        image = view.findViewById(R.id.image);
        DateFormat = new SimpleDateFormat("dd-MM-yyyy");
        UpcomingCard = view.findViewById(R.id.UpcomingCard);


        newTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), NewTask.class);
                startActivity(intent);


            }
        });

        UpcomingCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inflater = LayoutInflater.from(view.getContext());
                View layout = inflater.inflate(R.layout.toast_custom, (ViewGroup) view.findViewById(R.id.toast_custom_layout));
                TextView toastText = layout.findViewById(R.id.message);
                toastText.setText("Description:"+tasks.get(0).getDesc()+"\n" +
                        "StartDate:"+tasks.get(0).getStartDate()+"\n" +
                        "Duration:"+ tasks.get(0).getDuration()+"hours");

                Toast toast = new Toast(view.getContext());
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();

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

        ArrayList<Category> categories = new ArrayList<>();
        categories = util.getAllCategories();
        adapter.setCategories(categories,tasks);
    }


    public void SetCardView(){

        Collections.sort(tasks, new Comparator<Task>() {

            public int compare(Task task1, Task task2) {
                return task2.getEndDate().compareTo(task1.getEndDate());
            }
        });


        Task highestTask = tasks.get(0);
        String date = DateFormat.format(highestTask.getEndDate());
        Log.d(TAG, "SetCardView: "+ highestTask.getTitle());
        Log.d(TAG, "SetCardView: "+ highestTask.getEndDate());
        DateCardText.setText(date);
                TitleCardText.setText(highestTask.getTitle());

        Glide.with(MainViewFragment.this).asBitmap()
                .load(highestTask.getImageUrl())
                .centerCrop()
                .into(image);
        int categoryNumber = highestTask.getCategoryId();
        Glide.with(MainViewFragment.this).asBitmap()
                .load(categories.get(categoryNumber).getImageUrl())
                .centerCrop()
                .into(categoryView);


    }


    private void startTimer() {
        IsRunning = true;
        if(startTime==0){
            startTime = System.currentTimeMillis();
        }

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