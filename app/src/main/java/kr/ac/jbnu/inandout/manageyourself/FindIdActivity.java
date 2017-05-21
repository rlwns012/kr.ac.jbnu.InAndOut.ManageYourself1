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

import static android.R.attr.name;

/**
 * Created by rlwns on 2017-04-03.
 */

public class FindIdActivity extends Activity {
    private EditText etName, etBirth;
    private TextView foundid;
    SoundPool soundPool;
    int sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findid);
        etName = (EditText) findViewById(R.id.editText4);
        etBirth = (EditText) findViewById(R.id.editText5);
        foundid = (TextView)findViewById(R.id.foundId);

        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        sound = soundPool.load(this,R.raw.button,1);
    }

    public void getIdButtonClicked(View view) {

        soundPool.play(sound,1,1,0,0,1);

        String name = etName.getText().toString();
        String birth = etBirth.getText().toString();
        UserDBOpenHelper udbHelper = new UserDBOpenHelper(FindIdActivity.this);
        ArrayList<String> ids = udbHelper.findId(name, birth);
        Iterator iterator = ids.iterator();
        String book1 ="";
        if(!iterator.hasNext())           Toast.makeText(FindIdActivity.this,
                "이름과 생년월일을 다시 확인해주세요", Toast.LENGTH_SHORT).show();
        while (iterator.hasNext() ){
            book1 = book1 + iterator.next() +"  ";
        }
        foundid.setText(book1);

    }


    public void gotoLoginButtonClicked(View view) {
        soundPool.play(sound,1,1,0,0,1);

        finish();
    }

}
