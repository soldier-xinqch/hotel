<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include/include.jsp"%>

<div class="row">
	<div class="form-group">
 		<label class="col-sm-3 control-label">用户名称</label>
       	<div class="col-sm-9">
       		<input type="text" class="form-control input-xlarge" name="userName"  value="">
       		<input type="hidden" class="form-control input-xlarge" name="id"  value="">
       	</div>
       </div>
 		<div class="form-group">
 		<label class="col-sm-3 control-label">初始密码</label>
       	<div class="col-sm-9">
       		<input type="password" class="form-control input-xlarge" name="oldPasswd"  value="">
       		<input type="hidden" class="form-control input-xlarge" name="id"  value="">
       	</div>
       </div>
       <div class="form-group">
 		<label class="col-sm-3 control-label">新密码</label>
       	<div class="col-sm-9">
       		<input type="password" class="form-control input-xlarge" name="newPasswd"  value="">
       	</div>
       </div>
       <div class="form-group">
 		<label class="col-sm-3 control-label">验证输入</label>
       	<div class="col-sm-9">
       		<input type="password" class="form-control input-xlarge" name="rNewPasswd"  value="">
       	</div>
       </div>
</div>