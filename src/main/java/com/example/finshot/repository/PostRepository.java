package com.example.finshot.repository;

import com.example.finshot.model.PostsModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostsModel, Long> {
    @Query("SELECT MAX(p.postId) FROM PostsModel p")
    Long findMaxPostId();

    @Query("SELECT p FROM PostsModel p WHERE deletedAt IS NULL")
    List<PostsModel> findAllFiltered();

    @Query("SELECT p FROM PostsModel p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<PostsModel> findBySearch(@Param("title") String title);
}
