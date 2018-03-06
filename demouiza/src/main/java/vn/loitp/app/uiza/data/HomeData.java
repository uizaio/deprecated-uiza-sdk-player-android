package vn.loitp.app.uiza.data;

import vn.loitp.restapi.uiza.model.v2.getdetailentity.Item;

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

    private Item mCurrentItem;

    public Item getItem() {
        return mCurrentItem;
    }

    public void setItem(Item item) {
        this.mCurrentItem = item;
    }
}
