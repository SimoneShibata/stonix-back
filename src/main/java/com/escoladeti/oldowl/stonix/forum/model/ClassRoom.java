package com.escoladeti.oldowl.stonix.forum.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

/**
 * Created by felipe on 13/10/16.
 */
@Entity
public class ClassRoom extends SuperEntity {
    @ManyToOne
    private User teacher;

    @ManyToMany
    private List<User> students;

    private String name;
    private String description;

    public List<User> getStudents() {
        return students;
    }

    public void setStudents(List<User> students) {
        this.students = students;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassRoom classRoom = (ClassRoom) o;

        if (teacher != null ? !teacher.equals(classRoom.teacher) : classRoom.teacher != null) return false;
        if (name != null ? !name.equals(classRoom.name) : classRoom.name != null) return false;
        return description != null ? description.equals(classRoom.description) : classRoom.description == null;

    }

    @Override
    public int hashCode() {
        int result = teacher != null ? teacher.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ClassRoom{" +
                "teacher=" + teacher +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
