package com.example.task4;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class DisplayMenu extends AppCompatActivity {
    private ImageView iv_profil;
    private SearchView sv_serach;
    private ImageButton btn_upload;
    private RecyclerView rv_display;
    AdapterRV lihatRV;
    private ArrayList<FileModel> upFileList;

    private ActivityResultLauncher<Intent> launcherIntentImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null){
                        Uri selectedPhoto = result.getData().getData();
                        iv_profil.setImageURI(selectedPhoto);
                    }

                }
            }
    );

    private ActivityResultLauncher<Intent> launcherImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            DisplayMenu.this.upFile(result);
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_menu);

        upFileList = new ArrayList<>();
        iv_profil = findViewById(R.id.iv_profil);
        sv_serach = findViewById(R.id.sv_search);
        btn_upload = findViewById(R.id.btn_upload);
        rv_display = findViewById(R.id.rv_display);
        System.out.println(upFileList);

        rv_display.setLayoutManager(new LinearLayoutManager(this));
        lihatRV = new AdapterRV(upFileList, this);
        rv_display.setAdapter(lihatRV);
        
        sv_serach.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                DisplayMenu.test(view);
            }
        });

        sv_serach.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<FileModel> cariGambar = new ArrayList<>();
                Iterator<FileModel> it = upFileList.iterator();
                while(it.hasNext()){
                    FileModel gambar = it.next();
                    if(gambar.fileName.toLowerCase().contains(newText.toLowerCase())){
                        cariGambar.add(gambar);
                    }
                }
                if(!newText.isEmpty()){
                    lihatRV.listData = cariGambar;
                    lihatRV.notifyDataSetChanged();
                    return false;
                }
                lihatRV.listData = upFileList;
                lihatRV.notifyDataSetChanged();
                return false;
            }
        });

        iv_profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPicker = new Intent(Intent.ACTION_GET_CONTENT);
                intentPicker.setType("image/*");
                launcherIntentImage.launch(Intent.createChooser(intentPicker, "Choose a Photo"));
            }
        });

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.PICK");
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                launcherImage.launch(intent);
            }
        });
    }

    private static void test(View view) {
    }

    public void upFile(ActivityResult result){
        if(result.getResultCode() == RESULT_OK && result.getData() != null){
            Uri fileUri = result.getData().getData();
            FileModel upFile = new FileModel(fileUri);
            upFileList.add(upFile);
            lihatRV.notifyDataSetChanged();
        }
    }
}