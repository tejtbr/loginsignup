package com.example.loginsignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginsignup.views.signinfragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class password extends AppCompatActivity {


    private EditText email;
    private Button getamail;
    private Button back;
    private String emailtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        email=findViewById(R.id.email);
        getamail=findViewById(R.id.getamail);
        back=findViewById(R.id.back);

        getamail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                emailtext=email.getText().toString();
                if(emailtext.isEmpty()){
                    Toast.makeText(password.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                }else{
                    passwordreset(emailtext);
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(password.this, signinfragment.class));
                finish();
            }
        });
    }
    private void passwordreset(String emailtext) {

        FirebaseAuth auth=FirebaseAuth.getInstance();
        auth.sendPasswordResetEmail(emailtext).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(password.this, "Please chech your mail reset your password", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(password.this, signinfragment.class));
                    finish();


                }else{
                    Toast.makeText(password.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}