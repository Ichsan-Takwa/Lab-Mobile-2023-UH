package com.example.tgsprak3intent;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
   CircleImageView imageProfile;
   EditText fullName, username;
   Button submit;

   ImageAccess picture;

   private ActivityResultLauncher<Intent>ProfileLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
       new ActivityResultCallback<ActivityResult>() {
           @Override
           public void onActivityResult(ActivityResult result) {
               if (result.getResultCode() == RESULT_OK && result.getData() !=null) {
                   Uri ChooseImage = result.getData().getData();
                   imageProfile.setImageURI(ChooseImage);
                   picture.setImageUri(ChooseImage);
               }
           }
       }
   );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit = findViewById(R.id.btn_submit);
        fullName = findViewById(R.id.et_fullname);
        username = findViewById(R.id.et_username);
        imageProfile = findViewById(R.id.img_profile);
        picture = new ImageAccess();

        imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentImage = new Intent(Intent.ACTION_GET_CONTENT);
                intentImage.setType("image/*");
                ProfileLauncher.launch(Intent.createChooser(intentImage, "Choose Your Photo"));

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = fullName.getText().toString();
                String user = username.getText().toString();

                Boolean isEmpty = false;

                if (picture.getImageUri() == null){
                    Toast.makeText(MainActivity.this, "Pilih foto !", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (nama.isEmpty()){
                    fullName.setError("Tidak boleh kosong!");
                    Toast.makeText(getApplicationContext(), "Silahkan isi nama anda", Toast.LENGTH_SHORT).show();
                    isEmpty = true;
                }
                if (user.isEmpty()) {
                        username.setError("Tidak boleh kosong!");
                        Toast.makeText(getApplicationContext(),"Silahkan isi username anda!", Toast.LENGTH_SHORT).show();
                        isEmpty = true;
                }
                if (!isEmpty){
                    Intent intent = new Intent(MainActivity.this, EditActivity.class);
                    intent.putExtra("FullName", nama);
                    intent.putExtra("Username", user);
                    intent.putExtra("profile", picture);
                    startActivity(intent);
                }
            }

        });

    }

}
