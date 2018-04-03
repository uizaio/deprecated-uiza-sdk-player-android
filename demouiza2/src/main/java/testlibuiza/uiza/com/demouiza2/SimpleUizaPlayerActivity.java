package testlibuiza.uiza.com.demouiza2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;

import com.google.gson.Gson;
import com.uiza.player.ui.data.UizaData;
import com.uiza.player.ui.player.v2.cannotslide.FrmUizaVideoV2;
import com.uiza.player.ui.player.v2.cannotslide.UizaPlayerActivityV2;
import com.uiza.player.ui.util.UizaUIUtil;
import com.uiza.player.ui.views.helper.InputModel;

import java.util.ArrayList;
import java.util.List;

import vn.loitp.core.base.BaseActivity;
import vn.loitp.core.common.Constants;
import vn.loitp.core.utilities.LActivityUtil;
import vn.loitp.core.utilities.LLog;
import vn.loitp.core.utilities.LPref;
import vn.loitp.restapi.restclient.RestClientV2;
import vn.loitp.restapi.uiza.model.v2.auth.Auth;

import static vn.loitp.core.common.Constants.KEY_UIZA_ENTITY_COVER;
import static vn.loitp.core.common.Constants.KEY_UIZA_ENTITY_ID;
import static vn.loitp.core.common.Constants.KEY_UIZA_ENTITY_TITLE;

public class SimpleUizaPlayerActivity extends BaseActivity {

    private FrameLayout containerUizaVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        containerUizaVideo = (FrameLayout) findViewById(io.uiza.sdk.ui.R.id.container_uiza_video);

        initContainerVideo();
    }

    @Override
    protected boolean setFullScreen() {
        return false;
    }

    @Override
    protected String setTag() {
        return getClass().getSimpleName();
    }

    @Override
    protected Activity setActivity() {
        return this;
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_simple_uiza_player;
    }

    private void initContainerVideo() {
        String entityId = "69af37f9-b5de-446a-bb58-44fe1e315ba2";
        //String entityCover = "//dev-static.uiza.io/69af37f9-b5de-446a-bb58-44fe1e315ba2-thumbnail-1522730799619-1522730799098.jpeg";
        String entityCover = "https://kenh14cdn.com/2018/2/25/photo-3-15195643072361674959682.jpg";
        String entityTitle = "Japan girl bikini part8!日本妹比堅尼戰鬥格!!";

        InputModel inputModel = UizaUIUtil.createInputModel(entityId, entityCover, entityTitle);
        UizaData.getInstance().setInputModel(inputModel);

        List<String> listLinkPlay = new ArrayList<>();
        listLinkPlay.add("https://dev-cdn.uiza.io:443/mx5Z5wIs/package/playlist.mpd");
        listLinkPlay.add("https://cdn-vn-cache-3.uiza.io:443/a204e9cdeca44948a33e0d012ef74e90/mx5Z5wIs/package/playlist.mpd");
        UizaData.getInstance().setLinkPlay(listLinkPlay);

        FrmUizaVideoV2 frmUizaVideoV2 = new FrmUizaVideoV2();
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(containerUizaVideo.getId(), frmUizaVideoV2);
        transaction.commit();
    }
}
