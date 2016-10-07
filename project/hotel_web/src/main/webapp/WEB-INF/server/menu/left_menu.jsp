<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include/include.jsp"%>
<!-- sidebar  BEGIN -->
<a class="menu-toggler" id="menu-toggler" href="#">
	<span class="menu-text"></span>
</a>
<div class="sidebar" id="sidebar">
	<script type="text/javascript">
		try {
			ace.settings.check('sidebar', 'fixed')
		} catch (e) {
		}
	</script>

	<div class="sidebar-shortcuts" id="sidebar-shortcuts">
		<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
			<button class="btn btn-success">
				<i class="icon-signal"></i>
			</button>

			<button class="btn btn-info">
				<i class="icon-pencil"></i>
			</button>

			<button class="btn btn-warning">
				<i class="icon-group"></i>
			</button>

			<button class="btn btn-danger">
				<i class="icon-cogs"></i>
			</button>
		</div>

		<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
			<span class="btn btn-success"></span> <span class="btn btn-info"></span>

			<span class="btn btn-warning"></span> <span class="btn btn-danger"></span>
		</div>
	</div>
	<!-- #sidebar-shortcuts -->

	<ul class="nav nav-list">
		<li class="${activeFlag =='homeindex'?'active':''}">
			<a href="/home/index"> <i class="fa fa-home"></i> <span class="menu-text"> 控制台</span>
			</a>
		</li>
		<c:if test="${null != menus}">
			<c:forEach items="${menus}" var="appMenu">
			     <li ${appMenu.id ==activeMenuFlag ?'class="active"':'' }>
						<a href="${appMenu.menuUrl}" class="${null!=appMenu.menuStyle?appMenu.menuStyle:''}">
							<i class="${appMenu.menuIcon }"></i>
							<span class="menu-text">${appMenu.menuName }</span>
							<c:if test="${appMenu.menuStatus == '3'}">
								<span class="badge badge-success">即将上线</span>
							</c:if>
							<c:if test="${appMenu.menuStatus == '1'}"><!-- new  -->
								<span class="badge badge-danger">new</span>
							</c:if>
							<c:if test="${null !=appMenu.menu&&fn:length(appMenu.menu)>0}"><!-- Next  -->
								<b class="arrow icon-angle-down"></b>
							</c:if>
						</a>
						<c:if test="${null !=appMenu.menu}">
							<ul class="submenu">
								<c:forEach items="${appMenu.menu}" var="sonMenu">
									<li class="${sonMenu.menuFlag == menuKey?'active':''}">
										<a  href="${sonMenu.menuUrl}" class="${null!=sonMenu.menuStyle?sonMenu.menuStyle:''}" >
											<i class="${sonMenu.menuIcon }"></i>
											${sonMenu.menuName }
											<c:if test="${null !=sonMenu.menu&&fn:length(sonMenu.menu)>0}"><!-- Next  -->
												<b class="arrow icon-angle-down"></b>
											</c:if>
										</a>
										<c:if test="${null !=sonMenu.menu}">
										<ul class="submenu">
											<c:forEach items="${sonMenu.menu}" var="thirdMenu">
												<li>
													<a href="${thirdMenu.menuUrl}" class="${null!=thirdMenu.menuStyle?thirdMenu.menuStyle:''}" >
														<i class="${thirdMenu.menuIcon }"></i>
														${thirdMenu.menuName }
														<c:if test="${null !=thirdMenu.menu&&fn:length(thirdMenu.menu)>0}"><!-- Next  -->
															<b class="arrow icon-angle-down"></b>
														</c:if>
													</a>
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
	</ul>
	<!-- /.nav-list -->
	<div class="sidebar-collapse" id="sidebar-collapse">
		<i class="icon-double-angle-left" data-icon1="icon-double-angle-left"
			data-icon2="icon-double-angle-right"></i>
	</div>
	<script type="text/javascript">
		try {
			ace.settings.check('sidebar', 'collapsed')
		} catch (e) {
		}
	</script>
</div>
<!-- sidebar END  -->