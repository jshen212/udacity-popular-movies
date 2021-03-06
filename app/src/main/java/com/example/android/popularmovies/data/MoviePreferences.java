package com.example.android.popularmovies.data;

import android.content.Context;

public class MoviePreferences {
    private static final String BASE_URL = "http://api.themoviedb.org/3/movie/";
    private static final String POPULAR = "popular";
    private static final String TOP_RATED = "top_rated";
    private static final Integer GRID_COLS = 3;
    private static final String MOVIE_POSTER_SIZE = "w342/";
    private static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";
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

    public static String getMovies(String category) {
        return buildCategorySpecificUrl(category);
    }

    public static String getPosterSize() { return getPosterDimension(); }

    private static String getPosterDimension() { return MOVIE_POSTER_SIZE; }

    public static String getPosterBaseUrl() { return getImgBaseUrl(); }

    private static String getImgBaseUrl() {return IMAGE_BASE_URL; }

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
