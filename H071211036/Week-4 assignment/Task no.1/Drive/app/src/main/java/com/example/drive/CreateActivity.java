package com.example.drive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.drive.databinding.ActivityCreateBinding;
import com.example.drive.databinding.ActivityMainBinding;

public class CreateActivity extends AppCompatActivity {

    private ActivityCreateBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.registerBt.setOnClickListener(view -> {
            Intent intent = new Intent(this, ExplorerActivity.class);
            startActivity(intent);
        });
    }
}