package com.example.talkyourself.MentalHealthBlog;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.talkyourself.R;
import java.util.ArrayList;


public class MHealthBlogAdapter extends RecyclerView.Adapter<MHealthBlogAdapter.MyViewHolder> {

    Context context;
    ArrayList<mHealthBlogModel> model;

    public MHealthBlogAdapter(Context c, ArrayList<mHealthBlogModel> m) {
        context = c;
        model = m;
    }

    @NonNull
    @Override
    public MHealthBlogAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MHealthBlogAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.mental_health_blog_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MHealthBlogAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int pos) {

        holder.newsTitle.setText("☉ "+ model.get(pos).getTitle());

        String title = model.get(pos).title;
        String blog = model.get(pos).blog;

        holder.newsTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), BlogDetails.class);
                i.putExtra("title",  model.get(pos).getTitle());
                view.getContext().startActivity(i);
                i.putExtra("blog", model.get(pos).getBlog());
                view.getContext().startActivity(i);

            }
        });


    }


    @Override
    public int getItemCount() {
        return model.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView newsTitle;
        public MyViewHolder(View itemView) {
            super(itemView);
            newsTitle =  itemView.findViewById(R.id.coronanews_title);


        }

    }
}