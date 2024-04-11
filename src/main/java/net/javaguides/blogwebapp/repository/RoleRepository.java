package net.javaguides.blogwebapp.repository;

import net.javaguides.blogwebapp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @className: RoleRepository
 * @author: ChelsyLan
 * @description: TODO
 */
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
