package com.moringaschool.adoptpet;

import android.content.Context;
import android.widget.ArrayAdapter;

import retrofit2.Callback;

public class PetFInderArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mPets;

   public PetFInderArrayAdapter(Context mContext, int resource, String[] mPets){
       super(mContext, resource);
       this.mContext= mContext;
       this.mPets= mPets;
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
