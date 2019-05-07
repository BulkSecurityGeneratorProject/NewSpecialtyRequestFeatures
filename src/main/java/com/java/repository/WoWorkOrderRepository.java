package com.java.repository;

import com.java.domain.WoWorkOrder;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the WoWorkOrder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WoWorkOrderRepository extends JpaRepository<WoWorkOrder, Long> {

}
