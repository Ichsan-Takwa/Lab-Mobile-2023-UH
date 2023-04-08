package com.example.tgsprak3intent;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {
    ImageView imgpost;
    EditText textcapt;
    Button buttonPost;
    ImageAccess setProfile, UploudPict;


    private ActivityResultLauncher<Intent> PictureLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
        new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK && result.getData() !=null) {
                    Uri ChooseImage = result.getData().getData();
                    imgpost.setImageURI(ChooseImage);
                    UploudPict.setImageUri(ChooseImage);
                }
            }
        }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        imgpost = findViewById(R.id.iv_postimage);
        textcapt = findViewById(R.id.et_caption);
        buttonPost = findViewById(R.id.btn_post);

        Intent intent = getIntent();
        String nama = intent.getStringExtra("FullName");
        String user = intent.getStringExtra("Username");

        setProfile = intent.getParcelableExtra("profile");
        UploudPict = new ImageAccess();


        imgpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentImage = new Intent(Intent.ACTION_GET_CONTENT);
                intentImage.setType("image/*");
                PictureLauncher.launch(Intent.createChooser(intentImage, "Choose Your Photo"));
            }
        });



        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isEmpty = false;
                String capt = textcapt.getText().toString();

                if (UploudPict.getImageUri() == null){
                    Toast.makeText(EditActivity.this, "Pilih gambar!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isEmpty) {
                    Intent edit = new Intent(EditActivity.this, PostingActivity.class);
                    edit.putExtra("nama", nama);
                    edit.putExtra("user", user);
                    edit.putExtra("caption", capt);
                    edit.putExtra("profile", setProfile);
                    edit.putExtra("gambar", UploudPict);
                    startActivity(edit);
                }

            }
        });
    }
}
