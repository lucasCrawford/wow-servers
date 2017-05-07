package com.thorrism.repository;

import com.thorrism.entity.ServerStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Hercules on 5/7/2017.
 */
@Repository
public interface ServerStatusRepository extends CrudRepository<ServerStatus, Long> {
}
