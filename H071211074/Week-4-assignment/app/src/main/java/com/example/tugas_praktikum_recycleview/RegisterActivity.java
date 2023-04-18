package com.example.tugas_praktikum_recycleview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;

import com.example.tugas_praktikum_recycleview.databinding.ActivityRegisterBinding;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    User user;
    static String section = "login1";
    public final static String DATA_USER = "data user";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        if (getIntent().getParcelableExtra(DATA_USER) == null) {
            user = new User();
        } else {
            user = getIntent().getParcelableExtra(DATA_USER);
        }

        if (section.equals("login1")) {
            login1();
        } else if (section.equals("login2")) {
            login2();
        } else if (section.equals("register1")) {
            register1();
        } else {
            register2();
        }
    }
    public void login1() {
        binding.til1.setHint("Email");

        binding.mbtnNext.setOnClickListener(view -> {
            if (user.email == null || !binding.et1.getText().toString().equals(user.email)) {
                binding.et1.setError("couldn't find your De'Drive Account");
            } else {
                Intent intent = new Intent(this, RegisterActivity.class);
                intent.putExtra(DATA_USER, user);
                startActivity(intent);
                section = "login2";
            }
        });
        binding.btnCreateAcc.setOnClickListener(view -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            intent.putExtra(DATA_USER, user);
            startActivity(intent);
            section = "register1";
        });
    }
    public void login2() {
        binding.til1.setHint("Password");
        binding.btnCreateAcc.setVisibility(View.GONE);
        binding.et1.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        binding.til1.setEndIconMode(TextInputLayout.END_ICON_PASSWORD_TOGGLE);
        binding.et1.setTransformationMethod(new PasswordTransformationMethod());

        binding.mbtnNext.setOnClickListener(view -> {
            user = getIntent().getParcelableExtra(DATA_USER);
            if (user.password == null || !binding.et1.getText().toString().equals(user.password)) {
                binding.et1.setError("wrong password");
            } else {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra(DATA_USER, user);
                startActivity(intent);
            }
        });
    }
    public void register1() {
        binding.tvTitle.setText("Create a De'Drive Account");
        binding.til2.setVisibility(View.VISIBLE);
        binding.til1.setHint("Fullname");
        binding.til2.setHint("Email");
        binding.btnCreateAcc.setVisibility(View.GONE);

        binding.mbtnNext.setOnClickListener(view -> {
            user.fullname = binding.et1.getText().toString();
            user.email = binding.et2.getText().toString();
            if (user.fullname == null || user.email == null) {
                if (user.fullname == null) {
                    binding.et1.setError("name must be at least 3 characters");
                }
                if (user.email == null) {
                    binding.et2.setError("incorrect email format");
                }
                binding.et1.setError("name must be at least 3 characters");
                binding.et2.setError("incorrect email format");
                return;
            } else {
                Intent intent = new Intent(this, RegisterActivity.class);
                intent.putExtra(DATA_USER, user);
                startActivity(intent);
                section = "register2";
            }
            user.fullname = binding.et1.getText().toString();
            user.email = binding.et2.getText().toString();
        });
    }
    public void register2() {
        binding.tvTitle.setText("Create a De'Drive Account");
        binding.til2.setVisibility(View.VISIBLE);
        binding.et1.setHint("Password");
        binding.et2.setHint("Confirm Password");
        binding.et1.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        binding.til1.setEndIconMode(TextInputLayout.END_ICON_PASSWORD_TOGGLE);
        binding.et1.setTransformationMethod(new PasswordTransformationMethod());
        binding.et2.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        binding.til2.setEndIconMode(TextInputLayout.END_ICON_PASSWORD_TOGGLE);
        binding.et2.setTransformationMethod(new PasswordTransformationMethod());
        binding.btnCreateAcc.setVisibility(View.GONE);

        binding.mbtnNext.setOnClickListener(view -> {
            user.password = binding.et1.getText().toString();
            user.confirmPassword = binding.et2.getText().toString();
            if (user.password == null || binding.et1.length() < 8) {
                binding.et1.setError("password must be at least 8 characters");
            } else {
                Intent intent = new Intent(this, RegisterActivity.class);
                intent.putExtra(DATA_USER, user);
                startActivity(intent);
                section = "login1";
            }
        });
    }
}