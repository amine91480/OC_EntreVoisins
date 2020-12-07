package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.model.Neighbour;


import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bumptech.glide.Glide.init;

public class ProfilActivity extends AppCompatActivity {

    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;
    private List<Neighbour> mFavorites;
    private Neighbour neighbour;
    private Integer mNeighbourId;

    @BindView(R.id.show_name_neighbour)
    TextView mName;
    @BindView(R.id.show_name_avatar_neighbour)
    TextView mNameAvatar;
    @BindView(R.id.show_location_neighbour)
    TextView mLocation;
    @BindView(R.id.show_phone_neighbour)
    TextView mPhone;
    @BindView(R.id.show_adress_neighbour)
    TextView mAdress;
    @BindView(R.id.show_about_neighbour)
    TextView mAbout;
    @BindView(R.id.show_avatar_neighbour)
    ImageView mAvatar;
    @BindView(R.id.floatingActionButtonProfil)
    ImageView mFav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        ButterKnife.bind(this);
        mApiService = DI.getNeighbourApiService();
        mNeighbours = mApiService.getNeighbours();
        mFavorites = mApiService.getFavorites();
        receipIntent();
        configureView();
        setFavoriteMethode();
    }

    private void receipIntent() {
        Intent intent = getIntent();
        mNeighbourId = (Integer.valueOf((int) intent.getLongExtra("neighbourId",0)) - 1 );
        neighbour = mApiService.getNeighbour(mNeighbourId);
    }

    private void configureView() {
        Glide.with(this).load(neighbour.getAvatarUrl())
                .apply(RequestOptions.noTransformation())
                .into(mAvatar);
        mName.setText(neighbour.getName());
        mNameAvatar.setText(neighbour.getName());
        mLocation.setText(neighbour.getAddress());
        mPhone.setText(neighbour.getPhoneNumber());
        mAdress.setText("www.facebook/" + neighbour.getName().toLowerCase());
        mAbout.setText(neighbour.getAboutMe());
        if (neighbour.isFavorite() == true) {
            mFav.setImageResource(R.drawable.ic_star_white_24dp);
        }
        if (neighbour.isFavorite() == false) {
            mFav.setImageResource(R.drawable.ic_star_border_white_24dp);
        }
    }

    private void setFavoriteMethode() {
        findViewById(R.id.floatingActionButtonProfil).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!neighbour.isFavorite()) {
                    neighbour.setFavorite(true);
                    mFav.setImageResource(R.drawable.ic_star_white_24dp);
                } else {
                    mFav.setImageResource(R.drawable.ic_star_border_white_24dp);
                    neighbour.setFavorite(false);
                }
                mApiService.createNeighbour(neighbour);
            }
        });
    }
}
