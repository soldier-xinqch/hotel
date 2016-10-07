package org.hotel.service;

import java.util.List;

import org.hotel.common.BaseService;
import org.hotel.model.AttendanceRecord;
import org.hotel.model.Org;

/**
 *  菜单数据接口
 * @author xinqch
 *
 */
public interface IAttendanceRecordService extends BaseService<AttendanceRecord> {

	/**
	 *  查询所有考勤信息
	 * @return
	 */
	List<AttendanceRecord> findAll();

	List<AttendanceRecord> findAttendanceRecordByOrgs(List<Org> orgs);
}
