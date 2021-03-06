﻿﻿    $(function () {
	//首先定义表格选项
	var tabId = $("#mac_grid");
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
	            	var data = dataJSON.list;
	                return { curPage: dataJSON.pageNo, totalRecords: dataJSON.total, data: data };
	            }
	        };
	var colModel = [
	                { title: "组织名称",dataIndx:"orgName", width: '18%', dataType: "string", align: "center" },
	        		{ title: "设备名称",dataIndx:"macName", width: '18%', dataType: "string", align: "center"},
	        		{ title: "设备mac",dataIndx:"mac", width: '20%', dataType: "string", align: "center" },
	        		{ title: "设备描述",dataIndx:"macDesc", width: '30%', dataType: "string", align: "center" },
	        		{ title: "创建时间",dataIndx:"createTime", width: '13%', dataType: "string", align: "center",
	        			 render: function (ui) {
		         	            var rowData=ui.rowData;
		                     return formatDateTime(rowData.createTime,"yyyy-MM-dd HH:mm:ss");
		        			 }
	        		}
	        	];
	var tab_toolbar = {
            items: [
                { type: 'button', label: '添加', listeners: [{ click: tab_addRow}], cls: 'btn btn-sm btn-success' },
                { type: 'button', label: '修改', listeners: [{ click:tab_editRow}], cls: 'btn btn-sm btn-primary' },
                { type: 'button', label: '删除', listeners: [{ click: tab_deleteRow}], cls: 'btn btn-sm btn-danger' }
            ]
        };
	
    var obj = { 
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
        pageModel: { type: "remote", rPP: 10, rPPOptions: [10, 20, 50, 100] },//定义表尾分页参数 local为本地数据，remote 为远程数据
        scrollModel: { autoFit: false },
        collapsible: { on: true, collapsed: false }
    };
    obj.columnTemplate = {            
        title: function (ui) {
            return ui.column.Title + " (" + ui.column.width + ")";
        }
    };
    obj.toolbar = tab_toolbar;
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
            var updateDialogMsg = '<div"><form id="update_tab_from" class="form-horizontal" role="form">'+$("#org_mac_dialog").html()+'</form></div>';
            bootbox.dialog({
            	title: "修改设备信息", 
            	message:updateDialogMsg,
            	animate: true,
    	    	buttons: {
    	             success: {
    	                 label: "保存",
    	                 className: "btn btn-sm btn-success",
    	                 callback: function () {
     	                	 $.ajax({
    	                		   type: "POST",
    	                		   url: "modifyOrgMac",
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
    	$('#update_tab_from input[name=id]').val(row.id);
    	$('#update_tab_from input[name=macName]').val(row.macName);
    	$('#update_tab_from input[name=mac]').val(row.mac);
    	$('#update_tab_from textarea[name=macDesc]').val(row.macDesc);
    	$('#update_tab_from select[name=orgId]').find("option[value="+row.orgId+"]").attr("selected",true);
    	$('#update_tab_from select[name=orgId]').pqSelect({
              checkbox: false, //adds checkbox to options   
              width:'270px'
          }).pqSelect('close');  
    }
    //append Row
    function tab_addRow() {
        var $frm = $("form#crud-form");
        $frm.find("input").val("");
        var addMessage = '<div class="dialog-cls"><form id="add_tab_from" class="form-horizontal" role="form">'+$("#org_mac_dialog").html()+'</form></div>';
        bootbox.dialog({ 
        	title: "新增设备", 
        	message:addMessage,
        	animate: true,
	    	buttons: {
	             success: {
	                 label: "保存",
	                 className: "btn btn-sm btn-success",
	                 callback: function () {
	                	 if(vailDateParam('ADD')){
	                		 return false;
	                	 }
 	                	 $.ajax({
	                		   type: "POST",
	                		   url: "addOrgMac",
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
	    	callback:setTimeout(selectInput,0)
        });
    }
    function selectInput(){
    	$('#add_tab_from .row').find(" .form-group div").find("select").pqSelect({ 
    		selectClsType:'',
    		deselect: false,
    		width:'270px'
    	});
//    	$('#add_tab_from input[name=mac]').not("[type=submit]").jqBootstrapValidation();
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
        		  title: "删除设备信息",
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
	                		   url: "deleteOrgMac",
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
    
    function vailDateParam(status){
    	var isSuccess;
    	var macName = $('form input[name=macName]').val();
    	var mac = $('form input[name=mac]').val();
    	$.ajax({
 		   type: "GET",
 		   url: "validate",
 		   async: false,
 		   data: "macName="+macName+"&mac="+mac+"&status="+status,
 		   success: function(data){
 			   if('success' != data.type){
 				   Lobibox.notify("error", {
 					   	size: 'mini',
 		        		position: 'center top',
 		        		msg: data.message
 		        	});
 				  isSuccess=true;
 			   }
 			   if('success'==data.type){
 				   isSuccess=false;
 			   }
 		   }
 		});
    	return isSuccess;
    }
});    