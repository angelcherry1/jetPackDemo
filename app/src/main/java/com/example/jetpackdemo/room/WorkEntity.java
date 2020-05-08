package com.example.jetpackdemo.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @author:lixiaobiao
 * @date:On 2020/5/8
 * @Desriptiong: 23231
 */
@androidx.room.Entity(tableName = "works")
public class WorkEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "acount")
    private String acount;
    @ColumnInfo(name = "name")
    private String name;



    public WorkEntity(String acount, String name) {
        this.acount = acount;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAcount() {
        return acount;
    }

    public void setAcount(String acount) {
        this.acount = acount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "WorkEntity{" +
                "id=" + id +
                ", acount='" + acount + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
