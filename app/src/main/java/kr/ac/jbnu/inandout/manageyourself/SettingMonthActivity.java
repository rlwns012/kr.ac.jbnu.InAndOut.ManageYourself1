package kr.ac.jbnu.inandout.manageyourself;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by rlwns on 2017-05-21.
 */

public class SettingMonthActivity extends Activity{

    private User user;
    private SharedPreferences checkMonth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setmonth);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user"); // 로그인에서 받아온 user 정보를 넘겨 받는다.

        checkMonth =getSharedPreferences("checkMonth", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = checkMonth.edit();
        editor.putBoolean(user.getId(),true);
        editor.commit();

    }

    public void setMonthConfirm(View view){
        //기간 입력시에 알맞은 값을 넣었는지 확인

        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }
}
