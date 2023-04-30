package com.example.driveku;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.driveku.databinding.ActivityMainBinding;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    User user;
    public final static String EXTRA_USER = "extra user";

    static String section = "login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getIntent().getParcelableExtra(EXTRA_USER) == null) {
            user = new User();
        } else {
            user = getIntent().getParcelableExtra(EXTRA_USER);
        }

        if (section.equals("login")) {
            login();
        } else if (section.equals("register")) {
            register();
        } else if (section.equals("confirm")) {
            confirm();
        }
    }

    public void login() {
        binding.field1Et.setText("");
        binding.field2Et.setText("");
        binding.field1Et.setHint("Username");
        binding.field2Et.setHint("Password");
        binding.field2Et.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        binding.field2Til.setEndIconMode(TextInputLayout.END_ICON_PASSWORD_TOGGLE);
        binding.field2Et.setTransformationMethod(new PasswordTransformationMethod());
        binding.titleTv.setText("Login");
        binding.subtitleTv.setVisibility(View.VISIBLE);
        binding.nextBtn.setText("Login");
        binding.createAccountBtn.setVisibility(View.VISIBLE);
        binding.nextBtn.setOnClickListener(view -> {
            if (binding.field1Et.getText().toString().equals(user.getNama()) && binding.field2Et.getText().toString().equals(user.getPass())) {
                Intent intent = new Intent(MainActivity.this, Explorer.class);
                intent.putExtra(EXTRA_USER, user);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Username atau password salah", Toast.LENGTH_SHORT).show();
            }
        });

        binding.createAccountBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(EXTRA_USER, user);
            startActivity(intent);
            section = "register";
        });
    }

    public void register() {
        binding.field1Et.setText("");
        binding.field2Et.setVisibility(View.GONE);
        binding.field1Et.setHint("Username");
        binding.titleTv.setText("Make Acount");
        binding.subtitleTv.setVisibility(View.GONE);
        binding.nextBtn.setText("Register");
        binding.createAccountBtn.setVisibility(View.GONE);
        binding.nextBtn.setOnClickListener(view -> {
            if (binding.field1Et.getText().toString().equals("")) {
                Toast.makeText(MainActivity.this, "Username  tidak boleh kosong", Toast.LENGTH_SHORT).show();
            } else {
                user.setNama(binding.field1Et.getText().toString());
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra(EXTRA_USER, user);
                startActivity(intent);
                section = "confirm";

            }
        });
    }
    public void confirm(){
        binding.field2Et.setVisibility(View.VISIBLE);
        binding.field1Et.setText("");
        binding.field2Et.setText("");
        binding.field1Et.setHint("Password");
        binding.field2Et.setHint("Confirm Password");
        binding.field1Et.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        binding.field2Et.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        binding.field1Til.setEndIconMode(TextInputLayout.END_ICON_PASSWORD_TOGGLE);
        binding.field2Til.setEndIconMode(TextInputLayout.END_ICON_PASSWORD_TOGGLE);
        binding.field1Et.setTransformationMethod(new PasswordTransformationMethod());
        binding.field2Et.setTransformationMethod(new PasswordTransformationMethod());
        binding.titleTv.setText("Make Acount");
        binding.subtitleTv.setVisibility(View.GONE);
        binding.nextBtn.setText("Register");
        binding.createAccountBtn.setVisibility(View.GONE);

        binding.nextBtn.setOnClickListener(view -> {
            if (binding.field1Et.getText().toString().equals(binding.field2Et.getText().toString())) {
                user.setPass(binding.field1Et.getText().toString());
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra(EXTRA_USER, user);
                startActivity(intent);
                section = "login";
            } else {
                Toast.makeText(MainActivity.this, "Password tidak sama", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
