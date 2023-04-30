package com.example.task3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.task3.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    String proses;
    boolean isOperatorSet, isOperated;
    private ActivityMainBinding binding;

    //    private static String orang;
    char operator;

    // variabel dengan jangkauan satu class yang dibutuhkan
//    menyimpan operator yang digubakan | String atau char
//    mengecek apakah operator telah ditekan | boolean
//    mengecek apakah telah dilakukan operasi | boolean

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        isOperatorSet = false;
        isOperated = false;

        binding.btn0.setOnClickListener(view -> onNumberClick(view));
        binding.btn1.setOnClickListener(view -> onNumberClick(view));
        binding.btn2.setOnClickListener(view -> onNumberClick(view));
        binding.btn3.setOnClickListener(view -> onNumberClick(view));
        binding.btn4.setOnClickListener(view -> onNumberClick(view));
        binding.btn5.setOnClickListener(view -> onNumberClick(view));
        binding.btn6.setOnClickListener(view -> onNumberClick(view));
        binding.btn7.setOnClickListener(view -> onNumberClick(view));
        binding.btn8.setOnClickListener(view -> onNumberClick(view));
        binding.btn9.setOnClickListener(view -> onNumberClick(view));

        binding.btnBagi.setOnClickListener(view -> onOperatorClick(view));
        binding.btnKali.setOnClickListener(view -> onOperatorClick(view));
        binding.btnKurang.setOnClickListener(view -> onOperatorClick(view));
        binding.btnTambah.setOnClickListener(view -> onOperatorClick(view));

        binding.btnHitung.setOnClickListener(view -> onResultClick(view));

        binding.btnAc.setOnClickListener(view -> onSpecialClick(view));
        binding.btnDel.setOnClickListener(view -> onSpecialClick(view));

    }


//

        void onNumberClick(View view){
            Button num = (Button) view;
            String text = binding.numpad.getText().toString();
            //Mencegah angka berawalan 0
            // buat dua kondisi
            // jika nilai pada numpad == 0 maka ganti nilai numpad dengan nilai button yang sekarang <>
            // else :
            //      -   ambil nilai yang ada di numpad kedalam variabel [.getText()]
            //      -   tambahkan dengan nilai button yang sekarang di klik
            //      -   set nilai numpad dengan nilai hasil penggabungan  [settext()]

            if (isOperated == true){
                binding.numpad.setText("0");
                isOperated = false;
            }

            if (binding.numpad.getText().equals("0")){
                binding.numpad.setText(num.getText().toString());
            }else {
                text += num.getText().toString();
                binding.numpad.setText(text);
            }

        }

        void onOperatorClick(View view) {
            Button operatorBtn = (Button) view;
            String text = binding.numpad.getText().toString();

            if (isOperated == true){
               return;
            }

            // simpan/tambahkan nilai button ke numpad (sama seperti cara logic diatas)
            //      -   ambil nilai yang ada di numpad kedalam variabel [.getText()]
            //      -   tambahkan dengan nilai button yang sekarang di klik
            //      -   set nilai numpad dengan nilai hasil penggabungan  [settext()]

            if (isOperatorSet == true && text.charAt(text.length() - 1) == operator){
                text = text.replaceFirst("[+ x / -]", operatorBtn.getText().toString());
                binding.numpad.setText(text);

            } else if (isOperatorSet) {
                return;

            }else {
                binding.numpad.getText().toString();
                text += operatorBtn.getText().toString();
                binding.numpad.setText(text);

            }



            operator = operatorBtn.getText().toString().charAt(0);
            isOperatorSet = true;


            // Mencegah Terdapat dua operator :
//        buat boolean dgn jangkauan class untuk mengecek apakah operator sudah di klik
//         jika nilainya true (operator telah ditekan)
            //  lakukan return; untuk menghentikan proses selanjutnya

        }

         //   Menyimpan Jenis operator yang digubakan:
