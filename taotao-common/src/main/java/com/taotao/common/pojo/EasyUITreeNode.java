package com.taotao.common.pojo;

/**
 * Created by ASUS on 2017/10/26.
 */
public class EasyUITreeNode {
    private long id;
    private String text;
    private String state;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
