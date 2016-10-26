package com.escoladeti.oldowl.stonix.forum.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by TI2 on 26/10/2016.
 */
@Entity
public class TaskOption extends SuperEntity
{
    private String description;
    private Boolean correct;

    @ManyToOne
    private Task task;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskOption that = (TaskOption) o;

        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (correct != null ? !correct.equals(that.correct) : that.correct != null) return false;
        return task != null ? task.equals(that.task) : that.task == null;

    }

    @Override
    public int hashCode() {
        int result = description != null ? description.hashCode() : 0;
        result = 31 * result + (correct != null ? correct.hashCode() : 0);
        result = 31 * result + (task != null ? task.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TaskOption{" +
                "description='" + description + '\'' +
                ", correct=" + correct +
                ", task=" + task +
                '}';
    }
}
