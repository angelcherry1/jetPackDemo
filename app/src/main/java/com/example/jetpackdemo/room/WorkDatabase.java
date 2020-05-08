package com.example.jetpackdemo.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * @author:lixiaobiao
 * @date:On 2020/5/8
 * @Desriptiong: 23231
 */
@Database(entities = {WorkEntity.class},version = 1,exportSchema = false)
public abstract class WorkDatabase extends RoomDatabase {
    public abstract WorkDao getWorkDao();
}
