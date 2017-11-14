package vn.loitp.app.uiza.data;

/**
 * Created by www.muathu@gmail.com on 11/14/2017.
 */

public class HomeData {
    private static final HomeData ourInstance = new HomeData();

    public static HomeData getInstance() {
        return ourInstance;
    }

    private HomeData() {
    }

    private int currentPosition;

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }
}
