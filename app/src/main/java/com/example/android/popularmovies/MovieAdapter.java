package com.example.android.popularmovies;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.popularmovies.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {
    private static ArrayList<Movie> mMovieData;
    private final MovieAdapterOnClickHandler mClickHandler;


    public interface MovieAdapterOnClickHandler {
        void onClick(String movieItem);
    }

    public MovieAdapter(MovieAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final ImageView mMovieImageView;

        public MovieAdapterViewHolder(View view) {
            super(view);
            mMovieImageView = (ImageView) view.findViewById(R.id.movie_image);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            Movie selectedMovie = mMovieData.get(adapterPosition);
            mClickHandler.onClick(selectedMovie.toString());
        }
    }

    @Override
    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movie_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new MovieAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieAdapterViewHolder movieAdapterViewHolder, int position) {
        Movie movieForSpecificPosition = mMovieData.get(position);
        Uri uri = Uri.parse(movieForSpecificPosition.getPoster_path());
        Context context = movieAdapterViewHolder.mMovieImageView.getContext();
        Picasso.with(context).load(uri).into(movieAdapterViewHolder.mMovieImageView);
    }


    @Override
    public int getItemCount() {
        if(null == mMovieData) return 0;
        return mMovieData.size();
    }

    public void setMovieData(ArrayList<Movie> movieData) {
        mMovieData = movieData;
        notifyDataSetChanged();
    }

    public static void sortMovieData(String sortCategory) {
        if(sortCategory == "POPULAR") {
            Collections.sort(mMovieData, new PopularityComparator());
        } else {
            Collections.sort(mMovieData, new RatingComparator());
        }
    }

    public static class PopularityComparator implements Comparator<Movie> {

        @Override
        public int compare(Movie m1, Movie m2) {
            Log.d("compare", String.valueOf(m1.getPopularity().compareTo(m2.getPopularity())));
            return m1.getPopularity().compareTo(m2.getPopularity());
        }
    }

    public static class RatingComparator implements Comparator<Movie> {

        @Override
        public int compare(Movie m1, Movie m2) {
            Log.d("m1 vote avg", String.valueOf(m1.getVote_avg()));
            Log.d("compare", String.valueOf(m1.getVote_avg().compareTo(m2.getVote_avg())));
            return m1.getVote_avg().compareTo(m2.getVote_avg());
        }
    }
}
