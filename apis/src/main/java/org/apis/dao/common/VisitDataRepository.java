package org.apis.dao.common;

import java.util.Date;

import org.domain.common.VisitData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VisitDataRepository extends JpaRepository<VisitData, String> {

	@Query("select count(0) from VisitData where visitIp=?1 and createTime>=?2 and createTime<=?3")
	Integer findCountInOneHourByIp(String ip,Date start,Date end);
	
	@Query("select count(0) from VisitData where userData=?1 and createTime>=?2 and createTime<=?3")
	Integer findCountInOneHourByUserData(String userData,Date start,Date end);
}
