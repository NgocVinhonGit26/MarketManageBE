package com.dhbkhn.manageusers.DTO;

import java.sql.Timestamp;

public class CommentsDTO {
    private int id;
    private int product_id;
    private int user_id;
    private String content;
    private Timestamp created_at;
    private int likes;
    private int dislikes;
    private String userName;
    private String userAvatar;

    public CommentsDTO() {
    }

    public CommentsDTO(int id, int product_id, int user_id, String content, Timestamp created_at, int likes,
            int dislikes, String userName, String userAvatar) {
        this.id = id;
        this.product_id = product_id;
        this.user_id = user_id;
        this.content = content;
        this.created_at = created_at;
        this.likes = likes;
        this.dislikes = dislikes;
        this.userName = userName;
        this.userAvatar = userAvatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

}
