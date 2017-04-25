package com.thorrism.repository;

import com.thorrism.entity.Server;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Hercules on 4/23/2017.
 */
@Repository
public interface ServerRepository extends CrudRepository<Server, Long> {

    List<Server> findServerByFriendlyNameIsStartingWithIgnoreCase(String name);

}
