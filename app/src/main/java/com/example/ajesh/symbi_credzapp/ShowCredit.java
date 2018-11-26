package com.example.ajesh.symbi_credzapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowCredit extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "ViewData";

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private ListView myList;
    private ArrayList<String> List = new ArrayList<>();
    private Button buttonBack;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_credit);
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        databaseReference = FirebaseDatabase.getInstance().getReference();
        buttonBack = (Button) findViewById(R.id.buttonBack);
        myList = (ListView) findViewById(R.id.myList);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, List);
        myList.setAdapter(arrayAdapter);
        FirebaseUser user = firebaseAuth.getCurrentUser();
        buttonBack.setOnClickListener(this);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                UserInformation value = dataSnapshot.getValue(UserInformation.class);
                List.add(String.valueOf(value));
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        email = (TextView) findViewById(R.id.email);
//        addAmount = (TextView) findViewById(R.id.addAmount);

//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                showData(dataSnapshot);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                System.out.println("The read failed: " + databaseError.getCode());
//            }
//        });

//        buttonBack.setOnClickListener(this);

    }

//    private void showData(DataSnapshot dataSnapshot)
//    {
//
////        String message = editMessage.getText().toString().trim();
//        String id = databaseReference.push().getKey();
//        for (DataSnapshot ds: dataSnapshot.getChildren())
//        {
//            String name= email.getText().toString().trim();
//            String amount= addAmount.getText().toString().trim();
//            UserInformation userInformation = new UserInformation(name,amount,null);
//            FirebaseUser user = firebaseAuth.getCurrentUser();
//            ds.getValue();
////            Log.d(TAG, "showData=name: " + userInformation.getEmail());
////            Log.d(TAG, "showData=amount: " + userInformation.getAmount());
//           System.out.println(userInformation);
//            Toast.makeText(this," Information Loaded..",Toast.LENGTH_LONG).show();
//        }
//    }


//
//    @Override
//    public void onClick(View view) {
//        if (view == buttonBack) {
//            goBack();
//
//        }
//    }
    private void goBack()
    {
        ShowCredit.this.finish();
    }
@Override
public void onClick(View view) {
    if (view == buttonBack) {
        goBack();

    }
}
}
