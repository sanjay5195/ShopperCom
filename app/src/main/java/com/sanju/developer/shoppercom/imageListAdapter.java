package com.sanju.developer.shoppercom;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Sanju on 15-Jun-17.
 */

public class imageListAdapter extends ArrayAdapter<image1Upload> {

    private Activity context;
    private int resource;
    public static List<image1Upload> listImage;

    public imageListAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List<image1Upload> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        listImage=objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();

        View v=inflater.inflate(resource,null);
        TextView tvname= (TextView) v.findViewById(R.id.tvimgname);
        ImageView imageView= (ImageView) v.findViewById(R.id.imgview);
        TextView tvsize=(TextView) v.findViewById(R.id.tvimgsize);


        tvname.setText(listImage.get(position).getName());
        tvsize.setText(listImage.get(position).getSize());
        Glide.with(context).load(listImage.get(position).getUrl()).into(imageView);
        return v;

    }
}
