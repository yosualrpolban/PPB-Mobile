package com.example.mommayells;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ScheduleViewModel extends AndroidViewModel {

    private ScheduleRepository mRepositorySchedule;

    private final LiveData<List<Schedule>> mAllSchedule;

    public ScheduleViewModel (Application application) {
        super(application);
        mRepositorySchedule = new ScheduleRepository(application);
        mAllSchedule = mRepositorySchedule.getAllTugas();
    }

    LiveData<List<Schedule>> getAllSchedule() { return mAllSchedule; }

    public void insert(Schedule tugas) { mRepositorySchedule.insert(tugas); }
}
