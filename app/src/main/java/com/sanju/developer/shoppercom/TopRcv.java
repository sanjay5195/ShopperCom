package com.sanju.developer.shoppercom;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TopRcv extends AppCompatActivity {
    private DatabaseReference mDatabaseRef;
    private List<image1Upload> imglist;
    private List<image1Upload> imglist_copy;
    private List<String> urlpos,urlcopy;
    private ListView iv;
    private imageListAdapter adapter;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image__list);
        imglist = new ArrayList<>();
        urlpos = new ArrayList<>();
        urlcopy = new ArrayList<>();
        imglist_copy = new ArrayList<>();
        iv= (ListView) findViewById(R.id.listviewimg);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        mDatabaseRef= FirebaseDatabase.getInstance().getReference(TopAdd.FB_DATABASE_PATH);
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();

                // fetch the database
                for(DataSnapshot snapshot: dataSnapshot.getChildren())
                {
                    image1Upload img=snapshot.getValue(image1Upload.class);
                    imglist.add(img);
                 urlpos.add(img.getUrl());
               //     String value = iv.getItemAtPosition(position).toString();
                }
                for(int i=(imglist.size()-1);i>=0;i--){
                    imglist_copy.add(imglist.get(i));
                    urlcopy.add(urlpos.get(i));
                }
                adapter=new imageListAdapter(TopRcv.this,R.layout.image_item,imglist_copy);
                iv.setAdapter(adapter);
            }







            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });


        iv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Toast.makeText(TopRcv.this, imglist_copy.get(position).getName().toString(), Toast.LENGTH_SHORT).show();


                AlertDialog.Builder alertadd = new AlertDialog.Builder(TopRcv.this);
                alertadd.setTitle(imglist_copy.get(position).getName());

                LayoutInflater factory = LayoutInflater.from(TopRcv.this);
                View view1 = factory.inflate(R.layout.image, null);

                ImageView image= (ImageView) view1.findViewById(R.id.img_full);

                Glide.with(getApplicationContext()).load(imglist_copy.get(position).getUrl()).into(image);

                alertadd.setView(view1);
                alertadd.setNeutralButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dlg, int sumthin) {

                    }
                });

                alertadd.show();


            }
        });



    }
}
