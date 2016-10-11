package com.example.fengxinlin.nanodegreep9;

/**
 * Created by fengxinlin on 10/7/16.
 */
public class HabitObject {
    private int id;
    private String habit;
    private int frequency;

    public HabitObject(String habit, int frequency) {
        this.habit = habit;
        this.frequency = frequency;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHabit() {
        return this.habit;
    }

    public void setHabit(String habit) {
        this.habit = habit;
    }

    public int getFrequency() {
        return this.frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
