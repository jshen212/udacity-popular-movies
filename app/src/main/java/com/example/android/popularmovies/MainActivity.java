package com.example.android.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.popularmovies.data.MoviePreferences;
import com.example.android.popularmovies.models.Movie;
import com.example.android.popularmovies.utilities.NetworkUtils;
import com.example.android.popularmovies.utilities.OpenMovieJsonUtils;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

import static com.example.android.popularmovies.MovieAdapter.*;

public class MainActivity extends AppCompatActivity implements MovieAdapterOnClickHandler {

    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;
    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_movies);
        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);

        GridLayoutManager layoutManager = new GridLayoutManager(this, MoviePreferences.getPreferredGridCols());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        mMovieAdapter = new MovieAdapter(this);
        mRecyclerView.setAdapter(mMovieAdapter);
        loadPopularMovies();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.filters, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == 2131427448) {
            loadPopularMovies();
        } else {
            loadTopRatedMovies();
        }
        mMovieAdapter.notifyDataSetChanged();
        return super.onOptionsItemSelected(item);
    }

    private void loadPopularMovies() {
        showMovieDataView();
        String movieCategory = MoviePreferences.buildCategorySpecificUrl("POPULAR");
        new FetchMovieTask().execute(movieCategory);
        mMovieAdapter.notifyDataSetChanged();
    }

    private void loadTopRatedMovies() {
        showMovieDataView();
        String movieCategory = MoviePreferences.buildCategorySpecificUrl("TOP_RATED");
        new FetchMovieTask().execute(movieCategory);
        mMovieAdapter.notifyDataSetChanged();
    }

    private void showMovieDataView() {
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(Movie movieItem) {
        Context context = this;
        Class destinationClass = DetailActivity.class;
        Intent intentToStartDetailActivity = new Intent(context, destinationClass);
        Log.d("movie item", movieItem.toString());
        intentToStartDetailActivity.putExtra("movieObject", movieItem);
        startActivity(intentToStartDetailActivity);
    }

    public class FetchMovieTask extends AsyncTask<String, Void, ArrayList<Movie>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<Movie> doInBackground(String... params) {
            if(params.length == 0) {
                return null;
            }

            String preferredMovieCategory = params[0];
            URL movieRequestUrl = NetworkUtils.buildUrl(preferredMovieCategory);
            try {
                String jsonMovieResponse = NetworkUtils.getResponseFromHttpUrl(movieRequestUrl);
                ArrayList<Movie> jsonMovieData = OpenMovieJsonUtils.getSimpleMovieStringsFromJson(MainActivity.this, jsonMovieResponse);
                return jsonMovieData;
            } catch(Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> movieData) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if(movieData != null) {
                showMovieDataView();
                mMovieAdapter.setMovieData(movieData);
            } else {
                showErrorMessage();
            }
        }
    }
}
