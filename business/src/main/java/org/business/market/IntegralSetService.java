package org.business.market;

import org.domain.system.User;
import org.vo.IntegralSetDto;

public interface IntegralSetService {

	/**
	 * 更新积分设置
	 * @param dto
	 * @param user
	 */
	public void save(IntegralSetDto dto, User user);
	
}
