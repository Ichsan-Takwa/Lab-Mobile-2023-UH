package com.example.tugas_praktikum_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private String[] Item = {"Bola", "Kerucut", "Balok"};
    TextView Label1, Label2, Label3, Hasil;
    EditText editText1, editText2, editText3;
    Button Btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner List = findViewById(R.id.listItem);

        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_dropdown_item, Item);
        List.setAdapter(adapter);

        Label1 = findViewById(R.id.labelView1);
        Label2 = findViewById(R.id.labelView2);
        Label3 = findViewById(R.id.labelView3);

        editText1 = findViewById(R.id.editTextNumber1);
        editText2 = findViewById(R.id.editTextNumber2);
        editText3 = findViewById(R.id.editTextNumber3);

        Btn1 = findViewById(R.id.button1);
        Hasil = findViewById(R.id.hasil);

        List.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = adapterView.getItemAtPosition(i).toString();
                if (selectedItem == "Kerucut") {
                    Label3.setVisibility(View.VISIBLE);
                    editText3.setVisibility(View.VISIBLE);
                    Label2.setVisibility(View.GONE);
                    editText2.setVisibility(View.GONE);
                    Label1.setText("Jari-jari");
                    Label3.setText("Tinggi");
                    Hasil.setText("Hasil");
                } else if (selectedItem == "Balok") {
                    Label2.setVisibility(View.VISIBLE);
                    editText2.setVisibility(View.VISIBLE);
                    Label3.setVisibility(View.VISIBLE);
                    editText3.setVisibility(View.VISIBLE);
                    Label1.setText("Panjang");
                    Label2.setText("Lebar");
                    Label3.setText("Tinggi");
                    Hasil.setText("Hasil");
                } else {
                    Label2.setVisibility(View.GONE);
                    editText2.setVisibility(View.GONE);
                    Label3.setVisibility(View.GONE);
                    editText3.setVisibility(View.GONE);
                    Label1.setText("Jari-jari");
                    Hasil.setText("Hasil");
                }
                Toast.makeText(getApplicationContext(), adapter.getItem(i), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text1 = editText1.getText().toString();
                String text2 = editText2.getText().toString();
                String text3 = editText3.getText().toString();

                String selectedItem = List.getSelectedItem().toString();
                double nilai1, nilai2, nilai3, hasil;
                if (selectedItem == "Bola") {
                    if (text1.isEmpty()) {
                        editText1.setError("Harap Isi");
                    } else {
                        nilai1 = Double.parseDouble(editText1.getText().toString());
                        hasil = (4.00 / 3.00) * Math.PI * Math.pow(nilai1, 3);

                        Hasil.setText(String.format("%.2f", hasil));
                    }
                } else if (selectedItem == "Kerucut") {
                    if (text1.isEmpty() && text3.isEmpty()) {
                        editText1.setError("Harap Isi");
                        editText3.setError("Harap Isi");
                    } else if (text1.isEmpty()) {
                        editText1.setError("Harap Isi");
                    } else if (text3.isEmpty()) {
                        editText3.setError("Harap Isi");
                    } else {
                        nilai1 = Double.parseDouble(editText1.getText().toString());
                        nilai3 = Double.parseDouble(editText3.getText().toString());
                        hasil = (Math.PI * nilai1 * nilai1 * nilai3) / 3.00;

                        Hasil.setText(String.format("%.2f", hasil));
                    }
                } else {
                    if (text1.isEmpty() && text2.isEmpty() && text3.isEmpty()) {
                        editText1.setError("Harap Isi");
                        editText2.setError("Harap Isi");
                        editText3.setError("Harap Isi");
                    } else if (text1.isEmpty() && text2.isEmpty()) {
                        editText1.setError("Harap Isi");
                        editText2.setError("Harap Isi");
                        editText3.setError(null);
                    } else if (text2.isEmpty() && text3.isEmpty()) {
                        editText2.setError("Harap Isi");
                        editText3.setError("Harap Isi");
                        editText1.setError(null);
                    } else if (text3.isEmpty() && text1.isEmpty()) {
                        editText3.setError("Harap Isi");
                        editText1.setError("Harap Isi");
                        editText2.setError(null);
                    } else if (text1.isEmpty()) {
                        editText1.setError("Harap Isi");
                        editText2.setError(null);
                        editText3.setError(null);
                    } else if (text2.isEmpty()) {
                        editText2.setError("Harap isi");
                        editText1.setError(null);
                        editText3.setError(null);
                    } else if (text3.isEmpty()) {
                        editText3.setError("Harap isi");
                        editText1.setError(null);
                        editText2.setError(null);
                    } else {
                        nilai1 = Double.parseDouble(editText1.getText().toString());
                        nilai2 = Double.parseDouble(editText2.getText().toString());
                        nilai3 = Double.parseDouble(editText3.getText().toString());
                        hasil = nilai1 * nilai2 * nilai3;

                        Hasil.setText(String.format("%.2f", hasil));
                    }
                }
            }
        });
    }
}