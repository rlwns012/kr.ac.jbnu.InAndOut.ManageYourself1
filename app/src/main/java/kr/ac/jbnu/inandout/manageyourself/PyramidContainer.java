package kr.ac.jbnu.inandout.manageyourself;

/**
 * Created by rlwns on 2017-05-24.
 */

public class PyramidContainer {
    private String mission;
    private String vision;
    private String tactic;
    private String actionPlan;
    private String actionTask;

    public PyramidContainer() {
    }

    public PyramidContainer(String mission, String vision, String tactic, String actionTask, String actionPlan) {

        this.mission = mission;
        this.vision = vision;
        this.tactic = tactic;
        this.actionPlan = actionPlan;
        this.actionTask = actionTask;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public String getVision() {
        return vision;
    }

    public void setVision(String vision) {
        this.vision = vision;
    }

    public String getTactic() {
        return tactic;
    }

    public void setTactic(String tactic) {
        this.tactic = tactic;
    }

    public String getActionPlan() {
        return actionPlan;
    }

    public void setActionPlan(String actionPlan) {
        this.actionPlan = actionPlan;
    }

    public String getActionTask() {
        return actionTask;
    }

    public void setActionTask(String actionTask) {
        this.actionTask = actionTask;
    }
}
