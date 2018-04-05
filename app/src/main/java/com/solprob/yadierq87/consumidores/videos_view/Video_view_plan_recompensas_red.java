package com.solprob.yadierq87.consumidores.videos_view;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.solprob.yadierq87.consumidores.R;
import com.solprob.yadierq87.consumidores.pantallas_mensajes.Msg_pt6_video_plan_recompensa_red;

public class Video_view_plan_recompensas_red extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_video_view_plan_recompensas_red);
        setUpVideoView();
    }

    @Override
    public void onBackPressed()
    {
        finish();
        startActivityForResult(new Intent(this, Msg_pt6_video_plan_recompensa_red.class), 1);
    }

    private void setUpVideoView() {
        String youtubeVideoId = "ysoRfHTG0IM";
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + youtubeVideoId));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + youtubeVideoId));
        try {
            appIntent.putExtra("force_fullscreen",true);
            startActivity(appIntent);

        } catch (ActivityNotFoundException ex) {
            webIntent.putExtra("force_fullscreen",true);
            startActivity(webIntent);
        }
    }
}
