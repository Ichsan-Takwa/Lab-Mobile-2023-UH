package com.example.tugas_praktikum_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tugas_praktikum_3.databinding.ActivityMainBinding;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private boolean isOperated, isSetOp;
    String operator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        isOperated = false;
        isSetOp = false;

        /* Delete Button */
        binding.buttonAc.setOnClickListener(view -> onDelete(view));
        binding.buttonDel.setOnClickListener(view -> onDelete(view));
        /* Operator Button */
        binding.buttonBagi.setOnClickListener(view -> onOperator(view));
        binding.buttonKali.setOnClickListener(view -> onOperator(view));
        binding.buttonKurang.setOnClickListener(view -> onOperator(view));
        binding.buttonTambah.setOnClickListener(view -> onOperator(view));
        binding.buttonSamadengan.setOnClickListener(view -> onResult(view));
        /* Number Button */
        binding.button0.setOnClickListener(view -> onNumber(view));
        binding.button1.setOnClickListener(view -> onNumber(view));
        binding.button2.setOnClickListener(view -> onNumber(view));
        binding.button3.setOnClickListener(view -> onNumber(view));
        binding.button4.setOnClickListener(view -> onNumber(view));
        binding.button5.setOnClickListener(view -> onNumber(view));
        binding.button6.setOnClickListener(view -> onNumber(view));
        binding.button7.setOnClickListener(view -> onNumber(view));
        binding.button8.setOnClickListener(view -> onNumber(view));
        binding.button9.setOnClickListener(view -> onNumber(view));

    }

    private void onNumber(View view) {
        MaterialButton currentButton = (MaterialButton) view;
        String buttonText = currentButton.getText().toString();

        if(isOperated) {
            binding.numpad.setText("0");
            isOperated = false;
        }

        if (binding.numpad.getText().toString().equals("0")) {
            binding.numpad.setText(buttonText);
        } else {
            binding.numpad.setText(buttonText = binding.numpad.getText().toString() + buttonText);
        }
    }
    private void onOperator(View view) {
        MaterialButton currentButton = (MaterialButton) view;
        String buttonText = currentButton.getText().toString();
        String hasil = binding.numpad.getText().toString();

        isSetOp = true;
        if(isSetOp && hasil.substring(hasil.length()-1).equals(operator)) {
            hasil = hasil.replaceFirst("[X+-/]", buttonText);
            binding.numpad.setText(hasil);
            operator = buttonText;
            return;
        }

//        Toast.makeText(this, hasil.substring(hasil.length()-1).equals(buttonText)+"Berjalan", Toast.LENGTH_SHORT).show();
        hasil += buttonText;
        binding.numpad.setText(hasil);
        operator = buttonText;

    }
    private void onDelete(View view) {
        MaterialButton currentButton = (MaterialButton) view;
        String buttonText = currentButton.getText().toString();
        String dataToCalcalculate = binding.numpad.getText().toString();

        switch (buttonText) {
            case "AC":
                binding.numpad.setText("0");
                break;
            case "DEL":
                if (dataToCalcalculate.length() > 1) {
                    dataToCalcalculate = dataToCalcalculate.substring(0, dataToCalcalculate.length() - 1);
                    binding.numpad.setText(dataToCalcalculate);
                } else {
                    binding.numpad.setText("0");
                }
                break;
        }
    }
    private void onResult(View view) {
        MaterialButton currentButton = (MaterialButton) view;
        String buttonText =  binding.numpad.getText().toString() + currentButton.getText().toString();

        try {
            double result  = hitungString(buttonText);

            if (result%1 == 0) {
                int hasilInt = (int) result;
                binding.numpad.setText(String.valueOf(hasilInt));
            } else {
                binding.numpad.setText(String.valueOf(result));
            }
            isOperated = true;
        } catch (Exception e) {
            Toast.makeText(this, "operator kurang", Toast.LENGTH_SHORT).show();
        }
    }
    private double hitungString(String perhitungan) {
        String hasil = binding.numpad.getText().toString();
        String[] numbers = hasil.split("[X+-/]");

        String bil1 = numbers[0];
        String bil2 = numbers[1];

        char operator = hasil.charAt(bil1.length());
        double hasilKalkulator = 0;
        switch (String.valueOf(operator)) {
            case "+" :
                hasilKalkulator = Double.parseDouble(bil1) + Double.parseDouble(bil2);
                break;
            case "-" :
                hasilKalkulator = Double.parseDouble(bil1) - Double.parseDouble(bil2);
                break;
            case "X" :
                hasilKalkulator = Double.parseDouble(bil1) * Double.parseDouble(bil2);
                break;
            case "/" :
                hasilKalkulator = Double.parseDouble(bil1) / Double.parseDouble(bil2);
                break;
        }

        return hasilKalkulator;
    }
}