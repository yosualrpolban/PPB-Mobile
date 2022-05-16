package com.example.mommayells;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ScheduleRepository {
    private ScheduleDao mScheduleDao;
    private LiveData<List<Schedule>> mAllSchedule;


    ScheduleRepository(Application application) {
        ScheduleRoomDatabase db = ScheduleRoomDatabase.getDatabase(application);
        mScheduleDao = db.scheduleDao();
        mAllSchedule = mScheduleDao.getAlphabetizedWords();
    }

    LiveData<List<Schedule>> getAllTugas() {
        return mAllSchedule;
    }

    void insert(Schedule schedule) {
        new InsertScheduleAsyncTask(mScheduleDao).execute(schedule);
    }

    void deleteOne(String nameSchedule){
        ScheduleRoomDatabase.databaseWriteExecutor.execute(() -> {
            mScheduleDao.deleteOne(nameSchedule);
        });    }

    private static class InsertScheduleAsyncTask extends AsyncTask<Schedule, Void, Void> {
        private ScheduleDao scheduleDao;

        private InsertScheduleAsyncTask(ScheduleDao scheduleDao){
            this.scheduleDao = scheduleDao;
        }
        @Override
        protected Void doInBackground(Schedule... schedules) {
            scheduleDao.insert(schedules[0]);
            return null;
        }
    }

//    private static class DeleteScheduleAsyncTask extends  AsyncTask<Schedule,Void,Void>{
//        private ScheduleDao scheduleDao;
//
//        private DeleteScheduleAsyncTask(ScheduleDao scheduleDao){
//            this.scheduleDao = scheduleDao;
//        }
//
//        @Override
//        protected Void doInBackground(Schedule... schedules) {
//            scheduleDao.deleteOne(schedules[0]);
//            return null;
//        }
//    }
}
