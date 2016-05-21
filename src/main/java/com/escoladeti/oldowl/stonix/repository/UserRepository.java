package com.escoladeti.oldowl.stonix.repository;

import com.escoladeti.oldowl.stonix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Felipe on 09/04/2016.
 */
@Repository
public interface UserRepository extends SuperRepository<User>{
}
