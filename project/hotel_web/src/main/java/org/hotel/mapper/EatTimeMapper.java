package org.hotel.mapper;

import java.util.List;

import org.hotel.model.EatTime;

public interface EatTimeMapper {
    int deleteByPrimaryKey(String id);

    int insertSelective(EatTime record);

    EatTime selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EatTime record);

	List<EatTime> findEatTimeByOrgId(String orgId);

	List<EatTime> findAll();
}