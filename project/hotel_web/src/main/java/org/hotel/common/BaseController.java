package org.hotel.common;

import java.util.List;

import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class BaseController<T> {
	
	public static <T> PageInfo<T> pageMethod(List<T> list,int pageNo,int pageSize,String orderByField){
		PageHelper.startPage(pageNo,pageSize);
		if(!StringUtils.isEmpty(orderByField)){
			PageHelper.orderBy(orderByField);
		}
		PageInfo<T> pageInfo = new PageInfo<T>(list);
//		PageEntity<T> entitys = new PageEntity<T>();
//		entitys.setPageNo(pageNo);
//		entitys.setPageSize(pageSize);
//		entitys.setPageDatas(pageInfo.getList());
//		entitys.setTotalRecord(pageInfo.getTotal());
		return pageInfo;
	}

}