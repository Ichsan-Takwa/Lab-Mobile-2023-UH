package com.example.activityassignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Spinner sp_bangunruang; //membuat private untuk membatasi akses variabel
    private LinearLayout ll_jarijari, ll_panjang, ll_lebar, ll_tinggi;
    private EditText et_jarijari, et_panjang, et_lebar, et_tinggi;
    private Button button;
    private TextView tv_hasil;
    private ArrayList<String> bangun_ruang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp_bangunruang = findViewById(R.id.sp_bangunruang); // untuk mencari id dari content
        ll_jarijari = findViewById(R.id.ll_jarijari);
        et_jarijari = findViewById(R.id.et_jarijari);
        ll_panjang = findViewById(R.id.ll_panjang);
        et_panjang = findViewById(R.id.et_panjang);
        ll_lebar = findViewById(R.id.ll_lebar);
        et_lebar = findViewById(R.id.et_lebar);
        ll_tinggi = findViewById(R.id.ll_tinggi);
        et_tinggi = findViewById(R.id.et_tinggi);
        button = findViewById(R.id.button);
        tv_hasil = findViewById(R.id.tv_hasil);

        String[] bangun_ruang = {"Bola", "Kerucut", "Balok"};

        ArrayAdapter<String>adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, bangun_ruang);

        sp_bangunruang.setAdapter(adapter);

        sp_bangunruang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) { // adapter gunanya untuk mengkonversi format
                                                                                            // yang tidak kompatibel menjadi format yang sesuai
                                                                                            // seperti apk ini yang dimana ketika dipencet bola maka editText yang muncul hanya jari-jari
                                                                                            // dan ketika kita pencet kerucut maka akan muncul tinggi dan jarijari.
               //untuk mengambil nilai item pada posisi i dari tampilan adaptor dan mengubahnya menjadi string.
                String value = adapterView.getItemAtPosition(i).toString();


                if (value=="Bola"){
                    ll_jarijari.setVisibility(View.VISIBLE);
                    ll_panjang.setVisibility(View.GONE);
                    ll_lebar.setVisibility(View.GONE);
                    ll_tinggi.setVisibility(View.GONE);
                    tv_hasil.setText("Hasil");
                } else if (value=="Kerucut") {
                    ll_jarijari.setVisibility(View.VISIBLE);
                    ll_panjang.setVisibility(View.GONE);
                    ll_lebar.setVisibility(View.GONE);
                    ll_tinggi.setVisibility(View.VISIBLE);
                    tv_hasil.setText("Hasil");
                } else {
                    ll_jarijari.setVisibility(View.GONE);
                    ll_panjang.setVisibility(View.VISIBLE);
                    ll_lebar.setVisibility(View.VISIBLE);
                    ll_tinggi.setVisibility(View.VISIBLE);
                    tv_hasil.setText("Hasil");
                }
                Toast.makeText(getApplicationContext(), adapter.getItem(i), Toast.LENGTH_SHORT).show(); // pesan toast yg muncul di layar dalam waktu singkat,
                                                                                                        // menunjukkan nilai item pada posisi i di tampilan adaptor.
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { //onNothingSelected dipanggil saat tidak ada item di AdapterView yang dipilih.

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String jarijari = et_jarijari.getText().toString(); //membuat variabel untuk mangambil nilai text dari sebuah objek EditText
                String panjang = et_panjang.getText().toString();
                String lebar = et_lebar.getText().toString();
                String tinggi = et_tinggi.getText().toString();
                String pilih = sp_bangunruang.getSelectedItem().toString();


                if (pilih== "Bola") {
                    try { // try catch perintah untuk edittext ketika tidak memasukkan nilai atau null
                        if (jarijari.isEmpty()){
                            et_jarijari.setError("Data tidak boleh kosong");
                        }else{
                            double jarijari_num = Double.parseDouble(jarijari); //untuk mengkonversi string menjadi tipe data double
                            double hitung = (4.0/3.0) * Math.PI * Math.pow(jarijari_num, 3);
                            tv_hasil.setText(String.format("%.2f", hitung));

                        }
                    }catch (NumberFormatException ex){ //untuk menampilkan kesalahan pada program apabila tidak memasukkan nilai di editText
                        Toast.makeText(MainActivity.this, "Imputan terlalu besar", Toast.LENGTH_SHORT).show();
                    }

                } else if (pilih== "Kerucut") {
                    try {
                        if (jarijari.isEmpty() && tinggi.isEmpty()){
                            et_jarijari.setError("Data tidak boleh kosong!");
                            et_tinggi.setError("Data tidak boleh kosong");
                        } else if (tinggi.isEmpty()) {
                            et_tinggi.setError("Data tidak boleh kosong!");
                            et_jarijari.setError(null);
                        }else {
                            double jarijari_num = Double.parseDouble(jarijari);
                            double tinggi_num = Double.parseDouble(tinggi);
                            double hitung = (1.0 / 3.0) * Math.PI * Math.pow(jarijari_num, 2) * tinggi_num;
                            tv_hasil.setText(String.format("%.2f", hitung));
                        }
                    }catch (NumberFormatException ex){
                        Toast.makeText(MainActivity.this, "Imputan terlalu besar", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    try {
                        if ( panjang.isEmpty()){
                            et_panjang.setError("Data tidak boleh kosong");
                        }  if (lebar.isEmpty()) {
                            et_lebar.setError("Data tidak boleh kosong");
                        }  if (tinggi.isEmpty()) {
                            et_tinggi.setError("Data tidak boleh kosong");
                        }

                            if (panjang.isEmpty() && lebar.isEmpty() && tinggi.isEmpty()){
                                return;
                        }

                            double panjang_num = Double.parseDouble(panjang);
                            double lebar_num = Double.parseDouble(lebar);
                            double tinggi_num = Double.parseDouble(tinggi);
                            double hitung = panjang_num * lebar_num * tinggi_num;
                            tv_hasil.setText(String.format("%.2f", hitung));

                    } catch (NumberFormatException ex){
                        Toast.makeText(MainActivity.this, "Imputan terlalu besar", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}

// 1. di edit view, kalo fieldnya kosong dia error (pke function [variabel view].setErrr(string)
// 2. tampilkan hasil 2 angka diblkng koma