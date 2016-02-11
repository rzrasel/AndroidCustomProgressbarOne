package rz.rasel.androidcustomprogressbarone;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ActivityProgressBar extends AppCompatActivity {
    //|------------------------------------|
    private ProgressDialog progressBar;
    private int progressBarStatus = 0;
    private Handler progressBarHandler = new Handler();

    private long fileSize = 0;

    //|------------------------------------|
    //|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //|------------------------------------|
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        //|------------------------------------|
    }

    //|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
    public void sysBtnOnClickStartPd(View view) {
        //|------------------------------------|
        // prepare for a progress bar dialog
        progressBar = new ProgressDialog(view.getContext());
        progressBar.setCancelable(true);
        progressBar.setMessage("Progress Bar...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressBar.setProgress(0);
        progressBar.setMax(100);
        //|------------------------------------|
        // Get the Drawable custom_progressbar
        Drawable customDrawable;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            customDrawable = this.getDrawable(R.drawable.custom_progressbar);
        } else {
            customDrawable = getApplicationContext().getResources().getDrawable(R.drawable.custom_progressbar);
        }
        //|------------------------------------|
        // set the drawable as progress drawavle
        progressBar.setProgressDrawable(customDrawable);
        progressBar.show();
        //reset progress bar status
        progressBarStatus = 0;
        //|------------------------------------|
        //reset filesize
        fileSize = 0;
        //|------------------------------------|
        new Thread(new Runnable() {
            public void run() {
                //|------------------------------------|
                while (progressBarStatus < 100) {

                    // process some tasks
                    progressBarStatus = fileDownloadStatus();

                    //  sleep 1 second to show the progress
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // Update the progress bar
                    progressBarHandler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressBarStatus);
                        }
                    });
                }
                //|------------------------------------|
                // when, file is downloaded 100%,
                if (progressBarStatus >= 100) {
                    // sleep for  2 seconds, so that you can see the 100% of file download
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // close the progress bar dialog
                    progressBar.dismiss();
                }
                //|------------------------------------|
            }
        }).start();
        //|------------------------------------|
    }

    //|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
    //method returns the % of file downloaded
    public int fileDownloadStatus() {
        //|------------------------------------|
        while (fileSize <= 1000000) {
            //|------------------------------------|
            fileSize++;

            if (fileSize == 100000) {
                return 10;
            } else if (fileSize == 200000) {
                return 20;
            } else if (fileSize == 300000) {
                return 30;
            } else if (fileSize == 400000) {
                return 40;
            } else if (fileSize == 500000) {
                return 50;
            } else if (fileSize == 600000) {
                return 60;
            }
            // write your code here
            //|------------------------------------|
        }

        return 100;

    }
    //|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
}