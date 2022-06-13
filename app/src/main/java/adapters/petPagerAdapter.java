package adapters;

import android.support.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.moringaschool.adoptpet.models.Animal;
import com.moringaschool.adoptpet.ui.petDetailFragment;

import java.util.List;

public class petPagerAdapter extends FragmentPagerAdapter {
    private List<Animal> mPets;

    public petPagerAdapter(@NonNull FragmentManager fm, int behavior, List<Animal> pets) {
        super(fm, behavior);
        mPets = pets;
    }

    @Override
    public Fragment getItem(int position) {
        return petDetailFragment.newInstance(mPets.get(position));
    }

    @Override
    public int getCount() {
        return mPets.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mPets.get(position).getName();
    }
}