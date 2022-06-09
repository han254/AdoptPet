package com.moringaschool.adoptpet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class petSearchActivity extends AppCompatActivity {
    private Button adoptPet;
    @BindView(R.id.locationTextView)
    TextView mLocationTextView;
    @BindView(R.id.listView)
    ListView mListView;
    private String type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_search);
        adoptPet = (Button) findViewById(R.id.adoptPet);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mLocationTextView.setText("Pets to adopt near you: " + location);

        PetfinderApi client = PetfinderClient.getClient();

        Call<PetSearchResponse> call = client.getPets(location);
        call.enqueue(new Callback<PetSearchResponse>() {
            @Override
            public void onResponse(Call<PetSearchResponse> call, Response<PetSearchResponse> response) {
                if (response.isSuccessful()) {
                    List<Animal> petsList = response.body().getAnimals();
                    String[] pets = new String[petsList.size()];

                    for (int i = 0; i < pets.length; i++) {
                        pets[i] = petsList.get(i).getType();
                    }
                    ArrayAdapter adapter = new PetFInderArrayAdapter(this , android.R.layout.simple_list_item_1, pets);
                    mListView.setAdapter(adapter);
                } else {
                    Toast.makeText(petSearchActivity.this, response.message(), Toast.LENGTH_LONG).show();
                }
                adoptPet.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Intent intent = new Intent(petSearchActivity.this, adoptPetActivity.class);
                                                    startActivity(intent);
                                                }

                                            }
                );
            }

            @Override
            public void onFailure(Call<PetSearchResponse> call, Throwable t) {
            }
        });
    }
}