package com.digitalhouse.bnews.repository;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import io.reactivex.Completable;

public class FirebaseRepository {

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private static final String TAG = "LoginActivity";

    public Completable authenticate(String email, String password){
        return Completable.create(emitter -> {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Log.d(TAG, "signInWithEmail:success");
                                emitter.onComplete();
                            } else {
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                emitter.onError(task.getException());
                            }
                        }
                    });
        });
    }

    public Completable createsNewUser(String name, String email, String password, Activity activity){
        return Completable.create(emitter -> {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Log.d(TAG, "you've created a new user ; )");
                                UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(name)
                                        .build();

                                FirebaseUser user = firebaseAuth.getCurrentUser();

                                user.updateProfile(profileChangeRequest)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    Log.d(TAG, "you've updated user's information");
                                                    emitter.onComplete();
                                                }
                                            }
                                        });
                            } else {
                                Log.w(TAG, "something went wrong : (", task.getException());
                                emitter.onError(task.getException());
                            }
                        }
                    });
        });
    }

}
