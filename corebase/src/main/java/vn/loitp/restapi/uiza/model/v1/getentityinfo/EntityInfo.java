
package vn.loitp.restapi.uiza.model.v1.getentityinfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EntityInfo {

    @SerializedName("item")
    @Expose
    private Item item;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

}
