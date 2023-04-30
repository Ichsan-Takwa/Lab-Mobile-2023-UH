package com.example.task4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    private ImageView iv_foto;
    private TextView tv_fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        iv_foto = findViewById(R.id.iv_photo);
        tv_fileName = findViewById(R.id.tv_fileName);

        Intent terima = getIntent();
        Uri foto = terima.getParcelableExtra("foto");
        String namaFoto = terima.getStringExtra("namaFile");

        Glide.with(DetailActivity.this).load(foto).into(iv_foto);
        tv_fileName.setText(namaFoto);
    }
}