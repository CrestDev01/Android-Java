package com.example.placevisited.home.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.placevisited.home.data.AppDatabase;
import com.example.placevisited.home.data.PlaceDao;
import com.example.placevisited.home.data.PlaceEntity;

import java.util.List;

public class PlaceRepository {
    private PlaceDao placeDao;
    private LiveData<List<PlaceEntity>> allPlaces;

    public PlaceRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        placeDao = db.placeDao();
        allPlaces = placeDao.getAllPlaces();
    }

    public LiveData<List<PlaceEntity>> getAllPlaces() {
        return allPlaces;
    }

    public void insert(PlaceEntity place) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            placeDao.insert(place);
        });
    }

    public void update(PlaceEntity place) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            placeDao.update(place);
        });
    }

    public void deleteById(int id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            placeDao.deleteById(id);
        });
    }
}
