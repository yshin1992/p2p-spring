package org.apis.dao.system;

import org.domain.system.SysOperationLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysOperationLogRepository extends JpaRepository<SysOperationLog, String> {

}
