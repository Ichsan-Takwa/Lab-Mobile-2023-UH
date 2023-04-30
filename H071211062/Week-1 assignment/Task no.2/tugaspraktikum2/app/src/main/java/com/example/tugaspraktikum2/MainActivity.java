package com.example.tugaspraktikum2;

import static java.sql.DriverManager.println;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.Transliterator;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] ruang = {"Bola", "Kerucut", "Balok"};

    int selected = 0;

    private Button button;
    private TextView tvValue, tvValue2, tvValue3, hasil;
    private EditText etValue, etValue2, etValue3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinner);
        tvValue = findViewById(R.id.tvValue);
        tvValue2 = findViewById(R.id.tvValue2);
        tvValue3 = findViewById(R.id.tvValue3);
        etValue = findViewById(R.id.etValue);
        etValue2 = findViewById(R.id.etValue2);
        etValue3 = findViewById(R.id.etValue3);
        button = findViewById(R.id.button);
        hasil = findViewById(R.id.hasil);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, ruang);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);

        button.setOnClickListener(view1 -> {
            if (selected == 0) {
                if (!isBolaInputInvalid()) {
                    calculateBola();
                } else {
                    etValue.setError("Input harus terisi");
                }
            } else if (selected == 1) {
                if (!isKerucutInputInvalid()) {
                    calculateKerucut();
                } else {
                    etValue.setError("Input harus terisi");
                    etValue2.setError("input harus terisi");
                }
            } else {
                if (!isBalokInputInvalid()) {
                    calculateBalok();
                } else {
                    etValue.setError("Input harus terisi");
                    etValue2.setError("input harus terisi");
                    etValue3.setError("input harus terisi");
                }
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        selected = position;
        if (selected == 0) {
            showFirstEditText(true);
            showSecondEditText(false);
            showThirdEditText(false);
            tvValue.setText("jari-jari");
            etValue.setHint("jari-jari");
        } else if (selected == 1) {
            showFirstEditText(true);
            showSecondEditText(true);
            showThirdEditText(false);
        } else {
            showFirstEditText(true);
            showSecondEditText(true);
            showThirdEditText(true);
            tvValue.setText("Lebar");
            etValue.setHint("Lebar");
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    private void showFirstEditText(boolean isShow) {
        if (isShow) {
            tvValue.setVisibility(View.VISIBLE);
            etValue.setVisibility(View.VISIBLE);
        } else {
            tvValue.setVisibility(View.GONE);
            etValue.setVisibility(View.GONE);
        }
    }

    private void showSecondEditText(boolean isShow) {
        if (isShow) {
            tvValue2.setVisibility(View.VISIBLE);
            etValue2.setVisibility(View.VISIBLE);
        } else {
            tvValue2.setVisibility(View.GONE);
            etValue2.setVisibility(View.GONE);
        }
    }

    private void showThirdEditText(boolean isShow) {
        if (isShow) {
            tvValue3.setVisibility(View.VISIBLE);
            etValue3.setVisibility(View.VISIBLE);
        } else {
            tvValue3.setVisibility(View.GONE);
            etValue3.setVisibility(View.GONE);
        }
    }

    private void calculateBola() {
        Double jari_jari = Double.parseDouble(etValue.getText().toString());
        Double hasilbola = (Double) (4.0 / 3.0) * Math.PI * Math.pow(jari_jari, 3);
        hasil.setText(String.valueOf(hasilbola));
    }

    private void calculateKerucut() {
        Double jari_jari = Double.parseDouble(etValue.getText().toString());
        Double tinggi = Double.parseDouble(etValue2.getText().toString());
        Double hasilkerucut = (Double) (1.0 / 3.0 * Math.PI * Math.pow(jari_jari, 2) * tinggi);
        hasil.setText(String.valueOf(hasilkerucut));
    }

    private void calculateBalok() {
        Double panjang = Double.parseDouble(etValue.getText().toString());
        Double lebar = Double.parseDouble(etValue2.getText().toString());
        Double tinggi = Double.parseDouble(etValue3.getText().toString());

        Double hasilbalok = panjang * lebar * tinggi;
        hasil.setText(String.valueOf(hasilbalok));
    }

    private boolean isBolaInputInvalid() { return etValue.getText().toString().trim().isEmpty();
    }

    private boolean isKerucutInputInvalid() {
        return isBolaInputInvalid() && etValue2.getText().toString().trim().isEmpty();
    }

    private boolean isBalokInputInvalid() {
        return isKerucutInputInvalid() && etValue3.getText().toString().trim().isEmpty();
    }
}