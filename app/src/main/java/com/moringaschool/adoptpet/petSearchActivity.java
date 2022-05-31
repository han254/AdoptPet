package com.moringaschool.adoptpet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class petSearchActivity extends AppCompatActivity {
    private ListView mListView;
    private TextView mLocationTextView;
    private String[] restaurants = new String[]{"American Staffordshire Terrier","Jack Russell Terrier","Pit Bull", "German Shepherd", "Chihuahua", "Beagle", "morty", "Gilmore","Binx","BIchon Frise","French Bulldog","poodle","AMerican Esmiko Dog","Maltese","Havanese","Lhasa apso","samoyed","maltipo"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_search);

        mListView = (ListView) findViewById(R.id.listView);
        mLocationTextView = (TextView) findViewById(R.id.locationTextView);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, restaurants);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String restaurant = ((TextView)view).getText().toString();
                Toast.makeText(petSearchActivity.this, restaurant, Toast.LENGTH_LONG).show();
            }
        });
        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mLocationTextView.setText("Pets to adopt near you: " + location);

    }
}