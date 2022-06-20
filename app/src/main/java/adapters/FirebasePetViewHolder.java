package adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.adoptpet.R;
import com.moringaschool.adoptpet.constants;
import com.moringaschool.adoptpet.models.Animal;
import com.moringaschool.adoptpet.ui.petDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

class FirebaseRestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    View mView;
    Context mContext;

    public FirebaseRestaurantViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindRestaurant(Animal pet) {
        ImageView restaurantImageView = (ImageView) mView.findViewById(R.id.petImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.petNameTextView);
        TextView contactTextView = (TextView) mView.findViewById(R.id.contactTextView);


//        Picasso.get().load(pet.getPhotos()).into(restaurantImageView);

        nameTextView.setText(pet.getName());
//        contactTextView.setText(pet.getContact().getAddress());
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Animal> pets = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(constants.FIREBASE_CHILD_PETS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    pets.add(snapshot.getValue(Animal.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, petDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("pets", Parcels.wrap(pets));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}