
package vn.loitp.restapi.uiza.model.v2.listallentity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExtendData {

    @SerializedName("price")
    @Expose
    private Object price;
    @SerializedName("genre")
    @Expose
    private Object genre;
    @SerializedName("country")
    @Expose
    private Object country;
    @SerializedName("published-date")
    @Expose
    private Object publishedDate;
    @SerializedName("upload")
    @Expose
    private Object upload;

    public Object getPrice() {
        return price;
    }

    public void setPrice(Object price) {
        this.price = price;
    }

    public Object getGenre() {
        return genre;
    }

    public void setGenre(Object genre) {
        this.genre = genre;
    }

    public Object getCountry() {
        return country;
    }

    public void setCountry(Object country) {
        this.country = country;
    }

    public Object getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Object publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Object getUpload() {
        return upload;
    }

    public void setUpload(Object upload) {
        this.upload = upload;
    }

}
