package com.openclassrooms.entrevoisins.service;

import android.util.Log;
import android.widget.Toast;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public void showNeighbour(Neighbour neighbour) {
        Log.d("showNeighbour", String.valueOf(neighbour.getId()));
        Log.d("showNeighbour", neighbour.getName());
        Log.d("showNeighbour", String.valueOf(neighbours.indexOf(neighbour)));
        neighbours.indexOf(neighbour);}

}
