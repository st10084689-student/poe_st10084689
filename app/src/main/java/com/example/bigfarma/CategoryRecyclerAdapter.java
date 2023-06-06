package com.example.bigfarma;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.ViewHolder> {
    private static final String TAG = "CategoryRecyclerAdapter";

    private ArrayList<Category> categories = new ArrayList<>();

    private ArrayList<Task> tasks = new ArrayList<>();
    private Context context;

    private LayoutInflater inflater ;

    public CategoryRecyclerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_category_rec_view, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");

        holder.CategoryText.setText(categories.get(position).getName());

        getCategoryDuration();

        holder.categoryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomToast("Duration:"+ categories.get(position).getTotalDuration(),view);

            }
        });

        Glide.with(context)
                .asBitmap().load(categories.get(position).getImageUrl())
                        .into(holder.CategoryImage);


        holder.CategoryImage.setBackgroundResource(categories.get(position).getImageBackgroundUrl());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView CategoryImage;

        private TextView CategoryText;

        private CardView categoryCard;

        public ViewHolder(View itemView){
            super(itemView);

            CategoryImage = itemView.findViewById(R.id.backgroundImage);
            CategoryText = itemView.findViewById(R.id.categoryName);
            categoryCard = itemView.findViewById(R.id.CategoryCard);
        }

    }

    public void setCategories(ArrayList<Category> categories,ArrayList<Task> tasks) {
        this.categories = categories;
        this.tasks = tasks;
        notifyDataSetChanged();
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

    public void getCategoryDuration(){
        for (Category category : categories) {
            int categoryId = category.getId();
            int totalDuration = 0;
            Log.d(TAG, "categoryId"+categoryId);


            for (Task task : tasks) {
                Log.d(TAG, "task.getCategoryId() :"+task.getCategoryId() );
                if (task.getCategoryId() == categoryId) {
                    totalDuration += task.getDuration();
                    Log.d(TAG, "getCategoryDuration: "+ totalDuration+"id:"+task.getCategoryId());
                }
            }

            category.setTotalDuration(totalDuration);
        }
    }
}
