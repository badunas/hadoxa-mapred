package com.hadoxa.so.pojo;

public class Vote {

    private String id;
    private String postId;
    private String voteTypeId;
    private String creationDate;
    private String userId;
    private String bountyAmount;

    public Vote(String id,
                String postId,
                String voteTypeId,
                String creationDate,
                String userId,
                String bountyAmount) {
        this.id = id;
        this.postId = postId;
        this.voteTypeId = voteTypeId;
        this.creationDate = creationDate;
        this.userId = userId;
        this.bountyAmount = bountyAmount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getVoteTypeId() {
        return voteTypeId;
    }

    public void setVoteTypeId(String voteTypeId) {
        this.voteTypeId = voteTypeId;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBountyAmount() {
        return bountyAmount;
    }

    public void setBountyAmount(String bountyAmount) {
        this.bountyAmount = bountyAmount;
    }
}
