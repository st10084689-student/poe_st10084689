package com.example.bigfarma;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListTaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListTaskFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    RecyclerView TaskRecycler;
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListTaskFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListTaskFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListTaskFragment newInstance(String param1, String param2) {
        ListTaskFragment fragment = new ListTaskFragment();
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
        View view = inflater.inflate(R.layout.fragment_list_task, container, false);
        innit(view);
        blah();
        return view;
    }
    public void innit(View view){
        TaskRecycler = view.findViewById(R.id.taskRecyclerView);
    }

    public void blah(){

        ListTaskRecyclerAdapter adapter = new ListTaskRecyclerAdapter(requireContext());

        TaskRecycler.setAdapter(adapter);
        TaskRecycler.setLayoutManager(new LinearLayoutManager(requireContext(),TaskRecycler.VERTICAL,false));

//         ArrayList<Category> categories = new ArrayList<>();
//         categories.add(new Category("HomeWork","https://www.bing.com/ck/a?!&&p=cb7ddccb76127e66JmltdHM9MTY4NTQwNDgwMCZpZ3VpZD0xMjU5ZDZiZS1mMTk1LTY3YTAtMmJmYS1jNDc0ZjA2ODY2ZTMmaW5zaWQ9NTUyNQ&ptn=3&hsh=3&fclid=1259d6be-f195-67a0-2bfa-c474f06866e3&u=a1L2ltYWdlcy9zZWFyY2g_cT1JTUFHRVMgT0YgSE9NRVdPUksmRk9STT1JUUZSQkEmaWQ9ODIxRjUyNjAwQ0E5NDlCRTZFQkQ0OEQ4RDQ1RkE1QkFFMTFGQzdCRA&ntb=1"));
//        categories.add(new Category("WeekEnd","https://www.google.com/imgres?imgurl=https%3A%2F%2Fectutoring.com%2Fwp-content%2Fuploads%2F2022%2F02%2FADHD-Homework-Help-at-ectutoring.com_.png&tbnid=iENCQNbdZJF5TM&vet=12ahUKEwiC67jz_Zz_AhW9pycCHXbYD0oQMygCegUIARDkAQ..i&imgrefurl=https%3A%2F%2Fectutoring.com%2Fadhd-homework&docid=gELNzoII70chwM&w=1200&h=628&q=homework&ved=2ahUKEwiC67jz_Zz_AhW9pycCHXbYD0oQMygCegUIARDkAQ"));
//        categories.add(new Category("It Job ","C:\\Users\\jelid\\OneDrive\\Pictures\\Saved Pictures\\anime-graffiti-cars-lime-green-nsx.webp"));
//        categories.add(new Category("HomeWork","C:\\Users\\jelid\\OneDrive\\Pictures\\Saved Pictures\\anime-graffiti-cars-lime-green-nsx.webp"));

        Utility util = new Utility();
        ArrayList<Task> tasks = new ArrayList<>();
        tasks = util.getNewTask();
        adapter.setTasks(tasks);
    }
}