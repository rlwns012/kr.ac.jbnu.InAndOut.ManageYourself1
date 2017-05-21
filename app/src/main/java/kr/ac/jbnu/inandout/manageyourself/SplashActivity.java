package kr.ac.jbnu.inandout.manageyourself;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by rlwns on 2017-04-05.
 */
public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //프로그램 시작 화면 스플래시
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                finish();
            }
        }, 1000);// 1 초
    }
}

