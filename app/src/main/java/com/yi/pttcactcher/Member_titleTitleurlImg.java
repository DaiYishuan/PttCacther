package com.yi.pttcactcher;

import android.graphics.Bitmap;

public class Member_titleTitleurlImg  {
    private final Bitmap img;
    private final String titleUrl;
    private final String title;

    public Member_titleTitleurlImg(String title, String titleUrl, Bitmap img) {
        this.title = title;
        this.titleUrl = titleUrl;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public String getTitleUrl() {
        return titleUrl;
    }

    public Bitmap getImg() {
        return img;
    }
}
