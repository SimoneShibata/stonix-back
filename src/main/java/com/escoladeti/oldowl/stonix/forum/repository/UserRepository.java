package com.escoladeti.oldowl.stonix.forum.repository;

import com.escoladeti.oldowl.stonix.forum.model.User;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * Created by tdc on 09/04/16.
 */
@Repository
public interface UserRepository extends SuperRepository<User> {
    User findByEmail(String email);

    List<User> findAllByDeadIsFalseOrderByPunctuationDesc();

    List<User> findAllByDeadIsFalseOrderByLevelDesc();
}
