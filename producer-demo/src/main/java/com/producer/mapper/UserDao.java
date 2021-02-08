package com.producer.mapper;

import com.producer.entities.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    public List<User> getUsers();

    public int decrementWarehouse(int newNum);

    public int getGoodNum(int id);
}
