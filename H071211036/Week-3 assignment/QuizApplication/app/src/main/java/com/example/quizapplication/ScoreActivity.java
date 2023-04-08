package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    public static int correctAnswers = 0;
    public static int score = 0;

    private TextView score1, bestscore;

    private Button button;

    private String st1,st2;

    private Bundle bundle;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        score1 = findViewById(R.id.score1);
        bestscore = findViewById(R.id.bestscore);
        button = findViewById(R.id.btn_rtn);
        st1 = getIntent().getExtras().getString("text1");
        st2 = getIntent().getExtras().getString("text2");
        bundle = getIntent().getExtras().getBundle("image");


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent v = new Intent(ScoreActivity.this, Welcome.class);

                v.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);

                v.putExtra("text1",st1);
                v.putExtra("text2",st2);
                v.putExtra("image",bundle);
                v.putExtra("score", (CharSequence) bestscore);
                startActivity(v);
                finish();
            }
        });

        int score = getIntent().getIntExtra("Score", 0);
        score1.setText(String.valueOf(score));
        saveScore(score);

        SharedPreferences prefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
        int bestScore = prefs.getInt("bestScore", 0);
        bestscore.setText(String.valueOf(bestScore));
    }

    private void saveScore(int score) {
        SharedPreferences prefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
        int bestScore = prefs.getInt("bestScore", 0);
        if (score > bestScore) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("bestScore", score);
            editor.apply();
        }
    }

}
