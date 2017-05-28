package kr.ac.jbnu.inandout.manageyourself;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

/**
 * Created by hong on 2017-05-28.
 */

public class WrittenSWOT extends Activity {
    private DatabaseOpenHelper dbHelper;
    private User user;
    private SWOTContainer swotContainer;
    private TextView titleTV, strengthTV, weaknessTV, opportunityTV, threatTV, soTV, stTV, woTV, wtTV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writtenswot);

        Intent intent = getIntent();
        int idx = intent.getIntExtra("idx", 0);
        user = (User) intent.getSerializableExtra("user"); // 로그인에서 받아온 user 정보를 넘겨 받는다.
        dbHelper = new DatabaseOpenHelper(this);
        swotContainer = dbHelper.readSWOT(user.getId(),idx);

        titleTV = (TextView) findViewById(R.id.writtenswottitle);
        strengthTV = (TextView) findViewById(R.id.writtenStrengthTV);
        weaknessTV = (TextView) findViewById(R.id.writtenWeaknessTV);
        opportunityTV = (TextView) findViewById(R.id.writtenOpportunityTV);
        threatTV = (TextView) findViewById(R.id.writtenThreatTV);
        soTV = (TextView) findViewById(R.id.writtenSOTV);
        stTV = (TextView) findViewById(R.id.wriitenSTTV);
        woTV = (TextView) findViewById(R.id.writtenWOTV);
        wtTV = (TextView) findViewById(R.id.writtenWTTV);

        titleTV.setText(swotContainer.getTitle().toString());
        strengthTV.setText(swotContainer.getStrength());
        weaknessTV.setText(swotContainer.getWeakness());
        opportunityTV.setText(swotContainer.getOpportunity());
        threatTV.setText(swotContainer.getThreat());
        soTV.setText(swotContainer.getSo());
        stTV.setText(swotContainer.getSt());
        woTV.setText(swotContainer.getWo());
        wtTV.setText(swotContainer.getWt());

    }

}
