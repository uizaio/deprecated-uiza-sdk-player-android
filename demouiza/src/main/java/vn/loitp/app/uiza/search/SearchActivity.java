package vn.loitp.app.uiza.search;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.uiza.player.ui.player.v1.UizaPlayerActivity;

import java.util.List;

import vn.loitp.app.app.LSApplication;
import vn.loitp.app.uiza.home.view.EntityItem;
import vn.loitp.core.base.BaseActivity;
import vn.loitp.core.utilities.LDisplayUtils;
import vn.loitp.core.utilities.LKeyBoardUtil;
import vn.loitp.core.utilities.LLog;
import vn.loitp.core.utilities.LUIUtil;
import vn.loitp.restapi.restclient.RestClient;
import vn.loitp.restapi.uiza.UizaService;
import vn.loitp.restapi.uiza.model.v2.getdetailentity.Item;
import vn.loitp.restapi.uiza.model.v2.search.Search;
import vn.loitp.rxandroid.ApiSubscriber;
import vn.loitp.uiza.R;
import vn.loitp.views.placeholderview.lib.placeholderview.PlaceHolderView;
import vn.loitp.views.progressloadingview.avloadingindicatorview.lib.avi.AVLoadingIndicatorView;

import static vn.loitp.core.common.Constants.KEY_UIZA_ENTITY_COVER;
import static vn.loitp.core.common.Constants.KEY_UIZA_ENTITY_ID;
import static vn.loitp.core.common.Constants.KEY_UIZA_ENTITY_TITLE;

public class SearchActivity extends BaseActivity implements View.OnClickListener {
    private ImageView ivBack;
    private ImageView ivClearText;
    private EditText etSearch;
    private TextView tv;
    private AVLoadingIndicatorView avi;
    private PlaceHolderView placeHolderView;
    private final int NUMBER_OF_COLUMN_2 = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivClearText = (ImageView) findViewById(R.id.iv_clear_text);
        etSearch = (EditText) findViewById(R.id.et_search);
        tv = (TextView) findViewById(R.id.tv);
        avi = (AVLoadingIndicatorView) findViewById(R.id.avi);
        avi.hide();//dont smoothToHide();

        placeHolderView = (PlaceHolderView) findViewById(R.id.place_holder_view);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity, NUMBER_OF_COLUMN_2);
        placeHolderView.getBuilder()
                .setHasFixedSize(false)
                .setItemViewCacheSize(10)
                .setLayoutManager(gridLayoutManager);

        LUIUtil.setPullLikeIOSVertical(placeHolderView, new LUIUtil.Callback() {
            @Override
            public void onUpOrLeft(float offset) {
                //do nothing
            }

            @Override
            public void onUpOrLeftRefresh(float offset) {
                LLog.d(TAG, "onUpOrLeftRefresh");
                //swipeToRefresh();
            }

            @Override
            public void onDownOrRight(float offset) {
                //do nothing
            }

            @Override
            public void onDownOrRightRefresh(float offset) {
                LLog.d(TAG, "onDownOrRightRefresh");
                //loadMore();
            }
        });

        ivBack.setOnClickListener(this);
        ivClearText.setOnClickListener(this);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty()) {
                    ivClearText.setVisibility(View.GONE);
                    placeHolderView.removeAllViews();
                    tv.setVisibility(View.GONE);
                    //resetAllView();
                } else {
                    ivClearText.setVisibility(View.VISIBLE);
                    //search(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //do nothing
            }
        });

        LUIUtil.setImeiActionSearch(etSearch, new LUIUtil.CallbackSearch() {
            @Override
            public void onSearch() {
                search(etSearch.getText().toString());
            }
        });
    }

    @Override
    protected boolean setFullScreen() {
        return false;
    }

    @Override
    protected String setTag() {
        return "TAG" + getClass().getSimpleName();
    }

    @Override
    protected Activity setActivity() {
        return this;
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.uiza_search_activity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.iv_clear_text:
                etSearch.setText("");
                break;
        }
    }

    private void search(String keyword) {
        tv.setVisibility(View.GONE);
        avi.smoothToShow();
        placeHolderView.removeAllViews();

        UizaService service = RestClient.createService(UizaService.class);

        int limit = 20;
        int page = 0;

        subscribe(service.searchEntity(keyword, limit, page), new ApiSubscriber<Search>() {
            @Override
            public void onSuccess(Search search) {
                LLog.d(TAG, "search onSuccess " + LSApplication.getInstance().getGson().toJson(search));
                /*for (Item item : itemList) {
                    placeHolderView.addView(new EntityItem(getActivity(), item, sizeW, sizeH, new EntityItem.Callback() {
                        @Override
                        public void onClick(Item item, int position) {
                            onClickVideo(item, position);
                        }
                    }));
                }*/
                if (search == null || search.getItems().isEmpty()) {
                    tv.setText(getString(R.string.empty_list));
                    tv.setVisibility(View.VISIBLE);
                } else {
                    setupUIList(search.getItems());
                }
                avi.smoothToHide();
            }

            @Override
            public void onFail(Throwable e) {
                if (e == null || e.toString() == null) {
                    return;
                }
                LLog.e(TAG, "listAllEntity onFail " + e.toString());
                tv.setText("Error search " + e.toString());
                tv.setVisibility(View.VISIBLE);
                avi.smoothToHide();
            }
        });
    }

    private void resetAllView() {
        tv.setText("");
        tv.setVisibility(View.GONE);
    }

    private void setupUIList(List<Item> itemList) {
        int sizeW = LDisplayUtils.getScreenW(activity) / 2;
        int sizeH = sizeW * 9 / 16;
        for (Item item : itemList) {
            placeHolderView.addView(new EntityItem(activity, item, sizeW, sizeH, new EntityItem.Callback() {
                @Override
                public void onClick(Item item, int position) {
                    onClickVideo(item, position);
                }
            }));
        }
        LKeyBoardUtil.hide(activity);
    }

    private void onClickVideo(Item item, int position) {
        LLog.d(TAG, "onClickVideo at " + position + ": " + LSApplication.getInstance().getGson().toJson(item));
        Intent intent = new Intent(activity, UizaPlayerActivity.class);
        intent.putExtra(KEY_UIZA_ENTITY_ID, item.getId());
        intent.putExtra(KEY_UIZA_ENTITY_COVER, item.getThumbnail());
        intent.putExtra(KEY_UIZA_ENTITY_TITLE, item.getName());
        startActivity(intent);
        LUIUtil.transActivityFadeIn(activity);
    }
}