//        buat variable dgn jangkauan class untuk menyimpan operator yang ditekan
            // variabel berfungsi untuk mengetahui apakah operator yang dipilih adalah : + / x -



        void onSpecialClick(View view){
            Button btn = (Button) view;
            String btn_text = btn.getText().toString();
            String text = binding.numpad.getText().toString();

            if (btn_text.equals("AC")){
                binding.numpad.setText("0");
                isOperatorSet = false;
                isOperated = false;

            }else if (btn_text.equals("DEL")){ if (text.length() > 1) {
                if (text.charAt(text.length() - 1) == operator){
                    isOperatorSet = false;
                }

               text = text.substring(0,text.length() - 1);
               binding.numpad.setText(text);




                }else {
                binding.numpad.setText("0");
            }
            }



            //        buat dua kondisi
            // jika Acc
//        reset nilai numpad menjadi 0 (gunakan SetText)
            //  jika Del
//          ambil nilai numpad mulai dari awal hingga sebelum nilai terakhir    <<--- logic #211#
            // caranya
            // gunakan method <String>.substring(index awal, index akhir) (karakter pada index akhir tidak diambil)
            // contoh : "HaloIchsan".substring(0,9) --->>> "HaloIchsa"
            // simpan kembali hasil pemotongan string ke numpad

            // note : del tidak bileh menghapus angka pada numpad sampa habis
//                untuk itu, buatlah kondisi jika panjang numpad == 1, langsung set text menjadi 0
//                sedangkan panjang lebih dari satu jalankan logika #211# diatas
        }

        void onResultClick(View view){
            Button hitung = (Button) view;
            String text = binding.numpad.getText().toString();

            if (isOperated == true){
                return;
            }

            //        buat variabel untuk menyimpan hasil perhitungan yang mengambill hasil perhitungan dari method hitungString()
//        double result = hitungString(<numpadText>); // isi parameter dengan hasil gettext() dari numpad

        try {
            double result = hitungString(text);
            if (result %1 == 0){
                int resultInt = (int) result;
                binding.numpad.setText(String.valueOf(resultInt));
            }else {
                binding.numpad.setText(String.valueOf(result));
            }

            isOperated = true;
            isOperatorSet = false;
        } catch (Exception e){
            Toast.makeText(this, "Masukkan angka terlebih dahulu", Toast.LENGTH_SHORT).show();
        }


        }


        double hitungString(String perhitungan){

            //        misal operasikan 123+456 (masih dalam satu string)
//        buat variabel  (numpadValue) yang menyimpan hasil numPadTv.getText().toString();
//      buat array numbers[] untuk menyimpan hasil split (pisah)          String[] numbers = <StringNumpad>.split("[x+/-]");

//        numbers[0] =>  123
//        numbers[1] =>  456

          //  buat kondisi 4 konsisi untuk masing" operator (variabel yang punya jangkauan class)
//buat variabel <result> dengan tipe double ntuk menyimpan hasil operasi

            // kalo +
//            casting dan jmlahkann keduanya lalu simpan ke variabel result
            // kalo -
//        ...
            // kalo /
//        ...
            // kalo x
//        ....
//      jangan lupa ubah nilai boolean yang mengecek "apakah operator telah ditekan" menjadi false kembali
//        return result;try {


            String proses = binding.numpad.getText().toString();

            String[] pisah = proses.split("[+ x / -]");

            String num1Str = pisah[0];
            String num2Str = pisah[1];

            char operator = proses.charAt(num1Str.length());
            double hasil = 0;

            double num1 = (double) Integer.parseInt(num1Str);
            double num2 = (double) Integer.parseInt(num2Str);

            switch (String.valueOf(operator)) {
                case "+" :
                    hasil = num1 + num2;
                    break;
                case "-" :
                    hasil = num1 - num2;
                    break;
                case  "/" :
                    hasil = num1 / num2;
                    break;
                case  "x" :
                    hasil = num1 * num2;
                    break;
            }
            return hasil;


        }





    }
