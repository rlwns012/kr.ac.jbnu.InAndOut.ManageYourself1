package kr.ac.jbnu.inandout.manageyourself;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by rlwns on 2017-04-03.
 */

public class MenuActivity extends Activity {
    private Button swotbtn, diarybtn, pyramidbtn, careerbtn;
    private User user;
    SoundPool soundPool;
    int sound;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        sound = soundPool.load(this, R.raw.button, 1);

        swotbtn = (Button) findViewById(R.id.SWOTbtn);
        diarybtn = (Button) findViewById(R.id.diarybtn);
        pyramidbtn = (Button) findViewById(R.id.pyramidbtn);
        careerbtn = (Button) findViewById(R.id.careerbtn);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user"); // 로그인에서 받아온 user 정보를 넘겨 받는다.

        swotbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(sound, 1, 1, 0, 0, 1);
                Intent intent = new Intent(MenuActivity.this, SWOTListActivity.class);
                Bundle b = new Bundle();
                intent.putExtras(b);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        diarybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(sound, 1, 1, 0, 0, 1);
                Intent svIntent = new Intent(
                        getApplicationContext(),//현재제어권자
                        MusicService.class); // 이동할 컴포넌트
                startService(svIntent); // 서비스 시작

                Intent intent = new Intent(MenuActivity.this, MyDiary.class);
                intent.putExtra("user", user);
                startActivityForResult(intent, 1);
            }
        });

        pyramidbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(sound, 1, 1, 0, 0, 1);
                Intent intent = new Intent(MenuActivity.this, PyramidActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        careerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(
                        "http://www.career.go.kr/cnet/web/counsel/explr/bomulsumHome.mdo")));
            }
        });
    }

    public void logout(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("setting", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();

        Intent intent = new Intent(MenuActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            user = (User) data.getSerializableExtra("user");
        }
    }
}
