package kr.ac.jbnu.inandout.manageyourself;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rlwns on 2017-05-21.
 */

public class SWOTActivity extends Activity {

    private User user;
    private DatabaseOpenHelper dbHelper;
    private Button closeBtn, saveBtn, listBtn;
    private TextView strength, weakness, opportunity, threat;
    private ArrayList<SWOTContainer> swotContainers;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swot);

        closeBtn = (Button)findViewById(R.id.closeSWOT);
        saveBtn= (Button)findViewById(R.id.SWOTSaveBtn);
        listBtn = (Button)findViewById(R.id.backToSWOTListBtn);

        strength = (TextView)findViewById(R.id.strengthET);
        weakness= (TextView)findViewById(R.id.weaknessET);
        opportunity = (TextView)findViewById(R.id.opportunityET);
        threat = (TextView)findViewById(R.id.threatET);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user"); // 로그인에서 받아온 user 정보를 넘겨 받는다.

        dbHelper = new DatabaseOpenHelper(this);
        swotContainers = dbHelper.readSWOT(user.getId(),1);//swot을 구분할 수 있는 idx값을 이용한다.

    }

    public void backToSWOTList(View view){

        Intent intent = new Intent(this, SWOTListActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }

    public void saveSWOT(View view){

    }
    public void closeSWOT(View view){
        finish();
    }

}
