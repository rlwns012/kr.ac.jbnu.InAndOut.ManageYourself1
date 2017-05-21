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
    private Button btneasy, btnnormal, btnhard, btnrank, btnmyinfo;
    private TextView tvHello;
    private User user;
    SoundPool soundPool;
    int sound;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        sound = soundPool.load(this,R.raw.button,1);

        btneasy = (Button) findViewById(R.id.btneasy);
        btnnormal = (Button) findViewById(R.id.btnnormal);
        btnhard = (Button) findViewById(R.id.btnhard);
        btnrank = (Button) findViewById(R.id.btnrank);
        btnmyinfo = (Button) findViewById(R.id.btnmyinfo);
        tvHello = (TextView) findViewById(R.id.tvHello);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user"); // 로그인에서 받아온 user 정보를 넘겨 받는다.

        tvHello.setText(user.getId().toString() + "님 환영합니다! 메뉴 중 선택해주세요.");

        // 난이도 EASY
        btneasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(sound,1,1,0,0,1);

               /* Intent intent = new Intent(MenuActivity.this, QuestionActivity.class);
                Bundle b = new Bundle();
                String level = btneasy.getText().toString();
                b.putString("level", level);
                intent.putExtras(b);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();*/

            }
        });

        // 난이도 NORMAL
        btnnormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(sound,1,1,0,0,1);

                /*Intent intent = new Intent(MenuActivity.this, QuestionActivity.class);
                Bundle b = new Bundle();
                String level = btnnormal.getText().toString();
                b.putString("level", level);
                intent.putExtras(b);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();*/

            }
        });

        // 난이도 HARD
        btnhard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(sound,1,1,0,0,1);

               /*Intent intent = new Intent(MenuActivity.this, QuestionActivity.class);
                Bundle b = new Bundle();
                String level = btnhard.getText().toString();
                b.putString("level", level);
                intent.putExtras(b);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();*/

            }
        });

        // 순위 보기
        btnrank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(sound,1,1,0,0,1);
               // Intent intent = new Intent(MenuActivity.this, RankActivity.class);
                //startActivity(intent);
            }
        });

        // 내 정보 보기
        btnmyinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(sound,1,1,0,0,1);
              //  Intent intent = new Intent(MenuActivity.this, MyInfoActivity.class);
               // intent.putExtra("user", user);
                //startActivity(intent);

            }
        });
    }

}
