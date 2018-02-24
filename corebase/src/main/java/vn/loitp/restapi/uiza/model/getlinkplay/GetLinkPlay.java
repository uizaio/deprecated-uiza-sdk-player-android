
package vn.loitp.restapi.uiza.model.getlinkplay;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetLinkPlay implements Serializable {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("linkplay_hls")
    @Expose
    private String linkplayHls;
    @SerializedName("linkplay_mpd")
    @Expose
    private String linkplayMpd;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLinkplayHls() {
        return linkplayHls;
    }

    public void setLinkplayHls(String linkplayHls) {
        this.linkplayHls = linkplayHls;
    }

    public String getLinkplayMpd() {
        return linkplayMpd;
    }

    public void setLinkplayMpd(String linkplayMpd) {
        this.linkplayMpd = linkplayMpd;
    }

}
