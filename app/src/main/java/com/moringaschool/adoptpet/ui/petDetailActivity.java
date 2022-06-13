package com.moringaschool.adoptpet.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.moringaschool.adoptpet.R;
import com.moringaschool.adoptpet.models.Animal;

import org.parceler.Parcels;

import java.util.List;

import adapters.petPagerAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class petDetailActivity extends AppCompatActivity {
    @BindView(R.id.viewPager) ViewPager mViewPager;
    private petPagerAdapter adapterViewPager;
    List<Animal> mPets;


}