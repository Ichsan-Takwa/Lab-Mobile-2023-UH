package com.example.driveku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.driveku.databinding.ActivityDisplayBinding;
import com.example.driveku.databinding.ActivityExplorerBinding;
import com.example.driveku.databinding.ActivityMainBinding;

public class Display extends AppCompatActivity {
    ActivityDisplayBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDisplayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FileModel data = getIntent().getParcelableExtra("File");

        binding.filenameTv.setText(data.fileName);
        binding.displayImage.setImageURI(data.imageUri);

        binding.backBt.setOnClickListener(view -> {
            finish();
        });
    }
}