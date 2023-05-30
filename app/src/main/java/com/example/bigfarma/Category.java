package com.example.bigfarma;

public class Category{
    private int id;
    private String Name;
    private String ImageUrl;

    public Category(int id, String name, String imageUrl) {
        this.id = id;
        Name = name;
        ImageUrl = imageUrl;
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

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
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
