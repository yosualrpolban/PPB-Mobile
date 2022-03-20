package com.example.mommayells;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Schedule.class}, version = 1, exportSchema = false)
public abstract class ScheduleRoomDatabase extends RoomDatabase{

    public abstract ScheduleDao scheduleDao();

    private static volatile ScheduleRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static ScheduleRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (ScheduleRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ScheduleRoomDatabase.class,"schedule_database").addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                ScheduleDao dao = INSTANCE.scheduleDao();
                dao.deleteAll();

                Schedule schedule = new Schedule("Hello Putin","03/22/22","Besok ketemu putin");
                dao.insert(schedule);
                schedule = new Schedule("Hello Jokowi","03/25/22","Besok ketemu Jokowi");
                dao.insert(schedule);
            });
        }
    };
}
