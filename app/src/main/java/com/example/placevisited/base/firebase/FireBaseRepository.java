package com.example.placevisited.base.firebase;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.example.placevisited.auth.SignInActivity;
import com.example.placevisited.auth.SignUpActivity;
import com.example.placevisited.model.User;
import com.example.placevisited.utils.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

public class FireBaseRepository {

    private final FirebaseFirestore mFireStore = FirebaseFirestore.getInstance();

    public String getCurrentUserId() {
        FirebaseAuth.AuthStateListener authStateListener = firebaseAuth -> {
            // Handle auth state changes if needed
        };
        FirebaseAuth.getInstance().addAuthStateListener(authStateListener);

        String currentUserID = "";
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            currentUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        }
        return currentUserID;
    }

    public void registerUser(SignUpActivity activity, Context context, User userInfo) {
        mFireStore.collection(Constants.USERS)
                .document(getCurrentUserId())
                .set(userInfo, SetOptions.merge())
                .addOnSuccessListener(aVoid -> activity.signUpSuccess())
                .addOnFailureListener(e -> Toast.makeText(context, "Failure", Toast.LENGTH_SHORT).show());
    }

    public void loadUserData(Activity activity, Context context) {
        mFireStore.collection(Constants.USERS)
                .document(getCurrentUserId())
                .get()
                .addOnSuccessListener(documentSnapShot -> {
                    User loggedInUser = documentSnapShot.toObject(User.class);
                    if (loggedInUser != null) {
                        if (activity != null) {
                            ((SignInActivity) activity).signInSuccess();
                        }
                    }
                })
                .addOnFailureListener(e -> Toast.makeText(context, "Failure", Toast.LENGTH_SHORT).show());
    }
}