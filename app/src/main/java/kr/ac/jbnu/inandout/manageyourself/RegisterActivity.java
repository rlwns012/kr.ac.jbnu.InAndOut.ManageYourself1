package kr.ac.jbnu.inandout.manageyourself;

import android.app.Activity;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by rlwns on 2017-04-04.
 */

public class RegisterActivity extends Activity {

    private Button btncomfirm, btnDuplication;
    private EditText etID, etPassword, etPasswordCheck, etName, etBirth;

    SoundPool soundPool;
    int sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        sound = soundPool.load(this,R.raw.button,1);

        btncomfirm = (Button) findViewById(R.id.btnconfirm);
        btnDuplication = (Button) findViewById(R.id.btnDuplication);

        etID = (EditText) findViewById(R.id.etID);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etPasswordCheck = (EditText) findViewById(R.id.etPasswordCheck);
        etName = (EditText) findViewById(R.id.etName);
        etBirth = (EditText) findViewById(R.id.etBirth);


        etPasswordCheck.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { // 안씀

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password = etPassword.getText().toString();
                String confirm = etPasswordCheck.getText().toString();

                if (password.equals(confirm)) {
                    etPassword.setBackgroundColor(Color.GREEN);
                    etPasswordCheck.setBackgroundColor(Color.GREEN);
                } else {
                    etPassword.setBackgroundColor(Color.RED);
                    etPasswordCheck.setBackgroundColor(Color.RED);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {  //안씀

            }
        });

        final UserDBOpenHelper udbhelper = new UserDBOpenHelper(this);

        btncomfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(sound,1,1,0,0,1);

                // 비밀번호 입력 확인
                if (etPassword.getText().toString().length() == 0) {
                    Toast.makeText(RegisterActivity.this, "비밀번호를 입력하세요!", Toast.LENGTH_SHORT).show();
                    etPassword.requestFocus();
                    return;
                }

                // 비밀번호 확인 입력 확인
                if (etPasswordCheck.getText().toString().length() == 0) {
                    Toast.makeText(RegisterActivity.this, "비밀번호 확인을 입력하세요!", Toast.LENGTH_SHORT).show();
                    etPasswordCheck.requestFocus();
                    return;
                }

                // 비밀번호 일치 확인
                if (!etPassword.getText().toString().equals(etPasswordCheck.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();
                    etPassword.setText("");
                    etPasswordCheck.setText("");
                    etPassword.requestFocus();
                    return;
                }

                // 이름 입력 확인
                if (etName.getText().toString().length() == 0) {
                    Toast.makeText(RegisterActivity.this, "이름을 입력하세요!", Toast.LENGTH_SHORT).show();
                    etName.requestFocus();
                    return;
                }

                // 생년월일 입력 확인
                if (etBirth.getText().toString().length() != 8) {
                    Toast.makeText(RegisterActivity.this, "생년월일을 입력하세요! 생년월일은 8자입니다.", Toast.LENGTH_SHORT).show();
                    etBirth.requestFocus();
                    return;
                }

                String id = etID.getText().toString();
                String password = etPassword.getText().toString();
                String name = etName.getText().toString();
                String birth = etBirth.getText().toString();

                User user = new User(id, password, name, birth);  //정보를 가져와 DB에 등록

                udbhelper.addUser(user);    //모든 조건이 만족하면 아이디를 DB에 등록한다.

                finish();

            }

        });

        btnDuplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(sound,1,1,0,0,1);

                // 아이디 입력 확인
                if (etID.getText().toString().length() < 3) {
                    Toast.makeText(RegisterActivity.this, "아이디을 입력하세요! 아이디는 3자 이상입니다!", Toast.LENGTH_SHORT).show();
                    etID.requestFocus();
                    return;
                }
                String id = etID.getText().toString(); //id를 받고 db에 넘겨서 중복을 확인한다.
                boolean flag = udbhelper.checkIdDuplication(id);  // 아이디 중복 확인
                if (flag) {
                    Toast.makeText(RegisterActivity.this, "중복된 아이디 입니다!", Toast.LENGTH_SHORT).show();
                    btncomfirm.setEnabled(false);
                } else {
                    Toast.makeText(RegisterActivity.this, "사용 가능한 아이디 입니다!", Toast.LENGTH_SHORT).show();
                    btncomfirm.setEnabled(true);
                }

            }
        });

    }

    public void gotoLoginButtonClicked(View view) {
        soundPool.play(sound,1,1,0,0,1);
        finish();
    }

}
