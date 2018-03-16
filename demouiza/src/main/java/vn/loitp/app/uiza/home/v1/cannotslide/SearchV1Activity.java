package vn.loitp.app.uiza.home.v1.cannotslide;

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

import com.uiza.player.ui.player.v1.cannotslide.UizaPlayerActivity;

import java.util.List;

import vn.loitp.app.app.LSApplication;
import vn.loitp.app.uiza.home.view.EntityItemV1;
import vn.loitp.app.uiza.home.view.LoadingView;
import vn.loitp.core.base.BaseActivity;
import vn.loitp.core.utilities.LDisplayUtils;
import vn.loitp.core.utilities.LKeyBoardUtil;
import vn.loitp.core.utilities.LLog;
import vn.loitp.core.utilities.LUIUtil;
import vn.loitp.restapi.restclient.RestClientV2;
import vn.loitp.restapi.uiza.UizaService;
import vn.loitp.restapi.uiza.model.v1.listAllEntity.Item;
import vn.loitp.restapi.uiza.model.v1.search.Search;
import vn.loitp.rxandroid.ApiSubscriber;
import vn.loitp.uiza.R;
import vn.loitp.utils.util.ToastUtils;
import vn.loitp.views.placeholderview.lib.placeholderview.PlaceHolderView;

import static vn.loitp.core.common.Constants.KEY_UIZA_ENTITY_COVER;
import static vn.loitp.core.common.Constants.KEY_UIZA_ENTITY_ID;
import static vn.loitp.core.common.Constants.KEY_UIZA_ENTITY_TITLE;

public class SearchV1Activity extends BaseActivity implements View.OnClickListener {
    private ImageView ivBack;
    private ImageView ivClearText;
    private EditText etSearch;
    private TextView tv;
    //private AVLoadingIndicatorView avi;
    private PlaceHolderView placeHolderView;
    private final int NUMBER_OF_COLUMN_1 = 1;
    private final int NUMBER_OF_COLUMN_2 = 2;
    private final int POSITION_OF_LOADING_REFRESH = 0;
    private boolean isRefreshing;
    private boolean isLoadMoreCalling;
    private final int limit = 50;
    private int page = 0;
    private int totalPage = Integer.MAX_VALUE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivClearText = (ImageView) findViewById(R.id.iv_clear_text);
        etSearch = (EditText) findViewById(R.id.et_search);
        etSearch.requestFocus();
        tv = (TextView) findViewById(R.id.tv);
        //avi = (AVLoadingIndicatorView) findViewById(R.id.avi);
        //avi.hide();//dont smoothToHide();

        placeHolderView = (PlaceHolderView) findViewById(R.id.place_holder_view);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity, NUMBER_OF_COLUMN_2);
        placeHolderView.getBuilder()
                .setHasFixedSize(false)
                .setItemViewCacheSize(10)
                .setLayoutManager(gridLayoutManager);

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (position) {
                    case POSITION_OF_LOADING_REFRESH:
                        return isRefreshing ? NUMBER_OF_COLUMN_2 : NUMBER_OF_COLUMN_1;
                    default:
                        return NUMBER_OF_COLUMN_1;
                }
            }
        });

        LUIUtil.setPullLikeIOSVertical(placeHolderView, new LUIUtil.Callback() {
            @Override
            public void onUpOrLeft(float offset) {
                //do nothing
            }

            @Override
            public void onUpOrLeftRefresh(float offset) {
                LLog.d(TAG, "onUpOrLeftRefresh");
                swipeToRefresh();
            }

            @Override
            public void onDownOrRight(float offset) {
                //do nothing
            }

            @Override
            public void onDownOrRightRefresh(float offset) {
                LLog.d(TAG, "onDownOrRightRefresh");
                loadMore();
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
                search(etSearch.getText().toString(), false);
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

    private void search(String keyword, boolean isCallFromLoadMore) {
        LLog.d(TAG, "search keyword: " + keyword);
        tv.setVisibility(View.GONE);
        //avi.smoothToShow();
        if (isCallFromLoadMore) {
            //do nothing
        } else {
            placeHolderView.removeAllViews();
            page = 0;
            totalPage = Integer.MAX_VALUE;
        }

        LLog.d(TAG, ">>>getData " + page + "/" + totalPage);

        if (page >= totalPage) {
            LLog.d(TAG, "page >= totalPage -> return");
            ToastUtils.showShort("This is last page");
            if (isCallFromLoadMore) {
                placeHolderView.removeView(getListSize() - 1);//remove loading view
                isLoadMoreCalling = false;
            }
            return;
        }

        ToastUtils.showShort("getData page " + page);

        UizaService service = RestClientV2.createService(UizaService.class);
        subscribe(service.searchEntityV1(keyword, limit, page), new ApiSubscriber<Search>() {
            @Override
            public void onSuccess(Search search) {
                LLog.d(TAG, "search onSuccess " + LSApplication.getInstance().getGson().toJson(search));

                if (totalPage == Integer.MAX_VALUE) {
                    int totalItem = search.getTotal();
                    float ratio = (float) (totalItem / limit);
                    LLog.d(TAG, "ratio: " + ratio);
                    if (ratio == 0) {
                        totalPage = (int) ratio;
                    } else if (ratio > 0) {
                        totalPage = (int) ratio + 1;
                    } else {
                        totalPage = (int) ratio;
                    }
                    LLog.d(TAG, ">>>totalPage: " + totalPage);
                }

                if (search == null || search.getItems().isEmpty()) {
                    tv.setText(getString(R.string.empty_list));
                    tv.setVisibility(View.VISIBLE);
                } else {
                    setupUIList(search.getItems());
                }
            }

            @Override
            public void onFail(Throwable e) {
                if (e == null || e.toString() == null) {
                    return;
                }
                LLog.e(TAG, "search onFail " + e.toString());
                tv.setText("Error search " + e.toString());
                tv.setVisibility(View.VISIBLE);
                //avi.smoothToHide();
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
            placeHolderView.addView(new EntityItemV1(activity, item, sizeW, sizeH, new EntityItemV1.Callback() {
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

    private void swipeToRefresh() {
        if (isRefreshing) {
            return;
        }
        isRefreshing = true;
        placeHolderView.addView(POSITION_OF_LOADING_REFRESH, new LoadingView());

        LUIUtil.setDelay(2000, new LUIUtil.DelayCallback() {
            @Override
            public void doAfter(int mls) {
                placeHolderView.removeView(POSITION_OF_LOADING_REFRESH);
                isRefreshing = false;
            }
        });
    }

    private void loadMore() {
        if (isLoadMoreCalling) {
            return;
        }
        isLoadMoreCalling = true;
        placeHolderView.addView(new LoadingView());
        placeHolderView.smoothScrollToPosition(getListSize() - 1);
        page++;
        search(etSearch.getText().toString().trim(), true);
    }

    private int getListSize() {
        LLog.d(TAG, "getListSize " + placeHolderView.getAllViewResolvers().size());
        return placeHolderView.getAllViewResolvers().size();
    }
}
