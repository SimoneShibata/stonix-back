package com.escoladeti.oldowl.stonix.forum.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by TI2 on 25/10/2016.
 */
@Entity
public class Task extends SuperEntity {
    @ManyToOne
    private TaskCategory taskCategory;

    private String title;
    private String description;

    public TaskCategory getTaskCategory() {
        return taskCategory;
    }

    public void setTaskCategory(TaskCategory taskCategory) {
        this.taskCategory = taskCategory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (taskCategory != null ? !taskCategory.equals(task.taskCategory) : task.taskCategory != null) return false;
        if (title != null ? !title.equals(task.title) : task.title != null) return false;
        return description != null ? description.equals(task.description) : task.description == null;

    }

    @Override
    public int hashCode() {
        int result = taskCategory != null ? taskCategory.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskCategory=" + taskCategory +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
