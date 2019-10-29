package com.example.testcallapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.chrisbanes.photoview.PhotoView;

public class PersonNotiActivity extends AppCompatActivity {

    PhotoView showimg;
    String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_noti);

        url = getIntent().getStringExtra("image_url");

        showimg = (PhotoView) findViewById(R.id.showimg);
        Glide.with(this).load(url)
                .apply(new RequestOptions())
                .into(showimg);
    }
}
