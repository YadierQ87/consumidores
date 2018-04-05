package com.solprob.yadierq87.consumidores.videos_view;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.VideoView;

import com.solprob.yadierq87.consumidores.R;

public class Video_view_example extends AppCompatActivity {


    private final static String START_LOADING_VIDEO = "start";
    private final static String STOP_LOADING_VIDEO = "stop";
    private VideoView videoView;
    private ProgressDialog progressBar;
    private ProgressBar mainProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_video_view_example);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setUpVideoView();
    }

    private void setUpVideoView() {
        // Prepara la URI del vídeo que será reproducido.
        String youtubeVideoId = "YgwyunOep7g";
        String frameVideo = "<html><body><iframe width=\"100%\" height=\"99%\" src=\"https://www.youtube.com/embed/YgwyunOep7g\" frameborder=\"0\" allowfullscreen></iframe></body></html>";
        WebView displayYoutubeVideo = (WebView) findViewById(R.id.videoExample);
        displayYoutubeVideo.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        WebSettings webSettings = displayYoutubeVideo.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setPluginState(WebSettings.PluginState.ON);
        displayYoutubeVideo.loadData(frameVideo, "text/html", "utf-8");
    }
}




