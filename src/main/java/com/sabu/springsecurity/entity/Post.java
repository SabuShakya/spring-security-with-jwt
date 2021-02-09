package com.sabu.springsecurity.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "post")
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "title")
    private String title;

    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @ManyToOne()
    private Users user_id;

}
