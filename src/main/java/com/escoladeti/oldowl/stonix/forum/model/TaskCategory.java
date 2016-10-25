package com.escoladeti.oldowl.stonix.forum.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by TI2 on 25/10/2016.
 */
@Entity
public class TaskCategory extends SuperEntity {

    private String name;
    private String description;

    @ManyToOne
    private ClassRoom classRoom;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskCategory that = (TaskCategory) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return classRoom != null ? classRoom.equals(that.classRoom) : that.classRoom == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (classRoom != null ? classRoom.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TaskCategory{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", classRoom=" + classRoom +
                '}';
    }
}
