package com.example.bigfarma;

public class Category{
    private int id;
    private String Name;

    private int ImageUrl;

    private int ImageBackgroundUrl;

    private int totalDuration;

    public int getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(int totalDuration) {
        this.totalDuration = totalDuration;
    }

    public Category(int id, String name, int imageUrl, int imageBackgroundUrl) {
        this.id = id;
        Name = name;
        ImageUrl = imageUrl;
        ImageBackgroundUrl = imageBackgroundUrl;
    }

    public int getImageBackgroundUrl() {
        return ImageBackgroundUrl;
    }

    public void setImageBackgroundUrl(int imageBackgroundUrl) {
        ImageBackgroundUrl = imageBackgroundUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(int imageUrl) {
        ImageUrl = imageUrl;
    }


    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", ImageUrl='" + ImageUrl + '\'' +
                '}';
    }
}
