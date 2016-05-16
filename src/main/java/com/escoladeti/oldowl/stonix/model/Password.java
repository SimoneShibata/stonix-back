package com.escoladeti.oldowl.stonix.model;

import com.google.common.base.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

/**
 * Created by Felipe on 16/05/2016.
 */

@Entity
public class Password {
    @Id
    private final String id;

    private String value;

    public String getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Password() {
        this.id = UUID.randomUUID().toString();
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Password that = (Password) o;
        return Objects.equal(this.value, that.value);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}


