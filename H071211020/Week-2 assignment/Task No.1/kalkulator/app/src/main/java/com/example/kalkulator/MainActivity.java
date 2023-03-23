package com.example.kalkulator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.example.kalkulator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    double result = 0;
    String ang1, ang2, operator;
    String[] value;
    boolean isOperator = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btn0.setOnClickListener(view -> onClick(view));
        binding.btn1.setOnClickListener(view -> onClick(view));
        binding.btn2.setOnClickListener(view -> onClick(view));
        binding.btn3.setOnClickListener(view -> onClick(view));
        binding.btn4.setOnClickListener(view -> onClick(view));
        binding.btn5.setOnClickListener(view -> onClick(view));
        binding.btn6.setOnClickListener(view -> onClick(view));
        binding.btn7.setOnClickListener(view -> onClick(view));
        binding.btn8.setOnClickListener(view -> onClick(view));
        binding.btn9.setOnClickListener(view -> onClick(view));

        binding.btnTambah.setOnClickListener(view -> onOperatorClick(view));
        binding.btnKurang.setOnClickListener(view -> onOperatorClick(view));
        binding.btnKali.setOnClickListener(view -> onOperatorClick(view));
        binding.btnBagi.setOnClickListener(view -> onOperatorClick(view));

        binding.btnSamadengan.setOnClickListener(view -> onSamaClick(view));

        binding.btnBack.setOnClickListener(view -> onSpecialClick(view));
        binding.btnHapus.setOnClickListener(view -> onSpecialClick(view));

    }

    void onClick(View view) {
        Button btn = (Button) view;
        String text = binding.hasil.getText().toString() + btn.getText().toString();

        if (binding.hasil.getText().toString().equals("0")) {
            binding.hasil.setText(btn.getText().toString());
        } else {
            binding.hasil.setText(text);
        }
    }

    void onOperatorClick(View view) {
        Button btn = (Button) view;
        String cek = binding.hasil.getText().toString();
        if (!isOperator) {

            String text = binding.hasil.getText().toString() + btn.getText().toString();
            binding.hasil.setText(text);
            operator = btn.getText().toString();

            isOperator = true;
        }else if (cek.substring(cek.length()-1).equals(operator)){
            operator = btn.getText().toString();
            binding.hasil.setText(cek.substring(0,cek.length()-1) + btn.getText().toString());
        }
    }

    void onSpecialClick(View view) {
        Button btn = (Button) view;
        String text = binding.hasil.getText().toString();
        if (btn.getText().toString().equals("AC")) {
            binding.hasil.setText("0");
            isOperator = false;
        } else if (btn.getText().toString().equals("DEL")) {
            if (text.length() > 1) {
                binding.hasil.setText(text.substring(0, text.length() - 1));
            } else {
                binding.hasil.setText("0");
            }
        }

    }

    void onSamaClick(View view) {

        try {
            hitungString(binding.hasil.getText().toString());
            if (result % 1 == 0) {
                binding.hasil.setText(String.valueOf((int) result));
            } else {
                binding.hasil.setText(String.valueOf(result));
            }
            isOperator = false;
        } catch (Exception e) {
            Toast.makeText(this, "Masukkan Angka Dulu", Toast.LENGTH_SHORT).show();
        }
    }

    void hitungString(String perhitungan) {
        String nilai = binding.hasil.getText().toString();

        if (nilai.substring(0, 1).equals("-")) {
            String[] value = nilai.substring(1).split("[X+/-]");
            ang1 = "-" + value[0];
            ang2 = value[1];
        } else {
            String[] value = nilai.split("[X+/-]");
            ang1 = value[0];
            ang2 = value[1];
        }

        char operator = nilai.charAt(ang1.length());
        switch (String.valueOf(operator)) {
            case "+":
                result = Double.parseDouble(ang1) + Double.parseDouble(ang2);
                break;
            case "-":
                result = Double.parseDouble(ang1) - Double.parseDouble(ang2);
                break;
            case "X":
                result = Double.parseDouble(ang1) * Double.parseDouble(ang2);
                break;
            case "/":
                result = Double.parseDouble(ang1) / Double.parseDouble(ang2);
                break;
        }
    }
}

