package kr.ac.jbnu.inandout.manageyourself;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by rlwns on 2017-05-21.
 */

public class PyramidActivity extends Activity {
    private EditText missionET, visionET, tacticET, actionPlanET, actionTaskET;
    private DatabaseOpenHelper dbHelper;
    private User user;
    private boolean flag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pyramid);

        dbHelper = new DatabaseOpenHelper(this);
        flag = false;

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user"); // 로그인에서 받아온 user 정보를 넘겨 받는다.

        missionET = (EditText) findViewById(R.id.missionET);
        visionET = (EditText) findViewById(R.id.visionET);
        tacticET = (EditText) findViewById(R.id.tacticET);
        actionPlanET = (EditText) findViewById(R.id.actionPlanET);
        actionTaskET = (EditText) findViewById(R.id.actionTaskET);

        PyramidContainer pyramidContainer = dbHelper.readPyramid(user.getId());
        if (pyramidContainer.getMission() !=null) {
            missionET.setText(pyramidContainer.getMission().toString());
            visionET.setText(pyramidContainer.getVision().toString());
            tacticET.setText(pyramidContainer.getTactic().toString());
            actionPlanET.setText(pyramidContainer.getActionPlan().toString());
            actionTaskET.setText(pyramidContainer.getActionTask().toString());
            flag = true;
        }
    }

    public void savePyramid(View view) {
        String mission = missionET.getText().toString();
        String vision = visionET.getText().toString();
        String tactic = tacticET.getText().toString();
        String actionPlan = actionPlanET.getText().toString();
        String actionTask = actionTaskET.getText().toString();

        if (flag) {
            dbHelper.deletePyramid(user.getId());
            dbHelper.creatPyramid(user.getId(),mission, vision, tactic, actionPlan, actionTask);
        }else{
            dbHelper.creatPyramid(user.getId(),mission, vision, tactic, actionPlan, actionTask);
        }
        Toast.makeText(this, "피라미드가 작성 되었습니다.", Toast.LENGTH_SHORT).show();

    }

    public void closePyramid(View view) {
        finish();
    }
}
