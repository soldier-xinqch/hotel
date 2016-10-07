<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include/include.jsp"%>

<div class="row">
 	<div class="form-group">
 		<label class="col-sm-3 control-label">类型名称</label>
       	<div class="col-sm-9">
       		<input type="text" class="form-control input-xlarge" name="orderName"  value="">
       		<input type="hidden" class="form-control input-xlarge" name="id"  value="">
       	</div>
       </div>
      	<div class="form-group">
       	<label class="col-sm-3 control-label">所属企业</label>
       	<div class="col-sm-9">
        	<select class="form-control input-xlarge"  name="orgId" data-placeholder="请选择上级菜单">
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
       	<label class="col-sm-3 control-label">类型描述</label>
       	<div class="col-sm-9">
       		<textarea id="form-field-9" name="orderDesc" class="form-control limited input-xlarge" maxlength="50"></textarea>
       	</div>
       </div>
</div>