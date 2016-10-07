package org.hotel.controller.attendance;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hotel.model.AttendanceBrush;
import org.hotel.model.Org;
import org.hotel.model.User;
import org.hotel.service.IAttendanceBrushService;
import org.hotel.service.IOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
 * 考勤单记录
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
	
	private List<Org> orgs;
	
	@RequestMapping("index")
	public String index(HttpServletRequest request){
		Cache cache = cacheManager.getCache("userCache");
		Element element =  cache.get("LoginUserKey");
		User user = (User) element.getValue();
		List<Org> orgs = orgService.findOrgListById(user.getOrgId());
		this.orgs =orgs; 
		request.setAttribute("orgs", orgs);
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
}
