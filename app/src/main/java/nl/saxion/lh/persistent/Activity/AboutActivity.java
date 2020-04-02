package nl.saxion.lh.persistent.Activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.MediaController;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.VideoView;

import nl.saxion.lh.persistent.R;

public class AboutActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView sectionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getIntent();

        toolbar = findViewById(R.id.next_toolbar);
        toolbar.setTitle("");
        sectionName = findViewById(R.id.section);
        sectionName.setText("Video");
        setSupportActionBar(toolbar);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        VideoView videoView = findViewById(R.id.video_view);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
    }

    public void goBack(View view) {
        Intent intent = new Intent(AboutActivity.this, MainActivity.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
}
