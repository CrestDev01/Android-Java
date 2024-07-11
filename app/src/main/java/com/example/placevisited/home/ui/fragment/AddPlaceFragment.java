package com.example.placevisited.home.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.placevisited.databinding.FragmentAddPlaceBinding;
import com.example.placevisited.home.MainActivity;
import com.example.placevisited.home.data.PlaceEntity;
import com.example.placevisited.home.viewModel.PlaceViewModel;

public class AddPlaceFragment extends Fragment {
    private PlaceViewModel placeViewModel;
    private FragmentAddPlaceBinding binding;

    public AddPlaceFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddPlaceBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        placeViewModel = new ViewModelProvider(this).get(PlaceViewModel.class);

        binding.buttonAddPlace.setOnClickListener(v -> {


            String placeTitle = binding.editTextPlaceName.getText().toString();
            String placeDesc = binding.editTextDescription.getText().toString();

            if (placeDesc.isEmpty() || placeTitle.isEmpty()) {
                Toast.makeText(getContext(), "Enter Proper Details.", Toast.LENGTH_SHORT).show();
            } else {
                PlaceEntity place = new PlaceEntity(placeTitle, placeDesc);
                placeViewModel.insert(place);
                ((MainActivity) requireActivity()).closeCurrentFragment();
            }
        });

    }
}