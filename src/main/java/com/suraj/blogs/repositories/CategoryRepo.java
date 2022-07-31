package com.suraj.blogs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suraj.blogs.entities.Category;


@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
