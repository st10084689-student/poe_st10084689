package com.example.bigfarma;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListTaskRecyclerAdapter extends RecyclerView.Adapter<ListTaskRecyclerAdapter.ViewHolder> {
    private static final String TAG = "ListTaskRecyclerAdapter";

    private ArrayList<Task> tasks = new ArrayList<>();
    private Context context;

    private ArrayList<Category> categories = new ArrayList<>();

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
        String imagepath= tasks.get(position).getImageUrl();
        holder.Date.setText(tasks.get(position).getEndDate());
        holder.Time.setText(tasks.get(position).getTime());

        File file = new File(imagepath);
        if (imagepath != null && !imagepath.isEmpty()&&tasks.get(position).getId()>3) {
            //TODO Fix picosso method
            holder.BackgroundImage.setBackground(null);
            Log.d(TAG, "onBindViewHolder: "+imagepath);
            Picasso.get().load(file).into(holder.BackgroundImage);

        }
        else{

                    Glide.with(context).asBitmap()
               .load(tasks.get(position).getImageUrl())
                            .centerCrop()
                .into(holder.BackgroundImage);
        }


        int pos = tasks.get(position).getCategoryId();


        Glide.with(context).asBitmap()
                .load(categories.get(pos).getImageUrl())
                .centerCrop()
                .into(holder.CategoryImage);

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

        private ImageView CategoryImage;

        private RelativeLayout relativelayout;

        public ViewHolder(View itemView){
            super(itemView);

            BackgroundImage = itemView.findViewById(R.id.Image);
            Name = itemView.findViewById(R.id.TaskName);
            Date = itemView.findViewById(R.id.DateTxt);
            Time = itemView.findViewById(R.id.TimeTxt);
            CategoryImage = itemView.findViewById(R.id.CategoryImage);
            relativelayout = itemView.findViewById(R.id.relativeCard);

        }

    }

    public void setTasks(ArrayList<Task> tasks,ArrayList<Category> categories) {
        this.tasks = tasks;
        this.categories= categories;
        notifyDataSetChanged();
    }
}
