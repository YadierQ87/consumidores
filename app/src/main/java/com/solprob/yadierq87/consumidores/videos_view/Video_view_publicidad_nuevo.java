package com.solprob.yadierq87.consumidores.videos_view;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.solprob.yadierq87.consumidores.R;
import com.solprob.yadierq87.consumidores.pantallas_mensajes.Msg_pt5_video_publicidad_nuevo;


public class Video_view_publicidad_nuevo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_video_view_publicidad_nuevo);
        setUpVideoView();
    }

    @Override
    public void onBackPressed()    {
        finish();
        startActivityForResult(new Intent(this, Msg_pt5_video_publicidad_nuevo.class), 1);
    }

    private void setUpVideoView() {
        String youtubeVideoId = "C3Jz1_FPcno";
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
