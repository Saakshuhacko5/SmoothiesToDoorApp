package com.example.smoothiestodoor;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.regex.*;

@Entity(tableName = "users")
public class UserEntity {


    @ColumnInfo(name = "username")
    String username;
    @ColumnInfo(name = "email")
    @PrimaryKey
    @NonNull
    String email;
    @ColumnInfo(name = "password")
    String password;


    public String getUsername() {
//        if(uname != null && uname != " ")
        return username;
    }

    public void setUsername(String username) {
            this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
       this.email = email;

    }

    public String getPassword() {
        return password;
    }


    public void setPassword(String pass)
    {
            this.password = pass;
    }
}


