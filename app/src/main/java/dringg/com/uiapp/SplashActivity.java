package dringg.com.uiapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;

import pk.muneebahmad.ui.UIManager;

/**
 * Created by ay on 8/21/2015.
 */
public class SplashActivity extends AppCompatActivity {
    private TextView tv;
    String txt = "LOADING";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splash_activity);
        UIManager.getsInstance().setActivatedView(UIManager.ActivatedView.VIEW_SPLASH);

        this.tv = (TextView) findViewById(R.id.tv_loading);
        this.tv.setText(txt);

        newThread();
        setLoadingText();
    }

    private void setLoadingText() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tv.setText(txt);
                tv.setTextColor(Color.parseColor("#1e90ff"));
            }
        }, 1100);
    }

    private void newThread() {

        Thread pThread = new Thread() {
            @Override
            public void run() {
                    try {
                        sleep(1000);
                        txt = "LOADED, NOW LAUNCHING... ";
                        sleep(600);
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    } catch (InterruptedException e) {

                    } finally {
                        finish();
                    }
                }
            };
            pThread.start();
        }

    @Override
    public void onBackPressed() {}

}/** end class. */
