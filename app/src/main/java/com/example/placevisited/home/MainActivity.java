package com.example.placevisited.home;

import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.placevisited.R;
import com.example.placevisited.home.data.PlaceEntity;
import com.example.placevisited.home.ui.fragment.AddPlaceFragment;
import com.example.placevisited.home.ui.fragment.EditPlaceFragment;
import com.example.placevisited.home.ui.fragment.HomeFragment;

import dagger.hilt.android.AndroidEntryPoint;

//@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            // Add the initial fragment
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();
        }

    }

    public void navigateToAddPlaceFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new AddPlaceFragment())
                .addToBackStack(null)
                .commit();
    }

    public void navigateToEditPlaceFragment(PlaceEntity placeEntity) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new EditPlaceFragment(placeEntity))
                .addToBackStack(null)
                .commit();
    }

    public void closeCurrentFragment() {
        getSupportFragmentManager().popBackStack();
    }
}