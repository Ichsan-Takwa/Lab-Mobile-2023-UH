package com.example.tugas_praktikum_recycleview;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ItemModel implements Parcelable {
    Uri photo;
    String filename;
    String addedDate;

    public ItemModel(Uri photo, String filename, String addedDate) {
        this.photo = photo;
        this.filename = filename;
        this.addedDate = addedDate;
    }

    public ItemModel() {

    }

    protected ItemModel(Parcel in) {
        photo = in.readParcelable(Uri.class.getClassLoader());
        filename = in.readString();
        addedDate = in.readString();
    }

    public static final Creator<ItemModel> CREATOR = new Creator<ItemModel>() {
        @Override
        public ItemModel createFromParcel(Parcel in) {
            return new ItemModel(in);
        }

        @Override
        public ItemModel[] newArray(int size) {
            return new ItemModel[size];
        }
    };

    public Uri getPhoto() {
        return photo;
    }

    public void setPhoto(Uri photo) {
        this.photo = photo;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeParcelable(photo, i);
        parcel.writeString(filename);
        parcel.writeString(addedDate);
    }
}
