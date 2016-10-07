package org.hotel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.hotel.model.Org;
import org.hotel.model.Schedule;

public interface ScheduleMapper {
    int deleteByPrimaryKey(String id);

    int insertSelective(Schedule record);

    Schedule selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Schedule record);

	List<Schedule> findAll();

	List<Schedule> findScheduleByOrgs(@Param("orgs") List<Org> orgs);

}