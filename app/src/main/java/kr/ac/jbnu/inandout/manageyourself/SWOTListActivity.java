package kr.ac.jbnu.inandout.manageyourself;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by rlwns on 2017-05-21.
 */

public class SWOTListActivity extends AppCompatActivity implements SWOTListBtnAdapterActivity.ListBtnClickListener {

    static final String[] LIST_MENU = {};
    private DatabaseOpenHelper dbHelper;
    private User user;
    private ArrayList<SWOTContainer> swotContainers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swotlist);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user"); // 로그인에서 받아온 user 정보를 넘겨 받는다.
        dbHelper = new DatabaseOpenHelper(this);
        swotContainers = dbHelper.readSWOT(user.getId());

        ListView listview;
        SWOTListBtnAdapterActivity adapter;
        ArrayList<SWOTListBtnActivity> items = new ArrayList<SWOTListBtnActivity>();

        // items 로드.
        loadItemsFromDB(items);

        // Adapter 생성
        adapter = new SWOTListBtnAdapterActivity(this, R.layout.activity_swotlistbtn, items, this);

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview2);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // TODO : item click
            }
        });
    }

    public boolean loadItemsFromDB(ArrayList<SWOTListBtnActivity> list) {
        SWOTListBtnActivity item;

        if (list == null) {
            list = new ArrayList<SWOTListBtnActivity>();
        }

        // 순서를 위한 i 값을 1로 초기화.

        // 아이템 생성.
        for (int i = swotContainers.size(); i > 0; i--) {
            item = new SWOTListBtnActivity();
            item.setText(String.valueOf(swotContainers.get(i-1).getIdx()));
            item.setTitle(swotContainers.get(i-1).getTitle());
            list.add(item);
        }

        return true;
    }

    @Override
    public void onListBtnClick(int position) {
        Intent intent = new Intent(this, WrittenSWOT.class);
        int index = swotContainers.size()-position;
        intent.putExtra("idx",index);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }
    public void addSWOT(View view){
        Intent intent = new Intent(this, SWOTActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }

}
