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

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.ViewHolder> {
    private static final String TAG = "CategoryRecyclerAdapter";

    private ArrayList<Category> categories = new ArrayList<>();
    private Context context;

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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");
        holder.CategoryText.setText(categories.get(position).getName());
        holder.CategoryText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"hallo ", Toast.LENGTH_SHORT).show();
            }
        });

        Glide.with(context)
                .asBitmap().load(categories.get(position).getImageUrl())
                        .into(holder.CategoryImage);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView CategoryImage;

        private TextView CategoryText;

        public ViewHolder(View itemView){
            super(itemView);

            CategoryImage = itemView.findViewById(R.id.categoryImage);
            CategoryText = itemView.findViewById(R.id.categoryName);
        }

    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }
}
