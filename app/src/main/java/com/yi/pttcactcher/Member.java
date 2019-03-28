package com.yi.pttcactcher;

public class Member {
    protected int id;
    private int image;
    protected String name;

    public Member() {
        super();
    }

    public Member(int id, int image, String name) {
        super();
        this.id = id;
        this.image = image;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
