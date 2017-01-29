package com.example.android.popularmovies.data;

import android.content.Context;

public class MoviePreferences {
    private static final String BASE_URL = "http://api.themoviedb.org/3/movie/";
    private static final String POPULAR = "popular";
    private static final String TOP_RATED = "top_rated";
    private static final String API_KEY = "?api_key=7451504112b084237b695f2c6005e815";
    private static final Integer GRID_COLS = 3;
    private static String builtUrl;

    public static String getBaseUrl() {
        return getMovieBaseUrl();
    }

    private static String getMovieBaseUrl() {
        return BASE_URL;
    }

    public static String getPopularField() {
        return getPopular();
    }

    private static String getPopular() {
        return POPULAR;
    }

    public static String getTopRatedField() {
        return getTopRated();
    }

    private static String getTopRated() {
        return TOP_RATED;
    }

    public static String getApiKey() {
        return getApi();
    }

    private static String getApi() {
        return API_KEY;
    }

    public static Integer getPreferredGridCols() { return getGridCols(); }

    private static Integer getGridCols() { return GRID_COLS; }

    public String getMovies(String category) {
        return buildCategorySpecificUrl(category);
    }

    public static String buildCategorySpecificUrl(String category) {
        if(category == "POPULAR") {
            builtUrl = BASE_URL + POPULAR + API_KEY;
        }
        if(category == "TOP_RATED") {
            builtUrl = BASE_URL + TOP_RATED + API_KEY;
        }
        return builtUrl;
    }
}
