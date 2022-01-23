package com.example.talkyourself;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    TextView Start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Start = findViewById(R.id.start);

        Animation myFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Start.startAnimation(myFadeInAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mIntent = new Intent(StartActivity.this, LoginActivity.class);
                startActivity(mIntent);
                finish();
            }
        }, 3000);
    }

}
