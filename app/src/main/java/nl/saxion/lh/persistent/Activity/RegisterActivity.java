package nl.saxion.lh.persistent.Activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import nl.saxion.lh.persistent.R;

public class RegisterActivity extends AppCompatActivity {
    private Toolbar toolbar;

    EditText mName;
    EditText mEmail;
    EditText mPhone;
    EditText mGithub;
    Button mButtonRegister;
    CheckBox mCheckBox;
    FirebaseFirestore db;
    private TextView sectionName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getIntent();

        toolbar = findViewById(R.id.next_toolbar);
        toolbar.setTitle("");
        sectionName = findViewById(R.id.section);
        sectionName.setText("Register");
        setSupportActionBar(toolbar);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        db = FirebaseFirestore.getInstance();
        mName = (EditText) findViewById(R.id.editName);
        mEmail = (EditText) findViewById(R.id.editEmail);
        mPhone = (EditText) findViewById(R.id.editPhone);
        mGithub = (EditText) findViewById(R.id.editGithub);
        mButtonRegister = (Button) findViewById(R.id.mButtonLogIn);
        mCheckBox = findViewById(R.id.checkBox);
    }

    public void registerUser(View view) {
        String user = mName.getText().toString().trim();
        String email = mEmail.getText().toString().trim();
        String phoneNr = mPhone.getText().toString().trim();
        String github = mGithub.getText().toString().trim();
        if (mCheckBox.isChecked()) {
            displayMessage("Registered");
            Map<String, Object> userData = new HashMap<>();
            userData.put("Name", user);
            userData.put("Email", email);
            userData.put("PhoneNr", phoneNr);
            userData.put("Profile", github);
            userData.put("Point", CodeChallengeActivity.getPoint());
            db.collection("usersData")
                    .add(userData)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d("success", "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("Fail", "Error adding document", e);
                        }
                    });
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        } else {
            displayMessage("Please agree to GDPR to register");
        }

    }

    private void displayMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    public void goBack(View view) {
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
}
