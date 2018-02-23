package vn.loitp.app.uiza.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uiza.player.ui.player.v1.FrmUizaVideo;
import com.uiza.player.ui.player.v1.UizaPlayerActivity;
import com.uiza.player.ui.views.helper.InputModel;

import java.util.ArrayList;
import java.util.List;

import vn.loitp.app.app.LSApplication;
import vn.loitp.app.uiza.data.HomeData;
import vn.loitp.app.uiza.home.model.ChannelObject;
import vn.loitp.app.uiza.home.model.Item;
import vn.loitp.app.uiza.home.view.ChannelItem;
import vn.loitp.app.uiza.home.view.ChannelList;
import vn.loitp.app.uiza.home.view.PosterView;
import vn.loitp.core.base.BaseFragment;
import vn.loitp.core.utilities.LLog;
import vn.loitp.core.utilities.LUIUtil;
import vn.loitp.uiza.R;
import vn.loitp.views.placeholderview.lib.placeholderview.InfinitePlaceHolderView;
import vn.loitp.views.progressloadingview.avloadingindicatorview.lib.avi.AVLoadingIndicatorView;

/**
 * Created by www.muathu@gmail.com on 7/26/2017.
 */

public class FrmChannel extends BaseFragment {
    private final String TAG = getClass().getSimpleName();
    private TextView tv;
    private InfinitePlaceHolderView infinitePlaceHolderView;
    private AVLoadingIndicatorView avLoadingIndicatorView;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.uiza_frm_channel, container, false);
        tv = (TextView) view.findViewById(R.id.tv);
        tv.setText("Debug: " + HomeData.getInstance().getData());

        infinitePlaceHolderView = (InfinitePlaceHolderView) view.findViewById(R.id.place_holder_view);
        avLoadingIndicatorView = (AVLoadingIndicatorView) view.findViewById(R.id.avi);
        avLoadingIndicatorView.smoothToShow();

        LUIUtil.setPullLikeIOSVertical(infinitePlaceHolderView);
        getData();
        return view;
    }

    private List<Item> getList(List<Item> itemList, int startIndex, int endIndex) {
        if (startIndex < 0 || endIndex > itemList.size()) {
            return null;
        }
        List<Item> items = new ArrayList<>();
        for (int i = startIndex; i < endIndex; i++) {
            items.add(itemList.get(i));
        }
        LLog.d(TAG, "getList " + startIndex + " - " + endIndex + " -> " + items.size());
        return items;
    }

    private void setupData(List<Item> itemList) {
        //poster
        List<Item> itemListPoster = getList(itemList, 0, 5);
        infinitePlaceHolderView.addView(new PosterView(getActivity(), itemListPoster, new PosterView.Callback() {
            @Override
            public void onClick(Item item, int position) {
                onClickVideo(item, position);
            }
        }));

        //top movie
        ChannelObject channelObjectTopMovies = new ChannelObject();
        channelObjectTopMovies.setChannelName("Top Movies");
        List<Item> itemListTopMovies = getList(itemList, 6, 15);
        channelObjectTopMovies.setItemList(itemListTopMovies);
        infinitePlaceHolderView.addView(new ChannelList(getActivity(), channelObjectTopMovies, new ChannelItem.Callback() {
            @Override
            public void onClick(Item item, int position) {
                onClickVideo(item, position);
            }
        }));

        //top movie
        ChannelObject channelObjectNewestMovies = new ChannelObject();
        channelObjectNewestMovies.setChannelName("Newest Movies");
        List<Item> itemListNewestMovies = getList(itemList, 16, itemList.size() - 1);
        channelObjectNewestMovies.setItemList(itemListNewestMovies);
        infinitePlaceHolderView.addView(new ChannelList(getActivity(), channelObjectNewestMovies, new ChannelItem.Callback() {
            @Override
            public void onClick(Item item, int position) {
                onClickVideo(item, position);
            }
        }));

        //top movie
        ChannelObject channelObjectAllMovies = new ChannelObject();
        channelObjectAllMovies.setChannelName("All Movies");
        channelObjectAllMovies.setItemList(itemList);
        infinitePlaceHolderView.addView(new ChannelList(getActivity(), channelObjectAllMovies, new ChannelItem.Callback() {
            @Override
            public void onClick(Item item, int position) {
                onClickVideo(item, position);
            }
        }));

        avLoadingIndicatorView.smoothToHide();
    }

    private void onClickVideo(Item item, int position) {
        LLog.d(TAG, "onClickVideo at " + position + ": " + LSApplication.getInstance().getGson().toJson(item));
        InputModel inputModel = createInputModel(item);
        Intent intent = new Intent(getActivity(), UizaPlayerActivity.class);
        intent.putExtra(vn.loitp.core.common.Constants.KEY_UIZA_PLAYER, inputModel);
        startActivity(intent);
        LUIUtil.transActivityFadeIn(getActivity());
    }

    /*private InputModel createInputModel(String urlImg) {
        InputModel inputModel = new InputModel();
        inputModel.setEntityID("81");
        inputModel.setUrlImg(urlImg);
        inputModel.setTitle("Spider - Man: Homecoming (2017)");
        inputModel.setTime("2015");
        inputModel.setDuration("2h 13min");
        inputModel.setRate(13);
        inputModel.setDescription("Kim Bình Mai (金瓶梅, Jīnpíngméi), tên đầy đủ là Kim Bình Mai từ thoại (Truyện kể có xen thi từ về Kim Bình Mai); là bộ tiểu thuyết dài gồm 100 hồi [1] của Trung Quốc.\n" +
                "\n" +
                "Đây là \"bộ truyện dài đầu tiên mà cốt truyện hoàn toàn là hư cấu sáng tạo của một cá nhân\". Trước đó, các truyện kể đều dựa ít nhiều vào sử sách hoặc truyện kể dân gian, và đều là sự chắp nối công công sức của nhiều người[2]. Tên truyện do tên ba nhân vật nữ là Phan Kim Liên, Lý Bình Nhi và Bàng Xuân Mai mà thành.\n" +
                "\n" +
                "Theo một số nhà nghiên cứu văn học, thì tác giả là một người ở Sơn Đông không rõ họ tên, có bút hiệu là Tiếu Tiếu Sinh (có nghĩa là \"Ông thầy cười\") [3].\n" +
                "\n" +
                "Có thể nói trong các tiểu thuyết viết về \"nhân tình thế thái\" (nói gọn là \"thế tình\", tức \"tình đời\") ở Trung Quốc, thì đây là truyện có tiếng nhất, đã khiến cho nhiều người bàn luận [4].");
        inputModel.setStarring("Tom Holland, Michael Keaton, Robert Downey Jr.");
        inputModel.setDirector("Jon Watts");
        inputModel.setGenres("Action, Adventure, Sci-Fi");
        inputModel.setExtension("mpd");
        //inputModel.setDrmLicenseUrl("");
        inputModel.setAction(inputModel.getPlaylist() == null ? FrmUizaVideo.ACTION_VIEW : FrmUizaVideo.ACTION_VIEW_LIST);
        inputModel.setPreferExtensionDecoders(false);

        //TODO remove this code below
        inputModel.setUri("http://www.youtube.com/api/manifest/dash/id/bf5bb2419360daf1/source/youtube?as=fmp4_audio_clear,fmp4_sd_hd_clear&sparams=ip,ipbits,expire,source,id,as&ip=0.0.0.0&ipbits=0&expire=19000000000&signature=51AF5F39AB0CEC3E5497CD9C900EBFEAECCCB5C7.8506521BFC350652163895D4C26DEE124209AA9E&key=ik0");

        //inputModel.setUri("http://d3euja3nh8q8x3.cloudfront.net/2d5a599d-ca5d-4bb4-a500-3f484b1abe8e/other/playlist.mpd");

        inputModel.setAdTagUri("https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dskippablelinear&correlator=");
        return inputModel;
    }*/
    private InputModel createInputModel(Item item) {
        InputModel inputModel = new InputModel();
        inputModel.setEntityID(item.getId() + "");
        inputModel.setUrlImg(item.getPoster());
        inputModel.setTitle(item.getName());
        /*inputModel.setTime("2015");
        inputModel.setDuration("2h 13min");
        inputModel.setRate(13);
        inputModel.setDescription("Kim Bình Mai (金瓶梅, Jīnpíngméi), tên đầy đủ là Kim Bình Mai từ thoại (Truyện kể có xen thi từ về Kim Bình Mai); là bộ tiểu thuyết dài gồm 100 hồi [1] của Trung Quốc.\n" +
                "\n" +
                "Đây là \"bộ truyện dài đầu tiên mà cốt truyện hoàn toàn là hư cấu sáng tạo của một cá nhân\". Trước đó, các truyện kể đều dựa ít nhiều vào sử sách hoặc truyện kể dân gian, và đều là sự chắp nối công công sức của nhiều người[2]. Tên truyện do tên ba nhân vật nữ là Phan Kim Liên, Lý Bình Nhi và Bàng Xuân Mai mà thành.\n" +
                "\n" +
                "Theo một số nhà nghiên cứu văn học, thì tác giả là một người ở Sơn Đông không rõ họ tên, có bút hiệu là Tiếu Tiếu Sinh (có nghĩa là \"Ông thầy cười\") [3].\n" +
                "\n" +
                "Có thể nói trong các tiểu thuyết viết về \"nhân tình thế thái\" (nói gọn là \"thế tình\", tức \"tình đời\") ở Trung Quốc, thì đây là truyện có tiếng nhất, đã khiến cho nhiều người bàn luận [4].");
        inputModel.setStarring("Tom Holland, Michael Keaton, Robert Downey Jr.");
        inputModel.setDirector("Jon Watts");
        inputModel.setGenres("Action, Adventure, Sci-Fi");*/
        inputModel.setExtension("mpd");
        //inputModel.setDrmLicenseUrl("");
        inputModel.setAction(inputModel.getPlaylist() == null ? FrmUizaVideo.ACTION_VIEW : FrmUizaVideo.ACTION_VIEW_LIST);
        inputModel.setPreferExtensionDecoders(false);

        //TODO remove this code below
        //inputModel.setUri("http://www.youtube.com/api/manifest/dash/id/bf5bb2419360daf1/source/youtube?as=fmp4_audio_clear,fmp4_sd_hd_clear&sparams=ip,ipbits,expire,source,id,as&ip=0.0.0.0&ipbits=0&expire=19000000000&signature=51AF5F39AB0CEC3E5497CD9C900EBFEAECCCB5C7.8506521BFC350652163895D4C26DEE124209AA9E&key=ik0");
        //inputModel.setUri("http://d3euja3nh8q8x3.cloudfront.net/2d5a599d-ca5d-4bb4-a500-3f484b1abe8e/other/playlist.mpd");
        //inputModel.setUri("http://cdn-broadcast.yuptv.vn/ba_dash/0c45905848ca4ec99d2ed7c11bc8f8ad-a1556c60605a4fe4a1a22eafb4e89b44/index.mpd");

        //inputModel.setAdTagUri("https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dskippablelinear&correlator=");
        return inputModel;
    }

    private void getData() {
        /*UizaV2Service service = RestClient.createService(UizaV2Service.class);
        int limit = 100;
        int page = 0;
        subscribe(service.getAll(limit, page), new ApiSubscriber<GetAll>() {
            @Override
            public void onSuccess(GetAll getAll) {
                LLog.d(TAG, "getData onSuccess " + LSApplication.getInstance().getGson().toJson(getAll));
                List<Item> itemList = getAll.getItems();
                if (itemList == null || itemList.isEmpty()) {
                    LDialogUtil.showOne(getActivity(), getString(R.string.noti), getString(R.string.empty_list), getString(R.string.confirm), new LDialogUtil.CallbackShowOne() {
                        @Override
                        public void onClick() {
                            getActivity().onBackPressed();
                        }
                    });
                } else {
                    setupData(itemList);
                }
            }

            @Override
            public void onFail(Throwable e) {
                handleException(e);
            }
        });*/
    }
}