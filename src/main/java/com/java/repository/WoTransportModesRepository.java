package com.java.repository;

import com.java.domain.WoTransportModes;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the WoTransportModes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WoTransportModesRepository extends JpaRepository<WoTransportModes, Long> {

}
