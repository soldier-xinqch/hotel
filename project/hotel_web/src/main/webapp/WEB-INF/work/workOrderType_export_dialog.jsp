<%@page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/include/include.jsp"%>
<div class="row">
	<div class="form-group">
     	<label class="col-sm-4 control-label">所属组织</label>
     	<div class="col-sm-8">
      	<select class="form-control input-xlarge" name="orgId" data-placeholder="请选择上级组织">
      		<option ></option>
      		<c:if test="${null != orgs}">
			<c:forEach items="${orgs}" var="org">
		    	 <option value="${org.id}-${org.orgName}">${org.orgName}</option>
			</c:forEach>
		</c:if>
      	</select>
   		</div>
   	</div>
</div>	

