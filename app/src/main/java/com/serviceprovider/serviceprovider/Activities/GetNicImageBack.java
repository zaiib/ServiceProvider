package com.serviceprovider.serviceprovider.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.serviceprovider.serviceprovider.R;

import java.io.IOException;
import java.util.UUID;

public class GetNicImageBack extends AppCompatActivity {


    ImageView nicBack;
    Button btnImagePick,btnUplaodBack;
    private Uri filePath;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_nic_image_back);

        init();
        storage =  FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        btnUplaodBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uplaodInFireabase();
            }
        });
        btnImagePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePick();
            }
        });
    }

    private void ImagePick() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Image"),1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        assert data != null;
        filePath = data.getData();

        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
            nicBack.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
    private void uplaodInFireabase() {
        if (filePath != null){
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();
            StorageReference reference = storageReference.child("images/" + UUID.randomUUID().toString());
            reference.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(GetNicImageBack.this, "Image Uploaded", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(GetNicImageBack.this, CreateClientProfile.class);
                            startActivity(intent);
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {

                    double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                    progressDialog.setMessage("Uploaded "+(int)progress+"%" );
                }
            });
        }
    }

    private void init() {
        nicBack = findViewById(R.id.cnicBack);
        btnImagePick = findViewById(R.id.btnUploadBack);
        btnUplaodBack = findViewById(R.id.btnUplaodDataBack);
    }
}
