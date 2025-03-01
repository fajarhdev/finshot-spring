package com.example.finshot.repository;

import com.example.finshot.model.PostsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostsModel, Long> {
}
