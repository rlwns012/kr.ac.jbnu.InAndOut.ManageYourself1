package kr.ac.jbnu.inandout.manageyourself;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by hong on 2017-05-29.
 */

public class SWOTModiActivity extends Activity {
    private DatabaseOpenHelper dbHelper;
    private User user;
    private SWOTContainer swotContainer;
    private Button closeBtn, saveBtn, listBtn;
    private TextView titleModiTV, strengthModiTV, weaknessModiTV, opportunityModiTV, threatModiTV, soModiTV, stModiTV, woModiTV, wtModiTV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swotmodi);

        Intent intent = getIntent();
        int idx = intent.getIntExtra("idx", 0);
        user = (User) intent.getSerializableExtra("user"); // 로그인에서 받아온 user 정보를 넘겨 받는다.
        dbHelper = new DatabaseOpenHelper(this);
        swotContainer = dbHelper.readSWOT(user.getId(), idx);

        titleModiTV = (TextView) findViewById(R.id.swotModiTitle);
        strengthModiTV = (TextView) findViewById(R.id.strengthModiET);
        weaknessModiTV = (TextView) findViewById(R.id.weaknessModiET);
        opportunityModiTV = (TextView) findViewById(R.id.opportunityModiET);
        threatModiTV = (TextView) findViewById(R.id.threatModiET);
        soModiTV = (TextView) findViewById(R.id.soModiTV);
        stModiTV = (TextView) findViewById(R.id.stModiTV);
        woModiTV = (TextView) findViewById(R.id.woModiTV);
        wtModiTV = (TextView) findViewById(R.id.wtModiTV);

        closeBtn = (Button) findViewById(R.id.closeModiSWOT);
        saveBtn = (Button) findViewById(R.id.SWOTModiBtn);
        listBtn = (Button) findViewById(R.id.modiBackToSWOTListBtn);

        swotContainer = dbHelper.readSWOT(user.getId(), idx);

        titleModiTV.setText(swotContainer.getTitle().toString());
        strengthModiTV.setText(swotContainer.getStrength());
        weaknessModiTV.setText(swotContainer.getWeakness());
        opportunityModiTV.setText(swotContainer.getOpportunity());
        threatModiTV.setText(swotContainer.getThreat());
        soModiTV.setText(swotContainer.getSo());
        stModiTV.setText(swotContainer.getSt());
        woModiTV.setText(swotContainer.getWo());
        wtModiTV.setText(swotContainer.getWt());

    }

    public void modiBackToSWOTList(View view) {
        Intent intent = new Intent(this, SWOTListActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }

    public void modiSWOT(View view) {
        swotContainer.setTitle(titleModiTV.getText().toString());
        swotContainer.setStrength(strengthModiTV.getText().toString());
        swotContainer.setWeakness(weaknessModiTV.getText().toString());
        swotContainer.setOpportunity(opportunityModiTV.getText().toString());
        swotContainer.setTreat(threatModiTV.getText().toString());
        swotContainer.setSo(soModiTV.getText().toString());
        swotContainer.setSt(stModiTV.getText().toString());
        swotContainer.setWo(woModiTV.getText().toString());
        swotContainer.setWt(wtModiTV.getText().toString());

        dbHelper.updateSWOT(swotContainer);

        Intent dataIntent = new Intent();
        dataIntent.putExtra("swotContainer",swotContainer);
        setResult(2,dataIntent);
        finish();
    }

    public void closeModiSWOT(View view) {
        finish();
    }
}
