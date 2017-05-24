package kr.ac.jbnu.inandout.manageyourself;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by rlwns on 2017-05-23.
 */

public class MyDiary extends Activity {

    private TextView day, question;
    private EditText title, body;
    private User user;
    private DatabaseOpenHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydiary);

        dbHelper = new DatabaseOpenHelper(this);

        day = (TextView)findViewById(R.id.dayText);
        question = (TextView)findViewById(R.id.questionText);
        title = (EditText)findViewById(R.id.titleET);
        body = (EditText)findViewById(R.id.bodyET);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user"); // 로그인에서 받아온 user 정보를 넘겨 받는다.

        String daySetting = "Day " + String.valueOf(user.getDayCount());
        day.setText(daySetting);

        question.setText(dbHelper.readQuestion().toString()); //질문 디비가 있으며 질문 디비에서 매번 다른 질문을 꺼내와 세팅한다.
        day.setText(daySetting);



    }

    public void saveDiary(View view) {

    }

    public void closeDiary(View view) {

    }
}
