package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.model.Neighbour;


import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfilActivity extends AppCompatActivity {

    private List<Neighbour> mNeighbours;

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

    private NeighbourApiService mApiService;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        ButterKnife.bind(this);
        mApiService = DI.getNeighbourApiService();
        init();
    }

    private void init(){
        String neighbourName = "Name is not set";
        String neighbourLocation = "Location is not set";
        String neighbourPhone = "Phone number is not set";
        String neighbourAbout = "About is not set";
        String neighbourAvatar = "The URL AVATAR is not set";

        Intent intent = getIntent();
        if( intent != null ) {
            neighbourName =  intent.getStringExtra("neighbourName");
            neighbourLocation =  intent.getStringExtra("neighbourLocation");
            neighbourPhone =  intent.getStringExtra("neighbourPhone");
            neighbourAbout =  intent.getStringExtra("neighbourAbout");
            neighbourAvatar =  intent.getStringExtra("neighbourAvatar");
        }

        Log.d("ProfilActivity+OnCreate","Data transmis");
        Glide.with(this).load(neighbourAvatar)
                .apply(RequestOptions.noTransformation())
                .into(mAvatar);
        mName.setText(neighbourName);
        mNameAvatar.setText(neighbourName);
        mLocation.setText(neighbourLocation);
        mPhone.setText(neighbourPhone);
        mAdress.setText("www.facebook/"+neighbourName.toLowerCase());
        mAbout.setText(neighbourAbout);
    }
}