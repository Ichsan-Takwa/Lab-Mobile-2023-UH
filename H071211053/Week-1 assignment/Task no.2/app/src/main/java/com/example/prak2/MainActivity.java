package com.example.prak2;


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

public class MainActivity extends AppCompatActivity {

    Spinner spinner;

    EditText txtPanjang,txtJari2, txtLebar,txtTinggi;

    TextView hasil;

    Button btnHasil;

    String[] bgnRuang = {"Bola","Kerucut","Balok"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);
        txtJari2 = findViewById(R.id.txtJari2);
        txtPanjang = findViewById(R.id.txtPanjang);
        txtLebar = findViewById(R.id.txtLebar);
        txtTinggi = findViewById(R.id.txtTinggi);
        btnHasil = findViewById(R.id.btnHasil);
        hasil = findViewById(R.id.hasil);

        btnHasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(spinner.getSelectedItem().toString().equals(bgnRuang[0])){
                    if(txtJari2.getText().toString().isEmpty()){
                        txtJari2.setError("Field Harus Terisi");
                    } else {
                        Double jari2 = Double.valueOf(txtJari2.getText().toString());
                        Double volBola = (4.00 / 3.00) * Math.PI * jari2 * jari2 * jari2;
                        String duaFormat = String.format("%.2f",volBola);
                        hasil.setText(duaFormat);
                    }
                } else if (spinner.getSelectedItem().toString().equals(bgnRuang[1])) {
                    if(txtJari2.getText().toString().isEmpty() && txtTinggi.getText().toString().isEmpty()){
                        txtJari2.setError("Field Harus Terisi");
                        txtTinggi.setError("Field Harus Terisi");
                    } else if(txtJari2.getText().toString().isEmpty()){
                        txtJari2.setError("Field Harus Terisi");
                    } else if(txtTinggi.getText().toString().isEmpty()){
                        txtTinggi.setError("Field Harus Terisi");
                    } else {
                        Double jari2 = Double.valueOf(txtJari2.getText().toString());
                        Double tinggi = Double.valueOf(txtTinggi.getText().toString());
                        Double volKerucut = (Math.PI * jari2 * jari2 * tinggi) / 3.00;
                        String duaFormat = String.format("%.2f", volKerucut);
                        hasil.setText(duaFormat);
                    }
                } else if(spinner.getSelectedItem().toString().equals(bgnRuang[2])) {
                    if(txtPanjang.getText().toString().isEmpty() && txtLebar.getText().toString().isEmpty() && txtTinggi.getText().toString().isEmpty()) {
                        txtPanjang.setError("Field Harus Terisi");
                        txtLebar.setError("Field Harus Terisi");
                        txtTinggi.setError("Field Harus Terisi");
                    } else if(txtPanjang.getText().toString().isEmpty()) {
                        txtPanjang.setError("Field Harus Terisi");
                    } else if(txtLebar.getText().toString().isEmpty()) {
                        txtLebar.setError("Field Harus Terisi");
                    } else if(txtTinggi.getText().toString().isEmpty()) {
                        txtTinggi.setError("Field Harus Terisi");
                    } else {
                        Double panjang = Double.valueOf(txtPanjang.getText().toString());
                        Double lebar = Double.valueOf((txtLebar.getText().toString()));
                        Double tinggi = Double.valueOf(txtTinggi.getText().toString());
                        Double volBalok = panjang*lebar*tinggi;
                        String duaFormat = String.format("%.2f", volBalok);
                        hasil.setText(duaFormat);
                    }
                }
            }
        });

        txtPanjang.setVisibility(View.GONE);
        txtLebar.setVisibility(View.GONE);
        txtTinggi.setVisibility(View.GONE);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item,bgnRuang);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = parent.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, value, Toast.LENGTH_SHORT).show();

                switch (position){
                    case 0:
                        txtJari2.setVisibility(View.VISIBLE);
                        txtPanjang.setVisibility(View.GONE);
                        txtLebar.setVisibility(View.GONE);
                        txtTinggi.setVisibility(View.GONE);
                        break;
                    case 1:
                        txtJari2.setVisibility(View.VISIBLE);
                        txtPanjang.setVisibility(View.GONE);
                        txtLebar.setVisibility(View.GONE);
                        txtTinggi.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        txtJari2.setVisibility(View.GONE);
                        txtPanjang.setVisibility(View.VISIBLE);
                        txtLebar.setVisibility(View.VISIBLE);
                        txtTinggi.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {/* nope */}
        });
    }
}