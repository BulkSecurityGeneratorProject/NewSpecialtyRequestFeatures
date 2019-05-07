package com.java.repository;

import com.java.domain.WoShippingServices;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the WoShippingServices entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WoShippingServicesRepository extends JpaRepository<WoShippingServices, Long> {

}
