
package vn.loitp.restapi.uiza.model.v1.getentityinfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("shortDescription")
    @Expose
    private String shortDescription;
    @SerializedName("ingestId")
    @Expose
    private String ingestId;
    @SerializedName("process")
    @Expose
    private String process;
    @SerializedName("progress")
    @Expose
    private String progress;
    @SerializedName("transcodeFileId")
    @Expose
    private String transcodeFileId;
    @SerializedName("ingestMetadataId")
    @Expose
    private String ingestMetadataId;
    @SerializedName("inputType")
    @Expose
    private String inputType;
    @SerializedName("social")
    @Expose
    private double social;
    @SerializedName("publishToWeb")
    @Expose
    private double publishToWeb;
    @SerializedName("view")
    @Expose
    private double view;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("sourceFile")
    @Expose
    private String sourceFile;
    @SerializedName("referenceId")
    @Expose
    private String referenceId;
    @SerializedName("status")
    @Expose
    private double status;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("adminUserId")
    @Expose
    private String adminUserId;
    @SerializedName("owner")
    @Expose
    private Owner owner;
    @SerializedName("outputDetail")
    @Expose
    private List<OutputDetail> outputDetail = null;
    @SerializedName("sourceDetail")
    @Expose
    private List<Object> sourceDetail = null;
    @SerializedName("subtitle")
    @Expose
    private List<Object> subtitle = null;
    @SerializedName("metadata")
    @Expose
    private List<Object> metadata = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getIngestId() {
        return ingestId;
    }

    public void setIngestId(String ingestId) {
        this.ingestId = ingestId;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getTranscodeFileId() {
        return transcodeFileId;
    }

    public void setTranscodeFileId(String transcodeFileId) {
        this.transcodeFileId = transcodeFileId;
    }

    public String getIngestMetadataId() {
        return ingestMetadataId;
    }

    public void setIngestMetadataId(String ingestMetadataId) {
        this.ingestMetadataId = ingestMetadataId;
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    public double getSocial() {
        return social;
    }

    public void setSocial(double social) {
        this.social = social;
    }

    public double getPublishToWeb() {
        return publishToWeb;
    }

    public void setPublishToWeb(double publishToWeb) {
        this.publishToWeb = publishToWeb;
    }

    public double getView() {
        return view;
    }

    public void setView(double view) {
        this.view = view;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSourceFile() {
        return sourceFile;
    }

    public void setSourceFile(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public double getStatus() {
        return status;
    }

    public void setStatus(double status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(String adminUserId) {
        this.adminUserId = adminUserId;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<OutputDetail> getOutputDetail() {
        return outputDetail;
    }

    public void setOutputDetail(List<OutputDetail> outputDetail) {
        this.outputDetail = outputDetail;
    }

    public List<Object> getSourceDetail() {
        return sourceDetail;
    }

    public void setSourceDetail(List<Object> sourceDetail) {
        this.sourceDetail = sourceDetail;
    }

    public List<Object> getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(List<Object> subtitle) {
        this.subtitle = subtitle;
    }

    public List<Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(List<Object> metadata) {
        this.metadata = metadata;
    }

}
