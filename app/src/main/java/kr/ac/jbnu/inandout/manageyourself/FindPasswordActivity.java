package kr.ac.jbnu.inandout.manageyourself;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by rlwns on 2017-04-03.
 */

public class FindPasswordActivity extends Activity {
    private EditText etID, etBirth, etName;
    private TextView foundps;
    SoundPool soundPool;
    int sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findpassword);
        etID = (EditText) findViewById(R.id.editText8);
        etName = (EditText) findViewById(R.id.editText6);
        etBirth = (EditText) findViewById(R.id.editText7);
        foundps = (TextView)findViewById(R.id.foundPs);

        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        sound = soundPool.load(this,R.raw.button,1);
    }
    public void getPsButtonClicked(View view) {

        soundPool.play(sound,1,1,0,0,1);

        String id = etID.getText().toString();
        String name = etName.getText().toString();
        String birth = etBirth.getText().toString();
        UserDBOpenHelper udbHelper = new UserDBOpenHelper(FindPasswordActivity.this);
        ArrayList<String> pss = udbHelper.findPs(id, name, birth);
        Iterator iterator = pss.iterator();
        String book2 ="";
        if(!iterator.hasNext())           Toast.makeText(FindPasswordActivity.this,
                "아이디와 이름과 생년월일을 다시 확인해주세요", Toast.LENGTH_SHORT).show();
        while (iterator.hasNext() ){
            book2 = book2 + iterator.next() +"  ";
        }
        foundps.setText(book2);

    }
    public void gotoLoginButtonClicked(View view){
        soundPool.play(sound,1,1,0,0,1);

        finish();
    }
}
