package com.example.tombel.mymovie23;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CardViewMovieAdapter extends RecyclerView.Adapter<CardViewMovieAdapter.CardViewViewHolder>{

    private ArrayList<Movie> listMovie;
    private Context context;

    public CardViewMovieAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Movie> getListMovie() {
        return listMovie;
    }

    public void setListMovie(ArrayList<Movie> listMovie) {
        this.listMovie = listMovie;
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_movie, parent, false);
        CardViewViewHolder viewHolder = new CardViewViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int i) {
        final Movie m = getListMovie().get(i);

        Glide.with(context)
                .load(m.getPoster())
                .override(350, 550)
                .into(holder.imgPoster);

        holder.tvTitle.setText(m.getTitle());
        holder.tvOverview.setText(m.getOverview());

        holder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), DetActivity.class);
                intent.putExtra(DetActivity.EXTRA_TITLE, m.getTitle());
                intent.putExtra(DetActivity.EXTRA_OVERVIEW, m.getOverview());
                intent.putExtra(DetActivity.EXTRA_POSTER, m.getPoster());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return getListMovie().size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder{
        ImageView imgPoster;
        TextView tvTitle, tvOverview;
        Button btnShare;
        public CardViewViewHolder(View itemView) {
            super(itemView);
            imgPoster = (ImageView)itemView.findViewById(R.id.img_item_photo);
            tvTitle = (TextView)itemView.findViewById(R.id.tv_item_name);
            tvOverview = (TextView)itemView.findViewById(R.id.tv_item_remarks);
            btnShare = (Button)itemView.findViewById(R.id.btn_set_share);
        }
    }
}
