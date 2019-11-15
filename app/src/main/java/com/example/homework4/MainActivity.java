package com.example.homework4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonCreate;
    EditText editTextCreateBirdname, editTextCreateCode, editTextCreateName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCreate = findViewById(R.id.buttonSearch);

        editTextCreateBirdname = findViewById(R.id.editTextSearchBirdname);
        editTextCreateCode = findViewById(R.id.editTextSearchCode);
        editTextCreateName = findViewById(R.id.editTextSearchName);

        buttonCreate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("mybirds");

        if(view == buttonCreate) {

            String createBird = editTextCreateBirdname.getText().toString();
            String createCode = editTextCreateCode.getText().toString();
            String createPerson = editTextCreateName.getText().toString();

            Birds createBirds = new Birds(createBird, createCode, createPerson);

            myRef.push().setValue(createBirds);

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
                return true;
            case R.id.search_menu_item:
                Intent searchActivityintent = new Intent(MainActivity.this, Search.class);
                startActivity(searchActivityintent);
                return true;

            default:
                return false;
        }
    }
}
