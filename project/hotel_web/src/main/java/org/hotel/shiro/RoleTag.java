package org.hotel.shiro;

import javax.servlet.jsp.tagext.TagSupport;

/**
 *  权限标签类
 * @author xinqch
 *
 */
public class RoleTag extends TagSupport  {
	
	private static final long serialVersionUID = 1L;
//	private String privilege; //标签属性
//	@Override
//    public int doStartTag() {
//        User user =  AuthUtil.getCurrentUser();//获取登录用户信息
//        if(user == null) return SKIP_BODY;
//        if (isManager(user)) return EVAL_BODY_INCLUDE;  //超级管理员获取所有权限
//        boolean bResult = SecurityUtils.getSubject().isPermitted(privilege);//根据标签属性判断用户是否有此菜单功能权限，isPermitted的调用会触发doGetAuthorizationInfo方法
//        if(bResult){
//            return EVAL_BODY_INCLUDE;
//        }
//        return SKIP_BODY;
//    }
//
//
//    /**
//     * 判断用户是否超级管理员
//     * @return
//     */
//    private boolean isManager(User user){
//        List<Role> roles = user.getRoles();
//        boolean b = false ;
//        for (Role role : roles) { //遍历是否有超级管理员角色
//            if (role.getIsManager() == Constants.MANAGER_CODE) {
//                b = true ;
//                break ;
//            }
//        }
//        String accountName = user.getAccountName();
//        if (accountName.equals(Constants.ADMIN_ACCOUNT) 
//                || accountName.equals(Constants.SYSADMIN_ACCOUNT) 
//                || b) {
//            return true;
//        }
//        return false;
//
//    }
//
//    public String getPrivilege() {
//        return privilege;
//    }
//
//    public void setPrivilege(String privilege) {
//        this.privilege = privilege;
//    }

}
