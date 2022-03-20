package com.example.mommayells;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ScheduleRepository {
    private ScheduleDao mScheduleDao;
    private LiveData<List<Schedule>> mAllSchedule;


    ScheduleRepository(Application application){
        ScheduleRoomDatabase db = ScheduleRoomDatabase.getDatabase(application);
        mScheduleDao = db.scheduleDao();
        mAllSchedule = mScheduleDao.getAlphabetizedWords();
    }

    LiveData<List<Schedule>> getAllTugas(){
        return mAllSchedule;
    }

    void insert(Schedule schedule){
        ScheduleRoomDatabase.databaseWriteExecutor.execute(() ->{
            mScheduleDao.insert(schedule);
        });
    }

}
