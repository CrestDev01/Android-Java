package com.example.placevisited.home.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.placevisited.databinding.FragmentHomeBinding;
import com.example.placevisited.home.MainActivity;
import com.example.placevisited.home.ui.adapter.PlacesAdapter;
import com.example.placevisited.home.viewModel.PlaceViewModel;
import com.example.placevisited.utils.SwipeToDeleteCallback;


public class HomeFragment extends Fragment {
    private PlaceViewModel placeViewModel;
    private PlacesAdapter placeAdapter;
    private FragmentHomeBinding binding;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        placeViewModel = new ViewModelProvider(this).get(PlaceViewModel.class);
        placeAdapter = new PlacesAdapter();
        binding.placeRecyclerView.setAdapter(placeAdapter);
        binding.placeRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        placeAdapter.setOnItemClickListener((position, placeEntity) -> {
            ((MainActivity) requireActivity()).navigateToEditPlaceFragment(placeEntity);
        });
        // Set up swipe-to-delete functionality
        SwipeToDeleteCallback swipeHandler = new SwipeToDeleteCallback(placeAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeHandler);
        itemTouchHelper.attachToRecyclerView(binding.placeRecyclerView);

        placeAdapter.setSwipeToDeleteListener(position -> {
            placeViewModel.remove(position);
        });

        loadPlaces();

        binding.addPlaceFab.setOnClickListener(v -> ((MainActivity) requireActivity()).navigateToAddPlaceFragment());
    }

    private void loadPlaces() {
        placeViewModel.getAllPlaces().observe(getViewLifecycleOwner(), placeEntities -> {
            if (placeAdapter != null)
                placeAdapter.updatePlaces(placeEntities);
        });
    }
}