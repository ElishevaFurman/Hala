package com.example.faigy.hala.classes;

public class TeamMember {
    // class members
    int id;
    public String name, title, bio, description, image, memberType;

    public TeamMember(int id, String name, String title, String bio, String description, String image, String memberType) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.bio = bio;
        this.description = description;
        this.image = image;
        this.memberType = memberType;
    }

    public TeamMember() {
        // Required empty public constructor
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }
}
