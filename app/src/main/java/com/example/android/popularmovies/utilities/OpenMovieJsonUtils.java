package com.example.android.popularmovies.utilities;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;

public class OpenMovieJsonUtils {

    public static String[] getSimpleMovieStringsFromJson(Context context, String movieJsonStr) throws JSONException {
        final String MOVIE_LIST = "results";
        final String OWM_MESSAGE_CODE = "cod";

        String [] parsedMovieData = null;
        JSONObject movieJson = new JSONObject(movieJsonStr);

//        if(movieJson.has(MOVIE_LIST)) {
//            Log.d("movieJson", movieJson.toString());
//            int errorCode = movieJson.getInt(OWM_MESSAGE_CODE);
//
//            switch(errorCode) {
//                case HttpURLConnection.HTTP_OK:
//                    break;
//                case HttpURLConnection.HTTP_NOT_FOUND:
//                    return null;
//                default:
//                    return null;
//            }
//        }

        JSONArray moviesArray = movieJson.getJSONArray("results");
        parsedMovieData = new String[moviesArray.length()];
        for(int i = 0; i < moviesArray.length(); i++) {
            JSONObject movie = moviesArray.getJSONObject(i);
            parsedMovieData[i] = movie.toString();
        }
        return parsedMovieData;
    }
}
