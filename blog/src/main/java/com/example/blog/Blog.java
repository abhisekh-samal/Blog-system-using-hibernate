package com.example.blog;

import javax.persistence.*;

@Entity
@Table(name="blogs")
public class Blog {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;

private String title;
private String content;
private int user_id;

public Blog(){}

public Blog(String title,String content,int user_id){
this.title=title;
this.content=content;
this.user_id=user_id;
}

}