package kr.ac.jbnu.inandout.manageyourself;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by rlwns on 2017-05-21.
 */

public class SWOTActivity extends Activity {

    private User user;
    private DatabaseOpenHelper dbHelper;
    private Button closeBtn, saveBtn, listBtn;
    private TextView strengthTV, weaknessTV, opportunityTV, threatTV, soTV, stTV, woTV, wtTV,titleTV;
    private ArrayList<SWOTContainer> swotContainers;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swot);

        closeBtn = (Button) findViewById(R.id.closeSWOT);
        saveBtn = (Button) findViewById(R.id.SWOTSaveBtn);
        listBtn = (Button) findViewById(R.id.backToSWOTListBtn);

        strengthTV = (TextView) findViewById(R.id.strengthET);
        weaknessTV = (TextView) findViewById(R.id.weaknessET);
        opportunityTV = (TextView) findViewById(R.id.opportunityET);
        threatTV = (TextView) findViewById(R.id.threatET);
        soTV = (TextView) findViewById(R.id.soTV);
        stTV = (TextView) findViewById(R.id.stTV);
        woTV = (TextView) findViewById(R.id.woTV);
        wtTV = (TextView) findViewById(R.id.wtTV);
        titleTV = (TextView)findViewById(R.id.swotTitle);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user"); // 로그인에서 받아온 user 정보를 넘겨 받는다.

        dbHelper = new DatabaseOpenHelper(this);
        //swot을 구분할 수 있는 idx값을 이용한다.

    }

    public void backToSWOTList(View view) {

        Intent intent = new Intent(this, SWOTListActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }

    public void saveSWOT(View view) {
        String soStr = soTV.getText().toString();
        String stStr = stTV.getText().toString();
        String woStr = woTV.getText().toString();
        String wtStr = wtTV.getText().toString();
        String opportunity = opportunityTV.getText().toString();
        String weakness = weaknessTV.getText().toString();
        String strength = strengthTV.getText().toString();
        String threat = threatTV.getText().toString();
        String title = titleTV.getText().toString();

        dbHelper.creatSWOT(user.getId(), soStr, stStr, woStr, wtStr, opportunity, weakness, strength, threat, title);

        Intent intent = new Intent(this, SWOTListActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }

    public void closeSWOT(View view) {
        finish();
    }

}
