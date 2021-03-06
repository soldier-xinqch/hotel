﻿﻿    $(function () {
//	var data = [{
//        text: "p1",
//        nodes: [
//                { text: "p1-1", id: '00001', nodeId: '00001' },
//                { text: "p1-2", id: '00002' }, { text: "p1-3", id: '00003' }, 
//                { text: "p1-4", id: '00004', 
//                	nodes: [
//                	        { text: 'p1-1-1', id: '00005'}
//                	]}]
//        }];
//	var data = [{"id":"1a7095fe75174e34bfa7f0c9f1af4cfa","orgId":null,"parentId":"7eebbf04af9043d18fa50c6fbb1e59f1","orgFlag":"11","orgName":"1","orgDesc":"12","orgNo":null,"roleDesc":null,"realName":null,"telphone":null,"fixedTelephone":null,"email":null,"createUser":null,"createTime":1474782296000,"modifyUser":null,"modifyTime":100000000,"delFlag":0,"orgs":[{"id":"21ebccd842e74927aae420c87649b517","orgId":null,"parentId":"7eebbf04af9043d18fa50c6fbb1e59f1","orgFlag":"HITE","orgName":"楂樼骇绠＄悊閮?","orgDesc":"楂樼骇绠＄悊閮?","orgNo":null,"roleDesc":null,"realName":null,"telphone":null,"fixedTelephone":null,"email":null,"createUser":null,"createTime":1474779789000,"modifyUser":null,"modifyTime":100000000,"delFlag":0},{"id":"21ebccd842e74927aae420c87649b517","orgId":null,"parentId":"7eebbf04af9043d18fa50c6fbb1e59f1","orgFlag":"HITE","orgName":"楂樼骇绠＄悊閮?","orgDesc":"楂樼骇绠＄悊閮?","orgNo":null,"roleDesc":null,"realName":null,"telphone":null,"fixedTelephone":null,"email":null,"createUser":null,"createTime":1474779789000,"modifyUser":null,"modifyTime":100000000,"delFlag":0}]}];
//	var options = {
//      bootstrap2: false, 
//      showTags: true,
//      levels: 5,
//      data: data
//    };
//	$('#tree').treeview(options);  		
  		
  		
  		
  		
    	//首先定义表格选项
	var tabId = $("#org_grid");
	var dataModel = {
			 	cache: true,
//    	            recIndx: "customerid",
//    	            sortIndx: "contactname",
//    			 	sortDir: "up",
	            location: "remote",
	            sorting: "local",            
	            dataType: "JSON",
	            method: "GET",
	            url: "pageData",
	            getData: function (dataJSON) {  
	                return { curPage: dataJSON.pageNo, totalRecords: dataJSON.pageSize,data: dataJSON.list };
	            }
	        };
	var colModel = [
	                { title: "员工姓名",dataIndx:"orgName", width: '6%', dataType: "string", align: "center" },
	        		{ title: "部门名称",dataIndx:"parentId", width: '6%', dataType: "string", align: "center"},
	        		{ title: "日期",dataIndx:"orgNo", width: '8%', dataType: "string", align: "center" },
	        		{ title: "刷卡1",dataIndx:"orgDesc", width: '4%', dataType: "string", align: "center" },
	        		{ title: "早餐",dataIndx:"createTime", width: '8%', dataType: "string", align: "center" },
	        		{ title: "早餐金额",dataIndx:"createUser", width: '4%', dataType: "string", align: "center" },
	        		{ title: "刷卡2",dataIndx:"orgName", width: '8%', dataType: "string", align: "center" },
	        		{ title: "午餐",dataIndx:"parentId", width: '8%', dataType: "string", align: "center"},
	        		{ title: "午餐金额",dataIndx:"orgNo", width: '4%', dataType: "string", align: "center" },
	        		{ title: "刷卡3",dataIndx:"orgDesc", width: '8%', dataType: "string", align: "center" },
	        		{ title: "晚餐",dataIndx:"createTime", width: '8%', dataType: "string", align: "center" },
	        		{ title: "晚餐金额",dataIndx:"createUser", width: '4%', dataType: "string", align: "center" },
	        		{ title: "刷卡4",dataIndx:"createUser", width: '8%', dataType: "string", align: "center" },
	        		{ title: "夜餐",dataIndx:"createUser", width: '8%', dataType: "string", align: "center" },
	        		{ title: "夜餐金额",dataIndx:"createUser", width: '4%', dataType: "string", align: "center" },
	        	];
//	var tab_toolbar = {
//            items: [
//                { type: 'button', label: '添加', listeners: [{ click: tab_addRow}], cls: 'btn btn-sm btn-success' },
//                { type: 'button', label: '修改', listeners: [{ click:tab_editRow}], cls: 'btn btn-sm btn-primary' },
//                { type: 'button', label: '删除', listeners: [{ click: tab_deleteRow}], cls: 'btn btn-sm btn-danger' }
//            ]
//        };
	
    var obj = { 
    	title:"菜单信息列表",
//			width: '100%',
		height: "100%",
//            minWidth: 500,
//            height: 400,
        flexHeight: true,
        showTitle:false,
		showButton:true,
		wrap:false,
		track: true,
		resizable: true,//调整网格的居中和垂直
		sortIndx: 0,//排序列或多列的dataIndx。它变成多列排序使用阵列时
        colModel:colModel,
        dataModel:dataModel,
        numberCell:{resizable:true, title: "#"},
        selectionModel: { mode: 'single' },
        selectionModel: { type: 'row' },//选中 row 为整行 cell 为整列 model 为单元格
        pageModel: { type: "local", rPP: 10, rPPOptions: [10, 20, 50, 100] },//定义表尾分页参数 local为本地数据，remote 为远程数据
        scrollModel: { autoFit: false },
        collapsible: { on: true, collapsed: false }
    };
    obj.columnTemplate = {            
        title: function (ui) {
            return ui.column.Title + " (" + ui.column.width + ")";
        }
    };
//    obj.toolbar = tab_toolbar;
    var $grid = tabId.pqGrid(obj);
    $grid.pqGrid("option", $.paramquery.pqGrid.regional['zh']);            
    $grid.find(".pq-pager").pqPager("option", $.paramquery.pqPager.regional['zh']);   
    //bind width to select list.
    $("#sl_width").change(function (evt) {
        var val = $(this).val();
        $grid.pqGrid('option', 'width', val)
                .pqGrid('refresh');
    });
    $("#sl_margin").change(function (evt) {
        var val = $(this).val();
        $grid.css('margin', val).pqGrid('refresh');
    });
    //edit Row
    function tab_editRow() {
        var rowIndx = getRowIndx();
        if (rowIndx != null) {
            var row = $grid.pqGrid('getRowData', {rowIndx: rowIndx});
            var updateDialogMsg = '<div"><form id="update_tab_from" class="form-horizontal" role="form">'+$("#org_dialog").html()+'</form></div>';
            bootbox.dialog({
            	title: "修改企业信息", 
            	message:updateDialogMsg,
            	animate: true,
    	    	buttons: {
    	             success: {
    	                 label: "保存",
    	                 className: "btn btn-sm btn-success",
    	                 callback: function () {
     	                	 $.ajax({
    	                		   type: "POST",
    	                		   url: "modifyOrg",
    	                		   data: $("#update_tab_from").serialize(),
    	                		   success: function(data){
    	                			   if('success' == data.type){
    	                				   Lobibox.notify("success", {
    	                		        		icon: false,
    	                		        		height:'300px',
    	                		        		title: '修改提示',
    	                		        		msg: '修改成功'
    	                		        	});
    	                				   $grid.pqGrid( "refreshDataAndView" )
    	                			   }else{
    	                				   Lobibox.notify("error", {
    	                		        		icon: false,
    	                		        		height:'300px',
    	                		        		title: '修改提示',
    	                		        		msg: '修改失败，请检查修改的数据'
    	                		        	});
    	                			   }
    	                		   }
    	                		});
    	                 }
    	             },
    	             canle:{
    	            	 label: "取消",
    	                 className: "btn btn-sm btn-grey",
    	                 callback: function () {
    	                	 
    	                 }
    	             }
    	    	},
//        	    	callback:setTimeout(updateMeuns(row),500)
            });
            updateMeuns(row);
        }
    }
    function updateMeuns(row){
    	$('#update_tab_from .row').find(" .form-group div").find("select").pqSelect({ 
    		selectClsType:'',
    		deselect: false,
    		width:'270px'
    	});
//        	$('#update_tab_from input[name=id]').val(row.id);
//        	$('#update_tab_from input[name=menuIndex]').val(row.menuIndex);
//        	$('#update_tab_from input[name=menuName]').val(row.menuName);
//        	$('#update_tab_from input[name=menuIcon]').val(row.menuIcon);
//        	$('#update_tab_from select[name=menuLevel]').val(row.menuLevel);
//        	$('#update_tab_from select[name=menuParentId]').val(row.menuParentId);
//        	$('#update_tab_from select[name=menuStatus]').val(row.menuStatus);
//        	$('#update_tab_from input[name=menuUrl]').val(row.menuUrl);
//        	$('#update_tab_from textarea[name=menuStyle]').val(row.menuStyle);
//        	$("#update_tab_from select").pqSelect({    
//      	  	    deselect: false
//      	  	}).pqSelect('close');
    }
    //append Row
    function tab_addRow() {
        var $frm = $("form#crud-form");
        $frm.find("input").val("");
        var addMessage = '<div class="dialog-cls"><form id="add_tab_from" class="form-horizontal" role="form">'+$("#org_dialog").html()+'</form></div>';
        bootbox.dialog({ 
        	title: "新增菜单", 
        	message:addMessage,
        	animate: true,
	    	buttons: {
	             success: {
	                 label: "保存",
	                 className: "btn btn-sm btn-success",
	                 callback: function () {
 	                	 $.ajax({
	                		   type: "POST",
	                		   url: "addOrg",
	                		   data: $("#add_tab_from").serialize(),
	                		   success: function(data){
	                			   if('success' == data.type){
	                				   Lobibox.notify("success", {
	                		        		icon: false,
	                		        		height:'300px',
	                		        		title: '新增提示',
	                		        		msg: '添加成功'
	                		        	});
	                				   $grid.pqGrid( "refreshDataAndView" )
	                			   }else{
	                				   Lobibox.notify("error", {
	                		        		icon: false,
	                		        		height:'300px',
	                		        		title: '新增提示',
	                		        		msg: '添加失败，请检查添加的数据'
	                		        	});
	                			   }
	                		   }
	                		});
	                 }
	             },
	             canle:{
	            	 label: "取消",
	                 className: "btn btn-sm btn-grey",
	                 callback: function () {
	                	 
	                 }
	             }
	    	},
	    	callback:setTimeout(selectInput,200)
        });
        $("#popup-dialog-crud").dialog("open");
    }
    function selectInput(){
//    	$('#add_tab_from .row').find(" .form-group div").find("select").select2();
    	$('#add_tab_from .row').find(" .form-group div").find("select").pqSelect({ 
    		selectClsType:'',
    		deselect: false
//    		width:'270px'
    	});
    }
    function formatRepoProvince(repo) {
        if (repo.loading) return repo.text;
        var markup = "<div>"+repo.name+"</div>";
        return markup;
    }
    //delete Row.
    function tab_deleteRow() {
        var rowIndx = getRowIndx();
        if (rowIndx != null) {
        	var row = $grid.pqGrid('getRowData', {rowIndx: rowIndx});
        	var delMsg = '<form id="del_tab_from" class="form-horizontal" role="form"><input type="hidden" class="form-control input-xlarge" name="id" value="'+row.id+'"></form>';
        	bootbox.dialog({
        		  message: "您确定要删除这条信息么"+delMsg,
        		  title: "删除基础信息",
        		  buttons: {
        		    success: {
        		      label: "确定",
        		      className: "btn btn-sm btn-success",
        		      callback: function() {
        		    	 var add_tab_data=$("#del_tab_from input[type=hidden]").map(function(){
   	                		  return ($(this).attr("name")+'='+$(this).val());
   	                		}).get().join("&") ;
        		    	  $.ajax({
	                		   type: "POST",
	                		   url: "deleteOrg",
	                		   data: add_tab_data,
	                		   success: function(data){
	                			   if('success' == data.type){
	                				   $grid.pqGrid("deleteRow", { rowIndx: rowIndx });
	                	               $grid.pqGrid("setSelection", { rowIndx: rowIndx });
	                				   Lobibox.notify("success", {
	                		        		icon: false,
	                		        		height:'300px',
	                		        		title: '删除提示',
	                		        		msg: '删除成功'
	                		        	});
	                			   }else{
	                				   Lobibox.notify("error", {
	                		        		icon: false,
	                		        		height:'300px',
	                		        		title: '删除提示',
	                		        		msg: '删除失败，请检查删除的数据'
	                		        	});
	                			   }
	                			   $grid.pqGrid( "refreshDataAndView" )
	                		   }
	                		});
        		      }
        		    },
        		    canle:{
   	            	 label: "取消",
   	                 className: "btn btn-sm btn-grey",
   	                 callback: function () {
   	                 }
        		    }
        		  }
        		});
        }
    }
    function getRowIndx() {
        var arr = $grid.pqGrid("selection", { type: 'row', method: 'getSelection' });
        if (arr && arr.length > 0) {
            return arr[0].rowIndx;                                
        }
        else {
        	Lobibox.notify("info", {
        		//size: 'mini',
        		icon: false,
        		//width:'',
        		height:'300px',
        		title: '点击提示',
        		msg: '请选择一行数据'
        		});
            return null;
        }
    }
});    