package com.example.placevisited.startUp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.placevisited.auth.SignInActivity;
import com.example.placevisited.auth.SignUpActivity;
import com.example.placevisited.base.firebase.FireBaseRepository;
import com.example.placevisited.databinding.ActivityIntroBinding;
import com.example.placevisited.home.MainActivity;

public class IntroActivity extends AppCompatActivity {
    private ActivityIntroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String currentUserId = new FireBaseRepository().getCurrentUserId();
        if (!currentUserId.isBlank() || !currentUserId.isEmpty()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        binding.introBtnSignIn.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
            finish();
        });

        binding.introBtnSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
            finish();
        });
    }
}