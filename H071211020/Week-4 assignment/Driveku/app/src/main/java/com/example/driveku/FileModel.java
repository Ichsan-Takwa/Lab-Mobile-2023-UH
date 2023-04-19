package com.example.driveku;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.net.URI;
import java.nio.file.Path;

public class FileModel implements Parcelable {
    Uri imageUri;
    String fileName;
    String time;

    protected FileModel(Parcel in) {
        imageUri = in.readParcelable(Uri.class.getClassLoader());
        fileName = in.readString();
        time = in.readString();
    }

    public static final Creator<FileModel> CREATOR = new Creator<FileModel>() {
        @Override
        public FileModel createFromParcel(Parcel in) {
            return new FileModel(in);
        }

        @Override
        public FileModel[] newArray(int size) {
            return new FileModel[size];
        }
    };

    public FileModel() {}


    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeParcelable(imageUri, i);
        parcel.writeString(fileName);
        parcel.writeString(time);
    }
}
