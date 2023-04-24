package com.example.computershop.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="comments")
public class Comment{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private Long componentid;
    private Long userid;
    private String text;

    public Comment(String username, Long componentid, Long userid, String text) {
        this.username = username;
        this.componentid = componentid;
        this.userid = userid;
        this.text = text;
    }

    public Comment() {

    }
}
