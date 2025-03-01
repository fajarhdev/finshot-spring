package com.example.finshot.controller;

import com.example.finshot.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostService postService;

    @GetMapping("")
    public String getPosts(Model model) {
        return "home";
    }

}
