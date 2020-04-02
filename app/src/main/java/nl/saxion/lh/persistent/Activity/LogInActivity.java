package nl.saxion.lh.persistent.Activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import nl.saxion.lh.persistent.R;

public class LogInActivity extends AppCompatActivity {
    private Toolbar toolbar;

    EditText mTextUserName;
    TextView sectionName;
    EditText mTextPassword;
    Button mButtonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        getIntent();

        toolbar = findViewById(R.id.next_toolbar);
        toolbar.setTitle("");
        sectionName = findViewById(R.id.section);
        sectionName.setText("Login");
        setSupportActionBar(toolbar);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mTextUserName = (EditText) findViewById(R.id.mTextUsername);
        mTextPassword = (EditText) findViewById(R.id.mTextPassword);
        mButtonLogin = (Button) findViewById(R.id.mButtonLogIn);
    }

    public void logIn(View view) {
        String user = mTextUserName.getText().toString().trim();
        String pwd = mTextPassword.getText().toString().trim();
        if (user.equals("admin") && pwd.equals("admin")){
            Intent intent = new Intent(LogInActivity.this, UserDataActivity.class);
            startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        } else {
            displayMessage("Incorrect log in details");
        }
    }

    private void displayMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    public void goBack(View view) {
        Intent intent = new Intent(LogInActivity.this, MainActivity.class);
        startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
}
