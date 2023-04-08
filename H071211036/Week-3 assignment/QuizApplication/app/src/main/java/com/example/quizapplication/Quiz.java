 package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

 public class Quiz extends AppCompatActivity {

    private CircleImageView image1;

    private Button button1;

    private EditText editText;

     private Uri imageUri;

     String text1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        image1 = findViewById(R.id.imagecircle);
        button1 = findViewById(R.id.button1);
        editText = findViewById(R.id.edittext1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getText().toString().isEmpty()){
                    editText.setError("Required");
                }else if(imageUri == null){
                    Toast.makeText(Quiz.this, "Pick a photo", Toast.LENGTH_SHORT).show();
                }else{
                    editText.setError(null);
                    Intent intent = new Intent(Quiz.this,Welcome.class);
                    text1 = editText.getText().toString();
                    intent.putExtra("text2","0");
                    intent.putExtra("text1",text1);
                    intent.putExtra("image1",imageUri.toString());
                    startActivity(intent);
                    finish();


                }
            }
        });

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,0);
            }
        });



    }
     @Override
     protected void onActivityResult(int requestCode,int resultCode,Intent data){
         super.onActivityResult(requestCode,resultCode,data);

         if(requestCode == 0 && resultCode == RESULT_OK && data != null){
             imageUri = data.getData();
             image1.setImageURI(imageUri);
         }

     }
}