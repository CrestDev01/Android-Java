package com.example.placevisited.base.baseActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.placevisited.R;
import com.example.placevisited.databinding.ActivityBaseBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class BaseActivity extends AppCompatActivity {
    private ActivityBaseBinding binding;
    private boolean doubleBackToExitPressedOnce = false;
    private Dialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityBaseBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
    }

    public void showProgressDialog(String text) {
        mProgressDialog = new Dialog(this);
        mProgressDialog.setContentView(R.layout.dialog_progress);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setCanceledOnTouchOutside(false);
        TextView tvProgressText = mProgressDialog.findViewById(R.id.tv_progress_text);
        tvProgressText.setText(text);
        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        mProgressDialog.dismiss();
    }

    public void doubleBackToExit() {
        if (doubleBackToExitPressedOnce) {
            super.getOnBackPressedDispatcher().onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, R.string.please_click_back_again_exit, Toast.LENGTH_LONG).show();
        new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2500);
    }

    public void showErrorSnackBar(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
                .setBackgroundTint(ContextCompat.getColor(this, R.color.red))
                .show();
    }

    public void setErrorMessage(TextInputLayout textInputLayout, String message) {
        textInputLayout.setError(message);
        textInputLayout.setErrorEnabled(!message.isEmpty());
    }
}