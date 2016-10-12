package org.hotel.service.Impl;

import java.util.Date;
import java.util.List;

import org.hotel.mapper.AttendanceRecordMapper;
import org.hotel.model.AttendanceRecord;
import org.hotel.model.Org;
import org.hotel.service.IAttendanceRecordService;
import org.hotel.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("attendanceRecordService")
public class AttendanceRecordServiceImpl implements IAttendanceRecordService{
	
	@Autowired
	private AttendanceRecordMapper attendanceRecordMapper;

	@Override
	public int insert(AttendanceRecord attendanceRecord) {
		attendanceRecord.setId(CommonUtil.getUUID());
		attendanceRecord.setCreateTime(new Date());
		attendanceRecord.setDelFlag(deleteFlag_insert);
		attendanceRecord.setModifyTime(dateInit);
		if(null == attendanceRecord.getRestTime())attendanceRecord.setRestTime(dateInit);
		if(null == attendanceRecord.getWorkTime())attendanceRecord.setWorkTime(dateInit);
		return attendanceRecordMapper.insertSelective(attendanceRecord);
	}

	@Override
	public int modify(AttendanceRecord attendanceRecord) {
		attendanceRecord.setModifyTime(new Date());
		return attendanceRecordMapper.updateByPrimaryKeySelective(attendanceRecord);
	}

	@Override
	public int logicDelete(String id) {
		AttendanceRecord attendanceRecord = new AttendanceRecord();
		attendanceRecord.setId(id);
		attendanceRecord.setModifyTime(new Date());
		attendanceRecord.setDelFlag(deleteFlag_del);
		return attendanceRecordMapper.updateByPrimaryKeySelective(attendanceRecord);
	}

	@Override
	public int physicsDelete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<AttendanceRecord> findAll() {
		return attendanceRecordMapper.findAll();
	}

	@Override
	public List<AttendanceRecord> findAttendanceRecordByOrgs(List<Org> orgs) {
		return attendanceRecordMapper.findAttendanceRecordByOrgs(orgs);
	}

	@Override
	public List<AttendanceRecord> findAttendanceRecordByLikes(List<Org> orgs, String[] staffId) {
		return attendanceRecordMapper.findAttendanceRecordByLikes(orgs,staffId);
	}
	
}
