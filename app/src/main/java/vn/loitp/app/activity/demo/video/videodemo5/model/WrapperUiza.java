
package vn.loitp.app.activity.demo.video.videodemo5.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WrapperUiza {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("samples")
    @Expose
    private List<Sample> samples = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Sample> getSamples() {
        return samples;
    }

    public void setSamples(List<Sample> samples) {
        this.samples = samples;
    }

}
