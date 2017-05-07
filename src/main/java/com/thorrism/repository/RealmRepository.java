package com.thorrism.repository;

import com.thorrism.entity.Realm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Hercules on 5/7/2017.
 */
@Repository
public interface RealmRepository extends CrudRepository<Realm, Long> {
}
