package com.example.task3_no1;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Photo implements Parcelable{
    private Uri fotoUri;

    public Photo() {}

    protected Photo(Parcel in) {
        fotoUri = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<Photo> CREATOR = new Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };

    public Uri getFotoUri() {
        return fotoUri;
    }

    public void setFotoUri(Uri fotoUri) {
        this.fotoUri = fotoUri;
    }

    public Photo(Uri fotoUri) {
        this.fotoUri = fotoUri;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeParcelable(fotoUri, i);
    }


}
