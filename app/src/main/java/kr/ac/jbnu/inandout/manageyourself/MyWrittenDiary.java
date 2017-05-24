package kr.ac.jbnu.inandout.manageyourself;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

/**
 * Created by rlwns on 2017-05-25.
 */

public class MyWrittenDiary extends Activity {

    private TextView titleTV, bodyTV, dayCountTV, dateTV;
    private DatabaseOpenHelper dbHelper;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividy_writtendiary);

        titleTV = (TextView) findViewById(R.id.titleTV);
        bodyTV = (TextView) findViewById(R.id.bodyTV);
        dayCountTV = (TextView) findViewById(R.id.dayCountTV);
        dateTV = (TextView) findViewById(R.id.dateTV);

        dbHelper = new DatabaseOpenHelper(this);
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user"); // 로그인에서 받아온 user 정보를 넘겨 받는다.

        DiaryContainer diaryContainer = dbHelper.readDiary(user.getId(),user.getDayCount());
        titleTV.setText(diaryContainer.getTitle().toString());
        bodyTV.setText(diaryContainer.getBody().toString());
        dayCountTV.setText(String.valueOf(diaryContainer.getDayCount()));
        //dateTV.setText(diaryContainer.getDate().toString());
    }
}
