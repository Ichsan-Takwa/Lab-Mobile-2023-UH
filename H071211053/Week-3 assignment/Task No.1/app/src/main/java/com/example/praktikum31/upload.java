package com.example.praktikum31;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Outline;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.TextView;

public class upload extends AppCompatActivity {
    TextView username, fullname, caption;
    ImageView photo_profile, uploadImage;
    private final String EXTRA_FULLNAME = "EXTRA_FULLNAME";
    private final String EXTRA_CAPTION = "EXTRA_CAPTION";
    private final String EXTRA_USERNAME = "EXTRA_USERNAME";
    private final String EXTRA_PROFILE_PICTURE = "EXTRA_PROFILE_PICTURE";
    private final String EXTRA_UPLOAD_PICTURE = "EXTRA_UPLOAD_PICTURE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        username = findViewById(R.id.username);
        fullname = findViewById(R.id.fullname);
        caption = findViewById(R.id.caption);
        photo_profile = findViewById(R.id.profile_picture);
        uploadImage = findViewById(R.id.uploadImage);

        Intent intent = getIntent();
        String intent_caption = intent.getStringExtra(EXTRA_CAPTION);
        String imageUploadString = intent.getStringExtra(EXTRA_UPLOAD_PICTURE);
        String imageUriString = intent.getStringExtra(EXTRA_PROFILE_PICTURE);
        String intent_fullname = intent.getStringExtra(EXTRA_FULLNAME);
        String intent_username = intent.getStringExtra(EXTRA_USERNAME);
        Uri profile_picture = Uri.parse(imageUriString);
        Uri upload_picture = Uri.parse(imageUploadString);

        username.setText(intent_username);
        caption.setText(intent_caption);
        fullname.setText(intent_fullname);
        photo_profile.setImageURI(profile_picture);
        uploadImage.setImageURI(upload_picture);

        photo_profile.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                int size = Math.min(view.getWidth(), view.getHeight());
                outline.setOval(0, 0, size, size);
            }
        });
        photo_profile.setClipToOutline(true);
    }
}