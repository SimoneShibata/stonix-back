package com.escoladeti.oldowl.stonix.repository;

import com.escoladeti.oldowl.stonix.model.Password;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Felipe on 16/05/2016.
 */
@Repository
public interface PasswordRepository extends JpaRepository<Password, String>{
}
