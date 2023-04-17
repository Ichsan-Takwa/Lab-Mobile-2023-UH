package com.example.task3_no2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HasilActivity4 extends AppCompatActivity {
    public static final String EXTRA_USER = "Extra User Instance" ;
    private TextView gameOver, score, bestScore;
    private Button back;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil4);

        gameOver = findViewById(R.id.tv_gg);
        score = findViewById(R.id.tv_hasilScore);
        bestScore = findViewById(R.id.tv_bestScore);
        back = findViewById(R.id.btn_back);

        int hasilScore = getIntent().getIntExtra("score", 0);
        score.setText(hasilScore + "");


        user = getIntent().getParcelableExtra("user");
        gameOver.setText("GGWP "+ User.name);

        if (hasilScore > User.bestScore) {
            User.bestScore = hasilScore;
        }

        bestScore.setText(User.bestScore + "");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HasilActivity4.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
}