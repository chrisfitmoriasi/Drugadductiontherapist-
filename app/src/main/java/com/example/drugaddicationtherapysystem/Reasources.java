package com.example.drugaddicationtherapysystem;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;

public class Reasources extends AppCompatActivity {
     VideoView  videoView;
    String video_url="https://firebasestorage.googleapis.com/v0/b/drugaddictiontherapysystem.appspot.com/o/recovered%20drug%20addict%20testimony.mp4?alt=media&token=a52f5e58-4371-4cdd-82f7-5ed5d1882fe6";
    ProgressDialog pd;
    PDFView pdfView0, pdfView1,pdfView2,pdfView3, pdfView4;
    Button download1,download2,download3,download4;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reasources);
        // testimony videos
       // download1=(Button)findViewById(R.id.start_downloading);

        //download2=(Button)findViewById(R.id.start_downloading1);
        //download3=(Button)findViewById(R.id.start_downloading2);
       // download4=(Button)findViewById(R.id.start_downloading3);
        pdfView0 = (PDFView) findViewById(R.id.pdfView);
        pdfView0.fromAsset("wheat.pdf").load();
        pdfView1 = (PDFView) findViewById(R.id.pdfView1);
        pdfView1.fromAsset("IntelligentFaith.pdf").load();
        pdfView2 = (PDFView) findViewById(R.id.pdfView2);
        pdfView2.fromAsset("Healthsongandpoem.pdf").load();
        pdfView3 = (PDFView) findViewById(R.id.pdfView3);
        pdfView3.fromAsset("SmokingDrinking.pdf").load();
        pdfView4 = (PDFView) findViewById(R.id.pdfView4);
        pdfView4.fromAsset("WithdrawalTreatment.pdf").load();
            // Add a Media controller to allow forward/reverse/pause/resume
            final MediaController mMediaController = new MediaController(
                    Reasources.this, true);
        videoView = (VideoView)findViewById(R.id.videos);
            mMediaController.setEnabled(false);
            videoView.setMediaController(mMediaController);
        pd = new ProgressDialog(Reasources.this);
        pd.setMessage("Buffering video please wait...");
        pd.show();
        Uri uri;
        uri = Uri.parse(video_url);
        videoView.setVideoURI(uri);
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mMediaController.setEnabled(true);
                //close the progress dialog when buffering is done
                pd.dismiss();

            }
        });
    }
}