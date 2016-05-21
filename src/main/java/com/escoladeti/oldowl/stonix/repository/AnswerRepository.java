package com.escoladeti.oldowl.stonix.repository;

import com.escoladeti.oldowl.stonix.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tdc on 09/04/16.
 */
@Repository
public interface AnswerRepository extends SuperRepository<Answer> {
}
