<%@page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/include/include.jsp"%>
	<div class="row">
		<form id="add_tab_from" class="form-horizontal" role="form">
			<div class='col-xs-6'>
				<div class="form-group">
					<label class="col-sm-3 control-label">菜单名称</label>
					<div class="col-sm-9">
						<input type="text" class="form-control input-xlarge" name="staffName"  value="">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">菜单图标</label>
					<div class="col-sm-9">
						<input type="text" class="form-control input-xlarge" name="sex"  value="">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">菜单链接</label>
					<div class="col-sm-9">
						<input type="text" class="form-control input-xlarge" name="cardId" value="" >
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">菜单级别</label>
					<div class="col-sm-9">
						<input type="text" class="form-control input-xlarge" name="birthday"  value="">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">菜单状态</label>
					<div class="col-sm-9">
						<input type="text" class="form-control input-xlarge" name="telphone"  value="">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">联系邮箱</label>
					<div class="col-sm-9">
						<input type="text" class="form-control input-xlarge" name="email"  value="">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">菜单样式</label>
					<div class="col-sm-9">
						<textarea id="form-field-9" name="staffAddress" class="form-control limited input-xlarge" maxlength="50"></textarea>
					</div>
				</div>
			</div>
			<div class='col-xs-6'>
					<div class="form-group">
			       	<label class="col-sm-3 control-label">所属组织</label>
			       	<div class="col-sm-9">
			        	<select class="form-control input-xlarge" name="orgId" data-placeholder="请选择上级组织">
			        		<option data-value='' value="">&nbsp;</option>
			        		<c:if test="${null != orgs}">
								<c:forEach items="${orgs}" var="org">
							    	 <option value="${org.id}-${org.orgName}">${org.orgName}</option>
								</c:forEach>
							</c:if>
			        	</select>
			      		</div>
			      	</div>
			      <div class="form-group">
					<label class="col-sm-3 control-label">员工编号</label>
					<div class="col-sm-9">
						<input type="text" class="form-control input-xlarge" name="staffNo"  >
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">员工卡号</label>
					<div class="col-sm-9">
						<input type="text" class="form-control input-xlarge" name="staffCardNo"  >
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">入职时间</label>
					<div class="col-sm-9">
						<input type="text" class="form-control input-xlarge" name="intoTime"  >
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">员工备注</label>
					<div class="col-sm-9">
						<textarea id="form-field-9" name="ortherMemo" class="form-control limited input-xlarge" maxlength="50"></textarea>
					</div>
				</div>
			</div>
		</form>
</div>	
