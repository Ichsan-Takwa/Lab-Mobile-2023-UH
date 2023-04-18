package com.example.tugas_praktikum_recycleview;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.widget.Adapter;

import com.example.tugas_praktikum_recycleview.databinding.ActivityMainBinding;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    ActivityResultLauncher<Intent> imageSelectLauncher, profilSelectLauncher;
    ArrayList<ItemModel> listData;
    Uri image;
    ItemModel data;
    ItemAdapter adapter1, adapter2;
    int layout = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        data = new ItemModel();
        listData = new ArrayList<>();
        adapter1 = new ItemAdapter(this.listData, R.layout.item_grid1);
        adapter2 = new ItemAdapter(this.listData, R.layout.item_grid2);

        binding.rvItem.setLayoutManager(new GridLayoutManager(this, 1));
        binding.rvItem.addItemDecoration(
                new DividerItemDecoration(binding.rvItem.getContext(), DividerItemDecoration.VERTICAL)
        );

        imageSelectLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if(result.getResultCode() == RESULT_OK || result.getData() != null) {
                if (result.getData() != null) {
                    Intent intent = result.getData();
                    image = intent.getData();

                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                        Date date = new Date();
                        String currentTime = dateFormat.format(date);
                        String fileName = getFileName(image);

                        listData.add(new ItemModel(image, fileName, currentTime));
                        binding.rvItem.setAdapter(adapter1);
                    } catch (Exception exception) {
                        throw  new RuntimeException(exception);
                    }
                }
            }
        });

        profilSelectLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK || result.getData() != null) {
                Intent intent = result.getData();
                image = intent.getData();

                try {
                    binding.ivProfil.setImageURI(image);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        binding.fbtnUpload.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            imageSelectLauncher.launch(intent);
        });

        binding.ivProfil.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            profilSelectLauncher.launch(intent);
        });

        binding.ibGrid.setOnClickListener(view -> {
            layout = (layout +1) % 4;

            if (layout == 0) {
                binding.rvItem.setLayoutManager(new GridLayoutManager(this, 1));
                binding.rvItem.setAdapter(adapter1);
            } else if (layout == 1) {
                binding.rvItem.setLayoutManager(new GridLayoutManager(this, 2));
                binding.rvItem.setAdapter(adapter1);
            } else if (layout == 2){
                binding.rvItem.setLayoutManager(new GridLayoutManager(this, 3));
                binding.rvItem.setAdapter(adapter1);
            } else {
                binding.rvItem.setLayoutManager(new GridLayoutManager(this, 1));
                binding.rvItem.setAdapter(adapter2);
                return;
            }
        });

        ItemAdapter.onItemListener = new ItemAdapter.OnItemListener() {
            @Override
            public void onItemClick(int position) {
                ItemModel currentData = listData.get(position);
                Intent intent = new Intent(MainActivity.this, PhotoActivity.class);
                intent.putExtra(PhotoActivity.PHOTO, currentData);
                startActivity(intent);
            }
        };
    }

    @SuppressLint("Range")
    private String getFileName(Uri image) {
            String result = null;
            if (image.getScheme().equals("content")) {
                Cursor cursor = getContentResolver().query(image, null, null, null, null);
                try {
                    if (cursor != null && cursor.moveToFirst()) {
                        result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                    }
                } finally {
                    cursor.close();
                }
            }
            if (result == null) {
                result = image.getPath();
                int cut = result.lastIndexOf('/');
                if (cut != -1) {
                    result = result.substring(cut + 1);
                }
            }
            return result;
        }
}