package ru.tabiin.counters.domain.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "counters")
public class CounterItem {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "target")
    public int target;

    @ColumnInfo(name = "progress")
    public int progress;

    @ColumnInfo(name = "completed")
    public boolean completed;

    @ColumnInfo(name = "mode")
    public String mode = "linear";
    /*
    3 counter modes:
    linear (linear progressBar)
    circle (circle progressBar)
    swipe (no progressBar)
     */

    @Ignore
    public CounterItem(String title, int target, int progress) {
        this.title = title;
        this.target = target;
        this.progress = progress;
    }

    @Ignore
    public CounterItem(String title, int target, int progress, String mode) {
        this.title = title;
        this.target = target;
        this.progress = progress;
        this.mode = mode;
    }

    //@Ignore
    public CounterItem(int id, String title, int target, int progress) {
        this.id = id;
        this.title = title;
        this.target = target;
        this.progress = progress;
    }

    @Ignore
    public CounterItem(int id, String title, int target, int progress, String mode) {
        this.id = id;
        this.title = title;
        this.target = target;
        this.progress = progress;
        this.mode = mode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }


}
