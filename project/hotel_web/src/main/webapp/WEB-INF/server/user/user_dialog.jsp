<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include/include.jsp"%>

<div class="row">
	<div class="form-group">
 		<label class="col-sm-3 control-label">用户名</label>
       	<div class="col-sm-9">
       		<input type="text" class="form-control input-xlarge" name="username"  value="">
       	</div>
       </div>
       	<div class="form-group oldPasswd">
 		<label class="col-sm-3 control-label">原密码</label>
       	<div class="col-sm-9">
       		<!-- <p><input id="password" class="form-control input-xlarge" type="password" value="123" placeholder="password" /></p> -->
       		<input type="password" data-toggle="password"  class="form-control input-xlarge" name="olPpassword"  value="">
       	</div>
       </div>
	<div class="form-group">
 		<label class="col-sm-3 control-label" id="newPasswd">密码</label>
       	<div class="col-sm-9">
       		<!-- <p><input id="password" class="form-control input-xlarge" type="password" value="123" placeholder="password" /></p> -->
       		<input type="password" data-toggle="password" id="password" class="form-control input-xlarge" name="password"  value="">
       	</div>
       </div>
      	<div class="form-group">
       	<label class="col-sm-3 control-label">所属部门</label>
       	<div class="col-sm-9">
        	<select class="form-control input-xlarge" name="orgId" data-placeholder="请选择用户所属部门">
        		<option value=""></option>
        		<c:if test="${null != orgs}">
					<c:forEach items="${orgs}" var="org">
				    	 <option value="${org.id}-${org.orgName}">${org.orgName}</option>
					</c:forEach>
				</c:if>
        	</select>
      		</div>
      	</div>
 		<div class="form-group">
 		<label class="col-sm-3 control-label">用户昵称</label>
       	<div class="col-sm-9">
       		<input type="text" class="form-control input-xlarge" name="nickName"  value="">
       		<input type="hidden" class="form-control input-xlarge" name="id"  value="">
       	</div>
       </div>
       <div class="form-group">
 		<label class="col-sm-3 control-label">真实姓名</label>
       	<div class="col-sm-9">
       		<input type="text" class="form-control input-xlarge" name="realName"  value="">
       	</div>
       </div>
       <div class="form-group">
 		<label class="col-sm-3 control-label">用户邮箱</label>
       	<div class="col-sm-9">
       		<input type="text" class="form-control input-xlarge" name="email"  value="">
       	</div>
       </div>
       <div class="form-group">
 		<label class="col-sm-3 control-label">用户手机</label>
       	<div class="col-sm-9">
       		<input type="text" class="form-control input-xlarge" name="telphone"  value="">
       	</div>
       </div>
       <div class="form-group">
 		<label class="col-sm-3 control-label">固定电话</label>
       	<div class="col-sm-9">
       		<input type="text" class="form-control input-xlarge" name="fixedTelphone"  value="">
       	</div>
       </div>
</div>