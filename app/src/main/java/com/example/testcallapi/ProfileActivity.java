package com.example.testcallapi;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.testcallapi.adapter.ProfileAdapter;
import com.example.testcallapi.api.ApiClient;
import com.example.testcallapi.api.ApiInterface;
import com.example.testcallapi.models.Person;
import com.example.testcallapi.models.Profile;
import com.example.testcallapi.MainActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    private TextView name,gender,age,social;
    private List<Profile> personList,profileList;
    private RecyclerView recyclerView;
    private ProfileAdapter profileAdapter;
    String uid_profile;
    int uid;
    private ImageView img;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = (TextView) findViewById(R.id.Name);
        gender = (TextView) findViewById(R.id.gender);
        age = (TextView) findViewById(R.id.age);
        social = (TextView) findViewById(R.id.social);
        uid = getIntent().getIntExtra("name",0);
        img = (ImageView) findViewById(R.id.profile_image) ;
        //uid = Integer.parseInt(name_uid);
        personList = new ArrayList<>();
        profileList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.profile_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        profileAdapter = new ProfileAdapter(getApplicationContext(),profileList);
        recyclerView.setAdapter(profileAdapter);


        ApiInterface apiService = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<List<Profile>> call = apiService.getProfile();
        Call<List<Profile>> call2 = apiService.getSpec(uid);
        call.enqueue(new Callback<List<Profile>>() {
            @Override
            public void onResponse(Call<List<Profile>> call, Response<List<Profile>> response) {
                personList = response.body();
                Log.d("TAG", "Response1 = "+personList);

                name.setText(personList.get(uid).getRealname());
                age.setText(personList.get(uid).getAge());
                gender.setText(personList.get(uid).getGender());
                social.setText(personList.get(uid).getSocial());

                uid_profile = "http://35.240.254.46:5000/showimg/profile/" +uid;

                Glide.with(getApplicationContext())
                        .load(uid_profile)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                                return false;
                            }
                        })
                        .into(img);



            }

            @Override
            public void onFailure(Call<List<Profile>> call, Throwable t) {
                Log.d("TAG", "Response11 = "+t.toString());

            }
        });

        call2.enqueue(new Callback<List<Profile>>() {
            @Override
            public void onResponse(Call<List<Profile>> call, Response<List<Profile>> response) {
                profileList = response.body();
                Log.d("TAG", "Response = "+personList);
                profileAdapter.setPersonList(profileList);
            }

            @Override
            public void onFailure(Call<List<Profile>> call, Throwable t) {
                Log.d("TAG", "Response = "+t.toString());
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
