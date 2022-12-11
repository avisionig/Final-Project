package models;

import models.schedule.Schedule;

public interface Schedulable {
    default Schedule getSchedule() {
        return null;
    }

    default void viewSchedule() {
    }
}
