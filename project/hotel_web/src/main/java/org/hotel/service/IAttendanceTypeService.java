package org.hotel.service;

import java.util.List;

import org.hotel.common.BaseService;
import org.hotel.model.AttendanceType;

/**
 *  菜单数据接口
 * @author xinqch
 *
 */
public interface IAttendanceTypeService extends BaseService<AttendanceType> {

	/**
	 *  查询所有考勤信息
	 * @return
	 */
	List<AttendanceType> findAll();
}
