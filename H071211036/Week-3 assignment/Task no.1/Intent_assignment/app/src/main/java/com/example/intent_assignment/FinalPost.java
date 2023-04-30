package com.example.intent_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class FinalPost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.final_post);

        ImageView ivProfileImage = findViewById(R.id.profile_image2);
        ImageView ivPostImage = findViewById(R.id.imageView_posting);
        TextView tvFullname = findViewById(R.id.textView_fullname);
        TextView tvUsername = findViewById(R.id.textView_username);
        TextView tvCaption = findViewById(R.id.textView_caption);

        Photo userPhoto = getIntent().getParcelableExtra("USER_PHOTO");
        Photo postPhoto = getIntent().getParcelableExtra("POST_PHOTO");
        String fullname = getIntent().getStringExtra("FULLNAME");
        String username = getIntent().getStringExtra("USERNAME");
        String caption = getIntent().getStringExtra("CAPTION");

        ivProfileImage.setImageURI(userPhoto.getUriPhoto());
        ivPostImage.setImageURI(postPhoto.getUriPhoto());
        tvFullname.setText(fullname);
        tvUsername.setText(username);
        tvCaption.setText(caption);
    }
}