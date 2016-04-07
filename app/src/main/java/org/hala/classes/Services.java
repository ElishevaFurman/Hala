package org.hala.classes;

public class Services {
    int id;
    String title, description, image;

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Services(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}
