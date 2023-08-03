package com.example.loginsignup.views;

import android.content.Intent;
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
import android.widget.EditText;
import android.widget.TextView;

import com.example.loginsignup.R;
import com.example.loginsignup.password;
import com.example.loginsignup.viewmodel.authviewmodel;
import com.google.firebase.auth.FirebaseUser;

public class signinfragment extends Fragment {

    private EditText emailedit,passedit;
    private TextView signup;
    private Button signinbtn;
    private authviewmodel viewmodel;
    private NavController navController;

    private TextView password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewmodel=new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(authviewmodel.class);

        viewmodel.getUserdata().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                navController.navigate(R.id.action_signinfragment_to_signoutfragment);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signinfragment, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emailedit=view.findViewById(R.id.emailEditSignIn);
        passedit=view.findViewById(R.id.passEditSignIn);
        signup=view.findViewById(R.id.signUpText);
        signinbtn=view.findViewById(R.id.signInBtn);
        password=view.findViewById(R.id.password);
        //intializing for navigation
        navController= Navigation.findNavController(view);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_signinfragment_to_signupfragment);
            }
        });

        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=emailedit.getText().toString();
                String pass=passedit.getText().toString();
                if(!email.isEmpty()&&!pass.isEmpty()){
                    viewmodel.login(email,pass);
                }
            }
        });

        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), com.example.loginsignup.password.class);
                startActivity(intent);
            }
        });

    }
}