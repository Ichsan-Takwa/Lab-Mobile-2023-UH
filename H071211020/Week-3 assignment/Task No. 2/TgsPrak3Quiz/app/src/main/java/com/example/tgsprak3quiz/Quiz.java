package com.example.tgsprak3quiz;

import java.util.ArrayList;
import java.util.Collections;

import kotlin.collections.UCollectionsKt;

public class Quiz {
    String soal;
    String[] opsi;

    int jawabanBenar;
    int bobot;

    public Quiz(String soal, String[] opsi, int jawabanBenar, int bobot) {
        this.soal = soal;
        this.opsi = opsi;
        this.jawabanBenar = jawabanBenar;
        this.bobot = bobot;
    }



     static Quiz[] getQuizSample(){

        ArrayList daftarQuiz = new ArrayList<Quiz>();

        daftarQuiz.add(new Quiz("Ada ayam lima, dikali dua. Berapa semuanya?", new String[]{"5", "2", "3", "10"}, 0, 13));
        daftarQuiz.add(new Quiz("Siapa nama presiden kedua Indonesia?", new String[]{"Soekarno", "Soeharto", "B.J. Habibie", "Megawati"}, 1, 10));
        daftarQuiz.add(new Quiz("Siapa nama presiden ketiga Indonesia?", new String[]{"Soekarno", "Soeharto", "B.J. Habibie", "Megawati"}, 2, 10));
        daftarQuiz.add(new Quiz("Siapa nama presiden keempat Indonesia?", new String[]{"Soekarno", "Soeharto", "B.J. Habibie", "GUSDUR"}, 3, 10));
        daftarQuiz.add(new Quiz("Siapa nama presiden kelima Indonesia?", new String[]{"SBY", "Soeharto", "B.J. Habibie", "Megawati"}, 3, 10));
        daftarQuiz.add(new Quiz("Siapakah Saya ?", new String[]{"Lah kok nanya", "YNKTS", "Mungkin orang", "Dahlah Skip"}, 3, 20));
        daftarQuiz.add(new Quiz("Siapakah Dia ?", new String[]{"Lah kok nanya", "YNKTS", "Mungkin orang", "Dahlah Skip"}, 2, 12));

        Collections.shuffle(daftarQuiz);
        return (Quiz[]) daftarQuiz.toArray(new Quiz[]{});


    }
    public int getJawabanBenar() {
        return jawabanBenar;
    }

    public String getPilihanBenar(int index) {
        return opsi[index];
    }

}
