package com.yi.pttcactcher;

public class Member_titleTitleurlImgurl {
    private final String title;
    private final String titleUrl;
    private final String imgUrl;

    public Member_titleTitleurlImgurl(String title, String titleUrl, String imgUrl){
        super();
        this.title = title;
        this.titleUrl = titleUrl;
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getTitleUrl() {
        return titleUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
