package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.calculator.databinding.ActivityMainBinding;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity{

    private boolean isnewOp;
    private boolean isSetOp;
    String operator;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        isnewOp = true;
        isSetOp = false;

        /* Number Button */
        binding.button0.setOnClickListener(view -> onClick(view));
        binding.button1.setOnClickListener(view -> onClick(view));
        binding.button2.setOnClickListener(view -> onClick(view));
        binding.button3.setOnClickListener(view -> onClick(view));
        binding.button4.setOnClickListener(view -> onClick(view));
        binding.button5.setOnClickListener(view -> onClick(view));
        binding.button6.setOnClickListener(view -> onClick(view));
        binding.button7.setOnClickListener(view -> onClick(view));
        binding.button8.setOnClickListener(view -> onClick(view));
        binding.button9.setOnClickListener(view -> onClick(view));

        /* Operator Button */
        binding.buttonPlus.setOnClickListener(view -> onOperator(view));
        binding.buttonMinus.setOnClickListener(view -> onOperator(view));
        binding.buttonMultiply.setOnClickListener(view -> onOperator(view));
        binding.buttonDivide.setOnClickListener(view -> onOperator(view));

        /* Delete Button */
        binding.buttonAc.setOnClickListener(view -> onDelete(view));
        binding.buttonC.setOnClickListener(view -> onDelete(view));

        /* Equals Button */
        binding.buttonEquals.setOnClickListener(view ->onEquals(view));
    }


    private void onClick(View view) {

        if(isnewOp){

            binding.numpad.setText("");

        } isnewOp = false;

        MaterialButton currentButton = (MaterialButton) view;
        String buttonNumb = binding.numpad.getText().toString() + currentButton.getText().toString();
        binding.numpad.setText(buttonNumb);

    }

    private void onOperator(View view) {
        MaterialButton currentButton = (MaterialButton) view;
        String buttonText = currentButton.getText().toString();
        String hasil = binding.numpad.getText().toString();

        isSetOp = true;
        isnewOp = false;
        if(isSetOp && hasil.substring(hasil.length()-1).equals(operator)) {
            hasil = hasil.replaceFirst("[X+-/]", buttonText);
            binding.numpad.setText(hasil);
            operator = buttonText;
            return;
        }
        hasil += buttonText;
        binding.numpad.setText(hasil);
        operator = buttonText;
    }

    private void onDelete(View view) {
        MaterialButton currentButton = (MaterialButton) view;
        String buttonDel = currentButton.getText().toString();
        String MinSatu = binding.numpad.getText().toString();

        if (buttonDel.equals("AC")){
            binding.numpad.setText("0");
        } else if (buttonDel.equals("C")){
            MinSatu = MinSatu.substring(0, MinSatu.length()-1);
            binding.numpad.setText(MinSatu);
        }  isnewOp = true;
    }
    private void onEquals(View view) {
        MaterialButton currentButton = (MaterialButton) view;
        String buttonText =  binding.numpad.getText().toString() + currentButton.getText().toString();

        try {
            double equals  = countString(buttonText);

            if (equals%1 == 0) {
                int hasilInt = (int) equals;
                binding.numpad.setText(String.valueOf(hasilInt));
            } else {
                binding.numpad.setText(String.valueOf(equals));
            }
            isnewOp = true;
        } catch (Exception e) {
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
        }
    }
    private double countString(String perhitungan) {
        String result = binding.numpad.getText().toString();
        String[] numbers = result.split("[X+-/]");

        String numb1 = numbers[0];
        String numb2 = numbers[1];

        char operator = result.charAt(numb1.length());
        double CalcResult = 0;
        switch (String.valueOf(operator)) {
            case "+" :
                CalcResult = Double.parseDouble(numb1) + Double.parseDouble(numb2);
                break;
            case "-" :
                CalcResult = Double.parseDouble(numb1) - Double.parseDouble(numb2);
                break;
            case "X" :
                CalcResult = Double.parseDouble(numb1) * Double.parseDouble(numb2);
                break;
            case "/" :
                CalcResult = Double.parseDouble(numb1) / Double.parseDouble(numb2);
                break;
        }

        return CalcResult;
    }
}