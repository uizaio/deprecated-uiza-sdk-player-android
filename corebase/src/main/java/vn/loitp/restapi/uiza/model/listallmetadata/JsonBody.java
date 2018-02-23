package vn.loitp.restapi.uiza.model.listallmetadata;

/**
 * Created by LENOVO on 2/23/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JsonBody {

    @SerializedName("limit")
    @Expose
    private int limit;
    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("metadataId")
    @Expose
    private List<String> metadataId = null;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<String> getMetadataId() {
        return metadataId;
    }

    public void setMetadataId(List<String> metadataId) {
        this.metadataId = metadataId;
    }

}