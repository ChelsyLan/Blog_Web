package net.javaguides.blogwebapp.service;

import net.javaguides.blogwebapp.dto.RegistrationDto;
import net.javaguides.blogwebapp.entity.User;

/**
 * @className: UserService
 * @author: ChelsyLan
 * @description: TODO
 */
public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    User findByEmail(String email);
}
