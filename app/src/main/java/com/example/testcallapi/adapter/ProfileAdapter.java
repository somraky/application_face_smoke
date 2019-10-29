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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.testcallapi.MainActivity;
import com.example.testcallapi.PersonNotiActivity;
import com.example.testcallapi.R;
import com.example.testcallapi.Utils;
import com.example.testcallapi.models.Person;
import com.example.testcallapi.models.Profile;

import java.util.List;


public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.MyviewHolder> {

    Context context;
    List<Profile> personList;

    public ProfileAdapter(Context context, List<Profile> personList) {
        this.context = context;
        this.personList = personList;
    }


    public void setPersonList(List<Profile> personList) {
        this.personList = personList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ProfileAdapter.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_item_1, parent, false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProfileAdapter.MyviewHolder holder, int position) {
        //holder.datetime.setText(Utils.DateFormat(personList.get(position).getTimestamp()));


        holder.date.setText(Utils.DateFormat(personList.get(position).getTimestamp()));
        holder.timeago.setText(Utils.TimeFormat(personList.get(position).getTimestamp()));






        final String urlimg = "http://35.240.254.46:5000/showimg/face/" +personList.get(position).getId();
        final String bigurlimg = "http://35.240.254.46:5000/showimg/frame/" +personList.get(position).getId();






        Glide.with(context).load(urlimg)
                .apply(RequestOptions.centerCropTransform())
                .into(holder.img);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PersonNotiActivity.class);
                intent.putExtra("image_url", bigurlimg);
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
        TextView  date, timeago;
        ProgressBar progressBar;
        CardView cardView;


        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.profile_image);
            date = (TextView) itemView.findViewById(R.id.date_time);
            timeago = (TextView) itemView.findViewById(R.id.timeago);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progress_load_photo);
            cardView = (CardView) itemView.findViewById(R.id.cdv);







        }

    }
}
