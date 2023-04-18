package com.example.task4;

import android.net.Uri;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileModel {
    String addedDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    Uri fileUri;
    String fileName;

    public FileModel(Uri fileUri){
        this.fileUri = fileUri;
        this.fileName = new File(fileUri.getPath()).getName();
    }
}
