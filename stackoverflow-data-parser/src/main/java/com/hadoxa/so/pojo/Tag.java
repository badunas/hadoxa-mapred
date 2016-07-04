package com.hadoxa.so.pojo;

/**
 * Created by kuntcevichal on 5/2/2016.
 */
public class Tag {

    private String id;
    private String tagName;
    private String count;
    private String excerptPostId;
    private String wikiPostId;

    public Tag(String id, String tagName, String count, String excerptPostId, String wikiPostId) {
        this.id = id;
        this.tagName = tagName;
        this.count = count;
        this.excerptPostId = excerptPostId;
        this.wikiPostId = wikiPostId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getExcerptPostId() {
        return excerptPostId;
    }

    public void setExcerptPostId(String excerptPostId) {
        this.excerptPostId = excerptPostId;
    }

    public String getWikiPostId() {
        return wikiPostId;
    }

    public void setWikiPostId(String wikiPostId) {
        this.wikiPostId = wikiPostId;
    }
}
