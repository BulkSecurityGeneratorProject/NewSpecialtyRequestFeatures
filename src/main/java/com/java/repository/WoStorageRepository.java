package com.java.repository;

import com.java.domain.WoStorage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the WoStorage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WoStorageRepository extends JpaRepository<WoStorage, Long> {

}
