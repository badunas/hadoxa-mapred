package com.hadoxa.so.pojo;

/**
 * @author alexandr.kuntcevich@oxagile.com
 */
public class User {
    private String id;
    private String reputation;
    private String creationDate;
    private String displayName;
    private String emailHash;
    private String lastAccessDate;
    private String webSiteUrl;
    private String location;
    private String aboutMe;
    private String age;
    private String views;
    private String upVotes;
    private String downVotes;

    public User(String id,
                String reputation,
                String creationDate,
                String displayName,
                String emailHash,
                String lastAccessDate,
                String webSiteUrl,
                String location,
                String aboutMe,
                String age,
                String views,
                String upVotes,
                String downVotes) {
        this.id = id;
        this.reputation = reputation;
        this.creationDate = creationDate;
        this.displayName = displayName;
        this.emailHash = emailHash;
        this.lastAccessDate = lastAccessDate;
        this.webSiteUrl = webSiteUrl;
        this.location = location;
        this.aboutMe = aboutMe;
        this.age = age;
        this.views = views;
        this.upVotes = upVotes;
        this.downVotes = downVotes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReputation() {
        return reputation;
    }

    public void setReputation(String reputation) {
        this.reputation = reputation;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmailHash() {
        return emailHash;
    }

    public void setEmailHash(String emailHash) {
        this.emailHash = emailHash;
    }

    public String getLastAccessDate() {
        return lastAccessDate;
    }

    public void setLastAccessDate(String lastAccessDate) {
        this.lastAccessDate = lastAccessDate;
    }

    public String getWebSiteUrl() {
        return webSiteUrl;
    }

    public void setWebSiteUrl(String webSiteUrl) {
        this.webSiteUrl = webSiteUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getUpVotes() {
        return upVotes;
    }

    public void setUpVotes(String upVotes) {
        this.upVotes = upVotes;
    }

    public String getDownVotes() {
        return downVotes;
    }

    public void setDownVotes(String downVotes) {
        this.downVotes = downVotes;
    }
}
