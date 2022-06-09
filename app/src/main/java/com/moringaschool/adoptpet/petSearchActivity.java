package com.moringaschool.adoptpet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_search);
        ButterKnife.bind(this);

        adoptPet = (Button) findViewById(R.id.adoptPet);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String pet = ((TextView)view).getText().toString();
                Toast.makeText(petSearchActivity.this, pet, Toast.LENGTH_LONG).show();
            }
        });

        Intent intent = getIntent();
        String find = intent.getStringExtra("find");
        mLocationTextView.setText("Pets to adopt: " + find);
        Log.e("success", "Response is successful");

        PetfinderApi client = PetfinderClient.getClient();
        Log.e("finderLog", "check finder" + find);

        Call<PetSearchResponse> call = client.getPets(find);

        call.enqueue(new Callback<PetSearchResponse>() {
            @Override
            public void onResponse(Call<PetSearchResponse> call, Response<PetSearchResponse> response) {
                if (response.isSuccessful()) {
                    List<Animal> petsList = response.body().getAnimals();
                    String[] pets = new String[petsList.size()];

                    for (int i = 0; i < pets.length; i++) {
                        pets[i] = petsList.get(i).getType();
                    }
                    ArrayAdapter adapter = new PetFInderArrayAdapter(petSearchActivity.this, android.R.layout.simple_list_item_1,pets);
                    mListView.setAdapter(adapter);
                } else {
                    Log.e("onFailure", "ResponseFailure" + response.code());                }
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
                Log.e("Error Message", "onFailure: ", t);
            }
        });
    }
}