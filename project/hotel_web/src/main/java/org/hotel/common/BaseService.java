package org.hotel.common;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public interface BaseService<T> {
	
	public static final Byte deleteFlag_insert = 0;
	
	public static final Byte deleteFlag_del = 1;
	
	public static final Date dateInit = new Date(100000000L);
	
	/**
	 * 新增
	 * @param org
	 * @return
	 */
	int insert(T t);
	/**
	 *  修改
	 * @param org
	 * @return
	 */
	int modify(T t);
	/**
	 * 逻辑删除
	 * @param orgId
	 * @return
	 */
	int logicDelete(String id);
	/**
	 *  物理删除
	 * @param id
	 * @return
	 */
	int physicsDelete(String id);
}
