package com.example.placevisited.auth;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.placevisited.R;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.placevisited.base.baseActivity.BaseActivity;
import com.example.placevisited.databinding.ActivitySignUpBinding;
import com.example.placevisited.home.MainActivity;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends BaseActivity {
    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupActionbar();

        binding.signUpBtnSignIn.setOnClickListener(v -> registerUser());

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                removeError();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };

        binding.signUpEtName.addTextChangedListener(textWatcher);
        binding.signUpEtEmail.addTextChangedListener(textWatcher);
        binding.signUpEtPassword.addTextChangedListener(textWatcher);
    }

    private void removeError() {
        setErrorMessage(binding.signUpTilName, "");
        setErrorMessage(binding.signUpTilEmail, "");
        setErrorMessage(binding.signUpTilPassword, "");
    }

    private void setupActionbar() {
        setSupportActionBar(binding.signUpToolBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        binding.signUpToolBar.setNavigationOnClickListener(v -> getOnBackPressedDispatcher().onBackPressed());
    }

    private void registerUser() {
        String name = binding.signUpEtName.getText().toString().trim();
        String email = binding.signUpEtEmail.getText().toString().trim();
        String password = binding.signUpEtPassword.getText().toString().trim();
        if (validateForm(name, email, password)) {
            showProgressDialog(getResources().getString(R.string.progress_please_wait));
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Log.e("TAG", "registerUser: ");
                            signUpSuccess();
                        } else {
                            hideProgressDialog();
                            Toast.makeText(
                                    SignUpActivity.this,
                                    "Registration failed",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    });
        }
    }

    private boolean validateForm(String name, String email, String password) {
        if (name.isEmpty()) {
            setErrorMessage(binding.signUpTilName, "Please enter a name.");
            return false;
        }

        if (email.isEmpty()) {
            setErrorMessage(binding.signUpTilEmail, "Please enter an email.");
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            setErrorMessage(binding.signUpTilEmail, "Enter valid email.");
            return false;
        }

        if (password.isEmpty()) {
            setErrorMessage(binding.signUpTilPassword, "Please enter a password.");
            return false;
        }

        if (password.length() < 4) {
            setErrorMessage(binding.signUpTilPassword, "Password must be 5 or more character long.");
            return false;
        }

        return true;
    }

    public void signUpSuccess() {
        hideProgressDialog();
        Toast.makeText(this, "Registration successful, Welcome!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}