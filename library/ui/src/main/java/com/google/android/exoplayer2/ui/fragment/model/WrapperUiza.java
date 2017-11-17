
package com.google.android.exoplayer2.ui.fragment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class WrapperUiza implements Serializable {

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
