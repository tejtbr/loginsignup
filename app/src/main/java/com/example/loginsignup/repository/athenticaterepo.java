package com.example.loginsignup.repository;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class athenticaterepo {
    private Application application;
    private FirebaseAuth firebaseauth;
    private MutableLiveData<FirebaseUser> firebaseUserMutableLiveData;
    private MutableLiveData<Boolean> userlogedout;
    public athenticaterepo(Application application) {
        this.application = application;
        firebaseauth = FirebaseAuth.getInstance();
        this.firebaseUserMutableLiveData = new MutableLiveData<>();
        userlogedout = new MutableLiveData<>();

        if(firebaseauth.getCurrentUser()!=null){
            firebaseUserMutableLiveData.postValue(firebaseauth.getCurrentUser());
        }

    }

    public void register(String email,String pass){
        firebaseauth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    firebaseUserMutableLiveData.postValue(firebaseauth.getCurrentUser());
                }else{
                    Toast.makeText(application, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void login(String email,String pass){
        firebaseauth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    firebaseUserMutableLiveData.postValue(firebaseauth.getCurrentUser());
                }else{
                    Toast.makeText(application, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    //getters
    public MutableLiveData<FirebaseUser> getFirebaseUserMutableLiveData() {
        return firebaseUserMutableLiveData;
    }

    public MutableLiveData<Boolean> getUserlogedout() {
        return userlogedout;
    }



    //function for logout

    public void signout(){
        firebaseauth.signOut();
        userlogedout.postValue(true);
    }
}
