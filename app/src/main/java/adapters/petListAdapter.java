package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.adoptpet.R;
import com.moringaschool.adoptpet.models.Animal;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class petListAdapter extends RecyclerView.Adapter<petListAdapter.petViewHolder> {
    private List<Animal> mPets;
    private Context mContext;

    public petListAdapter(Context context, List<Animal> pets) {
        mContext = context;
        mPets =pets;
    }
    public class petViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.petImageView) ImageView petImageView;
        @BindView(R.id.petNameTextView) TextView mNameTextView;
        @BindView(R.id.breedTextView) TextView mBreedTextView;
        @BindView(R.id.contactTextView) TextView mContactTextView;

        private Context mContext;

        public petViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();

        }

        public void bindPet(Animal pet) {
            mNameTextView.setText(pet.getName());
            mBreedTextView.setText(pet.getBreeds().getPrimary());
            mContactTextView.setText(pet.getContact().getEmail());
        }
    }
    @Override
    public petListAdapter.petViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_list_item, parent, false);
        petViewHolder viewHolder = new petViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(petListAdapter.petViewHolder holder, int position) {
        holder.bindPet(mPets.get(position));

    }

    @Override
    public int getItemCount() {
        return mPets.size();
    }
}