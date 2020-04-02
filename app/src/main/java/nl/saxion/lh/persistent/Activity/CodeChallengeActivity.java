package nl.saxion.lh.persistent.Activity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import nl.saxion.lh.persistent.R;

public class CodeChallengeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView sectionName;
    private Database sqlData;
    private int questionNr;
    private static int numbersOfQ;
    private static int point;
    private ArrayList<String> questions = new ArrayList<>();
    private ArrayList<String> image = new ArrayList<>();
    private ArrayList<String> answers = new ArrayList<>();
    private TextView question;
    private ImageView imageQuestion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_challenge);
        getIntent();

        numbersOfQ = 0;
        point = 0;
        questionNr = 0;

        toolbar = findViewById(R.id.next_toolbar);
        toolbar.setTitle("");
        sectionName = findViewById(R.id.section);
        sectionName.setText("Code challenge");
        setSupportActionBar(toolbar);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        question = findViewById(R.id.question);
        imageQuestion = findViewById(R.id.imageQuestion);


        sqlData = new Database(this);

        sqlData.deleteAll();

        sqlData.addChallenge("Find the shortest way?", "question1.jpg", "a");
        sqlData.addChallenge("What type of insect do you see in this picture?", "question2.jpg", "butterfly");
        sqlData.addChallenge("How many flamingos do you see in the picture?", "question3.jpg", "12");
        sqlData.addChallenge("Which type of star is the sun?", "question4.jpg", "supernova");

        Cursor cursor = sqlData.getAllRecords();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            numbersOfQ++;
            questions.add(cursor.getString(cursor.getColumnIndex(Database.COL_1)));
            image.add(cursor.getString(cursor.getColumnIndex(Database.COL_2)));
            answers.add(cursor.getString(cursor.getColumnIndex(Database.COL_3)));
        }

        question.setText(questions.get(questionNr));
        try {
            InputStream inputStream = this.getAssets().open(image.get(questionNr));
            Drawable d = Drawable.createFromStream(inputStream, null);
            imageQuestion.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void submitAnswer(View view) {
        EditText answer = (EditText) findViewById(R.id.answer);
        String result = answer.getText().toString();
        if (result.toLowerCase().equals(answers.get(questionNr))) {
            point++;
        }

        questionNr++;
        answer.getText().clear();

        if (questionNr >= numbersOfQ) {
            Intent intent = new Intent(CodeChallengeActivity.this, RegisterActivity.class);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        } else {
            question.setText(questions.get(questionNr));
            try {
                InputStream inputStream = this.getAssets().open(image.get(questionNr));
                Drawable d = Drawable.createFromStream(inputStream, null);
                imageQuestion.setImageDrawable(d);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (questionNr == questions.size() - 1) {
            Button button = findViewById(R.id.submit);
            button.setText("SUBMIT");
        }
    }

    public void goBack(View view) {
        Intent intent = new Intent(CodeChallengeActivity.this, MainActivity.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    public static int getPoint() {
        return point;
    }

    public static int getNumbersOfQ() {
        return numbersOfQ;
    }
}