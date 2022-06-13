package com.moringaschool.adoptpet.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.moringaschool.adoptpet.R;

import java.util.List;

import adapters.petListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.moringaschool.adoptpet.models.Animal;
import com.moringaschool.adoptpet.models.PetSearchResponse;
import com.moringaschool.adoptpet.network.PetfinderApi;
import com.moringaschool.adoptpet.network.PetfinderClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class petListActivity extends AppCompatActivity {
    private Button adoptPet;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;

    private petListAdapter mAdapter;

    public List<Animal> pets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_search);
        ButterKnife.bind(this);

//        adoptPet = (Button) findViewById(R.id.adoptPet);

        Intent intent = getIntent();
        String find = intent.getStringExtra("find");

        PetfinderApi client = PetfinderClient.getClient();
        Log.e("finderLog", "check finder" + find);
        Call<PetSearchResponse> call = client.getPets(find);
        call.enqueue(new Callback<PetSearchResponse>() {
            @Override
            public void onResponse(Call<PetSearchResponse> call, Response<PetSearchResponse> response) {

                hideProgressBar();

                if (response.isSuccessful()) {
                    pets = response.body().getAnimals();
                    mAdapter = new petListAdapter(petListActivity.this, pets);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(petListActivity.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);

                    showPets();
                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<PetSearchResponse> call, Throwable t) {
                hideProgressBar();
                showFailureMessage();
            }
        });
    }

    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showPets() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}
