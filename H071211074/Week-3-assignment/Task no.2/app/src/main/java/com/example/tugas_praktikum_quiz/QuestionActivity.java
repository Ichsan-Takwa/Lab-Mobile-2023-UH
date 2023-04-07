package com.example.tugas_praktikum_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tugas_praktikum_quiz.databinding.ActivityQuestionBinding;

import java.util.ArrayList;
import java.util.Arrays;

public class QuestionActivity extends AppCompatActivity {

    private ActivityQuestionBinding binding;
    int score, currentNum, currentQuestionIndex;
    String selectedAnswer;
    ArrayList<Integer> angka;
    User dataUser;
    public final static String DATA_USER = "data user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        score = 0;
        currentNum = 1;
        currentQuestionIndex = 0;
        selectedAnswer = "";

        dataUser = getIntent().getParcelableExtra(DATA_USER);

        angka = new ArrayList<>();
        angka.add(0);
        angka.add(1);
        angka.add(2);
        angka.add(3);
        angka.add(4);
        angka.add(5);
        angka.add(6);
        angka.add(7);
        angka.add(8);
        angka.add(9);

        binding.btnOpsi1.setOnClickListener(view -> onClick(view));
        binding.btnOpsi2.setOnClickListener(view -> onClick(view));
        binding.btnOpsi3.setOnClickListener(view -> onClick(view));
        binding.btnOpsi4.setOnClickListener(view -> onClick(view));

        loadNewQuestion();
        binding.tvTotalQuestion.setText("Quiz ke " + currentNum + " dari " + 5);
    }

    private void onClick(View view) {
        Button button = (Button) view;
        selectedAnswer = button.getText().toString();

        if(selectedAnswer.equals(Quiz.correctAnswers[currentQuestionIndex])) {
            button.setBackgroundColor(Color.GREEN);
            score += Quiz.score[currentQuestionIndex];
        } else {
            button.setBackgroundColor(Color.RED);
        }

        dataUser.score = score;
        currentQuestionIndex++;

        binding.tvTotalQuestion.setText("Quiz ke " + currentNum + " dari " + 5);
        currentNum++;
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            button.setBackgroundColor(Color.BLACK);
            if (currentNum <= 5) {
                loadNewQuestion();
            } else {
                Intent intent = new Intent(this, ScoreActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra(ScoreActivity.DATA_USER, dataUser);
                startActivity(intent);
            }
        }, 1000);
    }
    void loadNewQuestion() {
        int rand = (int) (Math.random() * angka.size());

        currentQuestionIndex = angka.get(rand);

        binding.tvQuestion.setText(Quiz.question[currentQuestionIndex]);
        binding.btnOpsi1.setText(Quiz.choices[currentQuestionIndex][0]);
        binding.btnOpsi2.setText(Quiz.choices[currentQuestionIndex][1]);
        binding.btnOpsi3.setText(Quiz.choices[currentQuestionIndex][2]);
        binding.btnOpsi4.setText(Quiz.choices[currentQuestionIndex][3]);
        angka.remove(rand);
    }
}









