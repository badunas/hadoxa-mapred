package com.hadoxa.so.pojo;

/**
 * Created by kuntsali on 4/14/16.
 */
public class Comment {

    private String id;
    private String postId;
    private String score;
    private String text;
    private String userId;
    private String creationDate;

    public Comment(String id,
                   String postId,
                   String score,
                   String text,
                   String userId,
                   String creationDate) {
        this.id = id;
        this.postId = postId;
        this.score = score;
        this.text = text;
        this.userId = userId;
        this.creationDate = creationDate;
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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}
