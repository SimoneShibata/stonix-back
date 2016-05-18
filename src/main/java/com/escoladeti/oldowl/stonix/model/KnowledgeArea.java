package com.escoladeti.oldowl.stonix.model;

import com.google.common.base.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

/**
 * Created by Felipe on 13/05/2016.
 */
@Entity
public class KnowledgeArea {
    @Id
    private final String id;

    private String name;

    public KnowledgeArea() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KnowledgeArea that = (KnowledgeArea) o;
        return Objects.equal(this.name, that.name);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
