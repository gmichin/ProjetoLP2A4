package com.ifsp.lp.work.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.ifsp.lp.work.dto.UserDTO;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    
    private Map<String, UserDTO> userMap;

    public UserRepository() {
        userMap = new HashMap<>();
    }

    public List<UserDTO> getAllUsers() {
        return new ArrayList<>(userMap.values());
    }

    public UserDTO getUserById(String userId) {
        return userMap.get(userId);
    }

    public UserDTO saveUser(UserDTO user) {
        userMap.put(user.getId().toString(), user);
        return user;
    }

    public void deleteUser(String userId) {
        userMap.remove(userId);
    }
}