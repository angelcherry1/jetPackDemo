package com.example.jetpackdemo.room;

import android.content.Context;

import androidx.room.Room;

/**
 * @author:lixiaobiao
 * @date:On 2020/5/8
 * @Desriptiong: 23231
 */
public class WorkRepository {
    public static volatile WorkRepository instance;
    private WorkDao workDao;
    private WorkDatabase workDatabase;

    public WorkRepository(Context context){
        //一般数据查询是一直用耗时操作，都在子线程中进行，我这里为了方便，所以添加了allowMainThreadQueries()允许在主线程中操作。
        workDatabase= Room.databaseBuilder(context,WorkDatabase.class,"work_db").allowMainThreadQueries().build();
        workDao=workDatabase.getWorkDao();
    }

    public static WorkRepository getInstance(Context context){
        if(null==instance){
            synchronized (WorkRepository.class){
                if(null==instance){
                    instance=new WorkRepository(context);
                }
            }
        }
        return instance;
    }
        public WorkDao getWorkDao(){
        return  workDao;
        }
}
