package com.example.android.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.android.popularmovies.models.Movie;

public class DetailActivity extends AppCompatActivity {
    private String mMovieDetails;
    private TextView mMovieDisplayTitle;
    private Movie parcedMovieObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);

        mMovieDisplayTitle = (TextView) findViewById(R.id.movie_detail_title);
        Intent intentThatStartedThisActivity = getIntent();

        if(intentThatStartedThisActivity != null) {
            if(intentThatStartedThisActivity.hasExtra("movieObject")) {
                parcedMovieObj = (Movie) intentThatStartedThisActivity.getSerializableExtra("movieObject");
//                mMovieDisplayTitle.setText(parcedMovieObj.toString());
                mMovieDisplayTitle.setText(parcedMovieObj.getMovie_title());
            }
        }


    }
}
