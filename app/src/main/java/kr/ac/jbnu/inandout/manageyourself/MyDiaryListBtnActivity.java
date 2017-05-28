package kr.ac.jbnu.inandout.manageyourself;

public class MyDiaryListBtnActivity {
    private String textStr ;
    private String title;

    public void setText(String text) {
        textStr = text ;
    }

    public String getText() {
        return this.textStr ;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}