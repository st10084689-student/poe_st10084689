package com.example.bigfarma;

import java.util.ArrayList;

public class Task {
    private int id;
    private String title;

    private String ImageUrl;
    private String Desc;

    private String StartDate;

    private String EndDate;

    private String Time;

    public Task(int id, String title, String imageUrl, String desc, String startDate, String endDate, String time) {
        this.id = id;
        this.title = title;
        ImageUrl = imageUrl;
        Desc = desc;
        StartDate = startDate;
        EndDate = endDate;
        Time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
