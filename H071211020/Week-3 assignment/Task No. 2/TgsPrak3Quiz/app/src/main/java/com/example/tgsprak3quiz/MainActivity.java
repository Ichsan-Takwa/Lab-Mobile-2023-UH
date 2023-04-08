package com.example.tgsprak3quiz;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.example.tgsprak3quiz.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    User user;
    ActivityResultLauncher<Intent> imageSelectlauncher;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        user = new User();

        imageSelectlauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                Uri imgUri = result.getData().getData();
                binding.ivProfile.setImageURI(imgUri);
            }
        });

        binding.ivProfile.setOnClickListener(v -> {
            Intent getPicture = new Intent(Intent.ACTION_PICK);
            getPicture.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            imageSelectlauncher.launch(getPicture);
        });

    binding.btnMulai.setOnClickListener(v -> {
        String inputNama = binding.etNama.getText().toString();

        if (inputNama.isEmpty()){
            binding.etNama.setError("Nama tidak boleh kosong");
            return;}
        if (user.name == null){
            binding.tvNama.setText(inputNama);
            binding.tvBestScore.setText("Best Score : " + user.BestScore);
            binding.tvNama.setVisibility(View.VISIBLE);
            binding.tvBestScore.setVisibility(View.VISIBLE);
            binding.etNama.setVisibility(View.GONE);
            binding.btnMulai.setText("Mulai");
            user.name = inputNama;


        }else{
            Intent intent = new Intent(this, Question.class);
            intent.putExtra(Question.EXSTRA_USER,user);
            startActivity(intent);

        }

        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        user = intent.getParcelableExtra(Question.EXSTRA_USER);
        binding.tvBestScore.setText("Best Score : " + user.BestScore);

    }
}