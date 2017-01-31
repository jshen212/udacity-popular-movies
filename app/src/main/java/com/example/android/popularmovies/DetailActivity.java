package com.example.android.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.models.Movie;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private ImageView mMovieDetailPoster;
    private TextView mMovieDetailTitle;
    private TextView mMovieDetailRelDate;
    private TextView mMovieDetailVoteAvg;
    private TextView mMovieDetailSynopsis;
    private Movie parcedMovieObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);

        mMovieDetailPoster = (ImageView) findViewById(R.id.movie_detail_poster);
        mMovieDetailTitle = (TextView) findViewById(R.id.movie_detail_title);
        mMovieDetailRelDate = (TextView) findViewById(R.id.movie_detail_release_date);
        mMovieDetailVoteAvg = (TextView) findViewById(R.id.movie_detail_vote_avg);
        mMovieDetailSynopsis = (TextView) findViewById(R.id.movie_detail_synopsis);

        Intent intentThatStartedThisActivity = getIntent();

        if(intentThatStartedThisActivity != null) {
            if(intentThatStartedThisActivity.hasExtra("movieObject")) {
                parcedMovieObj = (Movie) intentThatStartedThisActivity.getSerializableExtra("movieObject");
                Picasso.with(this).load(parcedMovieObj.getPoster_path()).into(mMovieDetailPoster);
                mMovieDetailTitle.setText(parcedMovieObj.getMovie_title());
                mMovieDetailRelDate.setText(parcedMovieObj.getRelease_date());
                mMovieDetailVoteAvg.setText(parcedMovieObj.getVote_avg());
                mMovieDetailSynopsis.setText(parcedMovieObj.getOverview());
            }
        }


    }
}
