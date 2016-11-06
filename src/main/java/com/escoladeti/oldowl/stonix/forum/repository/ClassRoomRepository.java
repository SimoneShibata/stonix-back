package com.escoladeti.oldowl.stonix.forum.repository;

import com.escoladeti.oldowl.stonix.forum.model.ClassRoom;

import java.util.List;

/**
 * Created by felipe on 13/10/16.
 */
public interface ClassRoomRepository extends SuperRepository<ClassRoom> {

    List<ClassRoom> findByDeadIsFalseAndTeacherId(String teacherId);
}
