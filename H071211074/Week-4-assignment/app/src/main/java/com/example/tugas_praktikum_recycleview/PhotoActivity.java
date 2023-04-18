package com.example.tugas_praktikum_recycleview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.tugas_praktikum_recycleview.databinding.ActivityPhotoBinding;

public class PhotoActivity extends AppCompatActivity {

    private ActivityPhotoBinding binding;
    public final static String PHOTO= "photo";
    ItemModel data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhotoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        data = getIntent().getParcelableExtra(PHOTO);

        binding.ivPhoto.setImageURI(data.photo);

        binding.ibBack.setOnClickListener(view -> {
           finish();
        });
    }
}