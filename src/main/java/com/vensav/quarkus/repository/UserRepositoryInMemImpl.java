package com.vensav.quarkus.repository;

import com.vensav.quarkus.model.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class UserRepositoryInMemImpl implements UserRepository {

    private static final Map<Integer, User> userData = new HashMap<>();

    @Override
    public List<User> getAllUsers() {
        return userData.values().stream().toList();
    }

    @Override
    public User getUserById(int userId) {
        if (userData.containsKey(userId))
            return userData.get(userId);
        throw new RuntimeException("User not found");
    }

    @Override
    public User addNewUser(User user) {
        int newId = userData.keySet().stream().max(Comparator.comparing(Integer::valueOf)).orElse(0) + 1;
        user.setUserId(newId);
        userData.put(newId, user);
        return user;
    }

    @Override
    public void updateUserById(int userId, User user) {
        if (userData.containsKey(userId))
            userData.put(userId, user);
        else
            throw new RuntimeException("User not found");
    }

    @Override
    public void deleteUserById(int userId) {
        if (userData.containsKey(userId))
            userData.remove(userId);
        else
            throw new RuntimeException("User not found");
    }
}