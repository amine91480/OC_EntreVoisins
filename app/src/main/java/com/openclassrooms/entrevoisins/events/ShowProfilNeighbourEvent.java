package com.openclassrooms.entrevoisins.events;

import android.util.Log;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class ShowProfilNeighbourEvent {
    /**
     * Neighbour to show
     */
    public Neighbour neighbour;

    public ShowProfilNeighbourEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }

    /**
     * Constructor.
     * @param neighbour
     */

}
