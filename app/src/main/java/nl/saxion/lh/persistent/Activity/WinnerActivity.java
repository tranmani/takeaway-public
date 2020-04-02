package nl.saxion.lh.persistent.Activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nl.saxion.lh.persistent.R;

public class WinnerActivity extends AppCompatActivity {
    private List<User> usersData;
    private Toolbar toolbar;
    private TextView sectionName;
    private TextView email,phone,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        Intent intent = getIntent();
        int winnerIndex = intent.getIntExtra("winner", 0);

        toolbar = findViewById(R.id.next_toolbar);
        toolbar.setTitle("");
        sectionName = findViewById(R.id.section);
        sectionName.setText("Winner");
        setSupportActionBar(toolbar);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        usersData = UserDataActivity.usersData;

        name = findViewById(R.id.winnerName);
        phone = findViewById(R.id.winnerPhone);
        email = findViewById(R.id.winnerEmail);

        name.setText("Name: "+usersData.get(winnerIndex).getName());
        phone.setText("Phone: "+usersData.get(winnerIndex).getPhone());
        email.setText("Email: "+usersData.get(winnerIndex).getEmail());
    }

    public void goBack(View view) {
        Intent intent = new Intent(WinnerActivity.this, MainActivity.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
}
