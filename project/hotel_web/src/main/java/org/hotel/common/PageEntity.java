package org.hotel.common;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * 数据分页相关字段
 * 
 * @author xinqch
 *
 */
public class PageEntity<T>{
	
	public static Integer DEFAULT_PAGESIZE = 10;
	private int pageNo; // 当前页码
	private int pageSize; // 每页行数
	private long totalRecord; // 总记录数
	private long totalPage; // 总页数
	private int startPage;
	private int endPage;
	
    List<T> pageDatas = Lists.newArrayList();//分页数据集合

//    /**
//     * 根据当前显示页与每页显示记录数设置查询信息初始对象
//     * @param page 当前显示页号
//     * @param pageSize 当前页显示记录条数
//     */
//    public PageEntity(int page, int pageSize) {
//        this.pageNo = (page <= 0) ? 1 : page;
//        this.pageSize = (pageSize <= 0) ? DEFAULT_PAGESIZE : pageSize;
//    }
//    
//    public PageEntity(int pageNo, int pageSize,int pageTarget) {
//    	if(pageNo == pageTarget){
//    		this.pageNo = (pageNo <= 0) ? 1 : pageNo;
//    	}else{
//    		this.pageNo = (pageTarget <= 0) ? 1 : pageTarget;
//    	}
//        this.pageSize = (pageSize <= 0) ? DEFAULT_PAGESIZE : pageSize;
//    }

	/**
	 * 获取当前页号
	 * @return
	 */
	public int getPageNo() {
		return pageNo;
	}
	/**
     * 设置当前页号
     * @param page 当前页号
     */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	/**
     * 取得当前显示页号最多显示条数
     * @return 当前显示页号最多显示条数
     */
	public int getPageSize() {
		return (pageSize <= 0) ? DEFAULT_PAGESIZE : pageSize;
	}
	/**
     * 设置当前页显示记录条数
     * @param pageSize 当前页显示记录条数
     */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * 取得查询取得记录总数
	 * @return 取得查询取得记录总数
	 */
	public long getTotalRecord() {
		return totalRecord;
	}
	/**
     * 设置查询取得记录总数
     * @param count 查询取得记录总数
     */
	public void setTotalRecord(long l) {
		this.totalRecord = (l < 0) ? 0 : l;
        if (this.totalRecord == 0) {
            this.pageNo = 0;
            return;
        }
	}

	/**
	 *  获取总页数
	 * @return
	 */
	public long getTotalPage() {
		return totalPage;
	}

	/**
	 * 设置总页数
	 * @param totalPage
	 */
	public void setTotalPage() {
		if(this.totalRecord <0){
			this.totalPage = 0;
			return ;
		}
		if(totalRecord%pageSize < 1){
			this.totalPage = totalRecord%pageSize;
		}else{
			this.totalPage = totalRecord%pageSize+1;
			
		}
	}

	public List<T> getPageDatas() {
		return pageDatas;
	}

	public void setPageDatas(List<T> pageDatas) {
		this.pageDatas = pageDatas;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
}
