package com.example.praktikum31;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Outline;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class post extends AppCompatActivity {
    Uri imageUri;
    Button upload;
    EditText captionText;
    ImageView postImage;
    private final String EXTRA_FULLNAME = "EXTRA_FULLNAME";
    private final String EXTRA_CAPTION = "EXTRA_CAPTION";
    private final String EXTRA_USERNAME = "EXTRA_USERNAME";
    private final String EXTRA_PROFILE_PICTURE = "EXTRA_PROFILE_PICTURE";
    private final String EXTRA_UPLOAD_PICTURE = "EXTRA_UPLOAD_PICTURE";
    int SELECT_UPLOAD_IMAGE_CODE = 2;
    boolean imageSelected = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        postImage = findViewById(R.id.postImage);
        captionText = findViewById(R.id.captionEditText);
        upload = findViewById(R.id.upload);

        Intent intent = getIntent();
        String imageUriString = intent.getStringExtra(EXTRA_PROFILE_PICTURE);
        String fullname = intent.getStringExtra(EXTRA_FULLNAME);
        String username = intent.getStringExtra(EXTRA_USERNAME);
        Uri profile_picture = Uri.parse(imageUriString);

        postImage.setOnClickListener(view -> {
            Intent imageIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(imageIntent, SELECT_UPLOAD_IMAGE_CODE);
        });

        upload.setOnClickListener(view -> {
            if (imageSelected) {
                Toast.makeText(post.this, "Please Select a picture first", Toast.LENGTH_SHORT).show();
            } else {
                String caption = captionText.getText().toString();
                Intent uploadIntent = new Intent(post.this, upload.class);
                uploadIntent.putExtra(EXTRA_CAPTION, caption);
                uploadIntent.putExtra(EXTRA_FULLNAME, fullname);
                uploadIntent.putExtra(EXTRA_USERNAME, username);
                uploadIntent.putExtra(EXTRA_PROFILE_PICTURE, profile_picture.toString());
                uploadIntent.putExtra(EXTRA_UPLOAD_PICTURE, imageUri.toString());
                startActivity(uploadIntent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (SELECT_UPLOAD_IMAGE_CODE == 2 && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            postImage.setImageURI(imageUri);
            imageSelected = false;
        } else {
            Toast.makeText(this, "Failed to get Image", Toast.LENGTH_SHORT).show();
        }

    }
}