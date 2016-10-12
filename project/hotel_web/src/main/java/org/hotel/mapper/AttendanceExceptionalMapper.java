package org.hotel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.hotel.model.AttendanceExceptional;
import org.hotel.model.Org;

public interface AttendanceExceptionalMapper {
    int deleteByPrimaryKey(String id);

    int insert(AttendanceExceptional record);

    int insertSelective(AttendanceExceptional record);

    AttendanceExceptional selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AttendanceExceptional record);

    int updateByPrimaryKey(AttendanceExceptional record);

	List<AttendanceExceptional> findAll();

	List<AttendanceExceptional> findAttendanceExceptionalByOrgs(@Param("orgs") List<Org> orgs);

	List<AttendanceExceptional> findAttendanceExceptionalByLikes(@Param("orgs") List<Org> orgs,@Param("exceptions") List<String> exceptions,
			@Param("staffId") String[] staffId,@Param("startTime") String startTime,@Param("endTime") String endTime);
}