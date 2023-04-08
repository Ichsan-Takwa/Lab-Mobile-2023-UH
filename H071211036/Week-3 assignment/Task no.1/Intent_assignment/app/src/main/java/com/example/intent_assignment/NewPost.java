package com.example.intent_assignment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class NewPost extends AppCompatActivity {

    private EditText caption;
    private ImageView imageView;
    private Button button;

    String fullname, username;
    Photo userPhoto;
    Uri postPhotoUri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_post);

        imageView = findViewById(R.id.profile_image);
        button = findViewById(R.id.btn_upload);
        caption = findViewById(R.id.caption);

        fullname = getIntent().getStringExtra("FULLNAME");
        username = getIntent().getStringExtra("USERNAME");
        userPhoto = getIntent().getParcelableExtra("PHOTO");

        ActivityResultLauncher<Intent> activityResultLauncher =
                registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            if (data != null) {
                                postPhotoUri = data.getData();
                                imageView.setImageURI(postPhotoUri);
                            }
                        }
                    }
                });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                activityResultLauncher.launch(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String captionText = caption.getText().toString();

                Intent intent2 = new Intent(NewPost.this, FinalPost.class);
                intent2.putExtra("FULLNAME", fullname);
                intent2.putExtra("USERNAME", username);
                intent2.putExtra("USER_PHOTO", userPhoto);
                intent2.putExtra("CAPTION", captionText);

                Photo postPhoto = new Photo(postPhotoUri);
                intent2.putExtra("POST_PHOTO", postPhoto);
                startActivity(intent2);
            }
        });
    }
}
