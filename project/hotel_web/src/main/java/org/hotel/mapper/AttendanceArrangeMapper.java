package org.hotel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.hotel.model.AttendanceArrange;
import org.hotel.model.Org;

public interface AttendanceArrangeMapper {
    int deleteByPrimaryKey(String id);

    int insertSelective(AttendanceArrange record);

    AttendanceArrange selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AttendanceArrange record);

	List<AttendanceArrange> findAll();

	List<AttendanceArrange> findAttendanceArrangeByOrgs(@Param("orgs") List<Org> orgs);
}