package com.sanju.developer.shoppercom;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;

public class WoolenAdd extends AppCompatActivity {
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private ImageView imageView;
    private EditText imgName,imgSize;
    private Uri imguri;
    ProgressDialog Pd;
    public static final String FB_STOARAGE_PATH="woolen/";
    public static final String FB_DATABASE_PATH="woolen";
    public static final int REQUEST_CODE=1234;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH);

        imageView = (ImageView) findViewById(R.id.imageView);
        imgName = (EditText) findViewById(R.id.editText);
        imgSize = (EditText) findViewById(R.id.editTextsize);
    }

    public void btnBrowse(View v)
    {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent.createChooser(intent,"SELECT IMAGE"),REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE && resultCode==RESULT_OK && data != null && data.getData()!=null);
        {
            //   Toast.makeText(this, "hiiiiii", Toast.LENGTH_SHORT).show();
            imguri=data.getData();
            try
            {
                Bitmap bm= MediaStore.Images.Media.getBitmap(getContentResolver(),imguri);
                imageView.setImageBitmap(bm);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public String getImageText(Uri uri)
    {
        ContentResolver contentResolver= getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
    @SuppressWarnings("VisibleForTests")
    public void btnUpload(View v)
    {
        if(imguri !=null){
            Pd= new ProgressDialog(this);
            //ProgressDialog Pd=new ProgressDialog(getApplicationContext());
            Pd.setTitle("Please wait...!!");
            Pd.show();
            // This is get the storage reference
            StorageReference ref=mStorageRef.child(FB_STOARAGE_PATH + System.currentTimeMillis()+"."+getImageText(imguri));
            // add file on referece
            ref.putFile(imguri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Pd.dismiss();
                    Toast.makeText(getApplicationContext(), "IMAGE is upload", Toast.LENGTH_LONG).show();

                    // save the data in firebase
                    image1Upload image1upload= new image1Upload(imgName.getText().toString(),taskSnapshot.getDownloadUrl().toString(),imgSize.getText().toString());
                    String uploadId=mDatabaseRef.push().getKey();
                    mDatabaseRef.child(uploadId).setValue(image1upload);

                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Pd.dismiss();
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {

                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress=(100* taskSnapshot.getBytesTransferred())/ taskSnapshot.getTotalByteCount();
                            Pd.setMessage("UPLOADED "+(int)progress+"%");


                        }
                    });
        }
        else
        {
            Toast.makeText(getApplicationContext(), "PLEASE SELECT IMAGE", Toast.LENGTH_LONG).show();
        }
    }
}


