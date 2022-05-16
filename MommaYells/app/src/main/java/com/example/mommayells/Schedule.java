package com.example.mommayells;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "schedule_table")
public class Schedule{
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "nameSchedule")
    private String scheduleName;

    @ColumnInfo(name = "dateSchedule")
    private String scheduleDate;

    @ColumnInfo(name = "timeSchedule")
    private String scheduleTime;

    @ColumnInfo(name = "detailSchedule")
    private String scheduleDetail;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public Schedule(@NonNull String scheduleName, String scheduleDate, String scheduleTime, String scheduleDetail) {
        this.scheduleName = scheduleName;
        this.scheduleDate = scheduleDate;
        this.scheduleTime = scheduleTime;
        this.scheduleDetail = scheduleDetail;
    }
    public String getScheduleName(){return this.scheduleName;}
    public String getScheduleDate() {return scheduleDate;}
    public String getScheduleTime() {return scheduleTime;}
    public String getScheduleDetail() {return scheduleDetail;}




}






//    String scheduleName;
//
//    public Schedule(String ScheduleName){
//        this.scheduleName = ScheduleName;
//    }
//
//    public String getScheduleName() {
//        return scheduleName;
//    }
//
//    public void setScheduleName(String ScheduleName) {
//        this.scheduleName = ScheduleName;
//    }
