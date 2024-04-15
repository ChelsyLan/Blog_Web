package net.javaguides.blogwebapp.repository;

import net.javaguides.blogwebapp.dto.CommentDto;
import net.javaguides.blogwebapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @className: CommentRepository
 * @author: ChelsyLan
 * @description: TODO
 */
public interface CommentRepository extends JpaRepository<Comment,Long> {
    @Query(value = "SELECT c.* FROM comments c inner join posts p\n" + "where c.post_id = p.id and p.created_by =:userId ",
    nativeQuery = true)
    List<Comment> findCommentsByPost(Long userId);



}
