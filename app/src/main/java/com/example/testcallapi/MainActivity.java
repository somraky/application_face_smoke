package com.example.testcallapi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.testcallapi.adapter.firstPersonAdapter;
import com.example.testcallapi.api.ApiInterface2;
import com.example.testcallapi.models.Person;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.messaging.FirebaseMessaging;

import com.example.testcallapi.api.ApiClient;
import com.example.testcallapi.api.ApiInterface;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity  {

    private Button button1;
    private Button button2;
    private Switch switch1;
    private List<Person> personList;
    private com.example.testcallapi.adapter.firstPersonAdapter firstpersonAdapter;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private firstPersonAdapter firstPersonAdapter;
    private ImageView img;
    private TextView title,timeago;
    private Context context;
    private ProgressBar progressBar;
     String urlimg;
     Integer    uid;
     FloatingActionButton floatingActionButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        button1 = (Button) findViewById(R.id.button);
        FirebaseMessaging.getInstance().subscribeToTopic("ANDROID");





        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CameraActivity.class));
            }
        });

        img = (ImageView) findViewById(R.id.img);
        title = (TextView) findViewById(R.id.maintitle);
        timeago = (TextView) findViewById(R.id.timeago);
        progressBar = (ProgressBar) findViewById(R.id.progress_load_photo);
        personList = new ArrayList<>();

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        //LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),3);
        recyclerView.setLayoutManager(gridLayoutManager);
        firstpersonAdapter = new firstPersonAdapter(getApplicationContext(),personList);
        recyclerView.setAdapter(firstpersonAdapter);

        ApiInterface2 apiService2 = ApiClient.getRetrofit().create(ApiInterface2.class);
        ApiInterface apiservice  = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<List<Person>> call2 = apiService2.getPersons();
        Call<List<Person>> call = apiservice.getPersons();
        call2.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                personList = response.body();
                Log.d("TAG", "Response2 = "+personList);
                firstpersonAdapter.setPersonList(personList);

            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                Log.d("TAG", "Response22 = "+t.toString());
            }
        });

        call.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                personList = response.body();
                Log.d("TAG", "Response1 = "+personList);

                title.setText(personList.get(0).getRealname());
                timeago.setText(Utils.TimeFormat(personList.get(0).getTimestamp()));
                uid = personList.get(0).getUid();
                urlimg = "http://35.240.254.46:5000/showimg/frame/" +personList.get(0).getId();

                Log.d("TAG", "uid = "+uid);

                Glide.with(getApplicationContext())
                        .load(urlimg)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                progressBar.setVisibility(View.GONE);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                progressBar.setVisibility(View.GONE);
                                return false;
                            }
                        })
                        .into(img);

                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                        intent.putExtra("name",uid);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getApplicationContext().startActivity(intent);
                    }
                });



            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                Log.d("TAG", "Response11 = "+t.toString());
            }
        });








        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PersonActivity.class));
            }
        });



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



}
