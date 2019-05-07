package com.java.repository;

import com.java.domain.WoAdditionalServices;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the WoAdditionalServices entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WoAdditionalServicesRepository extends JpaRepository<WoAdditionalServices, Long> {

}
