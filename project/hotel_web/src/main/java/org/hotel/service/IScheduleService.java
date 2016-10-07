package org.hotel.service;

import java.util.List;

import org.hotel.common.BaseService;
import org.hotel.model.Org;
import org.hotel.model.Schedule;

public interface IScheduleService extends BaseService<Schedule> {

	/**
	 *  查询所有组织
	 * @return
	 */
	List<Schedule> findAll();

	List<Schedule> findScheduleByOrgs(List<Org> orgs);
}
