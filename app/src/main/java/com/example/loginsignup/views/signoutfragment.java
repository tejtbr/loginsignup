package com.example.loginsignup.views;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.loginsignup.R;
import com.example.loginsignup.viewmodel.authviewmodel;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class signoutfragment extends Fragment {

    private authviewmodel viewmodel;
    private NavController navController;
    private Button signout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewmodel=new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory
                .getInstance(requireActivity().getApplication())).get(authviewmodel.class);


            viewmodel.getUserstatus().observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean aBoolean) {
                    if (aBoolean) {
                        navController.navigate(R.id.action_signoutfragment_to_signinfragment);
                    }
                }
            });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signoutfragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController=Navigation.findNavController(view);

        signout=view.findViewById(R.id.signOutBtn);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewmodel.logout();
            }
        });
    }
}