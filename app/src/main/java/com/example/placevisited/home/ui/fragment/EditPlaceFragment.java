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

import com.example.placevisited.databinding.FragmentEditPlaceBinding;
import com.example.placevisited.home.MainActivity;
import com.example.placevisited.home.data.PlaceEntity;
import com.example.placevisited.home.viewModel.PlaceViewModel;

public class EditPlaceFragment extends Fragment {
    private PlaceViewModel placeViewModel;
    private FragmentEditPlaceBinding binding;
    private PlaceEntity placeEntity;

    public EditPlaceFragment() {
    }

    public EditPlaceFragment(PlaceEntity placeEntity) {
    this.placeEntity = placeEntity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEditPlaceBinding.inflate(inflater);
        binding.editTextPlaceName.setText(placeEntity.getPlaceTitle());
        binding.editTextDescription.setText(placeEntity.getPlaceDesc());
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
                PlaceEntity place = new PlaceEntity();
                place.setId(placeEntity.getId());
                place.setPlaceTitle(placeTitle);
                place.setPlaceDesc(placeDesc);
                placeViewModel.update(place);
                ((MainActivity) requireActivity()).closeCurrentFragment();

            }
        });
    }
}