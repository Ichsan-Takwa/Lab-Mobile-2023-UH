package com.example.task4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateActivity extends AppCompatActivity {
    private EditText et_fullname, et_email, et_password, et_confPassword;
    private Button btn_create;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        et_fullname = findViewById(R.id.et_fname);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        et_confPassword = findViewById(R.id.et_confPassword);
        btn_create = findViewById(R.id.btn_create);

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullName = et_fullname.getText().toString();
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();
                String confirmPassword = et_confPassword.getText().toString();

                if(password.equals(confirmPassword)){
                    Intent intent = new Intent(CreateActivity.this, LoginActivity.class);
                    User user = new User(fullName, email, password);
                    intent.putExtra("email", user.getEmail());
                    intent.putExtra("fullname", user.getFullName());
                    intent.putExtra("password", user.getPassword());
                    startActivity(intent);
                } else {
                    Toast.makeText(CreateActivity.this, "Register gagal, silahkan periksa kembali inputan anda!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}