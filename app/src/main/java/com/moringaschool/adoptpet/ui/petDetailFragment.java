package com.moringaschool.adoptpet.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moringaschool.adoptpet.R;
import com.moringaschool.adoptpet.models.Animal;


import org.parceler.Parcels;


import butterknife.BindView;
import butterknife.ButterKnife;


public class petDetailFragment extends Fragment {
    @BindView(R.id.petImageView) ImageView petImageView;
    @BindView(R.id.petNameTextView) TextView mNameTextView;
    @BindView(R.id.contactTextView) TextView mContactTextView;
    @BindView(R.id.breedTextView) TextView mBreedTextView;
    @BindView(R.id.websiteTextView) TextView mWebsiteLabel;
    @BindView(R.id.phoneTextView) TextView mPhoneLabel;
    @BindView(R.id.savePetButton) TextView mSavePetButton;

    private Animal mPet;


    public petDetailFragment() {
        // Required empty public constructor
    }

    public static petDetailFragment newInstance(Animal pet) {
        petDetailFragment petDetailFragment = new petDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("pet", Parcels.wrap(pet));
        petDetailFragment.setArguments(args);
        return petDetailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        mPet = Parcels.unwrap(getArguments().getParcelable("pet"));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pet_detail, container, false);
        ButterKnife.bind(this, view);
        mNameTextView.setText(mPet.getName());
        mBreedTextView.setText(mPet.getBreeds().getPrimary());
        mContactTextView.setText(mPet.getContact().getEmail());
        mPhoneLabel.setText(mPet.getContact().getEmail());

        return view;
    }
}