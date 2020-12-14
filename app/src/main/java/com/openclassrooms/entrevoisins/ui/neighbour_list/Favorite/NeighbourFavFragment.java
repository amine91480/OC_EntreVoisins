package com.openclassrooms.entrevoisins.ui.neighbour_list.Favorite;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.MyNeighbourRecyclerViewAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NeighbourFavFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NeighbourFavFragment extends Fragment {

    private List<Neighbour> mFavorites;

    private NeighbourApiService mApiService;
    private RecyclerView mRecyclerView;
    private MyNeighbourRecyclerViewAdapter mAdapter;

    @BindView(R.id.item_list_delete_button)
    ImageView deleteButton;

    /**
     * Create and return a new instance
     * @return @{@link NeighbourFavFragment}
     */
    public static NeighbourFavFragment newInstance() {
        NeighbourFavFragment fragment = new NeighbourFavFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
        mFavorites = mApiService.getFavorites();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return view;
    }

    @Subscribe
    private void initRecyclerView() {
        mFavorites = mApiService.getFavorites();
        mAdapter = new MyNeighbourRecyclerViewAdapter(mFavorites);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        initRecyclerView();
    }
}