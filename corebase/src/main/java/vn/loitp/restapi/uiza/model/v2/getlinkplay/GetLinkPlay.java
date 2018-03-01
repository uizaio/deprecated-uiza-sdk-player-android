
package vn.loitp.restapi.uiza.model.v2.getlinkplay;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetLinkPlay {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("hls")
    @Expose
    private List<Hl> hls = null;
    @SerializedName("hls_ts")
    @Expose
    private List<HlsT> hlsTs = null;
    @SerializedName("hevc")
    @Expose
    private List<Object> hevc = null;
    @SerializedName("mpd")
    @Expose
    private List<Mpd> mpd = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Hl> getHls() {
        return hls;
    }

    public void setHls(List<Hl> hls) {
        this.hls = hls;
    }

    public List<HlsT> getHlsTs() {
        return hlsTs;
    }

    public void setHlsTs(List<HlsT> hlsTs) {
        this.hlsTs = hlsTs;
    }

    public List<Object> getHevc() {
        return hevc;
    }

    public void setHevc(List<Object> hevc) {
        this.hevc = hevc;
    }

    public List<Mpd> getMpd() {
        return mpd;
    }

    public void setMpd(List<Mpd> mpd) {
        this.mpd = mpd;
    }

}
