package com.example.tgsprak3intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class PostingActivity extends AppCompatActivity {
    ImageView profile, postImage;
    TextView username, fullname, textCaption;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posting);

        profile = findViewById(R.id.iv_profile);
        postImage = findViewById(R.id.imageView);
        username = findViewById(R.id.tv_username);
        fullname = findViewById(R.id.tv_fullname);
        textCaption = findViewById(R.id.caption);

        Intent intent = getIntent();
        String fullName = intent.getStringExtra("nama");
        String user = intent.getStringExtra("user");
        String caption = intent.getStringExtra("caption");
        ImageAccess profil = intent.getParcelableExtra("profile");
        ImageAccess pict = intent.getParcelableExtra("gambar");


        profile.setImageURI(profil.getImageUri());
        postImage.setImageURI(pict.getImageUri());
        textCaption.setText(caption);
        fullname.setText(fullName);
        username.setText(user);

    }
}
