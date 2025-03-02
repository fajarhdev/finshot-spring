package com.example.finshot.service;

import com.example.finshot.model.PostsModel;

import java.util.List;

public interface PostService {
    public List<PostsModel> getAllPosts();
    public List<PostsModel> getAllPostsBySearch(String title);
    public PostsModel getPostById(Long id);
    public PostsModel createPost(PostsModel post);
    public PostsModel modifyPost(Long id, PostsModel post);
    public void deletePost(Long id);
    public void addView(Long id);
    public boolean checkPass(Long id, String pass);
}
