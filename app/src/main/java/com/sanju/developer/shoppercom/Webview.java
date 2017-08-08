package com.sanju.developer.shoppercom;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

/**
 * Created by Sanju on 18-Jun-17.
 */

public class Webview extends AppCompatActivity{

    private WebView webView;
    private Button back;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loding..");
        progressDialog.setMessage("Please Wait..!!");
        progressDialog.show();
        webView = (WebView) findViewById(R.id.webView1);
        back= (Button) findViewById(R.id.back);
        webView.getSettings().setJavaScriptEnabled(true);
       // webView.getSettings().setPluginsEnabled(true);
        webView.setBackgroundColor(Color.TRANSPARENT);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.loadUrl("http://sanjaychoudhary.net23.net");
        progressDialog.dismiss();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Webview.this,About.class);
                startActivity(intent);
                finish();
            }



        });





    }
}
