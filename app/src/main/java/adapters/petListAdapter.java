package adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.adoptpet.R;
import com.moringaschool.adoptpet.models.Animal;
import com.moringaschool.adoptpet.ui.petDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class petListAdapter extends RecyclerView.Adapter<petListAdapter.PetViewHolder> {
    private List<Animal> mPets;
    private Context mContext;


        public petListAdapter(Context context, List<Animal> pets) {
            mContext = context;
            mPets = pets;
        }

        @NonNull
        @Override
        public petListAdapter.PetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_list_item, parent, false);
            PetViewHolder viewHolder = new PetViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(petListAdapter.PetViewHolder holder, int position) {
            holder.bindPet(mPets.get(position));
        }

        @Override
        public int getItemCount() {
            return mPets.size();
        }

        public class PetViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            @BindView(R.id.petImageView) ImageView mPetImageView;
            @BindView(R.id.petNameTextView) TextView mPetNameTextView;
            @BindView(R.id.breedTextView) TextView mBreedTextView;
            @BindView(R.id.contactTextView) TextView mContactTextView;

            private Context mContext;

            public PetViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                mContext = itemView.getContext();
                itemView.setOnClickListener(this);
            }

            public void bindPet(Animal pet) {
                mPetNameTextView.setText(pet.getName());
                mBreedTextView.setText(pet.getBreeds().getPrimary());
                mContactTextView.setText(pet.getContact().getEmail());
            }

            @Override
            public void onClick(View v) {
                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(mContext, petDetailActivity.class);
                intent.putExtra("position", itemPosition);
                intent.putExtra("pets", Parcels.wrap(mPets));
                mContext.startActivity(intent);
            }
        }
    }
