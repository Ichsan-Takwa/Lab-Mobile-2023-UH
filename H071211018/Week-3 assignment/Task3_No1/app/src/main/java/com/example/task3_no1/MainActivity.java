package com.example.task3_no1;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.task3_no1.databinding.ActivityMainBinding;

import java.net.URI;

public class MainActivity extends AppCompatActivity {
    private ImageView profil;
    private TextView fullname, username;
    private Button submit;
    Photo fotoprofil;
    boolean isEmpty = false;

    private ActivityResultLauncher<Intent> launcherIntentImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null){
                        Uri selectedPhoto = result.getData().getData();
                        profil.setImageURI(selectedPhoto);
                        fotoprofil.setFotoUri(selectedPhoto);
                    }

                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profil = findViewById(R.id.profil);
        fullname = findViewById(R.id.fname);
        username = findViewById(R.id.uname);
        submit = findViewById(R.id.submit);
        fotoprofil = new Photo();

        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPicker = new Intent(Intent.ACTION_GET_CONTENT);
                intentPicker.setType("image/*");
                launcherIntentImage.launch(Intent.createChooser(intentPicker, "Choose a Photo"));
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fotoprofil.getFotoUri() == null || fullname.getText().toString().isEmpty() || username.getText().toString().isEmpty()){
                    if (fotoprofil.getFotoUri() == null){
                        Toast.makeText(MainActivity.this, "Pick a Profil First", Toast.LENGTH_SHORT).show();
                    }
                    if (fullname.getText().toString().isEmpty()){
                        fullname.setError("Data tidak boleh kosong");
                        Toast.makeText(MainActivity.this, "Enter your fullname", Toast.LENGTH_SHORT).show();
                    }
                    if (username.getText().toString().isEmpty()){
                        username.setError("Data tidak boleh kosong");
                        Toast.makeText(MainActivity.this, "Enter yout username", Toast.LENGTH_SHORT).show();
                    }
                    return;
                }

                if (!isEmpty){
                    Intent submit = new Intent(getApplicationContext(), MainActivity2.class);
                    submit.putExtra("profil", fotoprofil);
                    submit.putExtra("fullname", fullname.getText().toString());
                    submit.putExtra("username", username.getText().toString());
                    startActivity(submit);
                }

            }
        });

    }

}