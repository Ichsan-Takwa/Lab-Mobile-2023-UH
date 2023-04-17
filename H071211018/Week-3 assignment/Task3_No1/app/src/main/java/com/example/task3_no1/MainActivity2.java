package com.example.task3_no1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Scanner;

public class MainActivity2 extends AppCompatActivity {
    private ImageView image;
    private EditText caption;
    private Button upload;
    Photo foto, profil;
    boolean isEmpty = false;

    private ActivityResultLauncher<Intent> launcherIntentImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null){
                        Uri selectedPhoto = result.getData().getData();
                        foto.setFotoUri(selectedPhoto);
                        image.setImageURI(selectedPhoto);
                    }
                }
            }
    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        foto = new Photo();

        image = findViewById(R.id.img);
        caption = findViewById(R.id.capt);
        upload = findViewById(R.id.upload);

        Intent intent = getIntent();
        String fullname = intent.getStringExtra("fullname");
        String username = intent.getStringExtra("username");
        profil = intent.getParcelableExtra("profil");
        Photo post = new Photo();

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPicker = new Intent(Intent.ACTION_GET_CONTENT);
                intentPicker.setType("image/*");
                launcherIntentImage.launch(Intent.createChooser(intentPicker, "Choose a Photo"));
            }
        });


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (foto.getFotoUri() == null){
                    Toast.makeText(MainActivity2.this, "Pick a Profil First", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isEmpty){
                    Intent upload = new Intent(getApplicationContext(), MainActivity3.class);
                    upload.putExtra("image", foto);
                    upload.putExtra("caption", caption.getText().toString());
                    upload.putExtra("fullname", fullname);
                    upload.putExtra("username", username);
                    upload.putExtra("profil", profil);
                    upload.putExtra("post", post);
                    startActivity(upload);

                }

            }
        });
    }


}