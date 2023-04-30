package com.example.drive;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.drive.databinding.ActivityDisplayBinding;

public class DisplayActivity extends AppCompatActivity {
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