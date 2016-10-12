package org.hotel.service.Impl;

import java.util.Date;
import java.util.List;

import org.apache.shiro.util.CollectionUtils;
import org.hotel.mapper.ScheduleMapper;
import org.hotel.mapper.ScheduleStaffMapper;
import org.hotel.model.Org;
import org.hotel.model.ScheduleStaff;
import org.hotel.model.ScheduleWithBLOBs;
import org.hotel.service.IScheduleService;
import org.hotel.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service("scheduleService")
public class ScheduleServiceImpl implements IScheduleService{
	
	@Autowired
	private ScheduleMapper scheduleMapper;
	@Autowired
	private ScheduleStaffMapper scheduleStaffMapper; 
	
	@Override
	@Transactional
	public int insert(ScheduleWithBLOBs schedule) {
		schedule.setId(CommonUtil.getUUID());
		schedule.setCreateTime(new Date());
		schedule.setDelFlag(deleteFlag_insert);
		schedule.setModifyTime(dateInit);
		if(null == schedule.getStartTime())schedule.setStartTime(dateInit);
		if(null == schedule.getEndTime())schedule.setEndTime(dateInit);
		if(!StringUtils.isEmpty(schedule.getStaffId())){
			String[] temps = schedule.getStaffId().split(",");
			StringBuffer bf = new StringBuffer();
			for (String string : temps) {
				String[] staffs = string.trim().split("-");
				if(staffs.length<1) continue;
				ScheduleStaff staff = new ScheduleStaff();
				staff.setId(CommonUtil.getUUID());
				staff.setStaffId(staffs[0]);
				staff.setStaffNo(staffs[1]);
				staff.setStaffName(staffs[2]);
				staff.setScheduleId(schedule.getId());
				staff.setStartTime(schedule.getStartTime());
				staff.setEndTime(schedule.getEndTime());
				staff.setOrderId(schedule.getOrderId());
				staff.setOrgId(schedule.getOrgId());
				staff.setOrgName(schedule.getOrgName());
				scheduleStaffMapper.insert(staff);
				bf.append(staffs[0]+",");
			}
			schedule.setStaffId(bf.toString());
		}
		return scheduleMapper.insertSelective(schedule);
	}

	@Override
	@Transactional
	public int modify(ScheduleWithBLOBs schedule) {
		schedule.setModifyTime(new Date());
		List<String> staffSchedules = scheduleStaffMapper.findStaffScheduleByScheduleId(schedule.getId());
		if(!StringUtils.isEmpty(schedule.getStaffId())){
			String[] temps = schedule.getStaffId().split(",");
			StringBuffer bf = new StringBuffer();
			for (String string : temps) {
				String[] staffs = string.trim().split("-");
				if(staffs.length<1) continue;
				ScheduleStaff staff = new ScheduleStaff();
				staff.setStaffId(staffs[0]);
				staff.setStaffNo(staffs[1]);
				staff.setStaffName(staffs[2]);
				staff.setScheduleId(schedule.getId());
				staff.setStartTime(schedule.getStartTime());
				staff.setEndTime(schedule.getEndTime());
				staff.setOrderId(schedule.getOrderId());
				staff.setOrgId(schedule.getOrgId());
				staff.setOrgName(schedule.getOrgName());
				if(!CollectionUtils.isEmpty(staffSchedules)){
					if(!staffSchedules.contains(staffs[0])){
						scheduleStaffMapper.deleteScheduleStaff(staffs[0],schedule.getId());
					}else{
						ScheduleStaff staffSchedule = scheduleStaffMapper.findStaffScheduleByScheduleIdAndStaffId(staffs[0], schedule.getId());
						if(null != staffSchedule){
							staff.setId(staffSchedule.getId());
							scheduleStaffMapper.updateByPrimaryKeySelective(staff);
						}else{
							staff.setId(CommonUtil.getUUID());
							scheduleStaffMapper.insert(staff);
						}
					}
				}
				bf.append(staffs[0]+",");
			}
			schedule.setStaffId(bf.toString());
		}
		
		return scheduleMapper.updateByPrimaryKeySelective(schedule);
	}

	@Override
	public int logicDelete(String id) {
		ScheduleWithBLOBs schedule = new ScheduleWithBLOBs();
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
	public List<ScheduleWithBLOBs> findAll() {
		return scheduleMapper.findAll();
	}

	@Override
	public List<ScheduleWithBLOBs> findScheduleByOrgs(List<Org> orgs) {
		return scheduleMapper.findScheduleByOrgs(orgs);
	}
	
	@Override
	public ScheduleStaff findStaffScheduleByNoAndTime(String staffNo,String brushTime){
		return scheduleStaffMapper.findStaffScheduleByNoAndTime(staffNo,brushTime);
	}
	
}
