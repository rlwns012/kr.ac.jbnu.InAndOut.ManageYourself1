package kr.ac.jbnu.inandout.manageyourself;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by rlwns on 2017-05-21.
 */

public class SettingMonthActivity extends Activity {

    private User user;
    private EditText days;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setmonth);

        days = (EditText) findViewById(R.id.month);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user"); // 로그인에서 받아온 user 정보를 넘겨 받는다.

    }

    public void setMonthConfirm(View view) {
        //기간 입력시에 알맞은 값을 넣었는지 확인
        String daystr = days.getText().toString();
        int dayint = Integer.parseInt(daystr);
        if(dayint < 30 && dayint>1865){
            Toast.makeText(SettingMonthActivity.this, "설정할 수 있는 기간은 최소 30일 최대 1865일 입니다.", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(this, MenuActivity.class);
            user.setMaxDay(dayint);
            intent.putExtra("user", user);
            startActivity(intent);
            finish();
        }
    }
}
