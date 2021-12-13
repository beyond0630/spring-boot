package org.beyond.nacos.config.repository;

import org.beyond.nacos.config.model.entity.PayRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PayRecordRepository extends JpaRepository<PayRecord, String>, JpaSpecificationExecutor<PayRecord> {

}
