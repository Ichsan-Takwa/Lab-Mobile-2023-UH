package com.example.tugas_praktikum_intent;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import com.example.tugas_praktikum_intent.databinding.ActivityMainBinding;
import com.example.tugas_praktikum_intent.databinding.ActivityPostBinding;

import java.io.IOException;

public class PostActivity extends AppCompatActivity {

    ActivityResultLauncher <Intent> imageSelectLauncher;
    private ActivityPostBinding binding;
    private Bitmap image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        imageSelectLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK || result.getData() != null) {
                Intent intent = result.getData();
                Uri uri = intent.getData();

                try {
                    image = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    binding.imageView1.setImageBitmap(image);
                } catch (IOException ioException) {
                    throw new RuntimeException(ioException);
                }
            }
        });

        binding.imageView1.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            imageSelectLauncher.launch(intent);
        });

        binding.button1.setOnClickListener(view -> {
            Intent intent = new Intent(this, NewPostActivity.class);
            User.Post.deskripsi = binding.editText1.getText().toString();
            User.Post.photo = image;

            if (User.Post.deskripsi.isEmpty() || User.Post.photo == null) {
                if (User.Post.deskripsi.isEmpty()) {
                    binding.editText1.setError("Silahkan di isi!");
                }
                if (User.Post.photo == null) {
                    Toast.makeText(this, "Silahkan masukkan foto!", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            startActivity(intent);
        });
    }
}