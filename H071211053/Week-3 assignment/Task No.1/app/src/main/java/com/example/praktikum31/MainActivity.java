package com.example.praktikum31;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Outline;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView fullnameText, usernameText;
    Uri imageUri;
    Button login;
    private final String EXTRA_FULLNAME = "EXTRA_FULLNAME";
    private final String EXTRA_USERNAME = "EXTRA_USERNAME";
    private final String EXTRA_PROFILE_PICTURE = "EXTRA_PROFILE_PICTURE";
    int SELECT_IMAGE_CODE = 1;
    boolean imageSelected = true, usernameFill = true, fullnameFill = true;
    ImageView profile_picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login);
        fullnameText = findViewById(R.id.fullnameEditText);
        usernameText = findViewById(R.id.usernameEditText);
        profile_picture = findViewById(R.id.profilImage);

        if (!usernameText.getText().toString().equals("")) {
            usernameFill = false;
        }
        if (!fullnameText.getText().toString().equals("")) {
            fullnameFill = false;
        }

        login.setOnClickListener(view -> {
            if (fullnameFill || imageSelected || usernameFill) {
                Toast.makeText(MainActivity.this, "Please fill all the required data", Toast.LENGTH_SHORT).show();
            } else {
                String fullname = fullnameText.getText().toString();
                String username = fullnameText.getText().toString();
                Intent intent = new Intent(MainActivity.this, post.class);
                intent.putExtra(EXTRA_FULLNAME, fullname);
                intent.putExtra(EXTRA_USERNAME, username);
                intent.putExtra(EXTRA_PROFILE_PICTURE, imageUri.toString());
                startActivity(intent);
            }
        });

        profile_picture.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, SELECT_IMAGE_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (SELECT_IMAGE_CODE == 1 && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            profile_picture.setImageURI(imageUri);

            profile_picture.setOutlineProvider(new ViewOutlineProvider() {
                @Override
                public void getOutline(View view, Outline outline) {
                    int size = Math.min(view.getWidth(), view.getHeight());
                    outline.setOval(0, 0, size, size);
                }
            });
            profile_picture.setClipToOutline(true);
            imageSelected = false;
        } else {
            Toast.makeText(this, "Failed to get Image", Toast.LENGTH_SHORT).show();
        }

    }
}