package com.yi.pttcactcher;

public class Member_idNameUrl extends Member {
    private String url;

    public Member_idNameUrl(int id, String name, String url) {
        super();
        this.id = id;
        this.url = url;
        this.name = name;
    }

    public String getUrl(){
        return url;
    };

}