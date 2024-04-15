package net.javaguides.blogwebapp.repository;

import net.javaguides.blogwebapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @className: CommentRepository
 * @author: ChelsyLan
 * @description: TODO
 */
public interface CommentRepository extends JpaRepository<Comment,Long> {



}
