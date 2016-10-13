<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include/include.jsp"%>

<div class="row">
	<div class="form-group">
 		<label class="col-sm-3 control-label">用户名称</label>
       	<div class="col-sm-9">
       		<input type="text" class="form-control input-xlarge" name="userName"  value="" disabled="disabled">
       		<input type="hidden" class="form-control input-xlarge" name="id"  value="">
       	</div>
       </div>
      	<div class="form-group">
       	<label class="col-sm-3 control-label">角色授权</label>
       	<div class="col-sm-9">
        	<select class="form-control input-xlarge" name="roleDesc" multiple="multiple" data-placeholder="请选择角色">
        		<!-- <option data-value='' value=""></option> -->
        		<c:if test="${null != roles}">
					<c:forEach items="${roles}" var="role">
				    	 <option value="${role.id}">${role.roleName}</option>
					</c:forEach>
				</c:if>
        	</select>
      		</div>
      	</div>
</div>