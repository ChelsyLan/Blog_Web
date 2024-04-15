package net.javaguides.blogwebapp.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class SecurityUtils {
    public static org.springframework.security.core.userdetails.User getCurrentUser(){
        Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principle instanceof org.springframework.security.core.userdetails.User){
            return (User) principle;
        }else{
            return null;
        }
    }

    public static String getRoles(){
        User user = getCurrentUser();
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        for(GrantedAuthority authority:authorities){
            return authority.getAuthority();
        }
        return null;
    }
}
