package org.hotel.service.Impl;

import java.util.Date;
import java.util.List;

import org.hotel.mapper.ScheduleMapper;
import org.hotel.model.Org;
import org.hotel.model.Schedule;
import org.hotel.service.IScheduleService;
import org.hotel.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("scheduleService")
public class ScheduleServiceImpl implements IScheduleService{
	
	@Autowired
	private ScheduleMapper scheduleMapper;

	@Override
	public int insert(Schedule schedule) {
		schedule.setId(CommonUtil.getUUID());
		schedule.setCreateTime(new Date());
		schedule.setDelFlag(deleteFlag_insert);
		schedule.setModifyTime(dateInit);
		if(null == schedule.getStartTime())schedule.setStartTime(dateInit);
		if(null == schedule.getEndTime())schedule.setEndTime(dateInit);
		return scheduleMapper.insertSelective(schedule);
	}

	@Override
	public int modify(Schedule schedule) {
		return scheduleMapper.updateByPrimaryKeySelective(schedule);
	}

	@Override
	public int logicDelete(String id) {
		Schedule schedule = new Schedule();
		schedule.setId(id);
		schedule.setDelFlag(deleteFlag_del);
		schedule.setModifyTime(new Date());
		return scheduleMapper.updateByPrimaryKeySelective(schedule);
	}

	@Override
	public int physicsDelete(String id) {
		return scheduleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Schedule> findAll() {
		return scheduleMapper.findAll();
	}

	@Override
	public List<Schedule> findScheduleByOrgs(List<Org> orgs) {
		return scheduleMapper.findScheduleByOrgs(orgs);
	}
	
}
