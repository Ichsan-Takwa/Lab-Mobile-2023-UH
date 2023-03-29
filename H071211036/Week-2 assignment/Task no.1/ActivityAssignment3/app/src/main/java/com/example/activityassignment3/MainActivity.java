package com.example.activityassignment3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import com.example.activityassignment3.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    String opr;
    double hasil = 0;
    boolean isOperator = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button0.setOnClickListener(view -> onNumberClick(view));
        binding.button1.setOnClickListener(view -> onNumberClick(view));
        binding.button2.setOnClickListener(view -> onNumberClick(view));
        binding.button3.setOnClickListener(view -> onNumberClick(view));
        binding.button4.setOnClickListener(view -> onNumberClick(view));
        binding.button5.setOnClickListener(view -> onNumberClick(view));
        binding.button6.setOnClickListener(view -> onNumberClick(view));
        binding.button7.setOnClickListener(view -> onNumberClick(view));
        binding.button8.setOnClickListener(view -> onNumberClick(view));
        binding.button9.setOnClickListener(view -> onNumberClick(view));

        binding.divisionButton.setOnClickListener(view -> onOperatorClick(view));
        binding.multiplyButton.setOnClickListener(view -> onOperatorClick(view));
        binding.minusButton.setOnClickListener(view -> onOperatorClick(view));
        binding.plusButton.setOnClickListener(view -> onOperatorClick(view));

        binding.equalButton.setOnClickListener(view -> result(view));

        binding.acButton.setOnClickListener(view -> delete(view));
        binding.dButton.setOnClickListener(view -> delete(view));

    }

    private void onNumberClick(View view) {
        Button numbers = (Button)view;


        if (binding.numpad.getText().toString().equals("0")){
            binding.numpad.setText(numbers.getText().toString());
        }else {
            String text = binding.numpad.getText().toString() + numbers.getText().toString();
            binding.numpad.setText(text);
        }
    }

    private void onOperatorClick(View view) {
        Button operators = (Button)view;
        String test = binding.numpad.getText().toString();

        if(!isOperator){
            String text = binding.numpad.getText().toString() + operators.getText().toString();
            binding.numpad.setText(text);
            opr = operators.getText().toString();

            isOperator = true;

        }else if (test.substring(test.length() - 1).equals(opr)){
            opr = operators.getText().toString();
            binding.numpad.setText(test.substring(0,test.length()-1) + operators.getText().toString());
        }
    }

    private void delete(View view) {
        Button del = (Button)view;
        String text = binding.numpad.getText().toString();

        if(del.getText().toString().equals("AC")){
            binding.numpad.setText("O");
            isOperator = false;
        }else if (del.getText().toString().equals("C")){
            if(text.length() > 1){
                binding.numpad.setText(text.substring(0, text.length() - 1));
            }else {
                binding.numpad.setText("O");
            }
        }
    }

    private void result(View view) {
        count(binding.numpad.getText().toString());
        if(hasil % 1 == 0) {
            binding.numpad.setText(String.valueOf((int)hasil));
        }else{
            binding.numpad.setText(String.valueOf(hasil));
        }
    }

    private void count(String hitung) {
        String nilai = binding.numpad.getText().toString();
        String[] value = nilai.split("[×÷+-]");
        String nilai1 = value[0];
        String nilai2 = value[1];

        char opr = nilai.charAt(nilai1.length());
        if(String.valueOf(opr).equals("+")){
            hasil = Double.parseDouble(nilai1) + Double.parseDouble(nilai2);
        }else if(String.valueOf(opr).equals("-")){
            hasil = Double.parseDouble(nilai1) - Double.parseDouble(nilai2);
        }else if(String.valueOf(opr).equals("×")){
            hasil = Double.parseDouble(nilai1) * Double.parseDouble(nilai2);
        }else if(String.valueOf(opr).equals("÷")){
            hasil = Double.parseDouble(nilai1) / Double.parseDouble(nilai2);
        }

        isOperator = false;
    }
}


