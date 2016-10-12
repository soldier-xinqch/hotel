package org.hotel.controller.attendance;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.hotel.common.CommEnum.STAFFSTATUS;
import org.hotel.model.AttendanceBrush;
import org.hotel.model.Org;
import org.hotel.model.Staff;
import org.hotel.model.User;
import org.hotel.service.IAttendanceBrushService;
import org.hotel.service.IOrgService;
import org.hotel.service.IStaffService;
import org.hotel.utils.ExportPdfUtil;
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
 * 刷卡记录
 * @author xinqch
 *
 */
@Controller
@RequestMapping("attendanceBrush")
public class AttendanceBrushController {

	private final String urlStr = "attendanceBrush";
	
	@Autowired
	private IAttendanceBrushService attendanceBrushService;
	@Autowired
	private CacheManager cacheManager;
	@Autowired
	private IOrgService orgService;
	@Autowired
	private IStaffService staffService;
	
	private List<Org> orgs;
	
	@RequestMapping("index")
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
		List<Staff> staffs = staffService.findStaffByOrgs(orgs,STAFFSTATUS.WORKING.getValue());
		this.orgs =orgs; 
		request.setAttribute("orgs", orgs);
		request.setAttribute("staffs", staffs);
		request.setAttribute("menuKey", urlStr+"index");
		return "work/attendancebrush";
	}
	
	@RequestMapping(value="/pageData",method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> pageDatas(HttpServletRequest request){
		Map<String,Object> map = Maps.newHashMap();
		String pq_curpage = request.getParameter("pq_curpage");
		String pq_rpp = request.getParameter("pq_rpp");
		PageHelper.startPage(Integer.valueOf(pq_curpage), Integer.valueOf(pq_rpp));
		List<AttendanceBrush> attendanceBrushs = attendanceBrushService.findAttendanceBrushByOrgs(orgs);
		PageInfo<AttendanceBrush> baseInfos = new PageInfo<AttendanceBrush>(attendanceBrushs);
		map.put("list", baseInfos.getList());
		map.put("pageSize", baseInfos.getPageSize());
		map.put("pageNo", baseInfos.getPageNum());
		map.put("total", baseInfos.getTotal());
		return map;
	}
	
	@RequestMapping(value="exportPdf",method=RequestMethod.GET)
	public @ResponseBody String exportPdf(HttpServletRequest request,HttpServletResponse response){
		try{
			response.setContentType("application/pdf;charset=utf-8");
	        response.addHeader("Content-Disposition", "attachment;filename=staff.pdf");
	        response.setCharacterEncoding("utf-8");
		    
	        String startTime = request.getParameter("startTime");
	        String endTime = request.getParameter("endTime");
	        String[] field = request.getParameterValues("field");
	        String[] staffId = request.getParameterValues("staffId");
	        String orgId = request.getParameter("orgId");
	        if(null == null){
	        	field = new String[]{"员工编号-StaffNo","员工姓名-StaffName","所属部门-OrgName","考勤日期-Time","刷卡1-Brush1","刷卡2-Brush2","刷卡3-Brush3","刷卡4-Brush4","刷卡5-Brush5","刷卡6-Brush6","刷卡7-Brush7","刷卡8-Brush8"};
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
	    	List<AttendanceBrush> attendanceBrushs = attendanceBrushService.findAttendanceBrushByLikes(orgIds,staffId,startTime,endTime);
	        ExportPdfUtil<AttendanceBrush> exportPdf = new ExportPdfUtil<AttendanceBrush>();
	        ServletOutputStream outputStream = response.getOutputStream();
	        exportPdf.exportPDF(columnNames, methodNames, attendanceBrushs, "员工刷卡信息", outputStream);
	        outputStream.flush();
	        outputStream.close();
	    }catch (Exception e) {
	        e.printStackTrace();
	    }
		return urlStr;
	}
}
