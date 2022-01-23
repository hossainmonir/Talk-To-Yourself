package com.example.talkyourself.MentalHealthBlog;

import androidx.appcompat.app.AppCompatActivity;
import com.example.talkyourself.R;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class BlogDetails extends AppCompatActivity {
    TextView textTitle, textBlog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_details);

        Intent i = getIntent();
        String title = i.getStringExtra("title");
        String blog = i.getStringExtra("blog");
        textTitle = findViewById(R.id.titleview);
        textTitle.setText(title);

        textBlog = findViewById(R.id.blogview);
        textBlog.setText(blog);


    }
}