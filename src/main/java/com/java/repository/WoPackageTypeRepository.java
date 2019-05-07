package com.java.repository;

import com.java.domain.WoPackageType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the WoPackageType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WoPackageTypeRepository extends JpaRepository<WoPackageType, Long> {

}
