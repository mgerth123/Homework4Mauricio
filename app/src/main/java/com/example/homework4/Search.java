package com.example.homework4;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Search extends AppCompatActivity implements View.OnClickListener {

    TextView textViewShowBirds;
    Button buttonSearch;
    EditText editTextSearchBirdname, editTextSearchCode, editTextSearchName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        buttonSearch = findViewById(R.id.buttonSearch);

        editTextSearchBirdname = findViewById(R.id.editTextSearchBirdname);
        editTextSearchCode = findViewById(R.id.editTextSearchCode);
        editTextSearchName = findViewById(R.id.editTextSearchName);

        textViewShowBirds = findViewById(R.id.textViewShowBirds);

        buttonSearch.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("mybirds");

        if(view == buttonSearch) {

            String searchCode = editTextSearchCode.getText().toString();

            myRef.orderByChild("code").equalTo(searchCode).addChildEventListener(new ChildEventListener() {

                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    //String findKey = dataSnapshot.getKey();
                    Birds foundBirds = dataSnapshot.getValue(Birds.class);
                    String findTitle = foundBirds.bird;

                    textViewShowBirds.setText(findTitle);

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

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater optionsMenuInflater = getMenuInflater();
        optionsMenuInflater.inflate(R.menu.dropdown_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.main_activity_menu_item:
                Intent mainActivityintent = new Intent(Search.this, MainActivity.class);
                startActivity(mainActivityintent);
                return true;
            case R.id.search_menu_item:
                return true;

            default:
                return false;
        }
    }
}
