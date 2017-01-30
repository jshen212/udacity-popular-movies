package com.example.android.popularmovies.utilities;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.example.android.popularmovies.data.MoviePreferences;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.IDN;
import java.net.URI;

public class OpenMovieJsonUtils {

    public static String[] getSimpleMovieStringsFromJson(Context context, String movieJsonStr) throws JSONException {
        final String MOVIE_LIST = "results";
        final String POSTER_PATH= "poster_path";
        final String ADULT_STATUS = "adult";
        final String OVERVIEW= "overview";
        final String RELEASE_DATE = "release_date";
        final String GENRE_IDS = "genre_ids";
        final String ID = "id";
        final String ORIGINAL_TITLE = "original_title";
        final String ORIGINAL_LANGUAGE = "original_language";
        final String TITLE = "title";
        final String BACKDROP_PATH = "backdrop_path";
        final String POPULARITY = "popularity";
        final String VOTE_COUNT = "vote_count";
        final String VIDEO = "video";
        final String VOTE_AVG = "vote_average";

        String [] parsedMovieData = null;
        JSONObject movieJson = new JSONObject(movieJsonStr);
        JSONArray moviesArray = movieJson.getJSONArray("results");

        parsedMovieData = new String[moviesArray.length()];
        for(int i = 0; i < moviesArray.length(); i++) {
            JSONObject movie = moviesArray.getJSONObject(i);
            String poster_path = movie.getString(POSTER_PATH);
            String adult_status = movie.getString(ADULT_STATUS);
            String overview = movie.getString(OVERVIEW);
            String release_date = movie.getString(RELEASE_DATE);
            String genre_ids = movie.getString(GENRE_IDS);
            String id = movie.getString(ID);
            String original_title= movie.getString(ORIGINAL_TITLE);
            String original_language = movie.getString(ORIGINAL_LANGUAGE);
            String movie_title = movie.getString(TITLE);
            String backdrop_path = movie.getString(BACKDROP_PATH);
            String popularity = movie.getString(POPULARITY);
            String vote_count = movie.getString(VOTE_COUNT);
            String video = movie.getString(VIDEO);
            String vote_avg= movie.getString(VOTE_AVG);

//            String result = poster_path + adult_status + overview + release_date + genre_ids + id + original_title + original_language + movie_title + backdrop_path + popularity + vote_count + video + vote_avg;
//            parsedMovieData[i] = result;
            parsedMovieData[i] = MoviePreferences.getPosterBaseUrl() + MoviePreferences.getPosterSize() + poster_path;
        }
        return parsedMovieData;
    }
}
