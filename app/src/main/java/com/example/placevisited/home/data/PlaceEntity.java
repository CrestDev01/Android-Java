package com.example.placevisited.home.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "places")
public class PlaceEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String placeTitle;
    private String placeDesc;

    // Constructors

    public PlaceEntity(){}

    public PlaceEntity(String placeTitle, String placeDesc) {
        this.placeTitle = placeTitle;
        this.placeDesc = placeDesc;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaceTitle() {
        return placeTitle;
    }

    public void setPlaceTitle(String placeTitle) {
        this.placeTitle = placeTitle;
    }

    public String getPlaceDesc() {
        return placeDesc;
    }

    public void setPlaceDesc(String placeDesc) {
        this.placeDesc = placeDesc;
    }
}
