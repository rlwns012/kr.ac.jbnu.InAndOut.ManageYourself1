package kr.ac.jbnu.inandout.manageyourself;

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

    static final String[] LIST_MENU = {} ;
    // ... 코드 계속

    public boolean loadItemsFromDB(ArrayList<SWOTListBtnActivity> list) {
        SWOTListBtnActivity item ;
        int i ;

        if (list == null) {
            list = new ArrayList<SWOTListBtnActivity>() ;
        }

        // 순서를 위한 i 값을 1로 초기화.
        i = 4 ;

        // 아이템 생성.
        item = new SWOTListBtnActivity() ;
        item.setText(Integer.toString(i) + "번") ;
        list.add(item) ;
        i-- ;

        item = new SWOTListBtnActivity() ;
        item.setText(Integer.toString(i) + "번") ;
        list.add(item) ;
        i-- ;

        item = new SWOTListBtnActivity() ;
        item.setText(Integer.toString(i) + "번") ;
        list.add(item) ;
        i-- ;

        item = new SWOTListBtnActivity() ;
        item.setText(Integer.toString(i) + "번") ;
        list.add(item) ;

        return true ;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swotlist);
        // 코드 계속 ...

        ListView listview ;
        SWOTListBtnAdapterActivity adapter;
        ArrayList<SWOTListBtnActivity> items = new ArrayList<SWOTListBtnActivity>() ;

        // items 로드.
        loadItemsFromDB(items) ;

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
        }) ;
    }

    @Override
    public void onListBtnClick(int position) {
        Toast.makeText(this, "들어갑니다..", Toast.LENGTH_SHORT).show() ;
    }

}
