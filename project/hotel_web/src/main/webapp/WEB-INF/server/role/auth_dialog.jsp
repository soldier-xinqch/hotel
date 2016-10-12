<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include/include.jsp"%>

<div class="row">
	<div class="form-group">
 		<label class="col-sm-3 control-label">角色名称</label>
       	<div class="col-sm-9">
       		<input type="text" class="form-control input-xlarge" name="roleName"  value="" disabled="disabled">
       		<input type="hidden" class="form-control input-xlarge" name="roleId"  value="" disabled="disabled">
       		<input type="hidden" class="form-control input-xlarge" name="authId"  value="" disabled="disabled">
       	</div>
       </div>
      	<div class="form-group">
 		<label class="col-sm-3 control-label">菜单授权</label>
	       	<div class="col-sm-9">
	       		<select class="form-control input-xlarge" multiple="multiple" name="authElement" data-placeholder="请选择菜单">
	        		<option value=""></option>
	        		<c:if test="${null != menuList}">
						<c:forEach items="${menuList}" var="menu">
					    	 <option value="${menu.id}">${menu.menuName}</option>
						</c:forEach>
					</c:if>
	        	</select>
	       	</div>
       </div>
       
</div>
