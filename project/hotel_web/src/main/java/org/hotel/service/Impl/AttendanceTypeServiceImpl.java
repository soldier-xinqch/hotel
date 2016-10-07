package org.hotel.service.Impl;

import java.util.Date;
import java.util.List;

import org.hotel.mapper.AttendanceTypeMapper;
import org.hotel.model.AttendanceType;
import org.hotel.service.IAttendanceTypeService;
import org.hotel.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("attendanceTypeService")
public class AttendanceTypeServiceImpl implements IAttendanceTypeService{
	
	@Autowired
	private AttendanceTypeMapper attendanceTypeMapper;

	@Override
	public int insert(AttendanceType attendanceType) {
		attendanceType.setId(CommonUtil.getUUID());
		attendanceType.setCreateTime(new Date());
		attendanceType.setDelFlag(deleteFlag_insert);
		attendanceType.setModifyTime(dateInit);
		return attendanceTypeMapper.insertSelective(attendanceType);
	}

	@Override
	public int modify(AttendanceType attendanceRecord) {
		attendanceRecord.setModifyTime(new Date());
		return attendanceTypeMapper.updateByPrimaryKeySelective(attendanceRecord);
	}

	@Override
	public int logicDelete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int physicsDelete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<AttendanceType> findAll() {
		return attendanceTypeMapper.findAll();
	}
	
}
