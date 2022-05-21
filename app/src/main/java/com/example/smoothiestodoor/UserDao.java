package com.example.smoothiestodoor;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Insert
    void registerUser(UserEntity userentity);

    @Query("Select * from users where username=(:username) and password=(:password)")
    UserEntity login(String username , String password);
}
