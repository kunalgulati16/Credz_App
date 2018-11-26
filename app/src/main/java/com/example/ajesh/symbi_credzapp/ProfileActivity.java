package com.example.ajesh.symbi_credzapp;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;


public class ProfileActivity extends AppCompatActivity{

    private FirebaseAuth firebaseAuth;
    private TextView textViewUserEmail;
    private Button buttonLogout;
    private Button buttonCredit;
    private Button buttonShow;
    private Button buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        FirebaseUser user = firebaseAuth.getCurrentUser();
        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        textViewUserEmail.setText("Welcome "+user.getEmail());
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                ProfileActivity.this.finish();
            }
        });
        buttonCredit = (Button) findViewById(R.id.buttonCredit);
        buttonCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ProfileActivity.this,AddCredit.class));
            }
        });
        buttonShow = (Button) findViewById(R.id.buttonShow);
        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ProfileActivity.this,ShowCredit.class));
            }
        });
        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ProfileActivity.this,DeleteCredit.class));
            }
        });

    }

    }

