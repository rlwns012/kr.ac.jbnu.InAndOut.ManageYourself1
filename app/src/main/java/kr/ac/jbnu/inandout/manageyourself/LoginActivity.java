package kr.ac.jbnu.inandout.manageyourself;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by rlwns on 2017-04-03.
 */

public class LoginActivity extends Activity {

    private EditText editTextpassword, editTextid;
    SoundPool soundPool;
    int sound;
    private CheckBox Auto_LogIn;
    private SharedPreferences setting;
    private SharedPreferences.Editor editor;
    private UserDBOpenHelper udbHelper;
    private User user;
    private static int ONE_MINUTE = 5626;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        new AlarmHATT(getApplicationContext()).Alarm();

        startActivity(new Intent(this, SplashActivity.class));  //처음 게임 로딩 화면을 띄워준다.

        editTextid = (EditText) findViewById(R.id.editTextid);
        editTextpassword = (EditText) findViewById(R.id.editTextpassword);
        Auto_LogIn = (CheckBox) findViewById(R.id.autoLogin);

        setting = getSharedPreferences("setting", 0);
        editor = setting.edit();

        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        sound = soundPool.load(this, R.raw.button, 1);
        udbHelper = new UserDBOpenHelper(this);

        if (setting.getBoolean("Auto_Login_enabled", false)) {
            String id = setting.getString("ID", "");
            String password = setting.getString("PW", "");
            user = new User("", "", "", "");
            udbHelper.checkUser(id, password, user);
            Intent intent = new Intent(this, MenuActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
            finish();
        }
    }

    public class AlarmHATT {
        private Context context;

        public AlarmHATT(Context context) {
            this.context = context;
        }

        public void Alarm() {
            AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(LoginActivity.this, BroadcastD.class);

            PendingIntent sender = PendingIntent.getBroadcast(LoginActivity.this, 0, intent, 0);

            Calendar calendar = Calendar.getInstance();
            //알람시간 calendar에 set해주기

            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 14, 56, 10);

            //알람 예약
            am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);

        }
    }

    public void gameStartButtonClicked(View view) {

        soundPool.play(sound, 1, 1, 0, 0, 1);

        // 아이디와 패스워드를 받아서 회원이 맞으면 회원정보를 넘겨주면서 다음 액티비티로 넘어간다.
        String id = editTextid.getText().toString();
        String password = editTextpassword.getText().toString();

        udbHelper = new UserDBOpenHelper(this);
        user = new User("", "", "", "");


        if (udbHelper.checkUser(id, password, user)) {
            if (Auto_LogIn.isChecked()) {

                String ID = editTextid.getText().toString();
                String PW = editTextpassword.getText().toString();

                editor.putString("ID", ID);
                editor.putString("PW", PW);
                editor.putBoolean("Auto_Login_enabled", true);
                editor.commit();
            }

            if (user.getMaxDay() > 0) {
                Intent intent = new Intent(this, MenuActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(this, SettingMonthActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        } else {                    // 회원 정보가 틀렸다면 다시 입력하게끔 알림과 초기화를 해준다.
            editTextid.setText("");
            editTextpassword.setText("");
            editTextid.requestFocus();
            Toast.makeText(LoginActivity.this, "아이디와 비밀번호를 다시 확인해주세요", Toast.LENGTH_SHORT).show();
        }
    }

    // 아이디 찾기 화면으로 가기
    public void findIdButtonClicked(View view) {
        soundPool.play(sound, 1, 1, 0, 0, 1);
        Intent intent = new Intent(this, FindIdActivity.class);
        startActivity(intent);
    }

    // 비밀번호 찾기 화면으로 가기
    public void findPasswordButtonClicked(View view) {
        soundPool.play(sound, 1, 1, 0, 0, 1);
        Intent intent = new Intent(this, FindPasswordActivity.class);
        startActivity(intent);
    }

    // 회원가입 화면으로 가기
    public void registerButtonClicked(View view) {
        soundPool.play(sound, 1, 1, 0, 0, 1);
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
