package com.example.loginsignup.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.loginsignup.repository.athenticaterepo;
import com.google.firebase.auth.FirebaseUser;

public class authviewmodel extends AndroidViewModel {

    private athenticaterepo repository;
    private MutableLiveData<FirebaseUser> userdata;
    private MutableLiveData<Boolean> userstatus;

    //constructer for this class
    public authviewmodel(@NonNull Application application) {
        super(application);

        repository=new athenticaterepo(application);
        userdata=repository.getFirebaseUserMutableLiveData();
        userstatus=repository.getUserlogedout();

    }


    //getters
    public MutableLiveData<FirebaseUser> getUserdata() {
        return userdata;
    }

    public MutableLiveData<Boolean> getUserstatus() {
        return userstatus;
    }


    //methods for register
    public void register(String email, String pass){
        repository.register(email, pass);
    }

    //method for login
    public void login(String email,String pass){
        repository.login(email, pass);
    }

    //method for signout
    public void logout(){
        repository.signout();
    }

}
