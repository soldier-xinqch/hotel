package org.hotel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.hotel.model.AttendanceBrush;
import org.hotel.model.AttendanceSearch;
import org.hotel.model.Org;

public interface AttendanceBrushMapper {
    int deleteByPrimaryKey(String id);

    int insertSelective(AttendanceBrush record);

    AttendanceBrush selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AttendanceBrush record);

	List<AttendanceBrush> findAll();

	List<AttendanceBrush> findAttendanceBrushByOrgs(@Param("orgs") List<Org> orgs);

	AttendanceBrush findBrushByStaffAndMac(@Param("no") String no,@Param("mac") String mac,@Param("day") String day);

	List<AttendanceBrush> findAttendanceBrushByLikes(@Param("orgIds") List<Org> orgIds,@Param("staffId") String[] staffId,@Param("startTime") String startTime,@Param("endTime") String endTime);
	
	List<AttendanceSearch> findConts(@Param("orgs") List<Org> orgs);
	
}