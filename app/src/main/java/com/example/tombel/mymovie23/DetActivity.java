package com.example.tombel.mymovie23;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetActivity extends AppCompatActivity {

    public static String EXTRA_OVERVIEW = "extra_overview";
    public static String EXTRA_TITLE = "extra_title";
    public static String EXTRA_POSTER = "extra_poster";

    private TextView tvTitle;
    private TextView tvOverview;
    private ImageView imgPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_det);

        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvOverview = (TextView) findViewById(R.id.tv_overview);
        imgPoster = (ImageView) findViewById(R.id.img_Poster);

        String title = getIntent().getStringExtra(EXTRA_TITLE);
        String overview = getIntent().getStringExtra(EXTRA_OVERVIEW);

        Glide.with(getApplicationContext())
                .load(getIntent().getStringExtra(EXTRA_POSTER))
                .override(350, 550)
                .into(imgPoster);

        String overview_double = overview + " " + overview + " " + overview;

        tvTitle.setText(title);
        tvOverview.setText(overview_double);



    }
}
