package com.example.loginsignup.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavAction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.loginsignup.R;
import com.example.loginsignup.viewmodel.authviewmodel;
import com.google.firebase.auth.FirebaseUser;


public class signupfragment extends Fragment {

    private EditText emailedit,passedit;
    private TextView signinn;
    private Button signupbtnn;
    private authviewmodel viewmodel;
    private NavController navController;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewmodel=new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(authviewmodel.class);

        viewmodel.getUserdata().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                navController.navigate(R.id.action_signupfragment_to_signinfragment);
            }
        });
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signupfragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emailedit=view.findViewById(R.id.emailEditSignUp);
        passedit=view.findViewById(R.id.passEditSignUp);
        signinn=view.findViewById(R.id.signInText);
        signupbtnn=view.findViewById(R.id.signUpBtn);
        //intializing for navigation

        navController= Navigation.findNavController(view);

        signinn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_signupfragment_to_signinfragment);
            }
        });

        signupbtnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=emailedit.getText().toString();
                String pass=passedit.getText().toString();
                if(!email.isEmpty()&&!pass.isEmpty()){
                    viewmodel.register(email,pass);
                }
            }
        });

    }

}