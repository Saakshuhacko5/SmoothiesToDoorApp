package com.example.smoothiestodoor;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "orders")
public class OrderEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "useremail")
    String useremail;
    @ColumnInfo(name = "nameofsmoothie")
    String nameofsmothie;
    @ColumnInfo(name = "noofservings")
    int noofservings;
    @ColumnInfo(name = "totalprice")
    int totalprice;


    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getNameofsmothie() {
        return nameofsmothie;
    }

    public void setNameofsmothie(String nameofsmothie) {
        this.nameofsmothie = nameofsmothie;
    }

    public int getNoofservings() {
        return noofservings;
    }

    public void setNoofservings(int noofservings) {
        this.noofservings = noofservings;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }
}
