package com.openclassrooms.entrevoisins.service;

import android.util.Log;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * Get all my Favorites
     * @return {@link List}
     */
    @Override
    public List<Neighbour> getFavorites() {
        List<Neighbour> favorites = new ArrayList<>();
        for (Neighbour neighbour : neighbours) {
            if (neighbour.isFavorite()) {
                favorites.add(neighbour);
            }
        }
        return favorites;
    }

    /**
     * Delete the neighbour to the List neighbours
     * @ param neighbour
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * Create a new neigbour or update a neighbour existing on the list neighbours
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        boolean isFound = false;
        for (Neighbour n : neighbours) {
            if (n.getId() == neighbour.getId()) {
                n.setFavorite(neighbour.isFavorite());
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            neighbours.add(neighbour);
        }
    }

}
