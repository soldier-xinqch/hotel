<%@page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/include/include.jsp"%>
	<div class="row">
		<div class='col-xs-6'>
			<div class="form-group">
				<label class="col-sm-4 control-label">员工姓名</label>
				<div class="col-sm-8">
					<input type="text" class="form-control input-xlarge" name="staffName"  >
					<input type="hidden" class="form-control input-xlarge" name="id"  >
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">员工性别</label>
				<div class="col-sm-8">
					<select class="form-control input-xlarge" name="sex" data-placeholder="请选择员工性别">
		        		<option value=""></option>
		        		<option value="1">男</option>
		        		<option value="0">女</option>
		        	</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">身份证号</label>
				<div class="col-sm-8">
					<input type="text" class="form-control input-xlarge" name="cardId"  >
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">生日日期</label>
				<div class="col-sm-8">
					<input type="text" class="form-control input-xlarge datepicker" name="birthday"  >
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">联系电话</label>
				<div class="col-sm-8">
					<input type="text" class="form-control input-xlarge" name="telphone"  >
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">联系邮箱</label>
				<div class="col-sm-8">
					<input type="text" class="form-control input-xlarge" name="email"  >
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">联系地址</label>
				<div class="col-sm-8">
					<textarea id="form-field-9" name="staffAddress" class="form-control limited input-xlarge" maxlength="50"></textarea>
				</div>
			</div>
		</div>
		<div class='col-xs-6'>
			<div class="form-group">
		       	<label class="col-sm-4 control-label">所属部门</label>
		       	<div class="col-sm-8">
		        	<select class="form-control input-xlarge" name="orgId" data-placeholder="请选择所属部门">
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
				<label class="col-sm-4 control-label">员工编号</label>
				<div class="col-sm-8">
					<input type="text" class="form-control input-xlarge" name="staffNo"  >
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">员工卡号</label>
				<div class="col-sm-8">
					<input type="text" class="form-control input-xlarge" name="staffCardNo"  >
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">入职时间</label>
				<div class="col-sm-8">
					<input type="text" class="form-control input-xlarge datepicker" name="intoTime"  >
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">员工备注</label>
				<div class="col-sm-8">
					<textarea id="form-field-9" name="ortherMemo" class="form-control limited input-xlarge" maxlength="50"></textarea>
				</div>
			</div>
		</div>
</div>	

