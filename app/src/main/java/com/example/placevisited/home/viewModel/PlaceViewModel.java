package com.example.placevisited.home.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.placevisited.home.data.PlaceEntity;
import com.example.placevisited.home.repository.PlaceRepository;

import java.util.List;

public class PlaceViewModel extends AndroidViewModel {
    private PlaceRepository repository;
    private LiveData<List<PlaceEntity>> allPlaces;

    public PlaceViewModel(@NonNull Application application) {
        super(application);
        repository = new PlaceRepository(application);
        allPlaces = repository.getAllPlaces();
    }

    public LiveData<List<PlaceEntity>> getAllPlaces() {
        return allPlaces;
    }

    public void insert(PlaceEntity place) {
        repository.insert(place);
    }

    public void update(PlaceEntity place) {
        repository.update(place);
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }

    public void remove(int position) {
        deleteById(allPlaces.getValue().get(position).getId());
        allPlaces.getValue().remove(position);
        getAllPlaces();
    }
}