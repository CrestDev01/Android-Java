package com.example.placevisited.home.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.placevisited.databinding.ItemPlaceBinding;
import com.example.placevisited.home.data.PlaceEntity;

import java.util.List;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.PlaceViewHolder> {

    private List<PlaceEntity> places;
    private static OnItemClickListener listener;
    public SwipeToDeleteListener swipeToDeleteListener;


    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPlaceBinding binding = ItemPlaceBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PlaceViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        PlaceEntity place = places.get(position);
        holder.bind(place);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(position, place);
            }
        });
    }

    @Override
    public int getItemCount() {
        return places == null ? 0 : places.size();
    }

    public void updatePlaces(List<PlaceEntity> newPlaces) {
        this.places = newPlaces;
        notifyDataSetChanged();
    }

    static class PlaceViewHolder extends RecyclerView.ViewHolder {
        private ItemPlaceBinding binding;

        public PlaceViewHolder(ItemPlaceBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(PlaceEntity place) {
            binding.placeTitleTextView.setText(place.getPlaceTitle());
            binding.placeDescriptionTextView.setText(place.getPlaceDesc());

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(getAdapterPosition(), place);
                }
            });
        }
    }

    // Interface for handling on-click
    public interface OnItemClickListener {
        void onItemClick(int position, PlaceEntity placeEntity);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    // Interface for handling swipe-to-delete
    public interface SwipeToDeleteListener {
        void onSwipeToDelete(int position);
    }

    public void setSwipeToDeleteListener(SwipeToDeleteListener listener) {
        this.swipeToDeleteListener = listener;
    }
}