package com.listening.listening.service;

import com.listening.listening.model.Users;
import com.listening.listening.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public Users insert(Users users){
        return usersRepository.save(users);
    }
}
