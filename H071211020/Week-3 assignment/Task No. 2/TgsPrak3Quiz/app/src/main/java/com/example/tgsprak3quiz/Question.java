package com.example.tgsprak3quiz;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tgsprak3quiz.databinding.ActivityQuestionBinding;

public class Question extends AppCompatActivity {

    private ActivityQuestionBinding binding;

    public final static String EXSTRA_USER = "exstra-user";
    int score = 0;
    int totalQuestion = 5 ;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    User user;
    Quiz[] daftarQuiz;

    Quiz quiz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        daftarQuiz = Quiz.getQuizSample();

        user = getIntent().getParcelableExtra(EXSTRA_USER);

        binding.btnA.setOnClickListener(this::onClick);
        binding.btnB.setOnClickListener(this::onClick);
        binding.btnC.setOnClickListener(this::onClick);
        binding.btnD.setOnClickListener(this::onClick);

        binding.tvTitle.setText("Total Question : " + totalQuestion);

        loadNewQuestion();

    }

    public void onClick(View view){
        Button button = (Button) view;
        selectedAnswer = button.getText().toString();
        int jawabanBenar = quiz.getJawabanBenar();

        if (selectedAnswer.equals(quiz.getPilihanBenar(jawabanBenar))){
            score += quiz.bobot;
            button.setBackgroundColor(Color.GREEN);
        }else{
            button.setBackgroundColor(Color.RED);
        }


        if( score > user.BestScore){
            user.BestScore = score;
        }

        user.Score = score;
        currentQuestionIndex++;

        button.postDelayed(() -> loadNewQuestion(), 1000);

    }

    void loadNewQuestion(){

        if (currentQuestionIndex == totalQuestion){
            Intent intent = new Intent(this, Result.class);
            intent.putExtra(Question.EXSTRA_USER,user);
            startActivity(intent);
            return;
        }

        binding.btnA.setBackgroundColor(Color.parseColor("#FF6200EE"));
        binding.btnB.setBackgroundColor(Color.parseColor("#FF6200EE"));
        binding.btnC.setBackgroundColor(Color.parseColor("#FF6200EE"));
        binding.btnD.setBackgroundColor(Color.parseColor("#FF6200EE"));

        quiz = daftarQuiz[currentQuestionIndex];
        binding.tvQuestion.setText(quiz.soal);
        binding.btnA.setText(quiz.opsi[0]);
        binding.btnB.setText(quiz.opsi[1]);
        binding.btnC.setText(quiz.opsi[2]);
        binding.btnD.setText(quiz.opsi[3]);
        binding.tvTitle1.setText("Question " + (currentQuestionIndex + 1));
    }

}