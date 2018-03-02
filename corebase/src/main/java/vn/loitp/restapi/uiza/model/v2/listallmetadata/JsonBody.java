package vn.loitp.restapi.uiza.model.v2.listallmetadata;

/**
 * Created by LENOVO on 2/23/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JsonBody {
    @SerializedName("metadataId")
    @Expose
    private List<String> metadataId = null;

    public List<String> getMetadataId() {
        return metadataId;
    }

    public void setMetadataId(List<String> metadataId) {
        this.metadataId = metadataId;
    }

}