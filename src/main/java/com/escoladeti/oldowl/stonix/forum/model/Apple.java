package com.escoladeti.oldowl.stonix.forum.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by TI2 on 25/10/2016.
 */
@Entity
public class Apple extends SuperEntity{
    @ManyToOne
    private User teacher;

    @ManyToOne
    private User student;

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Apple apple = (Apple) o;

        if (teacher != null ? !teacher.equals(apple.teacher) : apple.teacher != null) return false;
        return student != null ? student.equals(apple.student) : apple.student == null;

    }

    @Override
    public int hashCode() {
        int result = teacher != null ? teacher.hashCode() : 0;
        result = 31 * result + (student != null ? student.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "teacher=" + teacher +
                ", student=" + student +
                '}';
    }
}
