package com.example.tgsprak3quiz;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

import com.google.android.material.imageview.ShapeableImageView;

public class User implements Parcelable {

    String name;
    int Score;
    int BestScore = 0;

    protected User(Parcel in) {
        name = in.readString();
        Score = in.readInt();
        BestScore = in.readInt();
    }
    public User(){

    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(Score);
        parcel.writeInt(BestScore);
    }
}