package com.suraj.blogs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suraj.blogs.entities.Comment;


@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
