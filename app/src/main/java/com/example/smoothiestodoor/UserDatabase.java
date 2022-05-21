package com.example.smoothiestodoor;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserEntity.class,OrderEntity.class}, version = 6)
public abstract class UserDatabase extends RoomDatabase {

    private static final String dbname = "user";
    private static UserDatabase userDatabase;

    static  UserDatabase getUserDatabase(Context context)
    {
        if(userDatabase == null)
        {
            synchronized ((UserDatabase.class)){
                if(userDatabase==null)
                {
                    userDatabase = Room.databaseBuilder(context,UserDatabase.class,dbname)
                            .fallbackToDestructiveMigration()
                            .build();

                }
            }

        }
        return userDatabase;
    }
    public abstract UserDao userDao();
    public abstract OrdersDao OrdersDao();
}
