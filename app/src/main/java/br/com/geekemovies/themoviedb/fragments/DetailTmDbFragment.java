package br.com.geekemovies.themoviedb.fragments;


import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.geekemovies.themoviedb.R;
import br.com.geekemovies.themoviedb.model.Result;

public class DetailTmDbFragment extends Fragment {

    public static DetailTmDbFragment newInstance(Result result){
        Bundle bundle = new Bundle();
        bundle.putSerializable("result",result);

        DetailTmDbFragment detailTmDbFragment = new DetailTmDbFragment();
        detailTmDbFragment.setArguments(bundle);
        return detailTmDbFragment;
    }


     TextView originalTitle    ;

     TextView releaseDate      ;

     TextView original_language;

     TextView overViewer       ;

    public DetailTmDbFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_tm_db, container, false);


        if (getResources().getBoolean(R.bool.phone)) {
             Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);


            FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }

        Result result = (Result) getArguments().get("result");
        if (getResources().getBoolean(R.bool.phone)){
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout)
                    getView().findViewById(R.id.toolbar_layout);
            appBarLayout.setTitle(result.getTitle());
        }
        ImageView imgBackdropPath = (ImageView) view.findViewById(R.id.item_poster_patch);
           String imageUr1 = String.format("%s%s%s", "https://image.tmdb.org/t/p/", // base
                   "w1280", // size
                   result.getBackdropPath());

          Picasso.with(getActivity())
                   .load(imageUr1)
                   .into(imgBackdropPath);


        originalTitle     = (TextView) view.findViewById(R.id.txt_original_title);
        releaseDate       = (TextView) view.findViewById(R.id.txt_release_date);
        original_language = (TextView) view.findViewById(R.id.txt_original_language);
        overViewer        = (TextView) view.findViewById(R.id.txt_over_viewer);


        originalTitle.setText("Original Title: " + result.getOriginalTitle());
        releaseDate.setText("Release Date: " + result.getReleaseDate());
        original_language.setText("Original Language: " + result.getOriginalLanguage());
        overViewer.setText("Overview: " + result.getOverview());

        return view;
    }

}
