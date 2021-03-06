package com.pikup.pash.pikup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserInformationActivity extends AppCompatActivity {
    private static final String TAG = "UserInformationActivity";
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private DatabaseReference databaseReference;
    private String userId;
    private ListView mListView;
    private ArrayAdapter adapter;
    private ArrayList information;
    private UserInfo userInfo;
    private Toolbar toolbar;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.userprofilemenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_delete) {
            startActivity(new Intent(UserInformationActivity.this, DeleteAggrement.class));
        }
        if (item.getItemId() == R.id.menu_home){
            startActivity(new Intent(UserInformationActivity.this, HomeActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mListView = (ListView) findViewById(R.id.listview);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        userId = user.getUid();
        information = new ArrayList<>();

        databaseReference.child("Users/" + userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                userInfo = dataSnapshot.getValue(UserInfo.class);
                information.add(userInfo.getFirstName());
                information.add(userInfo.getLastName());
                information.add(userInfo.getAddress());
                information.add(userInfo.getState());
                information.add(userInfo.getCity());
                information.add(userInfo.getZipCode());
                information.add(userInfo.getDateOfBirth());
                information.add(userInfo.getEmail());
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, information);
        mListView.setAdapter(adapter);
    }

}
