package org.hotel.service;

import java.util.List;

import org.hotel.common.BaseService;
import org.hotel.model.EatTime;

public interface IEatTimeService extends BaseService<EatTime>{

	/**
	 *  查询所有员工
	 * @return
	 */
	List<EatTime> findAll();
	/**
	 *  根据orgID查询员工
	 * @return
	 */
	List<EatTime> findEatTimeByOrgId(String orgId);
}
