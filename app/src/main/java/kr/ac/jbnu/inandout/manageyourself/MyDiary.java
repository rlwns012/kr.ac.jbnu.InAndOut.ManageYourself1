package kr.ac.jbnu.inandout.manageyourself;

import android.animation.AnimatorSet;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rlwns on 2017-05-23.
 */

public class MyDiary extends Activity {

    private TextView dayTV, questionTV;
    private EditText titleET, bodyET;
    private User user;
    private DatabaseOpenHelper dbHelper;
    private UserDBOpenHelper udbHelper;
    private String question;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydiary);

        dbHelper = new DatabaseOpenHelper(this);
        udbHelper = new UserDBOpenHelper(this);

        dayTV = (TextView) findViewById(R.id.dayText);
        questionTV = (TextView) findViewById(R.id.questionText);
        titleET = (EditText) findViewById(R.id.titleET);
        bodyET = (EditText) findViewById(R.id.bodyET);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user"); // 로그인에서 받아온 user 정보를 넘겨 받는다.

        String daySetting = "Day " + String.valueOf(user.getDayCount());
        dayTV.setText(daySetting);

        user.getMaxDay(); // 몇일을 설정했고 몇일이 남았는지 보여주기 위해 사용
        question = dbHelper.readQuestion().toString();

        questionTV.setText(question); //질문 디비가 있으며 질문 디비에서 매번 다른 질문을 꺼내와 세팅한다.


    }

    public void saveDiary(View view) {
        String title = titleET.getText().toString();
        String body = bodyET.getText().toString();

        String time;
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        time = sdfNow.format(new Date(System.currentTimeMillis()));

        dbHelper.creatDiary(user.getId(), title, question, body,
                time, user.getDayCount()); //date는 추후에 추가

        udbHelper.updateUserDay(user.getId(), user.getDayCount());
        int nextDay = user.getDayCount() + 1;
        user.setDayCount(nextDay);

        Intent userIntent = new Intent();
        userIntent.putExtra("user", user);

        setResult(1, userIntent);

        Intent svIntent = new Intent(
                getApplicationContext(),//현재제어권자
                MusicService.class); // 이동할 컴포넌트
        stopService(svIntent); // 서비스 종료

        Intent intent = new Intent(this, MyDiaryListActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }

    public void diarylistbuttonClicked(View view) {
        Intent svIntent = new Intent(
                getApplicationContext(),//현재제어권자
                MusicService.class); // 이동할 컴포넌트
        stopService(svIntent); // 서비스 종료

        Intent userIntent = new Intent();
        userIntent.putExtra("user", user);
        setResult(1, userIntent);
        Intent intent = new Intent(this, MyDiaryListActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }

    public void closeDiary(View view) {
        Intent svIntent = new Intent(
                getApplicationContext(),//현재제어권자
                MusicService.class); // 이동할 컴포넌트
        stopService(svIntent); // 서비스 종료
        Intent userIntent = new Intent();
        userIntent.putExtra("user", user);
        setResult(1, userIntent);
        finish();
    }

}
