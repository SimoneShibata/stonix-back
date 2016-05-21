package com.escoladeti.oldowl.stonix.repository;

import com.escoladeti.oldowl.stonix.model.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Felipe on 13/05/2016.
 */
@Repository
public interface EvaluationRepository extends SuperRepository<Evaluation>{
}
