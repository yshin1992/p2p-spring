package org.apis.dao.member;

import org.domain.member.MemberOperationLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberOperationLogRepository extends JpaRepository<MemberOperationLog, String> {

}
