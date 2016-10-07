package org.hotel.mapper;

import java.util.List;

import org.hotel.model.AttendanceType;

public interface AttendanceTypeMapper {
    int deleteByPrimaryKey(String id);

    int insert(AttendanceType record);

    int insertSelective(AttendanceType record);

    AttendanceType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AttendanceType record);

    int updateByPrimaryKey(AttendanceType record);

	List<AttendanceType> findAll();

}