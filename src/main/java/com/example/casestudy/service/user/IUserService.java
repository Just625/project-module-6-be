package com.example.casestudy.service.user;

import com.example.casestudy.model.User;
import com.example.casestudy.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    User findByUsername(String username);
}
