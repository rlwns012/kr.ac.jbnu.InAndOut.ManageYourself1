package kr.ac.jbnu.inandout.manageyourself;

/**
 * Created by rlwns on 2017-05-24.
 */

public class DiaryContainer {
    private String title;
    private String body;
    private String date;
    private String dayQues;

    public DiaryContainer(String title, String body, String date, String dayQues){
        this.title = title;
        this.body = body;
        this.date = date;
        this.dayQues = dayQues;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDayQues() {
        return dayQues;
    }

    public void setDayQues(String dayQues) {
        this.dayQues = dayQues;
    }
}
