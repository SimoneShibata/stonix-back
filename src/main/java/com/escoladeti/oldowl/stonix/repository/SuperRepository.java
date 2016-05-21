package com.escoladeti.oldowl.stonix.repository;

import com.escoladeti.oldowl.stonix.exception.DontUseException;
import com.escoladeti.oldowl.stonix.model.SuperEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * Created by theonly on 2016-05-21.
 */
@NoRepositoryBean
public interface SuperRepository<T extends SuperEntity> extends JpaRepository<T, String> {

    List<T> findByDeadIsFalse();

    @Override
    default List<T> findAll() {
        throw new DontUseException();
    }
}