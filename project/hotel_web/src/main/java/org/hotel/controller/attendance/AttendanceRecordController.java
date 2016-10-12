package org.hotel.controller.attendance;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.hotel.common.CommEnum.RESULTFLAG;
import org.hotel.common.CommEnum.STAFFSTATUS;
import org.hotel.model.AttendanceRecord;
import org.hotel.model.AttendanceType;
import org.hotel.model.Org;
import org.hotel.model.Staff;
import org.hotel.model.User;
import org.hotel.service.IAttendanceRecordService;
import org.hotel.service.IAttendanceTypeService;
import org.hotel.service.IOrgService;
import org.hotel.service.IStaffService;
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
 * 考勤单记录
 * @author xinqch
 *
 */
@Controller
@RequestMapping("attendanceRecord")
public class AttendanceRecordController {

private final String urlStr = "attendanceRecord";
	
	@Autowired
	private IAttendanceRecordService attendanceRecordService;
	@Autowired
	private IOrgService orgService;
	@Autowired
	private IStaffService staffService;
	@Autowired
	private IAttendanceTypeService attendanceTypeService;
	@Autowired
	private CacheManager cacheManager;
	
	private List<Org> orgs;
	
	private User user;
	
	@RequestMapping("index")
	public String index(HttpServletRequest request){
		Cache cache = cacheManager.getCache("userCache");
		Element element = cache.get("LoginUserKey");
		if(null  == element){
			Subject currentUser = SecurityUtils.getSubject();       
			currentUser.logout();
			return null;
		} 
		User user = (User) element.getValue();
		List<Org> orgs = orgService.findOrgListById(user.getOrgId());
		List<Staff> staffs = staffService.findStaffByOrgs(orgs,STAFFSTATUS.WORKING.getValue());
		this.orgs = orgs;
		request.setAttribute("staffs", staffs);
		request.setAttribute("orgs", orgs);
		request.setAttribute("menuKey", urlStr + "index");
		return "work/attendanceRecord";
	}
	
	@RequestMapping(value="/pageData",method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> pageDatas(HttpServletRequest request){
		Map<String,Object> map = Maps.newHashMap();
		String pq_curpage = request.getParameter("pq_curpage");
		String pq_rpp = request.getParameter("pq_rpp");
		PageHelper.startPage(Integer.valueOf(pq_curpage), Integer.valueOf(pq_rpp));
		List<AttendanceRecord> attendanceRecords = attendanceRecordService.findAttendanceRecordByOrgs(orgs);
		PageInfo<AttendanceRecord> baseInfos = new PageInfo<AttendanceRecord>(attendanceRecords);
		map.put("list", baseInfos.getList());
		map.put("pageSize", baseInfos.getPageSize());
		map.put("pageNo", baseInfos.getPageNum());
		map.put("total", baseInfos.getTotal());
		return map;
	}
	
	@RequestMapping(value="addAttendance",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> addOrg(HttpServletRequest request,AttendanceRecord attendance){
		Map<String,Object> map = Maps.newHashMap();
		dealMsg(attendance);
		attendance.setCreateUser(user.getId());
		int isSuccess = attendanceRecordService.insert(attendance);
		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
		return map;
	}
	@RequestMapping(value="modifyAttendance",method=RequestMethod.POST)
	public  @ResponseBody Map<String,Object> modifyOrg(HttpServletRequest request,AttendanceRecord attendance){
		Map<String,Object> map = Maps.newHashMap();
		dealMsg(attendance);
		attendance.setModifyId(user.getId());
		int isSuccess = attendanceRecordService.modify(attendance);
		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
		return map;
	}
	@RequestMapping(value="deleteAttendance",method=RequestMethod.POST)
	public  @ResponseBody Map<String,Object> deleteOrg(HttpServletRequest request){
		Map<String,Object> map = Maps.newHashMap();
		String id = request.getParameter("id");
		int isSuccess = attendanceRecordService.logicDelete(id);
		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
		return map;
	}
	private AttendanceRecord dealMsg(AttendanceRecord attendance){
		String orgId = attendance.getOrgId();
		if(!StringUtils.isEmpty(orgId)){
			String[] idAndName = orgId.split("-");
			attendance.setOrgId(idAndName[0]);
			attendance.setOrgName(idAndName[1]);
		}
		String staffId = attendance.getStaffId();
		if(!StringUtils.isEmpty(orgId)){
			String[] idAndName = staffId.split("-");
			attendance.setStaffId(idAndName[0]);
			attendance.setStaffName(idAndName[1]);
		}
		String typeId = attendance.getAttendanceTypeId();
		if(!StringUtils.isEmpty(orgId)){
			String[] idAndName = typeId.split("-");
			attendance.setAttendanceTypeId(idAndName[0]);
			attendance.setAttendanceTypeName(idAndName[1]);
		}
		return attendance;
	}
	
	@RequestMapping(value="exportExcel",method=RequestMethod.GET)
	public @ResponseBody String exportExcels(HttpServletRequest request,HttpServletResponse response){
		try{
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"); 
	        response.setHeader("Content-Disposition", "attachment;filename="+ new String(("staff" + ".xlsx").getBytes(), "utf-8"));
//	        String startTime = request.getParameter("startTime");
//	        String endTime = request.getParameter("endTime");
	        String[] field = request.getParameterValues("field");
	        String[] staffId = request.getParameterValues("staffId");
	        String orgId = request.getParameter("orgId");
	        if(null == null){
	        	field = new String[]{"员工编号-StaffNo","员工姓名-StaffName","所属部门-OrgName","工作日期-WorkTime","休息日期-RestTime","考勤类型-AttendanceTypeName","时间（小时）-Num"};
	        }
	        
	        String[] columnNames = new String[field.length];
	        String[] methodNames = new String[field.length];
	        for (int i = 0; i < field.length; i++) {
				String[] temps = field[i].split("-");
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
	        List<AttendanceRecord> attendanceRecords = attendanceRecordService.findAttendanceRecordByLikes(orgs,staffId);
	        // 生成ExcelEntity实体，包含4个必备参数
	        ExcelEntity<AttendanceRecord> excelEntity = new ExcelEntity<AttendanceRecord>("", columnNames, methodNames, attendanceRecords);
	        excelEntity.setHeader("考勤单信息");
	        Workbook excel = ExportExcelUtils.export2Excel(excelEntity);
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
