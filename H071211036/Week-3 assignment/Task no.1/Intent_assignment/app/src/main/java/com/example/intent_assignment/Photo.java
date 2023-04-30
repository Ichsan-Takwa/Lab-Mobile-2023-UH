package com.example.intent_assignment;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Photo implements Parcelable {
    private Uri uriPhoto;

    public Photo(Uri uriPhoto) {
        this.uriPhoto = uriPhoto;
    }

    protected Photo(Parcel in) {
        uriPhoto = in.readParcelable(Uri.class.getClassLoader());
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

    public Uri getUriPhoto() {
        return uriPhoto;
    }

    public void setUriPhoto(Uri uriPhoto) {
        this.uriPhoto = uriPhoto;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(uriPhoto, flags);
    }
}
