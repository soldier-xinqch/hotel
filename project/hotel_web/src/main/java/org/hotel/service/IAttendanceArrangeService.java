package org.hotel.service;

import java.util.List;

import org.hotel.common.BaseService;
import org.hotel.model.AttendanceArrange;
import org.hotel.model.Org;

/**
 *  排班数据接口
 * @author xinqch
 *
 */
public interface IAttendanceArrangeService extends BaseService<AttendanceArrange> {

	/**
	 *  查询所有考勤信息
	 * @return
	 */
	List<AttendanceArrange> findAll();

	List<AttendanceArrange> findAttendanceArrangeByOrgs(List<Org> orgs);
}
