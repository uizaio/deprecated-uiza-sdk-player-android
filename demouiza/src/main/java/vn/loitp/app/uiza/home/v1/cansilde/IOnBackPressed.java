package vn.loitp.app.uiza.home.v1.cansilde;

/**
 * Created by LENOVO on 3/9/2018.
 */

public interface IOnBackPressed {
    /**
     * If you return true the back press will not be taken into account, otherwise the activity will act naturally
     *
     * @return true if your processing has priority if not false
     */
    boolean onBackPressed();
}
