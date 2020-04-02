package nl.saxion.lh.persistent.Activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import nl.saxion.lh.persistent.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<User> users;

    public RecyclerViewAdapter(List<User> users,Context context) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View placeItemView = inflater.inflate(R.layout.user_row, parent, false);
        Item item = new Item(placeItemView);
        return item;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder item, int i) {
        User user = users.get(i);
        ((Item) item).tvName.setText("Name: " + user.getName());
        ((Item) item).tvEmail.setText("Email: " + user.getEmail());
        ((Item) item).tvPhone.setText("Phone: " + user.getPhone());
        ((Item) item).tvGit.setText("Github: " + user.getGithub());
        ((Item) item).tvPoint.setText("Point: " + user.getPoint() + "/" + CodeChallengeActivity.getNumbersOfQ());


    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class Item extends RecyclerView.ViewHolder {
        public TextView tvName;
        public TextView tvEmail;
        public TextView tvPhone;
        public TextView tvGit;
        public TextView tvPoint;
        public View mView;

        public Item(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.userName);
            tvEmail = itemView.findViewById(R.id.userEmail);
            tvPhone = itemView.findViewById(R.id.phoneNr);
            tvGit = itemView.findViewById(R.id.gitHub);
            tvPoint = itemView.findViewById(R.id.point);
            mView = itemView;
        }
    }
}
