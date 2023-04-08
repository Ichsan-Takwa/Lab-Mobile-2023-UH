package com.example.intent_assignment;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    Bitmap bitmap2;

    private CircleImageView pickPicture;
    private Button button;
    private TextInputEditText full_name,username;

    private Uri selectedPhoto;

    ActivityResultLauncher<Intent> activityResultLauncher =
        registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK){
                    Intent data = result.getData();
                    if (data != null) {
                        selectedPhoto = data.getData();
                        pickPicture.setImageURI(selectedPhoto);
                    }
                }
            }
        });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pickPicture = findViewById(R.id.profile_image);
        button = findViewById(R.id.btn_submit);
        full_name = findViewById(R.id.full_name);
        username = findViewById(R.id.username);

        pickPicture.setOnClickListener(new View.OnClickListener() {
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
                String fullname = full_name.getText().toString();
                String usernama = username.getText().toString();

                if (fullname.isEmpty() && usernama.isEmpty()){
                    full_name.setError("Field can't be empty");
                    username.setError("Field can't be empty");
                } else if(fullname.isEmpty()) {
                    full_name.setError("Field can't be empty");
                } else if (usernama.isEmpty()){
                    username.setError("Field can't be empty");
                } else{
                    Intent intent = new Intent(MainActivity.this, NewPost.class);
                    intent.putExtra("FULLNAME", fullname);
                    intent.putExtra("USERNAME", usernama);

                    Photo userPhoto = new Photo(selectedPhoto);
                    intent.putExtra("PHOTO", userPhoto);
                    startActivity(intent);
                }
            }
        });
    }

    public void submit(View view) {
    }
}