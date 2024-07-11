package com.example.placevisited.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.Toast;

import com.example.placevisited.R;
import com.example.placevisited.base.baseActivity.BaseActivity;
import com.example.placevisited.databinding.ActivitySignInBinding;
import com.example.placevisited.home.MainActivity;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends BaseActivity {
    private ActivitySignInBinding binding;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();

        setupActionbar();

        binding.signInBtnSignIn.setOnClickListener(v -> signInRegisteredUser());

        binding.signInEtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                removeError();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        binding.signInEtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                removeError();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void removeError() {
        setErrorMessage(binding.signInTilEmail, "");
        setErrorMessage(binding.signInTilPassword, "");
    }

    private void setupActionbar() {
        setSupportActionBar(binding.signInToolBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        binding.signInToolBar.setNavigationOnClickListener(v -> getOnBackPressedDispatcher().onBackPressed());
    }

    private boolean validateForm(String email, String password) {
        if (email.isBlank()) {
            setErrorMessage(binding.signInTilEmail, "Please enter an email.");
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            setErrorMessage(binding.signInTilEmail, "Enter valid email.");
            return false;
        }

        if (password.isBlank()) {
            setErrorMessage(binding.signInTilPassword, "Please enter a password.");
            return false;
        }

        if (password.length() < 4) {
            setErrorMessage(binding.signInTilPassword, "Password must be 5 or more character long.");
            return false;
        }

        return true;
    }

    private void signInRegisteredUser() {
        String email = binding.signInEtEmail.getText().toString().trim();
        String password = binding.signInEtPassword.getText().toString().trim();

        if (validateForm(email, password)) {
            showProgressDialog(getResources().getString(R.string.progress_please_wait));
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    signInSuccess();
                } else {
                    hideProgressDialog();
                    Toast.makeText(
                            SignInActivity.this,
                            "Authentication failed",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            });
        }
    }

    public void signInSuccess() {
        hideProgressDialog();
        Toast.makeText(this, "Welcome back!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}