package com.example.finshot.service;

import com.example.finshot.model.PostsModel;
import com.example.finshot.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    public List<PostsModel> getAllPosts() {

        List<PostsModel> getPosts = postRepository.findAllFiltered();
        return getPosts;
    }

    @Override
    public List<PostsModel> getAllPostsBySearch(String title) {
        List<PostsModel> getPost = postRepository.findBySearch(title);
        return getPost;
    }

    @Override
    public PostsModel getPostById(Long id) {
        PostsModel getPosts = postRepository.getReferenceById(id);
        return getPosts;
    }

    @Override
    public PostsModel createPost(PostsModel post) {
        Long maxPostId = postRepository.findMaxPostId();
        post.setPostId((maxPostId == null ? 1 : maxPostId + 1));
        post.setCreatedAt(new Date());

        PostsModel createPost = postRepository.saveAndFlush(post);
        return createPost;
    }

    @Override
    public PostsModel modifyPost(Long id, PostsModel post) {
        PostsModel getPosts = postRepository.getReferenceById(id);

        getPosts.setTitle(post.getTitle());
        getPosts.setContent(post.getContent());
        getPosts.setUpdatedAt(new Date());

        PostsModel modify = postRepository.saveAndFlush(getPosts);
        return modify;
    }

    @Override
    public void deletePost(Long id) {
        PostsModel post = postRepository.getReferenceById(id);
        post.setDeletedAt(new Date());

        postRepository.saveAndFlush(post);
    }

    @Override
    public void addView(Long id) {
        PostsModel getPost = postRepository.getReferenceById(id);
        getPost.setViews(getPost.getViews() + 1);
        postRepository.saveAndFlush(getPost);
    }

    @Override
    public boolean checkPass(Long id, String pass) {
        boolean result = false;
        PostsModel getPost = postRepository.getReferenceById(id);

        if (getPost.getPassword().equals(pass)) {
            result = true;
        }

        return result;
    }
}
