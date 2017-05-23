package kr.ac.jbnu.inandout.manageyourself;

/**
 * Created by rlwns on 2017-05-24.
 */

public class SWOTContainer {
    private int idx;
    private String title;
    private String strength;
    private String weakness;
    private String opportunity;
    private String treat;
    private String so;
    private String st;
    private String wo;
    private String wt;

    public SWOTContainer(int idx, String title, String strength, String weakness, String opportunity, String treat,
                         String so, String st, String wt, String wo) {
        this.idx = idx;
        this.title = title;
        this.strength = strength;
        this.weakness = weakness;
        this.opportunity = opportunity;
        this.treat = treat;
        this.so = so;
        this.st = st;
        this.wt = wt;
        this.wo = wo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getWeakness() {
        return weakness;
    }

    public void setWeakness(String weakness) {
        this.weakness = weakness;
    }

    public String getTreat() {
        return treat;
    }

    public void setTreat(String treat) {
        this.treat = treat;
    }

    public String getOpportunity() {
        return opportunity;
    }

    public void setOpportunity(String opportunity) {
        this.opportunity = opportunity;
    }

    public String getSo() {
        return so;
    }

    public void setSo(String so) {
        this.so = so;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public String getWo() {
        return wo;
    }

    public void setWo(String wo) {
        this.wo = wo;
    }

    public String getWt() {
        return wt;
    }

    public void setWt(String wt) {
        this.wt = wt;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }
}
