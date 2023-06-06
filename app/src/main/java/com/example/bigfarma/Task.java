package com.example.bigfarma;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class Task {
    private int id;
    private String title;

    private String ImageUrl;
    private String Desc;

    private String StartDate;

    private Date EndDate;

    private String Time;

    private int categoryId;



    private int Duration;

    public Task(int id, String title, String imageUrl, String desc, String startDate, Date endDate, String time, int categoryId, int _Duration) {
        this.id = id;
        this.title = title;
        ImageUrl = imageUrl;
        Desc = desc;
        StartDate = startDate;
        EndDate = endDate;
        Time = time;
        this.categoryId = categoryId;

        this.Duration = _Duration;
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

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getDuration() {
        return Duration;
    }

    public void setDuration(int duration) {
        Duration = duration;
    }
}
