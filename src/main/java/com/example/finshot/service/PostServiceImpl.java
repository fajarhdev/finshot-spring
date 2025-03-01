package com.example.finshot.service;

import com.example.finshot.model.PostsModel;
import com.example.finshot.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    public List<PostsModel> getAllPosts() {

        List<PostsModel> getPosts = postRepository.findAll();
        return getPosts;
    }

    @Override
    public PostsModel getPostById(Long id) {
        PostsModel getPosts = postRepository.getReferenceById(id);
        return getPosts;
    }

    @Override
    public PostsModel createPost(PostsModel post) {
        PostsModel createPost = postRepository.saveAndFlush(post);
        return createPost;
    }

    @Override
    public PostsModel modifyPost(Long id, PostsModel post) {
        PostsModel getPosts = postRepository.getReferenceById(id);

        getPosts.setTitle(post.getTitle());
        getPosts.setContent(post.getContent());

        PostsModel modify = postRepository.saveAndFlush(getPosts);
        return modify;
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public void addView(Long id) {
        PostsModel getPost = postRepository.getReferenceById(id);
        getPost.setViews(getPost.getViews() + 1);
        postRepository.saveAndFlush(getPost);
    }
}
