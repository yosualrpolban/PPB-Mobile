package com.example.mommayells;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Schedule.class}, version = 1, exportSchema = false)
public abstract class ScheduleRoomDatabase extends RoomDatabase {

    public abstract ScheduleDao scheduleDao();

    private static volatile ScheduleRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static ScheduleRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ScheduleRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ScheduleRoomDatabase.class, "schedule_database").addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private ScheduleDao scheduleDao;

        private  PopulateDbAsyncTask(ScheduleRoomDatabase db){
            this.scheduleDao = db.scheduleDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Schedule schedule = new Schedule("Hello Putin","03/22/22","12:00:00 ","Besok ketemu putin");
            scheduleDao.insert(schedule);
            schedule = new Schedule("Hello Jokowi","03/25/22","11:00:00","Besok ketemu Jokowi");
            scheduleDao.insert(schedule);
            schedule = new Schedule("Hello Prabowo","03/25/22","14:00:00","Besok ketemu Prabowo");
            scheduleDao.insert(schedule);
            schedule = new Schedule("Hello Denny Cagur","12/25/22","02:00:00","Ntar ketemu Denny Cagur");
            scheduleDao.insert(schedule);
            schedule = new Schedule("Hello Donald Trump","03/21/23","18:00:00","Tahun depan ketemu Donald Trump");
            scheduleDao.insert(schedule);
            schedule = new Schedule("Hello Sukirman","04/2/022","07:00:00","Minggu depan ketemu Sukirman");
            scheduleDao.insert(schedule);
            schedule = new Schedule("Hello Tante","04/25/22","19:00:00","Bulan depan ketemu tante");
            scheduleDao.insert(schedule);
            schedule = new Schedule("Hello Nadiem","11/27/22","20:00:00","Ulang tahun dirayain sama Nadiem Makarim");
            scheduleDao.insert(schedule);
            schedule = new Schedule("Hello Soeharto","06/28/31","15:00:00","Udah ga di dunia");
            scheduleDao.insert(schedule);
            return null;
        }
    }
}
//            // If you want to keep data through app restarts,
//            // comment out the following block
//            databaseWriteExecutor.execute(() -> {
//                // Populate the database in the background.
//                // If you want to start with more words, just add them.
//                ScheduleDao dao = INSTANCE.scheduleDao();
//                dao.deleteAll();
//
//                Schedule schedule = new Schedule("Hello Putin","03/22/22","12:00:00 ","Besok ketemu putin");
//                dao.insert(schedule);
//                schedule = new Schedule("Hello Jokowi","03/25/22","11:00:00","Besok ketemu Jokowi");
//                dao.insert(schedule);
//                schedule = new Schedule("Hello Prabowo","03/25/22","14:00:00","Besok ketemu Prabowo");
//                dao.insert(schedule);
//                schedule = new Schedule("Hello Denny Cagur","12/25/22","02:00:00","Ntar ketemu Denny Cagur");
//                dao.insert(schedule);
//                schedule = new Schedule("Hello Donald Trump","03/21/23","18:00:00","Tahun depan ketemu Donald Trump");
//                dao.insert(schedule);
//                schedule = new Schedule("Hello Sukirman","04/2/022","07:00:00","Minggu depan ketemu Sukirman");
//                dao.insert(schedule);
//                schedule = new Schedule("Hello Tante","04/25/22","19:00:00","Bulan depan ketemu tante");
//                dao.insert(schedule);
//                schedule = new Schedule("Hello Nadiem","11/27/22","20:00:00","Ulang tahun dirayain sama Nadiem Makarim");
//                dao.insert(schedule);
//                schedule = new Schedule("Hello Soeharto","06/28/31","15:00:00","Udah ga di dunia");
//                dao.insert(schedule);
//            });
//        }
//    };
//}
