package com.example.finshot.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "MASTER_POSTS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "post_id", unique = true, nullable = false)
    private Long postId;
    private String title;

    @Column(columnDefinition = "TEXT") // Maps to TEXT in PostgreSQL
    private String content;
    private String author;
    private String password;
    private int views;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
}
