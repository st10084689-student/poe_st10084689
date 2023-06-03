package com.example.bigfarma;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ListTaskRecyclerAdapter extends RecyclerView.Adapter<ListTaskRecyclerAdapter.ViewHolder> {
    private static final String TAG = "ListTaskRecyclerAdapter";

    private ArrayList<Task> tasks = new ArrayList<>();
    private Context context;

    public ListTaskRecyclerAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public ListTaskRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_lists_item, parent, false);
        ListTaskRecyclerAdapter.ViewHolder holder = new ListTaskRecyclerAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");
        holder.Name.setText(tasks.get(position).getTitle());

        Glide.with(context).asBitmap()
               .load(tasks.get(position).getImageUrl())
                .into(holder.BackgroundImage);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView BackgroundImage;

        private TextView Name;

        private TextView Date;

        private TextView Time;

        private ImageView Category_Image;

        public ViewHolder(View itemView){
            super(itemView);

            BackgroundImage = itemView.findViewById(R.id.Image);
            Name = itemView.findViewById(R.id.TaskName);
            Date = itemView.findViewById(R.id.DateTxt);
            Time = itemView.findViewById(R.id.TimeTxt);
            Category_Image = itemView.findViewById(R.id.CategoryImage);
        }

    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }
}
