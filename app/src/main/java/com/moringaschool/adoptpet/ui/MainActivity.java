package com.moringaschool.adoptpet.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.moringaschool.adoptpet.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.findPet) Button mFindPetButton;
    @BindView(R.id.locationEditText) EditText mLocationEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mFindPetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = mLocationEditText.getText().toString();
                Log.d(TAG, location);
                Intent intent = new Intent(MainActivity.this, petSearchActivity.class);
                intent.putExtra("animal",mLocationEditText.getText().toString());
                startActivity(intent);
                intent.putExtra("location", location);
                startActivity(intent);
                Toast.makeText(MainActivity.this, location, Toast.LENGTH_LONG).show();
            }
        });
    }
}

