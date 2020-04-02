package nl.saxion.lh.persistent.Activity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import nl.saxion.lh.persistent.R;

public class UserDataActivity extends AppCompatActivity {
    static List<User> usersData = new ArrayList<>();
    private Toolbar toolbar;
    FirebaseFirestore db;
    RecyclerView recyclerView;
    Context context;
    private TextView sectionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        getIntent();

        toolbar = findViewById(R.id.next_toolbar);
        toolbar.setTitle("");
        sectionName = findViewById(R.id.section);
        sectionName.setText("User Data");
        setSupportActionBar(toolbar);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        usersData.clear();
        context = this;

        db = FirebaseFirestore.getInstance();

        db.collection("usersData")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                User user = new User(document.getData().get("Name").toString(),
                                        document.getData().get("Email").toString(),
                                        document.getData().get("PhoneNr").toString(),
                                        document.getData().get("Profile").toString(),
                                        document.getData().get("Point").toString());
                                usersData.add(user);
                                recyclerView = findViewById(R.id.userDataRecyclerView);
                                recyclerView.setLayoutManager(new LinearLayoutManager(gC()));
                                recyclerView.setAdapter(new RecyclerViewAdapter(usersData, gC()));
                                Log.d("Success", document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w("Fail", "Error getting documents.", task.getException());
                        }
                    }
                });


    }

    public void goBack(View view) {
        Intent intent = new Intent(UserDataActivity.this, MainActivity.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    public void pickWinner(View view) {
        int highestPoint = -1;
        int winner=-1;
        for (int i = 0; i < usersData.size(); i++) {
            if (Integer.parseInt(usersData.get(i).getPoint()) > highestPoint) {
                highestPoint = Integer.parseInt(usersData.get(i).getPoint());
            }
        }

        List<User> users = new ArrayList<>();

        for (int i = 0; i < usersData.size(); i++) {
            if (Integer.parseInt(usersData.get(i).getPoint()) == highestPoint) {
                users.add(usersData.get(i));
            }
        }

        Random random = new Random();
        User user = users.get(random.nextInt(users.size()));

        for (int i = 0; i < usersData.size(); i++) {
            if (user.getName().equals(usersData.get(i).getName())
                    && user.getPoint().equals(usersData.get(i).getPoint())){
                winner = i;
            }
        }

        Intent myIntent = new Intent(UserDataActivity.this, WinnerActivity.class);
        myIntent.putExtra("winner", winner);
        startActivity(myIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    public Context gC() {
        return context;
    }

}
