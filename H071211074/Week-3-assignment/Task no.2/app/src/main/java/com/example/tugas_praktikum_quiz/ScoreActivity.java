package com.example.tugas_praktikum_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.tugas_praktikum_quiz.databinding.ActivityScoreBinding;

public class ScoreActivity extends AppCompatActivity {

    private ActivityScoreBinding binding;
    User user;
    User dataUser;
    public final static String DATA_USER = "data user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dataUser = getIntent().getParcelableExtra(DATA_USER);

        binding.tvScoreNumNow.setText(String.valueOf(dataUser.score));
        if (dataUser.score > dataUser.bestScore) {
            dataUser.bestScore = dataUser.score;
        }
        binding.tvBestScoreNum.setText(String.valueOf(dataUser.bestScore));
        binding.tvDesc.setText("GGWP " + dataUser.name + "!!");
        binding.btnHome.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.putExtra(MainActivity.DATA_USER, dataUser);
            startActivity(intent);
        });
    }
}