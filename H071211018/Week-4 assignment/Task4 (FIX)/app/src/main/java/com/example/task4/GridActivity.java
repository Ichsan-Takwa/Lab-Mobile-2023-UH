package com.example.task4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class GridActivity extends AppCompatActivity {
    private ImageButton ib_back;
    private TextView tv_fileName;
    private ImageView iv_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        ib_back = findViewById(R.id.ib_back);
        tv_fileName = findViewById(R.id.tv_fileName);
        iv_photo = findViewById(R.id.iv_photo);
    }
}