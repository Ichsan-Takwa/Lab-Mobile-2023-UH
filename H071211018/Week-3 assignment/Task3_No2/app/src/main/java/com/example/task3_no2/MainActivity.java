package com.example.task3_no2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageView profil;
    private EditText etName;
    private TextView tvName, bScore;
    private Button btnApply;
    Bitmap ProfilPict;
    User user;

    private ActivityResultLauncher<Intent> launcherIntentImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null){
                        Uri selectedPhoto = result.getData().getData();
                        profil.setImageURI(selectedPhoto);
                    }

                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profil = findViewById(R.id.iv_profil);
        etName = findViewById(R.id.et_name);
        tvName = findViewById(R.id.tv_name);
        btnApply = findViewById(R.id.btn_apply);
        bScore = findViewById(R.id.tv_bScore);

        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPicker = new Intent(Intent.ACTION_GET_CONTENT);
                intentPicker.setType("image/*");
                launcherIntentImage.launch(Intent.createChooser(intentPicker, "Choose a Photo"));
            }
        });

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputName = etName.getText().toString();
                if (inputName.isEmpty()){
                    etName.setError("Enter your name");
                    return;
                }

                if (User.name == null){
                    tvName.setText(inputName);
                    btnApply.setText("Play");
                    tvName.setVisibility(View.VISIBLE);
                    bScore.setVisibility(View.VISIBLE);
                    etName.setVisibility(View.GONE);
                    String outputName = etName.getText().toString();
                    User.name = inputName;
                    User.profilPict = profil.getDrawingCache();
                    User.name = outputName;
                    User.setName(inputName);
                    Toast.makeText(MainActivity.this, "name set to " + User.getName, Toast.LENGTH_SHORT).show();

                }else {
                    Intent intent = new Intent(MainActivity.this, GameActivity3.class);
                    intent.putExtra(HasilActivity4.EXTRA_USER, user);
                    startActivity(intent);

                }
            }
        });

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        bScore.setText(User.bestScore + "");
    }
}