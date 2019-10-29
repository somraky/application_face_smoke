package com.example.testcallapi.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.testcallapi.MainActivity;
import com.example.testcallapi.PersonNotiActivity;
import com.example.testcallapi.ProfileActivity;
import com.example.testcallapi.R;
import com.example.testcallapi.Utils;
import com.example.testcallapi.models.Person;

import java.util.List;


public class firstPersonAdapter extends RecyclerView.Adapter<firstPersonAdapter.MyviewHolder> {

    Context context;
    List<Person> personList;

    public firstPersonAdapter(Context context, List<Person> personList) {
        this.context = context;
        this.personList = personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public firstPersonAdapter.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_item_2, parent, false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final firstPersonAdapter.MyviewHolder holder, int position) {
        //holder.datetime.setText(Utils.DateFormat(personList.get(position).getTimestamp()));


        holder.title.setText(personList.get(position).getRealname());
        holder.timeago.setText(Utils.TimeFormat(personList.get(position).getTimestamp()));

        final Integer uid = personList.get(position).getUid();






        final String urlimg = "http://35.240.254.46:5000/showimg/face/" +personList.get(position).getId();
        final String bigurlimg = "http://35.240.254.46:5000/showimg/frame/" +personList.get(position).getId();






        Glide.with(context).load(urlimg)
                .apply(RequestOptions.centerCropTransform())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.img);

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProfileActivity.class);
                intent.putExtra("name",uid);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });




    }

    @Override
    public int getItemCount() {
        int limit = 10;
        if(personList != null){
            return personList.size();
        }
        return 0;
    }

    public class MyviewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView  title, timeago;
        ProgressBar progressBar;


        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img);
            title = (TextView) itemView.findViewById(R.id.title);
            timeago = (TextView) itemView.findViewById(R.id.timeago);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progress_load_photo);







        }

    }
}
