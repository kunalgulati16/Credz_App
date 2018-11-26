package com.example.ajesh.symbi_credzapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class AddCredit extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    private EditText email;
    private EditText addAmount;
    private EditText editMessage;
    private Button buttonAdd;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_credit);

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        databaseReference = FirebaseDatabase.getInstance().getReference();
        email = (EditText) findViewById(R.id.email);
        addAmount = (EditText) findViewById(R.id.addAmount);
        editMessage = (EditText) findViewById(R.id.editMessage);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        FirebaseUser user = firebaseAuth.getCurrentUser();

        buttonAdd.setOnClickListener(this);
    }

    private void saveUserInformation() {

        String name= email.getText().toString().trim();
        String amount= addAmount.getText().toString().trim();
        String message = editMessage.getText().toString().trim();
        String id = databaseReference.push().getKey();

        UserInformation userInformation = new UserInformation(name,amount,message);
        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference.child(id).setValue(userInformation);
        Toast.makeText(this," Information Saved..",Toast.LENGTH_LONG).show();
        AddCredit.this.finish();


    }

    @Override
    public void onClick(View view) {
        if (view == buttonAdd) {
            saveUserInformation();

        }
    }
}