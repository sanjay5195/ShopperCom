package com.sanju.developer.shoppercom;

/**
 * Created by Sanju on 15-Jun-17.
 */

public class image1Upload {
    public String name;
    public String url;
    public String size;

    public String getName() {
        return name;
    }


    public String getUrl() {
        return url;
    }
    public String getSize() {
        return size;
    }

    public image1Upload(String name, String url,String size) {
        this.name = name;
        this.url = url;
        this.size=size;
    }
    public image1Upload()
    {

    }
}
