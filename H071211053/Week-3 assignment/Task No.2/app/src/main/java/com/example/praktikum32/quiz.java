package com.example.praktikum32;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class quiz extends AppCompatActivity implements View.OnClickListener {
    int totalscore = 0;
    private final String EXTRA_PROFILE_PICTURE = "EXTRA_PROFILE_PICTURE";
    private final String EXTRA_USERNAME = "EXTRA_USERNAME";
    private final String EXTRA_SCORE = "EXTRA_SCORE";
    private final String EXTRA_HIGHSCORE = "EXTRA_HIGHSCORE";
    TextView quizNumber, question;
    Button answer1, answer2, answer3 , answer4;
    String[][] soal = {
            {"Berapakah 1+1", "2", "1", "3", "4", "100"},
            {"Yang manakah jawaban yang benar", "Yang ini", "Bukan yang ini", "Sepertinya yang ini", "Mungkin saja yang ini", "100"},
            {"Siapa diantara karakter berikut yang bukan dari anggota Hokago Tea Time", "Hirasawa Ui", "Hirasawa Yui", "Kotobuki Tsumugi", "Tainaka Ritsu", "100"},
            {"Pada awal Non-Non Biyori Berapakah Umur Renge", "6", "5", "7", "8", "100"},
            {"Nilai apa yang harus asisten berikan kepada saya?", "A", "B", "C", "D", "100"},
            {"Kreator One Piece atau nama mangaka dari serial One Piece adalah", "Eiichiro Oda", "Oda Nobunaga", "Goku", "Buggy D Star Clown", "100"},
            {"BEST ANIME OF ALL TIME", "Swort Art Online (SAO)", "No Game No Life (NGNL)", "FUllmetal Alchemist : Brotherhood (FMAB)", "Gun Gale Online (GGO)", "100"},
            {"Penggunaan CGI terburuk didapatkan oleh anime", "EX-Arm", "Fate/Zero", "Chainsawman", "Black Summoner", "100"},
            {"Siapakah diantara floor guardian Ainz Ooal Gown yang paling kuat", "Shalltear Bloodfallen", "Albedo", "Mare Bello Fiore", "Rubedo", "100"},
            {"Nama Bankai dari Yamamoto Genryusai ", "Zanka No Tachi", "Katen Kyōkotsu: Karamatsu Shinjū", "Kannonbiraki Benihime Aratame", "Daiguren Hyōrinmaru", "100"}
    };
    int[] soal_terpilih = generateRandomNumbers(5, 0, 9);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        question = findViewById(R.id.question);
        quizNumber = findViewById(R.id.quizNumber);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);

        answer1.setOnClickListener(this);
        answer2.setOnClickListener(this);
        answer3.setOnClickListener(this);
        answer4.setOnClickListener(this);

        int[] jawaban_acak = generateRandomNumbers(4, 1, 4);
        question.setText(soal[soal_terpilih[0]][0]);
        answer1.setText(soal[soal_terpilih[0]][jawaban_acak[0]]);
        answer2.setText(soal[soal_terpilih[0]][jawaban_acak[1]]);
        answer3.setText(soal[soal_terpilih[0]][jawaban_acak[2]]);
        answer4.setText(soal[soal_terpilih[0]][jawaban_acak[3]]);

    }

    public static int[] generateRandomNumbers(int count, int min, int max) {
        Random random = new Random();
        Set<Integer> generated = new HashSet<>();
        int[] randomNumbers = new int[count];
        for (int i = 0; i < count; i++) {
            int number;
            do {
                number = random.nextInt((max - min) + 1) + min;
            } while (generated.contains(number));
            randomNumbers[i] = number;
            generated.add(number);
        }
        return randomNumbers;
    }

    @Override
    public void onClick(View view) {
        int i = Integer.parseInt(quizNumber.getText().toString());
        int[] jawaban_acak = generateRandomNumbers(4, 1, 4);
        String kunci_jawaban = soal[soal_terpilih[i-1]][1];
        if (i == 5) {
            switch (view.getId()) {
                case R.id.answer1:
                    if (answer1.getText().toString().equals(kunci_jawaban)) {
                        totalscore += Integer.valueOf(soal[soal_terpilih[i-1]][5]);
                    }

                    break;
                case R.id.answer2:
                    if (answer2.getText().toString().equals(kunci_jawaban)) {
                        totalscore += Integer.valueOf(soal[soal_terpilih[i-1]][5]);
                    }
                    break;
                case R.id.answer3:
                    if (answer3.getText().toString().equals(kunci_jawaban)) {
                        totalscore += Integer.valueOf(soal[soal_terpilih[i-1]][5]);
                    }
                    break;
                case R.id.answer4:
                    if (answer4.getText().toString().equals(kunci_jawaban)) {
                        totalscore += Integer.valueOf(soal[soal_terpilih[i-1]][5]);
                    }
                    break;
            }

            Intent intent = getIntent();
            String profileUriString = intent.getStringExtra(EXTRA_PROFILE_PICTURE);
            String user_name = intent.getStringExtra(EXTRA_USERNAME);
            String topScore = intent.getStringExtra(EXTRA_HIGHSCORE);
            Uri profile_picture = Uri.parse(profileUriString);
            Intent scoreIntent = new Intent(quiz.this, score.class);
            scoreIntent.putExtra(EXTRA_USERNAME, user_name);
            scoreIntent.putExtra(EXTRA_PROFILE_PICTURE, profile_picture.toString());
            scoreIntent.putExtra(EXTRA_SCORE, String.valueOf(totalscore));
            scoreIntent.putExtra(EXTRA_HIGHSCORE, topScore);
            Toast.makeText(this, topScore, Toast.LENGTH_SHORT).show();
            startActivity(scoreIntent);

        } else {
            switch (view.getId()) {
                case R.id.answer1:
                    if (answer1.getText().toString().equals(kunci_jawaban)) {
                        totalscore += Integer.valueOf(soal[soal_terpilih[i]][5]);
                    }
                    question.setText(soal[soal_terpilih[i]][0]);
                    answer1.setText(soal[soal_terpilih[i]][jawaban_acak[0]]);
                    answer2.setText(soal[soal_terpilih[i]][jawaban_acak[1]]);
                    answer3.setText(soal[soal_terpilih[i]][jawaban_acak[2]]);
                    answer4.setText(soal[soal_terpilih[i]][jawaban_acak[3]]);
                    quizNumber.setText(String.valueOf(i+1));
                    break;
                case R.id.answer2:
                    if (answer2.getText().toString().equals(kunci_jawaban)) {
                        totalscore += Integer.valueOf(soal[soal_terpilih[i]][5]);
                    }
                    question.setText(soal[soal_terpilih[i]][0]);
                    answer1.setText(soal[soal_terpilih[i]][jawaban_acak[0]]);
                    answer2.setText(soal[soal_terpilih[i]][jawaban_acak[1]]);
                    answer3.setText(soal[soal_terpilih[i]][jawaban_acak[2]]);
                    answer4.setText(soal[soal_terpilih[i]][jawaban_acak[3]]);
                    quizNumber.setText(String.valueOf(i+1));
                    break;
                case R.id.answer3:
                    if (answer3.getText().toString().equals(kunci_jawaban)) {
                        totalscore += Integer.valueOf(soal[soal_terpilih[i]][5]);
                    }
                    question.setText(soal[soal_terpilih[i]][0]);
                    answer1.setText(soal[soal_terpilih[i]][jawaban_acak[0]]);
                    answer2.setText(soal[soal_terpilih[i]][jawaban_acak[1]]);
                    answer3.setText(soal[soal_terpilih[i]][jawaban_acak[2]]);
                    answer4.setText(soal[soal_terpilih[i]][jawaban_acak[3]]);
                    quizNumber.setText(String.valueOf(i+1));
                    break;
                case R.id.answer4:

                    if (answer4.getText().toString().equals(kunci_jawaban)) {
                        totalscore += Integer.valueOf(soal[soal_terpilih[i]][5]);
                    }
                    question.setText(soal[soal_terpilih[i]][0]);
                    answer1.setText(soal[soal_terpilih[i]][jawaban_acak[0]]);
                    answer2.setText(soal[soal_terpilih[i]][jawaban_acak[1]]);
                    answer3.setText(soal[soal_terpilih[i]][jawaban_acak[2]]);
                    answer4.setText(soal[soal_terpilih[i]][jawaban_acak[3]]);

                    quizNumber.setText(String.valueOf(i+1));
                    break;
            }
        }

    }
}