package org.apis.dao.member;

import org.domain.member.UserSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSummaryRepository extends JpaRepository<UserSummary, String> {

}
