package com.example.tugas2praktikum;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView jari,panjang,lebar,tinggi,hasil;
    private EditText edit1,edit2,edit3,edit4;
    private Spinner daftar;
    private Button hitung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jari = findViewById(R.id.jari);
        edit1 = findViewById(R.id.edit1);
        panjang = findViewById(R.id.panjang);
        edit2 = findViewById(R.id.edit2);
        lebar = findViewById(R.id.lebar);
        edit3 = findViewById(R.id.edit3);
        tinggi = findViewById(R.id.tinggi);
        edit4 = findViewById(R.id.edit4);
        daftar = findViewById(R.id.bangun);
        hitung = findViewById(R.id.hitung);
        hasil = findViewById(R.id.hasil);

        String[] jenisbangunruang = {"Bola", "Kerucut", "Balok"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_dropdown_item, jenisbangunruang);
        daftar.setAdapter(adapter);

        daftar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 1:
                        jari.setVisibility(View.VISIBLE);
                        edit1.setVisibility(View.VISIBLE);
                        panjang.setVisibility(View.GONE);
                        edit2.setVisibility(View.GONE);
                        lebar.setVisibility(View.GONE);
                        edit3.setVisibility(View.GONE);
                        tinggi.setVisibility(View.VISIBLE);
                        edit4.setVisibility(View.VISIBLE);

                        hitung.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String jari = edit1.getText().toString();
                                String tinggi = edit4.getText().toString();

                                if (jari.isEmpty() && tinggi.isEmpty()){
                                    edit1.setError("Mohon diisi dahulu");
                                    edit4.setError("Mohon diisi dahulu");
                                } else if (jari.isEmpty()){
                                    edit1.setError("Mohon diisi dahulu");
                                } else if (tinggi.isEmpty()){
                                    edit4.setError("Mohon diisi dahulu");
                                } else {
                                    double jariKerucut = Double.parseDouble(jari);
                                    double tinggiKerucut = Double.parseDouble(tinggi);
                                    double volume = (1.0/3.0) * Math.PI * Math.pow(jariKerucut,2) * tinggiKerucut;
                                    hasil.setText(String.format("%.2f",volume));
                                }
                            }
                        });
                        break;

                    case 2:
                        jari.setVisibility(View.GONE);
                        edit1.setVisibility(View.GONE);
                        panjang.setVisibility(View.VISIBLE);
                        edit2.setVisibility(View.VISIBLE);
                        lebar.setVisibility(View.VISIBLE);
                        edit3.setVisibility(View.VISIBLE);
                        tinggi.setVisibility(View.VISIBLE);
                        edit4.setVisibility(View.VISIBLE);

                        hitung.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String panjang = edit2.getText().toString();
                                String lebar = edit3.getText().toString();
                                String tinggi = edit4.getText().toString();
                                if (panjang.isEmpty() && lebar.isEmpty() && tinggi.isEmpty()){
                                    edit2.setError("Mohon diisi dahulu");
                                    edit3.setError("Mohon diisi dahulu");
                                    edit4.setError("Mohon diisi dahulu");
                                } else if (panjang.isEmpty() && lebar.isEmpty()){
                                    edit2.setError("Mohon diisi dahulu");
                                    edit3.setError("Mohon diisi dahulu");
                                } else if (lebar.isEmpty() && tinggi.isEmpty()){
                                    edit3.setError("Mohon diisi dahulu");
                                    edit4.setError("Mohon diisi dahulu");
                                } else if (panjang.isEmpty() && tinggi.isEmpty()){
                                    edit2.setError("Mohon diisi dahulu");
                                    edit4.setError("Mohon diisi dahulu");
                                } else if (panjang.isEmpty()){
                                    edit2.setError("Mohon diisi dahulu");
                                } else if (lebar.isEmpty()){
                                    edit3.setError("Mohon diisi dahulu");
                                } else if (tinggi.isEmpty()){
                                    edit4.setError("Mohon diisi dahulu");
                                } else {
                                    double panjangBalok = Double.parseDouble(panjang);
                                    double lebarBalok = Double.parseDouble(lebar);
                                    double tinggiBalok = Double.parseDouble(tinggi);
                                    double volume = panjangBalok * lebarBalok * tinggiBalok;
                                    hasil.setText(String.format("%.2f",volume));
                                }
                            }
                        });
                        break;

                    default:
                        jari.setVisibility(View.VISIBLE);
                        edit1.setVisibility(View.VISIBLE);
                        panjang.setVisibility(View.GONE);
                        edit2.setVisibility(View.GONE);
                        lebar.setVisibility(View.GONE);
                        edit3.setVisibility(View.GONE);
                        tinggi.setVisibility(View.GONE);
                        edit4.setVisibility(View.GONE);

                        hitung.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String jari = edit1.getText().toString();

                                if (jari.isEmpty()){
                                    edit1.setError("Mohon diisi dahulu");
                                } else {
                                    double jariBola = Double.parseDouble(jari);
                                    double volume = (4/3) * Math.PI * Math.pow(jariBola,3);
                                    hasil.setText(String.format("%.2f", volume));
                                }
                            }
                        });
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}