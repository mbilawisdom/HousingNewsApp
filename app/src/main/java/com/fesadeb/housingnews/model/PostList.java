package com.fesadeb.housingnews.model;

public class PostList {

    public static final int IMAGE_TYPE = 1;
    public String title, subtitle, Image;
    public int type;

    public PostList(int mtype, String mtitle, String msubtitle, String image) {
        this.title = mtitle;
        this.subtitle = msubtitle;
        this.type = mtype;
        this.Image = image;

    }
}
