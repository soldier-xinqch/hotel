package org.hotel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.hotel.model.ScheduleStaff;

public interface ScheduleStaffMapper {
    int deleteByPrimaryKey(String id);

    int insert(ScheduleStaff record);

    int insertSelective(ScheduleStaff record);

    ScheduleStaff selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ScheduleStaff record);

    int updateByPrimaryKey(ScheduleStaff record);
    
    List<String> findStaffScheduleByScheduleId(String scheduleId);
    
    int deleteScheduleStaff(@Param("staffId") String staffId,@Param("scheduleId") String scheduleId);
    
    ScheduleStaff findStaffScheduleByScheduleIdAndStaffId(@Param("staffId") String staffId,@Param("scheduleId") String scheduleId);
    
    ScheduleStaff findStaffScheduleByNoAndTime(@Param("staffNo") String staffNo,@Param("brushTime") String brushTime);
}