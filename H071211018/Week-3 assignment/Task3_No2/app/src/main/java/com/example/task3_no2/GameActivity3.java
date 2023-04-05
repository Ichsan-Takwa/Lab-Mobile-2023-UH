package com.example.task3_no2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity3 extends AppCompatActivity implements View.OnClickListener {
    private TextView totalSoalTv, soalTv;
    private Button opsi1Btn, opsi2Btn, opsi3Btn, opsi4Btn;

    ArrayList<Integer> totalQuiz;
    int currentQuestionIndex = 0;
    int score = 0;
    String selectedAns = "";
    int currentNum = 1;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3);

        totalQuiz = new ArrayList<>();
        totalQuiz.add(0);
        totalQuiz.add(1);
        totalQuiz.add(2);
        totalQuiz.add(3);
        totalQuiz.add(4);
        totalQuiz.add(5);
        totalQuiz.add(6);
        totalQuiz.add(7);
        totalQuiz.add(8);
        totalQuiz.add(9);

        user = getIntent().getParcelableExtra("user");

        totalSoalTv= findViewById(R.id.tv_totalSoal);
        soalTv = findViewById(R.id.tv_soal);
        opsi1Btn = findViewById(R.id.btn_opsi1);
        opsi2Btn = findViewById(R.id.btn_opsi2);
        opsi3Btn = findViewById(R.id.btn_opsi3);
        opsi4Btn = findViewById(R.id.btn_opsi4);

        opsi1Btn.setOnClickListener(view -> onClick(view));
        opsi2Btn.setOnClickListener(view -> onClick(view));
        opsi3Btn.setOnClickListener(view -> onClick(view));
        opsi4Btn.setOnClickListener(view -> onClick(view));

        totalSoalTv.setText("Question : "+currentNum + " of "+ 5  );
        
        loadNewQuestion();

    }

    private void loadNewQuestion() {
        totalSoalTv.setText("Question : "+currentNum + " of "+ 5  );
        Random rnd = new Random();
        int random = rnd.nextInt(totalQuiz.size());

        currentQuestionIndex = totalQuiz.get(random);

        if (currentNum < totalQuiz.size()) {
            opsi1Btn.setBackgroundColor(getResources().getColor(R.color.asli));
            opsi2Btn.setBackgroundColor(getResources().getColor(R.color.asli));
            opsi3Btn.setBackgroundColor(getResources().getColor(R.color.asli));
            opsi4Btn.setBackgroundColor(getResources().getColor(R.color.asli));


            soalTv.setText(Quiz.question[currentQuestionIndex]);
            opsi1Btn.setText(Quiz.option[currentQuestionIndex][0]);
            opsi2Btn.setText(Quiz.option[currentQuestionIndex][1]);
            opsi3Btn.setText(Quiz.option[currentQuestionIndex][2]);
            opsi4Btn.setText(Quiz.option[currentQuestionIndex][3]);
        }else {

        }

        totalQuiz.remove(random);

    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        String[] correctAns = Quiz.correctAns;
        selectedAns = ((Button) view).getText().toString().trim();


        if (selectedAns.equalsIgnoreCase(Quiz.correctAns[currentQuestionIndex])) {
            button.setBackgroundColor(getResources().getColor(R.color.benar));
            score += Quiz.score[currentQuestionIndex];
        }else {
            button.setBackgroundColor(getResources().getColor(R.color.salah));
        }

        currentNum++;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (currentNum <=5) {
                    currentQuestionIndex++;
                    loadNewQuestion();
                }else {
                    Intent intent = new Intent(GameActivity3.this, HasilActivity4.class);
                    intent.putExtra("user", user);
                    intent.putExtra("score", score);
                    startActivity(intent);
                }

            }
        }, 1000);

    }
}