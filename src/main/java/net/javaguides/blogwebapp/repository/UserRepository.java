package net.javaguides.blogwebapp.repository;

import net.javaguides.blogwebapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @className: UserRepository
 * @author: ChelsyLan
 * @description: TODO
 */
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
