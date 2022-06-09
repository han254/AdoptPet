package com.moringaschool.adoptpet;

import android.content.Context;
import android.widget.ArrayAdapter;

import retrofit2.Callback;

public class PetFInderArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mPets;

    public PetFInderArrayAdapter(Callback<PetSearchResponse> mContext, int resource, String[] mPets) {
        super((Context) mContext, resource);
        this.mContext = (Context) mContext;
        this.mPets = mPets;
    }

    @Override
    public Object getItem(int position) {
        String pets = mPets[position];
        return String.format(pets);
    }

    @Override
    public int getCount() {
        return mPets.length;
    }
}
