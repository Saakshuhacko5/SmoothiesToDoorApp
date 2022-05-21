package com.example.smoothiestodoor;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface OrdersDao
{

    @Query("Select * from orders where useremail = (:useremail)")
    List<OrderEntity> getAll(String useremail);

    @Insert
    void insert(OrderEntity orderEntity);

}
