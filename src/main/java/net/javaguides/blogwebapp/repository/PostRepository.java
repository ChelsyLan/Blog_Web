package net.javaguides.blogwebapp.repository;

import net.javaguides.blogwebapp.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @className: PostRepository
 * @author: ChelsyLan
 * @description: execute SQL instructions on Database
 */
public interface PostRepository extends JpaRepository<Post,Long> { // second parameter is data type pf primary key
    Optional<Post> findByUrl(String url);//no need to write SQL query

    @Query("SELECT p FROM Post p WHERE " +
            "p.title LIKE CONCAT('%', :query, '%') OR " +
            "p.shortDescription LIKE CONCAT('%', :query, '%')")
    List<Post> searchPosts(String query);

    @Query(value = "SELECT * FROM posts p WHERE p.created_by =:userId",nativeQuery = true)
    List<Post> findPostsByUser(Long userId);




}
