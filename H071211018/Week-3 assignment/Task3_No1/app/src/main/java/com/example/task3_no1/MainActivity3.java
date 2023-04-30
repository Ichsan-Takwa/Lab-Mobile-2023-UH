package com.example.task3_no1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {
    private ImageView profil, image;
    private TextView tv_fullname, tv_username, tv_caption;
    String fullname, username, caption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        profil = findViewById(R.id.profil);
        image = findViewById(R.id.img);
        tv_fullname = findViewById(R.id.fname);
        tv_username = findViewById(R.id.uname);
        tv_caption = findViewById(R.id.capt);

        Intent intent = getIntent();
        fullname = intent.getStringExtra("fullname");
        username = intent.getStringExtra("username");
        Photo pp = intent.getParcelableExtra("profil");
        Photo post = intent.getParcelableExtra("image");
        caption = intent.getStringExtra("caption");
        assert  pp != null;
        assert post != null;

        profil.setImageURI(pp.getFotoUri());
        image.setImageURI(post.getFotoUri());
        tv_caption.setText(caption);
        tv_fullname.setText(fullname);
        tv_username.setText(username);
    }
}