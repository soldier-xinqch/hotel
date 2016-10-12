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
       <div>
       		<c:if test="${null != menuList}">
       			<c:forEach items="${menuList}" var="appMenu">
   					<div class="list-group-item" style="height: 50px;">
       					<div class="left">
       						<input type="checkbox" id="${appMenu.id}" name=""  value="" />
			 				<label class="control-label"> ${appMenu.menuName }</label>
       					</div>
			       		<div class="right">
			       			<input type="checkbox" id="" value="ADD" />
				 			<label class="control-label"> 增加</label>
				 			<input type="checkbox" id="" value="MODIFY" />
				 			<label class="control-label"> 修改</label>
				 			<input type="checkbox" id="" value="DELETE" />
				 			<label class="control-label"> 删除</label>
				 			<input type="checkbox" id="" value="SEARCH" />
				 			<label class="control-label"> 查询</label>
				 			<input type="checkbox" id="" value="EXPORT" />
				 			<label class="control-label"> 导出</label>
			       		</div>
		        	</div>
       				<c:if test="${null !=appMenu.menu}">
   						<c:forEach items="${appMenu.menu}" var="sonMenu">
		       				<div class="list-group-item" style="height: 50px;margin-left: 30px;">
					 			<div class="left">
		       						<input type="checkbox" id="${sonMenu.id}" name="" value="" />
					 				<label class="control-label"> ${sonMenu.menuName }</label>
		       					</div>
					       		<div class="right">
					       			<input type="checkbox" id="" value="ADD" />
						 			<label class="control-label"> 增加</label>
						 			<input type="checkbox" id="" value="MODIFY" />
						 			<label class="control-label"> 修改</label>
						 			<input type="checkbox" id="" value="DELETE" />
						 			<label class="control-label"> 删除</label>
						 			<input type="checkbox" id="" value="SEARCH" />
						 			<label class="control-label"> 查询</label>
						 			<input type="checkbox" id="" value="EXPORT" />
						 			<label class="control-label"> 导出</label>
					       		</div>
					        </div>
		       					<c:if test="${null !=sonMenu.menu}">
		       						<c:forEach items="${sonMenu.menu}" var="thirdMenu">
			       						<div class="list-group-item" style="height: 50px;margin-left: 30px;">
								 			<div class="left">
					       						<input type="checkbox" id="${thirdMenu.id}" name="" value="" />
								 				<label class="control-label"> ${thirdMenu.menuName }</label>
					       					</div>
								       		<div class="right">
								       			<input type="checkbox" id="" value="ADD" />
									 			<label class="control-label"> 增加</label>
									 			<input type="checkbox" id="" value="MODIFY" />
									 			<label class="control-label"> 修改</label>
									 			<input type="checkbox" id="" value="DELETE" />
									 			<label class="control-label"> 删除</label>
									 			<input type="checkbox" id="" value="SEARCH" />
									 			<label class="control-label"> 查询</label>
									 			<input type="checkbox" id="" value="EXPORT" />
									 			<label class="control-label"> 导出</label>
								       		</div>
								        </div>
									</c:forEach>
								</c:if>
       					</c:forEach>
					</c:if>
       			</c:forEach>
       		</c:if>
       </div>
       
       <%-- <ul class="nav ">
		<c:if test="${null != menuList}">
			<c:forEach items="${menuList}" var="appMenu">
			     <li ${appMenu.id ==activeMenuFlag ?'class="active"':'' }>
			     			<input type="checkbox" id="" value="" />
							<span class="menu-text">${appMenu.menuName }</span>
						<c:if test="${null !=appMenu.menu}">
							<ul class="submenu">
								<c:forEach items="${appMenu.menu}" var="sonMenu">
									<li class="${sonMenu.menuFlag == menuKey?'active':''}">
											${sonMenu.menuName }
										<c:if test="${null !=sonMenu.menu}">
										<ul class="submenu">
											<c:forEach items="${sonMenu.menu}" var="thirdMenu">
												<li>
														${thirdMenu.menuName }
												</li>
											</c:forEach>
										</ul>
										</c:if>
									</li>
								</c:forEach>
							</ul>
						</c:if>
				 </li>
			</c:forEach>
		</c:if>
	</ul> --%>
</div>
