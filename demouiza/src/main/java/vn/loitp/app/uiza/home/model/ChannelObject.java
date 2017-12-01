package vn.loitp.app.uiza.home.model;

import com.uiza.player.ui.fragment.helper.InputModel;

import java.util.List;

/**
 * Created by www.muathu@gmail.com on 11/15/2017.
 */

public class ChannelObject {
    private String channelName;
    private List<InputModel> inputModelList;

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public List<InputModel> getInputModelList() {
        return inputModelList;
    }

    public void setInputModelList(List<InputModel> inputModelList) {
        this.inputModelList = inputModelList;
    }
}
