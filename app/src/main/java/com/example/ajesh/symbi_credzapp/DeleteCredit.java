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


public class DeleteCredit extends AppCompatActivity implements View.OnClickListener {

    private TextView textView2;
    private EditText email;
    private Button buttonDelete;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_credit);
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        databaseReference = FirebaseDatabase.getInstance().getReference();
        email = (EditText) findViewById(R.id.email);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);

        FirebaseUser user = firebaseAuth.getCurrentUser();
        buttonDelete.setOnClickListener(this);
    }
    private void clearInformation()
    {
        String name= email.getText().toString().trim();
        Toast.makeText(this," Still Working..",Toast.LENGTH_LONG).show();
        DeleteCredit.this.finish();
    }
    @Override
    public void onClick(View view) {
        if (view == buttonDelete) {
            clearInformation();

        }
    }
}
