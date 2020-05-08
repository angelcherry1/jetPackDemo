package com.example.jetpackdemo.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * @author:lixiaobiao
 * @date:On 2020/5/8
 * @Desriptiong: 23231
 */
@Dao
public interface WorkDao {

    @Insert
    void insert(WorkEntity...workEntities);

    @Update
    void update(WorkEntity...workEntities);

    @Delete
    void delete(WorkEntity...workEntities);

    @Query("select *from works")
    List<WorkEntity> geworks();

    @Query("delete from works")
    void deleteAll();
}
