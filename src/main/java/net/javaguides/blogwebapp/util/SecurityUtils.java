package net.javaguides.blogwebapp.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class SecurityUtils {
    private static final Logger logger = LoggerFactory.getLogger(SecurityUtils.class);
    public static org.springframework.security.core.userdetails.User getCurrentUser(){
        Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        if(principle instanceof org.springframework.security.core.userdetails.User){
            logger.info("Security Utils get User class");
            return (User) principle;
        }else{
            logger.info("something wrong with principle_instanceof_User.Entity");
            return null;
        }

    }
}
