package kr.ac.jbnu.inandout.manageyourself;

import android.graphics.drawable.Drawable;

public class SWOTListBtnActivity {
    private String textStr ;
    private String title;

    public void setText(String text) {
        textStr = text ;
    }

    public String getText() {
        return this.textStr ;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}