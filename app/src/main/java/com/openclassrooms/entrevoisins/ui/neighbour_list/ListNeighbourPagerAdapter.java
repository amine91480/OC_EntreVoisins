package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.openclassrooms.entrevoisins.ui.neighbour_list.Favorite.NeighbourFavFragment;


public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public ListNeighbourPagerAdapter(FragmentManager fm, int  numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return NeighbourFragment.newInstance();
            case 1:
                return NeighbourFavFragment.newInstance();
            default:
                return null;
        }
    }

    /**
     * get the number of pages
     * @return
     */
    @Override
    public int getCount() {
        return numOfTabs;
    }
}