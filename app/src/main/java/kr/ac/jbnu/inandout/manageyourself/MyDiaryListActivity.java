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

public class MyDiaryListActivity extends AppCompatActivity implements MyDiaryListBtnAdapterActivity.ListBtnClickListener {

    static final String[] LIST_MENU = {};
    private DatabaseOpenHelper dbHelper;
    private User user;
    private ArrayList<DiaryContainer> diaryContainers;

    // ... 코드 계속

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydiarylist);
        // 코드 계속 ...
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user"); // 로그인에서 받아온 user 정보를 넘겨 받는다.
        dbHelper = new DatabaseOpenHelper(this);
        diaryContainers = dbHelper.readDiary(user.getId());

        ListView listview;
        MyDiaryListBtnAdapterActivity adapter;
        ArrayList<MyDiaryListBtnActivity> items = new ArrayList<MyDiaryListBtnActivity>();

        // items 로드.
        loadItemsFromDB(items, user.getDayCount()); // 다이어리 작성 개수만큼 넣어주어야함 이건 곳 dayCount가 됨

        // Adapter 생성
        adapter = new MyDiaryListBtnAdapterActivity(this, R.layout.activity_mydiarylistbtn, items, this);

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // TODO : item click
            }
        });
    }

    public boolean loadItemsFromDB(ArrayList<MyDiaryListBtnActivity> list, int dayCount) {
        MyDiaryListBtnActivity item;

        if (list == null) {
            list = new ArrayList<MyDiaryListBtnActivity>();
        }

        // 순서를 위한 i 값을 1로 초기화.



        // 아이템 생성.
        for (int i = diaryContainers.size(); i > 0; i--) {
            item = new MyDiaryListBtnActivity();
            item.setText("Day " + Integer.toString(i));
            list.add(item);
        }

        /*

        item = new MyDiaryListBtnActivity() ;
        item.setText(Integer.toString(i) + "번") ;
        list.add(item) ;


        item = new MyDiaryListBtnActivity() ;
        item.setText(Integer.toString(i) + "번") ;
        list.add(item) ;

        item = new MyDiaryListBtnActivity() ;
        item.setText(Integer.toString(i) + "번") ;
        list.add(item) ;

        */
        return true;
    }

    @Override
    public void onListBtnClick(int position) {
        Toast.makeText(this, "들어갑니다..", Toast.LENGTH_SHORT).show();
        int dayCount = diaryContainers.size()-position;
        Intent intent = new Intent(this, MyWrittenDiary.class);
        intent.putExtra("user", user);
        intent.putExtra("dayCount",dayCount);
        startActivity(intent);
    }
}
