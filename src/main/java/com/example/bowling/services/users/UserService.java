package com.example.bowling.services.users;

import com.example.bowling.models.UserGame;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public void createUser(final String userName){
        UserGame.createUserGame(userName);
    }
}
