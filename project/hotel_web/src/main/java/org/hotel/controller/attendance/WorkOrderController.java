package org.hotel.controller.attendance;

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
import org.hotel.model.WorkOrder;
import org.hotel.model.WorkOrderType;
import org.hotel.service.IOrgService;
import org.hotel.service.IWorkOrderService;
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
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * 开餐时间
 * @author xinqch
 *
 */
@Controller
@RequestMapping("workOrder")
public class WorkOrderController extends BaseController<WorkOrder>{

	private final String urlStr ="workOrder";
	@Autowired
	private IWorkOrderService workOrderService;
	@Autowired
	private IOrgService orgService;
	@Autowired
	private CacheManager cacheManager;
	@Autowired
	private IWorkOrderTypeService workOrderTypeService;
	
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
		this.user =user; 
		List<WorkOrderType> workOrders = workOrderTypeService.findWorkOrdersByOrgs(orgs);
		request.setAttribute("orgs", orgs);
		request.setAttribute("workOrders", workOrders);
		request.setAttribute("menuKey", urlStr+"index");
		return "work/workOrder";
	}
	
	@RequestMapping(value="/pageData",method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> pageDatas(HttpServletRequest request){
		Map<String,Object> map = Maps.newHashMap();
		String pq_curpage = request.getParameter("pq_curpage");
		String pq_rpp = request.getParameter("pq_rpp");
		PageHelper.startPage(Integer.valueOf(pq_curpage), Integer.valueOf(pq_rpp));
		List<WorkOrder> workOrders = workOrderService.findWorkOrdersByOrgs(orgs);
		PageInfo<WorkOrder> baseInfos = new PageInfo<WorkOrder>(workOrders);
		map.put("list", baseInfos.getList());
		map.put("pageSize", baseInfos.getPageSize());
		map.put("pageNo", baseInfos.getPageNum());
		map.put("total", baseInfos.getTotal());
		return map;
	}
	
	@RequestMapping(value="addWorkOrder",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> addOrg(HttpServletRequest request,WorkOrder workOrder){
		Map<String,Object> map = Maps.newHashMap();
		try {
			dealMsg(workOrder);
			workOrder.setCreateUser(user.getId());
			int isSuccess = workOrderService.insert(workOrder);
			map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return map;
	}
	@RequestMapping(value="modifyWorkOrder",method=RequestMethod.POST)
	public  @ResponseBody Map<String,Object> modifyOrg(HttpServletRequest request,WorkOrder workOrder){
		Map<String,Object> map = Maps.newHashMap();
		dealMsg(workOrder);
		workOrder.setModifyUser(user.getId());
		int isSuccess = workOrderService.modify(workOrder);
		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
		return map;
	}
	@RequestMapping(value="deleteWorkOrder",method=RequestMethod.POST)
	public  @ResponseBody Map<String,Object> deleteOrg(HttpServletRequest request){
		Map<String,Object> map = Maps.newHashMap();
		String id = request.getParameter("id");
		int isSuccess = workOrderService.logicDelete(id);
		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
		return map;
	}
	private WorkOrder dealMsg(WorkOrder workOrder){
		String orgId = workOrder.getOrgId();
		if(!StringUtils.isEmpty(orgId)){
			String[] idAndName = orgId.split("-");
			workOrder.setOrgId(idAndName[0]);
			workOrder.setOrgName(idAndName[1]);
		}
		String workType = workOrder.getWorkType();
		if(!StringUtils.isEmpty(workType)){
			String[] idAndName = workType.split("-");
			workOrder.setWorkType(idAndName[0]);
			workOrder.setWorkTypeName(idAndName[1]);
		}
		return workOrder;
	}
	
	@RequestMapping(value="exportExcel",method=RequestMethod.GET)
	public @ResponseBody String exportExcels(HttpServletRequest request,HttpServletResponse response){
		try{
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"); 
	        response.setHeader("Content-Disposition", "attachment;filename="+ new String(("staff" + ".xlsx").getBytes(), "utf-8"));
	        String orgId = request.getParameter("orgId");
        	String[] staffField = new String[]{"班次编号-OrderNo","班次类型-WorkTypeName","所属部门-OrgName","上班起始-OnStart","上班时间-BeginWork","迟到终止-OnEnd",
        			"早退起始-OffStart","下班时间-EndWork","下班终止-OffEnd","下1时间-OffOtherTime","上2时间-OnOtherTime","工时-WorkTime","早餐-Breakfast",
        			"午餐-Lunch","晚餐-Dinner","夜餐-NightEating","次日餐次-TomorrowEatNum"};
	        String[] columnNames = new String[staffField.length];
	        String[] methodNames = new String[staffField.length];
	        for (int i = 0; i < staffField.length; i++) {
				String[] temps = staffField[i].split("-");
				columnNames[i] = temps[0];
				methodNames[i] = "get"+temps[1];
			}
	        List<Org> orgIds = null;
	        if(!StringUtils.isEmpty(orgIds)){
	        	orgIds = Lists.newArrayList();
	        	Org org = new Org();
	        	org.setId(orgId);
	        }else{
	        	orgIds = orgs;
	        }
	        List<WorkOrder> workOrders = workOrderService.findWorkOrdersByOrgs(orgs);
	        // 生成ExcelEntity实体，包含4个必备参数
	        ExcelEntity<WorkOrder> excelEntity = new ExcelEntity<WorkOrder>("", columnNames, methodNames, workOrders);
	        excelEntity.setHeader("员工信息");
	        //excelEntity.setFooter("脚注");
	        Workbook excel = ExportExcelUtils.export2Excel(excelEntity);
	        //ExcelExporter.export2Excel("题头","脚注", "sheet1", columnNames, methodNames, winds);//也可以这样调用,无需新建ExcelEntity对象
	        //将Workbook存为文件
//	        ExportExcelUtils.saveWorkBook2007(excel, excelEntity.getFileName());
	        ServletOutputStream outputStream = response.getOutputStream();
	        ExportExcelUtils.saveWorkBook2007(excel, excelEntity.getFileName(), outputStream);
	        outputStream.flush();
	        outputStream.close();
	    }catch (Exception e) {
	        e.printStackTrace();
	    }
		return urlStr;
	}
	
}