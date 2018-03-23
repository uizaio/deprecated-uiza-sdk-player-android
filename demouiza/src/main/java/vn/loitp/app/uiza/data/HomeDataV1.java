package vn.loitp.app.uiza.data;

import vn.loitp.restapi.uiza.model.v1.listallmetadata.Item;

/**
 * Created by www.muathu@gmail.com on 11/14/2017.
 */

public class HomeDataV1 {
    private static final HomeDataV1 ourInstance = new HomeDataV1();

    public static HomeDataV1 getInstance() {
        return ourInstance;
    }

    private HomeDataV1() {
    }

    private int currentPosition;

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }


    //API v1
    private Item mCurrentItem;

    public Item getItem() {
        return mCurrentItem;
    }

    public void setItem(Item item) {
        this.mCurrentItem = item;
    }
    //End API v1
}
