package com.example.mommayells;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ScheduleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Schedule schedule);

    @Query("DELETE FROM schedule_table")
    void deleteAll();

    @Query("SELECT * FROM schedule_table ORDER BY dateSchedule ASC")
    LiveData<List<Schedule>> getAlphabetizedWords();

}
