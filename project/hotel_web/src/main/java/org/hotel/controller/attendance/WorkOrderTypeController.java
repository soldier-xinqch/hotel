package org.hotel.controller.attendance;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.hotel.common.BaseController;
import org.hotel.common.CommEnum.RESULTFLAG;
import org.hotel.model.Org;
import org.hotel.model.User;
import org.hotel.model.WorkOrderType;
import org.hotel.service.IOrgService;
import org.hotel.service.IWorkOrderTypeService;
import org.hotel.utils.ExcelEntity;
import org.hotel.utils.ExportExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * 班次类型
 * @author xinqch
 *
 */
@Controller
@RequestMapping("workOrderType")
public class WorkOrderTypeController extends BaseController<WorkOrderType>{

	private final String urlStr ="workOrderType";
	@Autowired
	private IWorkOrderTypeService workOrderTypeService;
	@Autowired
	private IOrgService orgService;
	@Autowired
	private CacheManager cacheManager;
	
	private List<Org> orgs;
	
	private User user;
	
	@RequestMapping(value="index",method=RequestMethod.GET)
	public String index(HttpServletRequest request){
		Cache cache = cacheManager.getCache("userCache");
		Element element =  cache.get("LoginUserKey");
		if(null  == element){
			Subject currentUser = SecurityUtils.getSubject();       
			currentUser.logout();
			return null;
		} 
		User user = (User) element.getValue();
		List<Org> orgs = orgService.findOrgListById(user.getOrgId());
		this.orgs =orgs; 
		this.user = user;
		request.setAttribute("orgs", orgs);
		request.setAttribute("menuKey", urlStr+"index");
		return "work/workOrderType";
	}
	
	@RequestMapping(value="/pageData",method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> pageDatas(HttpServletRequest request){
		Map<String,Object> map = Maps.newHashMap();
		String pq_curpage = request.getParameter("pq_curpage");
		String pq_rpp = request.getParameter("pq_rpp");
		PageHelper.startPage(Integer.valueOf(pq_curpage), Integer.valueOf(pq_rpp));
		List<WorkOrderType> workOrders = workOrderTypeService.findWorkOrdersByOrgs(orgs);
		PageInfo<WorkOrderType> baseInfos = new PageInfo<WorkOrderType>(workOrders);
		map.put("list", baseInfos.getList());
		map.put("pageSize", baseInfos.getPageSize());
		map.put("pageNo", baseInfos.getPageNum());
		map.put("total", baseInfos.getTotal());
		return map;
	}
	
	@RequestMapping(value="addWorkOrderType",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> addOrg(HttpServletRequest request,WorkOrderType workOrderType){
		Map<String,Object> map = Maps.newHashMap();
		dealStaffMsg(workOrderType);
		workOrderType.setCreateUser(user.getId());
		int isSuccess = workOrderTypeService.insert(workOrderType);
		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
		return map;
	}
	@RequestMapping(value="modifyWorkOrderType",method=RequestMethod.POST)
	public  @ResponseBody Map<String,Object> modifyOrg(HttpServletRequest request,WorkOrderType workOrderType){
		Map<String,Object> map = Maps.newHashMap();
		dealStaffMsg(workOrderType);
		workOrderType.setModifyUser(user.getId());
		int isSuccess = workOrderTypeService.modify(workOrderType);
		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
		return map;
	}
	@RequestMapping(value="deleteWorkOrderType",method=RequestMethod.POST)
	public  @ResponseBody Map<String,Object> deleteOrg(HttpServletRequest request){
		Map<String,Object> map = Maps.newHashMap();
		String id = request.getParameter("id");
		int isSuccess = workOrderTypeService.logicDelete(id);
		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
		return map;
	}
	private WorkOrderType dealStaffMsg(WorkOrderType workOrderType){
		String orgId = workOrderType.getOrgId();
		if(!StringUtils.isEmpty(orgId)){
			String[] idAndName = orgId.split("-");
			workOrderType.setOrgId(idAndName[0]);
			workOrderType.setOrgName(idAndName[1]);
		}
		return workOrderType;
	}
	
	@RequestMapping(value="exportWorkType")
	public @ResponseBody void exportExcels(HttpServletRequest request,HttpServletResponse response){
		try{
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"); 
	        response.setHeader("Content-Disposition", "attachment;filename="+ new String(("排班类型"+new Date()+".xlsx").getBytes(), "utf-8"));
	        
	        List<WorkOrderType> workOrders = workOrderTypeService.findWorkOrdersByOrgs(orgs);
	        String[] columnNames = {"orderName","orgName","orderDesc"};
	        String[] methodNames = {"getOrderName","getOrgName","getOrderDesc"};
	        // 生成ExcelEntity实体，包含4个必备参数
	        ExcelEntity<WorkOrderType> excelEntity = new ExcelEntity<WorkOrderType>(null, columnNames, methodNames, workOrders);
	        Workbook excel = ExportExcelUtils.export2Excel(excelEntity);
	        
	        ServletOutputStream outputStream = response.getOutputStream();
	        ExportExcelUtils.saveWorkBook2007(excel, excelEntity.getFileName(), outputStream);
//	        ExportExcelUtils.exportExcelX("123123213",headMap,ja,null,0,outputStream);
	        // 清除缓存
	        outputStream.flush();
	        outputStream.close();
	    }catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
