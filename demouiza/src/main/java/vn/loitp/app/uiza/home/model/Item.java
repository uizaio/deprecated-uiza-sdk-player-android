
package vn.loitp.app.uiza.home.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("referenceId")
    @Expose
    private String referenceId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("shortDescription")
    @Expose
    private String shortDescription;
    @SerializedName("extendData")
    @Expose
    private ExtendData extendData;
    @SerializedName("poster")
    @Expose
    private String poster;
    @SerializedName("view")
    @Expose
    private double view;
    @SerializedName("status")
    @Expose
    private double status;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("metadata")
    @Expose
    private List<Metadatum> metadata = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public ExtendData getExtendData() {
        return extendData;
    }

    public void setExtendData(ExtendData extendData) {
        this.extendData = extendData;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public double getView() {
        return view;
    }

    public void setView(double view) {
        this.view = view;
    }

    public double getStatus() {
        return status;
    }

    public void setStatus(double status) {
        this.status = status;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<Metadatum> getMetadata() {
        return metadata;
    }

    public void setMetadata(List<Metadatum> metadata) {
        this.metadata = metadata;
    }

}
