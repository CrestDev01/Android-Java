package com.example.placevisited.home.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PlaceDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(PlaceEntity place);

    @Update
    void update(PlaceEntity place);

    @Query("DELETE FROM places WHERE id = :id")
    void deleteById(int id);

    @Query("SELECT * FROM places ORDER BY id ASC")
    LiveData<List<PlaceEntity>> getAllPlaces();
}