package com.example.finshot.controller;

import com.example.finshot.dto.PostDTO;
import com.example.finshot.model.PostsModel;
import com.example.finshot.service.PostService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class PostsController {

    @Autowired
    private PostService postService;

    @GetMapping("")
    public String getPosts(Model model) {
        List<PostsModel> posts = postService.getAllPosts();

        model.addAttribute("posts", posts);
        model.addAttribute("search", new PostDTO());

        return "home";
    }

    @PostMapping("/post/search")
    public String getPostsBySearch(Model model, @ModelAttribute("search") PostDTO postDTO) {

        List<PostsModel> posts = postService.getAllPostsBySearch(postDTO.getSearch());
        model.addAttribute("search", new PostDTO());
        model.addAttribute("posts", posts);
        return "home";
    }

    @GetMapping("/post/{id}")
    public String getPostById(@PathVariable("id") Long id, Model model) {
        PostsModel post = postService.getPostById(id);
        postService.addView(id);

        model.addAttribute("post", post);

        PostDTO postDTO = new PostDTO();
        postDTO.setId(id);

        model.addAttribute("pass", postDTO);

        return "post";
    }

    @GetMapping("/post/create")
    public String createPostById(Model model) {
        model.addAttribute("postDTO", new PostDTO());
        return "createpost";
    }


    @PostMapping("/post/create/new")
    public String handleCreatePost(@ModelAttribute("postDTO") PostDTO postDto) {
        
        PostsModel post = new PostsModel();

        post.setAuthor(postDto.getAuthor());
        post.setContent(postDto.getContent());
        post.setTitle(postDto.getTitle());
        post.setPassword(postDto.getPassword());

        postService.createPost(post);
        return "redirect:/";
    }


    @PostMapping("/post/modify/{id}")
    public String modifyPostById(@PathVariable("id") Long id, @ModelAttribute("pass") PostDTO postDTO, Model model) {
        PostsModel post = postService.getPostById(id);
        model.addAttribute("post", post);

        boolean check = postService.checkPass(id, postDTO.getPassword());
        
        if (check) {
            return "modifypost";
        }else {
            model.addAttribute("error", "Cannot edit this bulletin, Wrong Password");
            return "post";
        }

    }

    @PostMapping("/post/modify/new/{id}")
    public String handleModifyPost(@PathVariable("id") Long id, @ModelAttribute("postDTO") PostDTO postDTO) {

        PostsModel post = new PostsModel();

        post.setContent(postDTO.getContent());
        post.setTitle(postDTO.getTitle());

        postService.modifyPost(id, post);

        return "redirect:/";
    }

    @PostMapping("/post/delete/{id}")
    public String deletePostById(@PathVariable("id") Long id, @ModelAttribute("pass") PostDTO postDTO, Model model) {
        PostsModel post = postService.getPostById(id);

        model.addAttribute("post", post);

        boolean check = postService.checkPass(id, postDTO.getPassword());

        if (check) {
            postService.deletePost(id);
            return "redirect:/";
        }else {
            model.addAttribute("error", "Cannot delete this bulletin, Wrong Password");
            return "post";
        }
    }

    @GetMapping("/back")
    public String back(Model model) {
        return "redirect:/";
    }

}
