package org.hotel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.hotel.model.AttendanceRecord;
import org.hotel.model.Org;

public interface AttendanceRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(AttendanceRecord record);

    int insertSelective(AttendanceRecord record);

    AttendanceRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AttendanceRecord record);

    int updateByPrimaryKey(AttendanceRecord record);

	List<AttendanceRecord> findAll();

	List<AttendanceRecord> findAttendanceRecordByOrgs(@Param("orgs") List<Org> orgs);
}