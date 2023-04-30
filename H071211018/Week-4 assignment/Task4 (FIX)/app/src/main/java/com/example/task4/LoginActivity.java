package com.example.task4;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private ImageView iv_profil;
    private EditText et_email, et_password;
    private Button btn_createAccount, btn_login;
    private Uri profilPict;


    private ActivityResultLauncher<Intent> launcherIntentImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null){
                        Uri selectedPhoto = result.getData().getData();
                        iv_profil.setImageURI(selectedPhoto);
                    }

                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        iv_profil = findViewById(R.id.iv_profil);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        btn_createAccount = findViewById(R.id.btn_createAccount);
        btn_login = findViewById(R.id.btn_login);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String password = intent.getStringExtra("password");

        iv_profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Anda Dapat Mengubah Foto Profil Pada Saat Sudah Login!", Toast.LENGTH_SHORT).show();
            }
        });

        btn_createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, CreateActivity.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String I_email = et_email.getText().toString();
                String I_password = et_password.getText().toString();

                if(email == null || password == null) {
                    Toast.makeText(LoginActivity.this, "Akun anda tidak ditemukan! Silahkan register terlebih dahulu!", Toast.LENGTH_SHORT).show();
                } else {
                    if (!I_email.isEmpty() || !I_password.isEmpty()) {
                        if (I_email.equals(email) && I_password.equals(password)) {
                            Toast.makeText(LoginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                            Intent masuk = new Intent(LoginActivity.this, DisplayMenu.class);
                            startActivity(masuk);
                        } else {
                            Toast.makeText(LoginActivity.this, "Login gagal, silahkan periksa kembali inputan anda!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Silahkan isi inputan terlebih dahulu!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}