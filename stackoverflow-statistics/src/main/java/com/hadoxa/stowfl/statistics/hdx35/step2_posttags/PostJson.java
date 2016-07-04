package com.hadoxa.stowfl.statistics.hdx35.step2_posttags;

/**
 * Created by badun on 5/10/16.
 */
public class PostJson {
    private String id;
    private String tags;
    private String creationDate;
    private String postTypeId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getPostTypeId() {
        return postTypeId;
    }

    public void setPostTypeId(String postTypeId) {
        this.postTypeId = postTypeId;
    }
}
