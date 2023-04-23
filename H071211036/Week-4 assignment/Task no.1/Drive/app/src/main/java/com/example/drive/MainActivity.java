package com.example.drive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.drive.databinding.ActivityCreateBinding;
import com.example.drive.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.start.setOnClickListener(view -> {
                Intent intent = new Intent(this, CreateActivity.class);
                startActivity(intent);
        });
    }
}