package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Get all my Favorite
     * @return {@link List}
     */
    List<Neighbour> getFavorites();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Create a neighbour or update if is favorite
     * @param neighbour
     */
    void createNeighbour(Neighbour neighbour);

    /**
     * Show a neighbour
     * @param neighbour
     */
    void showNeighbour(Neighbour neighbour);


    Neighbour getNeighbour(int id);
}
