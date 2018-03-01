
package vn.loitp.restapi.uiza.model.v1.getentityinfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OutputDetail {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("entityId")
    @Expose
    private String entityId;
    @SerializedName("outputFolder")
    @Expose
    private String outputFolder;
    @SerializedName("outputPath")
    @Expose
    private String outputPath;
    @SerializedName("transcodeFileId")
    @Expose
    private String transcodeFileId;
    @SerializedName("profileId")
    @Expose
    private String profileId;
    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("format")
    @Expose
    private String format;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("taskId")
    @Expose
    private String taskId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getOutputFolder() {
        return outputFolder;
    }

    public void setOutputFolder(String outputFolder) {
        this.outputFolder = outputFolder;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public String getTranscodeFileId() {
        return transcodeFileId;
    }

    public void setTranscodeFileId(String transcodeFileId) {
        this.transcodeFileId = transcodeFileId;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
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

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

}
