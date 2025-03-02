package com.example.finshot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO implements Serializable {
    private String title;
    private String content;
    private String password;
    private String author;
    private Long id;
    private String search;
}
