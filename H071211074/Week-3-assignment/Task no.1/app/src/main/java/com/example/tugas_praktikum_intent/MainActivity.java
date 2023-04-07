package com.example.tugas_praktikum_intent;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import com.example.tugas_praktikum_intent.databinding.ActivityMainBinding;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    ActivityResultLauncher<Intent> imageSelectLauncher;
    private Bitmap image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        imageSelectLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK || result.getData() != null) {
                Intent intent = result.getData();
                Uri uri = intent.getData();

                try {
                    image = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    binding.imageView1.setImageBitmap(image);
                }catch (IOException ioException) {
                    throw new RuntimeException(ioException);
                }
            }
        });

        binding.imageView1.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            imageSelectLauncher.launch(intent);
            binding.imageView1.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#403E3E")));
        });

        binding.button1.setOnClickListener(view -> {
            Intent intent = new Intent(this, PostActivity.class);
            User.name = binding.editText1.getText().toString();
            User.username = binding.editText2.getText().toString();
            User.profile = image;

            if (User.name.isEmpty() || User.username.isEmpty() || User.profile == null) {
                if (User.name.isEmpty()) {
                    binding.editText1.setError("Silahkan di isi!");
                }
                if (User.username.isEmpty()) {
                    binding.editText2.setError("Silahkan di isi!");
                }
                if (User.profile == null) {
                    binding.imageView1.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#D51A1A")));
                }
                return;
            }
            startActivity(intent);
        });
    }
}