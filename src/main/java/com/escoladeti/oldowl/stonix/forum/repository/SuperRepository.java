package com.escoladeti.oldowl.stonix.forum.repository;

import com.escoladeti.oldowl.stonix.forum.model.SuperEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * Created by theonly on 2016-05-21.
 */
@NoRepositoryBean
public interface SuperRepository<T extends SuperEntity> extends JpaRepository<T, String> {

    List<T> findByDeadIsFalse();

    T findByIdAndDeadIsFalse(final String id);

    @Override
    default List<T> findAll() {
        return findByDeadIsFalse();
    }

    @Override
    default T findOne(final String id) {
        return findByIdAndDeadIsFalse(id);
    }
}