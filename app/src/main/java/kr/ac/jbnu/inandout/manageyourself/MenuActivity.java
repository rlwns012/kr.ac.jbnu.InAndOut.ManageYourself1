package kr.ac.jbnu.inandout.manageyourself;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by rlwns on 2017-04-03.
 */

public class MenuActivity extends Activity {
    private Button swotbtn, diarybtn, pyramidbtn, carrerbtn;
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
        carrerbtn = (Button) findViewById(R.id.careerbtn);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user"); // 로그인에서 받아온 user 정보를 넘겨 받는다.

        swotbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(sound, 1, 1, 0, 0, 1);
                Intent intent = new Intent(MenuActivity.this, SWOTActivity.class);
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
                Intent intent = new Intent(MenuActivity.this, MyDiary.class);
                intent.putExtra("user", user);
                startActivity(intent);
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
    }
}
