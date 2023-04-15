package com.example.praktikum32;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class score extends AppCompatActivity {
    TextView highScore, user;
    Button start;
    ImageView profilePic;
    private final String EXTRA_PROFILE_PICTURE = "EXTRA_PROFILE_PICTURE";
    private final String EXTRA_USERNAME = "EXTRA_USERNAME";
    private final String EXTRA_SCORE = "EXTRA_SCORE";
    private final String EXTRA_HIGHSCORE = "EXTRA_HIGHSCORE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        highScore = findViewById(R.id.highScore);
        user = findViewById(R.id.username);
        start = findViewById(R.id.start);
        profilePic = findViewById(R.id.profilePic);

        Intent intent = getIntent();
        String profileUriString = intent.getStringExtra(EXTRA_PROFILE_PICTURE);
        String user_name = intent.getStringExtra(EXTRA_USERNAME);
        String total_score = intent.getStringExtra(EXTRA_SCORE);
        String bestScore = intent.getStringExtra(EXTRA_HIGHSCORE);
        Uri profile_picture = Uri.parse(profileUriString);
        String topScore = bestScore;

        if (!total_score.equals("")) {
            Toast.makeText(this, total_score + " " + bestScore + " " + topScore, Toast.LENGTH_SHORT).show();
            if (Integer.valueOf(total_score) > Integer.valueOf(topScore)) {
                topScore = total_score;
                highScore.setText(total_score);
            } else {
                highScore.setText(topScore);
            }
        }

        profilePic.setImageURI(profile_picture);
        user.setText(user_name);

        String finalTopScore = topScore;
        start.setOnClickListener(view -> {
            Intent quizIntent = new Intent(score.this, quiz.class);
            quizIntent.putExtra(EXTRA_USERNAME, user_name);
            quizIntent.putExtra(EXTRA_PROFILE_PICTURE, profile_picture.toString());
            quizIntent.putExtra(EXTRA_HIGHSCORE, finalTopScore);
            startActivity(quizIntent);
        });
    }
}