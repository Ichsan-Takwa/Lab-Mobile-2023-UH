package com.example.tugas_praktikum_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.example.tugas_praktikum_intent.databinding.ActivityMainBinding;
import com.example.tugas_praktikum_intent.databinding.ActivityNewPostBinding;

public class NewPostActivity extends AppCompatActivity {

    private ActivityNewPostBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imageView1.setImageBitmap(User.profile);
        binding.textView1.setText(User.username);
        binding.textView2.setText(User.name);

        binding.imageView2.setImageBitmap(User.Post.photo);
        binding.textView3.setText(User.Post.deskripsi);
    }
}