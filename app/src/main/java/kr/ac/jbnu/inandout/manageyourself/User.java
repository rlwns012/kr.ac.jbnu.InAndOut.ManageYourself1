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
    private int score;
    private int timePerGame;
    private int playTime;
    private int playCount;

    public User(String id, String password, String name, String birth) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.score = 0;
        this.playTime = 0;
        this.timePerGame = 60;
        this.playCount = 0;
    }

    public void renewUser(int score, int playTime) {
        if (this.score < score) {        // 최고 점수를 받으면 점수와 게임당 시간을 갱신한다.
            this.score = score;
            this.timePerGame = playTime;
        }
        this.playCount++;       // 플레이 횟수 증가
        if (this.score == score && this.timePerGame > playTime) this.timePerGame = playTime;
        // 점수가 같고 게임당 시간이 적으면 게임당 시간을 갱신힌다.
        this.playTime += playTime;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPlayTime() {
        return playTime;
    }

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public int getTimePerGame() {
        return timePerGame;
    }

    public void setTimePerGame(int timePerGame) {
        this.timePerGame = timePerGame;
    }


}
