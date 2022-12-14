package com.example.taskmaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.taskmaster.R;
import com.example.taskmaster.activities.MainActivity;
import com.example.taskmaster.adapter.TaskRecyclerViewAdapter;
import com.example.taskmaster.database.TaskMasterDatabase;
import com.example.taskmaster.model.Tasks;

import java.util.ArrayList;
import java.util.List;

public class AllTasks extends AppCompatActivity {
    TaskMasterDatabase taskMasterDatabase;
    public static final String TASK_TITLE_TAG = "title";
    public static final String TASK_BODY_TAG = "body";
    public static final String TASK_STATE_TAG = "state";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tasks);
        taskMasterDatabase = Room.databaseBuilder(
                        getApplicationContext(),
                        TaskMasterDatabase.class,
                        MainActivity.DATABASE_NAME)
                        .fallbackToDestructiveMigration() // If Room gets confused, it tosses your database; don't use this in production!
                        .allowMainThreadQueries()
                        .build();
        setUpRecyclerView();
    }

    public void setUpRecyclerView(){
        List<Tasks> taskList = taskMasterDatabase.taskDao().findAll();
        taskList.add(new Tasks("gym", "go to gym", Tasks.State.ASSIGNED));
        taskList.add(new Tasks("store", "go shopping", Tasks.State.COMPLETE));
        taskList.add(new Tasks("nap", "take a nap", Tasks.State.NEW));
        taskList.add(new Tasks("Code Challenge", "Practice using hashmaps", Tasks.State.IN_PROGRESS));
        taskList.add(new Tasks("Whiteboard", "attempt to pass whiteboard", Tasks.State.ASSIGNED));
        taskList.add(new Tasks("walk", "go for a walk", Tasks.State.COMPLETE));
        RecyclerView taskRV = findViewById(R.id.AllTasksRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        taskRV.setLayoutManager(layoutManager);
        TaskRecyclerViewAdapter adapter = new TaskRecyclerViewAdapter(taskList, this);
        taskRV.setAdapter(adapter);
    }
}