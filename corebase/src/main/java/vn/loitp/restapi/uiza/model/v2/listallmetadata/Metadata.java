
package vn.loitp.restapi.uiza.model.v2.listallmetadata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Metadata {

    @SerializedName("total")
    @Expose
    private int total;
    @SerializedName("result")
    @Expose
    private int result;
    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("limit")
    @Expose
    private int limit;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

}
