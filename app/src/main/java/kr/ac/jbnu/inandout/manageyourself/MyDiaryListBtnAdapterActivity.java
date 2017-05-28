package kr.ac.jbnu.inandout.manageyourself;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

class MyDiaryListBtnAdapterActivity extends ArrayAdapter implements View.OnClickListener {
    // 버튼 클릭 이벤트를 위한 Listener 인터페이스 정의.
    public interface ListBtnClickListener {
        void onListBtnClick(int position);
    }

    // 생성자로부터 전달된 resource id 값을 저장.
    int resourceId;
    // 생성자로부터 전달된 ListBtnClickListener  저장.
    private ListBtnClickListener listBtnClickListener;


    // ListViewBtnAdapter 생성자. 마지막에 ListBtnClickListener 추가.
    MyDiaryListBtnAdapterActivity(Context context, int resource,
                                  ArrayList<MyDiaryListBtnActivity> list, ListBtnClickListener clickListener) {
        super(context, resource, list);

        // resource id 값 복사. (super로 전달된 resource를 참조할 방법이 없음.)
        this.resourceId = resource;

        this.listBtnClickListener = clickListener;
    }

    // 새롭게 만든 Layout을 위한 View를 생성하는 코드
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // 생성자로부터 저장된 resourceId(listview_btn_item)에 해당하는 Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this.resourceId/*R.layout.listview_btn_item*/, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)로부터 위젯에 대한 참조 획
        final TextView textTextView = (TextView) convertView.findViewById(R.id.textView1);

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        final MyDiaryListBtnActivity listViewItem = (MyDiaryListBtnActivity) getItem(position);

        // 아이템 내 각 위젯에 데이터 반영
        textTextView.setText(listViewItem.getText());

        // button2의 TAG에 position값 지정. Adapter를 click listener로 지정.
        Button diarylistenter = (Button) convertView.findViewById(R.id.diarylistenter);
        diarylistenter.setTag(position);
        diarylistenter.setOnClickListener(this);

        return convertView;
    }

    // button2가 눌려졌을 때 실행되는 onClick함수.
    public void onClick(View v) {
        // ListBtnClickListener(MainActivity)의 onListBtnClick() 함수 호출.
        if (this.listBtnClickListener != null) {
            this.listBtnClickListener.onListBtnClick((int) v.getTag());
        }
    }

}