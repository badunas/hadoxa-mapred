package com.hadoxa.so.pojo;

public class PostLink {

    private String id;
    private String creationDate;
    private String postId;
    private String relatedPostId;
    private String linkTypeId;

    public PostLink(String id,
                    String creationDate,
                    String postId,
                    String relatedPostId,
                    String linkTypeId) {
        this.id = id;
        this.creationDate = creationDate;
        this.postId = postId;
        this.relatedPostId = relatedPostId;
        this.linkTypeId = linkTypeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getRelatedPostId() {
        return relatedPostId;
    }

    public void setRelatedPostId(String relatedPostId) {
        this.relatedPostId = relatedPostId;
    }

    public String getLinkTypeId() {
        return linkTypeId;
    }

    public void setLinkTypeId(String linkTypeId) {
        this.linkTypeId = linkTypeId;
    }
}
