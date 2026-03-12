package com.example.blog;

import javax.persistence.*;

@Entity
@Table(name="comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String text;
    private int blog_id;
    private int user_id;

    public Comment(){}

    public Comment(String text,int blog_id,int user_id){
        this.text=text;
        this.blog_id=blog_id;
        this.user_id=user_id;
    }

    public int getId(){
        return id;
    }

    public String getText(){
        return text;
    }

    public int getBlog_id(){
        return blog_id;
    }

    public int getUser_id(){
        return user_id;
    }

    public void setText(String text){
        this.text=text;
    }

    public void setBlog_id(int blog_id){
        this.blog_id=blog_id;
    }

    public void setUser_id(int user_id){
        this.user_id=user_id;
    }
}