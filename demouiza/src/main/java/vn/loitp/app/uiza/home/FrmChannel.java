package vn.loitp.app.uiza.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.loitp.app.activity.customviews.placeholderview._lib.placeholderview.InfinitePlaceHolderView;
import vn.loitp.app.app.LSApplication;
import vn.loitp.app.base.BaseFragment;
import vn.loitp.app.uiza.data.HomeData;
import vn.loitp.app.uiza.home.model.ChannelObject;
import vn.loitp.app.uiza.home.model.PosterObject;
import vn.loitp.app.uiza.home.model.VideoObject;
import vn.loitp.app.uiza.home.view.ChannelItem;
import vn.loitp.app.uiza.home.view.ChannelList;
import vn.loitp.app.uiza.home.view.PosterView;
import vn.loitp.app.utilities.LLog;
import vn.loitp.app.utilities.LUIUtil;
import vn.loitp.livestar.R;

/**
 * Created by www.muathu@gmail.com on 7/26/2017.
 */

public class FrmChannel extends BaseFragment {
    private final String TAG = getClass().getSimpleName();
    private TextView tv;
    private InfinitePlaceHolderView infinitePlaceHolderView;

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

        LUIUtil.setPullLikeIOSVertical(infinitePlaceHolderView);
        setupData();
        return view;
    }

    private void setupData() {
        //poster
        List<PosterObject> posterObjectList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            PosterObject posterObject = new PosterObject();
            posterObject.setUrl("https://kenh14cdn.com/2017/photo-1-1508474775876.jpg");
            posterObjectList.add(posterObject);
        }
        infinitePlaceHolderView.addView(new PosterView(getActivity(), posterObjectList));


        //top movie
        ChannelObject channelObjectTopMovies = new ChannelObject();
        List<VideoObject> videoObjectListTop = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            VideoObject videoObject = createVideoObject("https://kenh14cdn.com/2016/photo-1-1472659093342.jpg");
            videoObjectListTop.add(videoObject);
        }
        channelObjectTopMovies.setChannelName("Top Movies");
        channelObjectTopMovies.setVideoObjectList(videoObjectListTop);
        infinitePlaceHolderView.addView(new ChannelList(getActivity(), channelObjectTopMovies, new ChannelItem.Callback() {
            @Override
            public void onClick(VideoObject videoObject, int position) {
                onClickVideo(videoObject, position);
            }
        }));


        //newest movie
        ChannelObject channelObjectNewestMovies = new ChannelObject();
        List<VideoObject> videoObjectListNewest = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            VideoObject videoObject = createVideoObject("https://kenh14cdn.com/2016/photo-4-1472659094854.jpg");
            videoObjectListNewest.add(videoObject);
        }
        channelObjectNewestMovies.setChannelName("Newest Movies");
        channelObjectNewestMovies.setVideoObjectList(videoObjectListNewest);
        infinitePlaceHolderView.addView(new ChannelList(getActivity(), channelObjectNewestMovies, new ChannelItem.Callback() {
            @Override
            public void onClick(VideoObject videoObject, int position) {
                onClickVideo(videoObject, position);
            }
        }));


        //dummy channel
        for (int j = 0; j < 10; j++) {
            ChannelObject channelObjectDummyMovies = new ChannelObject();
            List<VideoObject> videoObjectListDummy = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                VideoObject videoObject = createVideoObject("https://kenh14cdn.com/2016/160831-star-momo-1472637904135.jpg");
                videoObjectListDummy.add(videoObject);
            }
            channelObjectDummyMovies.setChannelName("Dummy Movies " + j);
            channelObjectDummyMovies.setVideoObjectList(videoObjectListDummy);
            infinitePlaceHolderView.addView(new ChannelList(getActivity(), channelObjectDummyMovies, new ChannelItem.Callback() {
                @Override
                public void onClick(VideoObject videoObject, int position) {
                    onClickVideo(videoObject, position);
                }
            }));
        }
    }

    private void onClickVideo(VideoObject videoObject, int position) {
        LLog.d(TAG, "onClickVideo at " + position + ": " + LSApplication.getInstance().getGson().toJson(videoObject));
    }

    private VideoObject createVideoObject(String url) {
        VideoObject videoObject = new VideoObject();
        videoObject.setUrl(url);
        videoObject.setTitle("Spider - Man: Homecoming (2017)");
        videoObject.setTime("2015");
        videoObject.setDuration("2h 13min");
        videoObject.setRate(13);
        videoObject.setDuration("Kim Bình Mai (金瓶梅, Jīnpíngméi), tên đầy đủ là Kim Bình Mai từ thoại (Truyện kể có xen thi từ về Kim Bình Mai); là bộ tiểu thuyết dài gồm 100 hồi [1] của Trung Quốc.\n" +
                "\n" +
                "Đây là \"bộ truyện dài đầu tiên mà cốt truyện hoàn toàn là hư cấu sáng tạo của một cá nhân\". Trước đó, các truyện kể đều dựa ít nhiều vào sử sách hoặc truyện kể dân gian, và đều là sự chắp nối công công sức của nhiều người[2]. Tên truyện do tên ba nhân vật nữ là Phan Kim Liên, Lý Bình Nhi và Bàng Xuân Mai mà thành.\n" +
                "\n" +
                "Theo một số nhà nghiên cứu văn học, thì tác giả là một người ở Sơn Đông không rõ họ tên, có bút hiệu là Tiếu Tiếu Sinh (có nghĩa là \"Ông thầy cười\") [3].\n" +
                "\n" +
                "Có thể nói trong các tiểu thuyết viết về \"nhân tình thế thái\" (nói gọn là \"thế tình\", tức \"tình đời\") ở Trung Quốc, thì đây là truyện có tiếng nhất, đã khiến cho nhiều người bàn luận [4].");
        videoObject.setStarring("Tom Holland, Michael Keaton, Robert Downey Jr.");
        videoObject.setDirector("Jon Watts");
        videoObject.setGenres("Action, Adventure, Sci-Fi");
        return videoObject;
    }
}