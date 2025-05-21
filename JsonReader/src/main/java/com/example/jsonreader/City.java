package com.example.jsonreader;

public class City {
    private String title;
    private int pvzCount;

    public City(String title, int PVZCount) {
        this.title = title;
        this.pvzCount = PVZCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPvzCount() {
        return pvzCount;
    }

    public void setPvzCount(int PVZCount) {
        this.pvzCount = PVZCount;
    }
}
