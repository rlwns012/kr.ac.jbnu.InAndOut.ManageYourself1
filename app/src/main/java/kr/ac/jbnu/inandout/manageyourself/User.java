package kr.ac.jbnu.inandout.manageyourself;

import java.io.Serializable;

/**
 * Created by rlwns on 2017-04-05.
 */

public class User implements Serializable {

    private String id;
    private String password;
    private String name;
    private String birth;
    private int dayCount;
    private int maxDay;


    public User(String id, String password, String name, String birth) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.dayCount = 0;
        this.maxDay = 0;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getbirth() {
        return birth;
    }

    public void setbirth(String birth) {
        this.birth = birth;
    }

    public int getDayCount() {
        return dayCount;
    }

    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }

    public int getMaxDay() {
        return maxDay;
    }

    public void setMaxDay(int maxDay) {
        this.maxDay = maxDay;
    }
}
