package com.example.praktikum2;

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

    private TextView label1,label2,label3,label4,hasil ;
    private EditText edit1,edit2,edit3,edit4 ;
    private Spinner daftar;
    private Button hitung;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        label1 = findViewById(R.id.label1);
        edit1 = findViewById(R.id.edit1);
        label2 = findViewById(R.id.label2);
        edit2 = findViewById(R.id.edit2);
        label3 = findViewById(R.id.label3);
        edit3 = findViewById(R.id.edit3);
        label4 = findViewById(R.id.label4);
        edit4 = findViewById(R.id.edit4);
        daftar = findViewById(R.id.bangun);
        hitung = findViewById(R.id.hitung);
        hasil = findViewById(R.id.hasil);

        String[] user1 = {"Bola","Kerucut","Balok"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>
                ( this, android.R.layout.simple_spinner_dropdown_item, user1);
        daftar.setAdapter(adapter);

        daftar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 1:
                        label1.setVisibility(View.VISIBLE);
                        edit1.setVisibility(View.VISIBLE);
                        label2.setVisibility(View.GONE);
                        edit2.setVisibility(View.GONE);
                        label3.setVisibility(View.GONE);
                        edit3.setVisibility(View.GONE);
                        label4.setVisibility(View.VISIBLE);
                        edit4.setVisibility(View.VISIBLE);

                        hitung.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String jari = edit1.getText().toString();
                                String tinggi = edit4.getText().toString();

                                if (jari.isEmpty() && tinggi.isEmpty()) {
                                    edit1.setError("Isi Dulu");
                                    edit4.setError("Isi Dulu");
                                } else if (jari.isEmpty()) {
                                    edit1.setError("Isi Dulu");
                                } else if (tinggi.isEmpty()) {
                                    edit4.setError("Isi Dulu");
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
                        label1.setVisibility(View.GONE);
                        edit1.setVisibility(View.GONE);
                        label2.setVisibility(View.VISIBLE);
                        edit2.setVisibility(View.VISIBLE);
                        label3.setVisibility(View.VISIBLE);
                        edit3.setVisibility(View.VISIBLE);
                        label4.setVisibility(View.VISIBLE);
                        edit4.setVisibility(View.VISIBLE);

                        hitung.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String panjang = edit2.getText().toString();
                                String lebar = edit3.getText().toString();
                                String tinggi =  edit4.getText().toString();
                                if (panjang.isEmpty() && lebar.isEmpty() && tinggi.isEmpty()){
                                    edit2.setError("Isi Dulu");
                                    edit3.setError("Isi Dulu");
                                    edit4.setError("Isi Dulu");
                                } else if (panjang.isEmpty() && lebar.isEmpty()) {
                                    edit2.setError("Isi Dulu");
                                    edit3.setError("Isi Dulu");
                                } else if (lebar.isEmpty() && tinggi.isEmpty()) {
                                    edit3.setError("Isi Dulu");
                                    edit4.setError("Isi Dulu");
                                } else if (panjang.isEmpty() && tinggi.isEmpty()) {
                                    edit2.setError("Isi Dulu");
                                    edit4.setError("Isi Dulu");
                                } else if (panjang.isEmpty()) {
                                    edit2.setError("Isi Dulu");
                                } else if (lebar.isEmpty()) {
                                    edit3.setError("Isi Dulu");
                                } else if (tinggi.isEmpty()) {
                                    edit4.setError("isi Dulu");
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
                        label1.setVisibility(View.VISIBLE);
                        edit1.setVisibility(View.VISIBLE);
                        label2.setVisibility(View.GONE);
                        edit2.setVisibility(View.GONE);
                        label3.setVisibility(View.GONE);
                        edit3.setVisibility(View.GONE);
                        label4.setVisibility(View.GONE);
                        edit4.setVisibility(View.GONE);

                        hitung.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String jari = edit1.getText().toString();

                                if (jari.isEmpty()){
                                    edit1.setError("Isi Dulu");
                                }else {
                                    double jariBola = Double.parseDouble(jari);
                                    double volume = (4/3) * Math.PI * Math.pow(jariBola,3);
                                    hasil.setText(String.format("%.2f",volume));
                                }
                            }
                        });
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


}