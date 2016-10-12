package org.hotel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.hotel.model.Org;
import org.hotel.model.Schedule;
import org.hotel.model.ScheduleWithBLOBs;

public interface ScheduleMapper {
    int deleteByPrimaryKey(String id);

    int insert(ScheduleWithBLOBs record);

    int insertSelective(ScheduleWithBLOBs record);

    ScheduleWithBLOBs selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ScheduleWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ScheduleWithBLOBs record);

    int updateByPrimaryKey(Schedule record);
    
    List<ScheduleWithBLOBs> findAll();

	List<ScheduleWithBLOBs> findScheduleByOrgs(@Param("orgs") List<Org> orgs); 

}