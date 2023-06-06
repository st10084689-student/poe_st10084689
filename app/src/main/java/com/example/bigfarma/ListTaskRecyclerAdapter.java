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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.platform.ConscryptPlatform;

public class ListTaskRecyclerAdapter extends RecyclerView.Adapter<ListTaskRecyclerAdapter.ViewHolder> {
    private static final String TAG = "ListTaskRecyclerAdapter";

    private ArrayList<Task> tasks = new ArrayList<>();
    private Context context;

    private LayoutInflater inflater ;


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
    public void onBindViewHolder(@NonNull ViewHolder holder,final int position) {
        Log.d(TAG, "onBindViewHolder: called");
        SimpleDateFormat DateFormate = new SimpleDateFormat("dd-MM-yyyy");
        String DateString = DateFormate.format(tasks.get(position).getEndDate());
        holder.Name.setText(tasks.get(position).getTitle());
        String imagepath= tasks.get(position).getImageUrl();
        holder.Date.setText(DateString);
        holder.Time.setText(tasks.get(position).getTime());

        holder.relativelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomToast("Description:"+tasks.get(position).getDesc()+"\n" +
                        "StartDate:"+tasks.get(position).getStartDate()+"\n" +
                        "Duration:"+ tasks.get(position).getDuration(),view);
            }
        });



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
            context= itemView.getContext();

            BackgroundImage = itemView.findViewById(R.id.Image);
            Name = itemView.findViewById(R.id.TaskName);
            Date = itemView.findViewById(R.id.DateTxt);
            Time = itemView.findViewById(R.id.TimeTxt);
            CategoryImage = itemView.findViewById(R.id.CategoryImage);
            relativelayout = itemView.findViewById(R.id.relativeCard);


        }

    }

    private void showCustomToast( String message, View view) {
        inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.toast_custom, (ViewGroup) view.findViewById(R.id.toast_custom_layout));

        TextView toastText = layout.findViewById(R.id.message);
        toastText.setText(message);

        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    public void setTasks(ArrayList<Task> tasks,ArrayList<Category> categories) {
        this.tasks = tasks;
        this.categories= categories;
        notifyDataSetChanged();
    }
}
