package kr.ac.jbnu.inandout.manageyourself;

import android.app.Application;

/**
 * Created by rlwns on 2017-05-24.
 */

public class MyApplication extends Application {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
