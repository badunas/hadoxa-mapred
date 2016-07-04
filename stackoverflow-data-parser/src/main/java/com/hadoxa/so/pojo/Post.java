package com.hadoxa.so.pojo;

public class Post {

    private String id;
    private String postTypeId;
    private String parentId;
    private String acceptedAnswerId;
    private String creationDate;
    private String score;
    private String viewCount;
    private String body;
    private String ownerUserId;
    private String lastEditorUserId;
    private String lastEditorDisplayName;
    private String lastEditDate;
    private String lastActivityDate;
    private String communityOwnedDate;
    private String closedDate;
    private String title;
    private String tags;
    private String answerCount;
    private String commentCount;
    private String favouriteCount;

    public Post(String id,
                String postTypeId,
                String parentId,
                String acceptedAnswerId,
                String creationDate,
                String score,
                String viewCount,
                String body,
                String ownerUserId,
                String lastEditorUserId,
                String lastEditorDisplayName,
                String lastEditDate,
                String lastActivityDate,
                String communityOwnedDate,
                String closedDate,
                String title,
                String tags,
                String answerCount,
                String commentCount,
                String favouriteCount) {
        this.id = id;
        this.postTypeId = postTypeId;
        this.parentId = parentId;
        this.acceptedAnswerId = acceptedAnswerId;
        this.creationDate = creationDate;
        this.score = score;
        this.viewCount = viewCount;
        this.body = body;
        this.ownerUserId = ownerUserId;
        this.lastEditorUserId = lastEditorUserId;
        this.lastEditorDisplayName = lastEditorDisplayName;
        this.lastEditDate = lastEditDate;
        this.lastActivityDate = lastActivityDate;
        this.communityOwnedDate = communityOwnedDate;
        this.closedDate = closedDate;
        this.title = title;
        this.tags = tags;
        this.answerCount = answerCount;
        this.commentCount = commentCount;
        this.favouriteCount = favouriteCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostTypeId() {
        return postTypeId;
    }

    public void setPostTypeId(String postTypeId) {
        this.postTypeId = postTypeId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getAcceptedAnswerId() {
        return acceptedAnswerId;
    }

    public void setAcceptedAnswerId(String acceptedAnswerId) {
        this.acceptedAnswerId = acceptedAnswerId;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getViewCount() {
        return viewCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(String ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public String getLastEditorUserId() {
        return lastEditorUserId;
    }

    public void setLastEditorUserId(String lastEditorUserId) {
        this.lastEditorUserId = lastEditorUserId;
    }

    public String getLastEditorDisplayName() {
        return lastEditorDisplayName;
    }

    public void setLastEditorDisplayName(String lastEditorDisplayName) {
        this.lastEditorDisplayName = lastEditorDisplayName;
    }

    public String getLastEditDate() {
        return lastEditDate;
    }

    public void setLastEditDate(String lastEditDate) {
        this.lastEditDate = lastEditDate;
    }

    public String getLastActivityDate() {
        return lastActivityDate;
    }

    public void setLastActivityDate(String lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    public String getCommunityOwnedDate() {
        return communityOwnedDate;
    }

    public void setCommunityOwnedDate(String communityOwnedDate) {
        this.communityOwnedDate = communityOwnedDate;
    }

    public String getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(String closedDate) {
        this.closedDate = closedDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(String answerCount) {
        this.answerCount = answerCount;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getFavouriteCount() {
        return favouriteCount;
    }

    public void setFavouriteCount(String favouriteCount) {
        this.favouriteCount = favouriteCount;
    }
}
