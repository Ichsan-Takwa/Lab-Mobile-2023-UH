package com.example.drive;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.drive.databinding.ActivityExplorerBinding;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ExplorerActivity extends AppCompatActivity {

    private ActivityExplorerBinding binding;


    ArrayList<FileModel> postList;
    RecycleViewAdapter adapter, adapterLagi;
    FileModel fileModel;
    ActivityResultLauncher<Intent> imageSelectLauncher;
    Uri imageUri;
    int tampilan;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExplorerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tampilan = 0;
        postList = new ArrayList<>();
        adapter = new RecycleViewAdapter(this, postList, R.layout.item_file_detail);
        adapterLagi = new RecycleViewAdapter(this, postList, R.layout.item_file_preview);
        fileModel = new FileModel();

        binding.explorerRv.setAdapter(adapter);
        binding.explorerRv.setLayoutManager(new LinearLayoutManager(this));

        imageSelectLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                imageUri = result.getData().getData();
                fileModel.setImageUri(imageUri);
                File file = new File(imageUri.getPath());
                fileModel.setFileName(file.getName());
            }
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalDateTime getNow = LocalDateTime.now();
            fileModel.setTime(dateTimeFormatter.format(getNow));

            postList.add(fileModel);
            adapter.notifyDataSetChanged();
            adapterLagi.notifyDataSetChanged();

        });

        binding.addFileBt.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.setType("image/*");
            imageSelectLauncher.launch(intent);
        });

        binding.changeLayoutBt.setOnClickListener(view -> {
            tampilan =(tampilan+1) %3;
            if (tampilan == 0) {
                binding.explorerRv.setAdapter(adapter);
                binding.explorerRv.setLayoutManager(new LinearLayoutManager(this));
            } else if (tampilan == 1) {
                binding.explorerRv.setAdapter(adapterLagi);
                binding.explorerRv.setLayoutManager(new GridLayoutManager(this,2));
            } else if (tampilan == 2) {
                binding.explorerRv.setAdapter(adapterLagi);
                binding.explorerRv.setLayoutManager(new GridLayoutManager(this,3));
            }

        });
    }
}