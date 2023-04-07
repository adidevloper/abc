package com.example.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.blog.entity.Category;
import com.example.blog.entity.Post;
import com.example.blog.entity.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

   List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);

//	@Query("select p from Post p where p.title like:key")
//	List<Post> findByTitleContaining(@Param ("key") String title);
	
	List<Post> findByTitleContaining(String keyword);
}
