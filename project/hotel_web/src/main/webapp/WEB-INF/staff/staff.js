    $(function () {
    	//首先定义表格选项
    	var tabId = $("#staff_grid");
    	function pqDatePicker(ui) {
    	    var $this = $(this);
    	    $this.datetimepicker({
    	    	language: 'zh-CN',
    			format: 'yyyy-mm-dd hh:ii:ss',
    			autoclose: true,
     	        todayBtn: true,
     	        pickerPosition: "bottom-left",
	            yearRange: "-20:+0", //20 years prior to present.
	            changeYear: true,
	            changeMonth: true,
	            //showButtonPanel: true,
	            onClose: function (evt, ui) {
	                $(this).focus();
	            }
	        });
    	}
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
    	        		{ title: "员工编号",dataIndx:"staffNo", width: '5%', dataType: "string", align: "center"},
    	        		{ title: "员工姓名",dataIndx:"staffName", width: '5%', dataType: "string", align: "center" },
    	        		{ title: "部门名称",dataIndx:"orgName", width: '5%', dataType: "string", align: "center" },
    	        		{ title: "性别",dataIndx:"sex", width: '5%', dataType: "string", align: "center" },
    	        		{ title: "身份证",dataIndx:"cardId", width: '10%', dataType: "string", align: "center" },
    	        		{ title: "员工卡号",dataIndx:"staffCardNo", width: '5%', dataType: "string", align: "center" },
    	        		{ title: "入职时间",dataIndx:"intoTime", width: '7%',dataType: "date", align: "center",
    	        			 filter: { type: 'textbox', condition: "between", init: pqDatePicker, listeners: ['change'] },
	   	        			 render: function (ui) {
	   	         	            var rowData=ui.rowData;
	   	                     return formatDateTime(rowData.birthday,"yyyy-MM-dd HH:mm:ss");
	
	   	        			 }
    	        		},
    	        		{ title: "联系电话",dataIndx:"telphone", width: '5%', dataType: "string", align: "center" },
    	        		{ title: "联系地址",dataIndx:"staffAddress", width: '15%', dataType: "string", align: "center" },
    	        		{ title: "联系邮箱",dataIndx:"email", width: '10%', dataType: "string", align: "center" },
    	        		{ title: "员工生日",dataIndx:"birthday", width: '7%', dataType: "date", align: "center" ,
    	        			filter: { type: 'textbox', condition: "between", init: pqDatePicker, listeners: ['change'] },
    	        			 render: function (ui) {
    	         	            var rowData=ui.rowData;
    	                     return formatDateTime(rowData.birthday,"yyyy-MM-dd HH:mm:ss");

    	        			 }
    	        		},
    	        		{ title: "年假",dataIndx:"yearRestDay", width: '5%', dataType: "string", align: "center" },
    	        		{ title: "存休",dataIndx:"keepRestDay", width: '5%', dataType: "string", align: "center" },
    	        		{ title: "操作", editable: false, minWidth: '10%', sortable: false,align: "center", render: function (ui) {
    	                    return "<button type='button' class='btn btn-xs btn-danger delete_btn'>办理离职</button>\
    	                        <button type='button' class='btn btn-xs btn-primary edit_btn'>年假管理</button>";
    	                	}
    	                }
    	        	];
    	var tab_toolbar = {
                items: [
                    { type: 'button', label: '添加', listeners: [{ click: tab_addRow}], cls: 'btn btn-sm btn-success' },
                    { type: 'button', label: '修改', listeners: [{ click:tab_editRow}], cls: 'btn btn-sm btn-primary' },
/*                    { type: 'button', label: '删除', listeners: [{ click: tab_deleteRow}], cls: 'btn btn-sm btn-danger' },*/
                    {type: 'separator' },
                    {
                        type: 'button',
                        label: "导出至Excel",
                        cls:'btn btn-sm btn-info',
                        listeners: [{"click": function (evt) {
//                        		tabId.pqGrid("exportData", {
//                        			url: "exportExcel1",
//                        			filename:'',
//                        			sheetName: "pqGrid sheet",
//                        			format:'xls',
////                        			noheader:true,
////                        			nopqdata:treu,
//                        			render:true,
//                        			title:'12312312312321321321',
//                        			zip:false
//                        		});
                        	tab_export();
//                    		window.open("exportExcel1");
                            }
                        }]
                    }
                ]
            };
        var obj = { 
        	title:"企业信息列表",
//			width: '100%',
			height: "100%",
    		title: "企业列表",
//            minWidth: 500,
//            height: 400,
            flexHeight: true,
            showTitle:true,
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
            pageModel: { type: "remote", rPP: 10, rPPOptions: [15, 20, 50, 100] },//定义表尾分页参数 local为本地数据，remote 为远程数据
            scrollModel: { autoFit: false },
            collapsible: { on: true, collapsed: false },
            refresh: function () {
                $(".delete_btn").bind('click',function(){
                	staffQuit();
                });
                $(".edit_btn").bind('click',function(){
                	staffYearRest();
                });
              }
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
                var updateDialogMsg = '<div><form id="update_tab_from" class="form-horizontal" role="form">'+$("#staff_dialog").html()+'</form></div>';
                bootbox.dialog({
                	title: "修改员工信息", 
                	message:updateDialogMsg,
                	animate: true,
        	    	buttons: {
        	             success: {
        	                 label: "保存",
        	                 className: "btn btn-sm btn-success",
        	                 callback: function () {
         	                	 $.ajax({
        	                		   type: "POST",
        	                		   url: "modifyStaff",
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
        	    	}
                });
                updateMeuns(row);
            }
        }
        function updateMeuns(row){
        	$('#update_tab_from input[name=id]').val(row.id);
        	$('#update_tab_from input[name=staffName]').val(row.staffName);
        	$('#update_tab_from input[name=cardId]').val(row.cardId);
        	$('#update_tab_from input[name=birthday]').val(formatDateTime(row.birthday,"yyyy-MM-dd HH:mm:ss"));
        	$('#update_tab_from input[name=telphone]').val(row.telphone);
        	$('#update_tab_from input[name=email]').val(row.email);
        	$('#update_tab_from textarea[name=staffAddress]').val(row.staffAddress);
        	$('#update_tab_from input[name=staffNo]').val(row.staffNo);
        	$('#update_tab_from input[name=staffCardNo]').val(row.staffCardNo);
        	$('#update_tab_from input[name=intoTime]').val(formatDateTime(row.intoTime,"yyyy-MM-dd HH:mm:ss"));
        	$('#update_tab_from textarea[name=ortherMemo]').val(row.ortherMemo);
        	
        	
        	$('#update_tab_from select[name=sex]').find("option[value="+row.sex+"]").attr("selected",true);
        	$('#update_tab_from select[name=sex]').pqSelect({    
  	  	    	deselect: false,
  	  	    	width:'270px'
  	  		}).pqSelect('close');
        	$('#update_tab_from select[name=orgId]').find("option[value="+row.orgId+"-"+row.orgName+"]").attr("selected",true);
        	$('#update_tab_from select[name=orgId]').pqSelect({    
  	  	    	deselect: false,
  	  	    	width:'270px'
  	  		}).pqSelect('close');
        	$('.datepicker').datetimepicker({
    			language: 'zh-CN',
    			format: 'yyyy-mm-dd hh:ii:ss',
    	        autoclose: true,
    	        todayBtn: true,
    	        pickerPosition: "bottom-left"
            });
        }
        //append Row
        function tab_addRow() {
            var $frm = $("form#crud-form");
            $frm.find("input").val("");
            var addMessage = '<div class="dialog-cls"><form id="add_tab_from" class="form-horizontal" role="form">'+$("#staff_dialog").html()+'</form></div>';
            bootbox.dialog({ 
            	title: "新增员工信息", 
            	message:addMessage,
            	animate: true,
    	    	buttons: {
    	             success: {
    	                 label: "保存",
    	                 className: "btn btn-sm btn-success",
    	                 callback: function () {
     	                	 $.ajax({
    	                		   type: "POST",
    	                		   url: "addStaff",
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
        	$('.datepicker').datetimepicker({
    			language: 'zh-CN',
    			format: 'yyyy-mm-dd hh:ii:ss',
    	        autoclose: true,
    	        todayBtn: true,
    	        pickerPosition: "bottom-left"
            });
        }
        function formatRepoProvince(repo) {
            if (repo.loading) return repo.text;
            var markup = "<div>"+repo.name+"</div>";
            return markup;
        }
//        
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
        function tab_export() {
          	var delMsg = '<div class="dialog-cls"><form id="export_from" class="form-horizontal" role="form">'+$("#staff_export_dialog").html()+'</form></div>';
          	bootbox.dialog({
          		  message: delMsg,
          		  title: "导出列表",
          		  buttons: {
          		    success: {
          		      label: "导出",
          		      className: "btn btn-sm btn-success",
          		      callback: function() {
          		    	 var add_tab_data=$("#export_from input[type=hidden]").map(function(){
     	                		  return ($(this).attr("name")+'='+$(this).val());
     	                		}).get().join("&") ;
          		    	 alert(1);
          		    	  $.ajax({
    	                		   type: "get",
    	                		   url: "exportExcel1",
    	                		   data: add_tab_data,
    	                		   success: function(data){
    	                			   alert(1);
    	                			   window.open("exportExcel1");
    	                			   if('success' == data.type){
    	                				   
    	                				   $grid.pqGrid("deleteRow", { rowIndx: rowIndx });
    	                	               $grid.pqGrid("setSelection", { rowIndx: rowIndx });
    	                				   Lobibox.notify("success", {
    	                		        		icon: false,
    	                		        		height:'300px',
    	                		        		title: '导出提示',
    	                		        		msg: '导出成功'
    	                		        	});
    	                			   }else{
    	                				   Lobibox.notify("error", {
    	                		        		icon: false,
    	                		        		height:'300px',
    	                		        		title: '导出提示',
    	                		        		msg: '导出失败，请检查删除的数据'
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
          	$('#export_from .row').find(" .form-group div").find("select").pqSelect({ 
	    		checkbox: true,
	    		width:'270px'
	    	});
          	$('.datepicker').datetimepicker({
    			language: 'zh-CN',
    			format: 'yyyy-mm-dd hh:ii:ss',
    	        autoclose: true,
    	        todayBtn: true,
    	        pickerPosition: "bottom-left"
            });
          }
        function staffQuit(){
        	var rowIndx = getRowIndx();
        	if (rowIndx != null) {
        		var row = $grid.pqGrid('getRowData', {rowIndx: rowIndx});
        		var updateDialogMsg = '<div><form id="quit_from" class="form-horizontal" role="form">'+$("#staff_quit_dialog").html()+'</form></div>';
        		bootbox.dialog({
        			title: "员工离职办理", 
        			message:updateDialogMsg,
        			animate: true,
        			buttons: {
        				success: {
        					label: "保存",
        					className: "btn btn-sm btn-success",
        					callback: function () {
        						$.ajax({
        							type: "POST",
        							url: "modifyStaff",
        							data: $("#quit_from").serialize(),
        							success: function(data){
        								if('success' == data.type){
        									Lobibox.notify("success", {
        										icon: false,
        										height:'300px',
        										title: '操作提示',
        										msg: '操作成功'
        									});
        									$grid.pqGrid( "refreshDataAndView" )
        								}else{
        									Lobibox.notify("error", {
        										icon: false,
        										height:'300px',
        										title: '操作提示',
        										msg: '操作失败，请检查修改的数据'
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
        			}
        		});
        		$('#quit_from input[name=id]').val(row.id);
            	$('#quit_from input[name=staffName]').val(row.staffName);
            	$('#quit_from input[name=staffNo]').val(row.staffNo);
            	$('#quit_from input[name=quitTime]').val(formatDateTime(row.quitTime,"yyyy-MM-dd HH:mm:ss"));
            	$('#quit_from textarea[name=quitDesc]').val(row.quitDesc);
            	$('#quit_from textarea[name=quitMemo]').val(row.quitMemo);
            	$('#quit_from select[name=staffStatus]').pqSelect({ 
    	    		checkbox: true,
    	    		width:'270px'
    	    	});
            	$('.datepicker').datetimepicker({
        			language: 'zh-CN',
        			format: 'yyyy-mm-dd hh:ii:ss',
        	        autoclose: true,
        	        todayBtn: true,
        	        pickerPosition: "bottom-left"
                });
        	}
        }
        function staffYearRest(){
        	var rowIndx = getRowIndx();
        	if (rowIndx != null) {
        		var row = $grid.pqGrid('getRowData', {rowIndx: rowIndx});
        		var updateDialogMsg = '<div><form id="rest_from" class="form-horizontal" role="form">'+$("#staff_rest_dialog").html()+'</form></div>';
        		bootbox.dialog({
        			title: "员工年假管理", 
        			message:updateDialogMsg,
        			animate: true,
        			buttons: {
        				success: {
        					label: "保存",
        					className: "btn btn-sm btn-success",
        					callback: function () {
        						$.ajax({
        							type: "POST",
        							url: "modifyStaff",
        							data: $("#rest_from").serialize(),
        							success: function(data){
        								if('success' == data.type){
        									Lobibox.notify("success", {
        										icon: false,
        										height:'300px',
        										title: '操作提示',
        										msg: '操作成功'
        									});
        									$grid.pqGrid( "refreshDataAndView" )
        								}else{
        									Lobibox.notify("error", {
        										icon: false,
        										height:'300px',
        										title: '操作提示',
        										msg: '操作失败，请检查修改的数据'
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
        			}
        		});
        		$('#rest_from input[name=id]').val(row.id);
            	$('#rest_from input[name=staffName]').val(row.staffName);
            	$('#rest_from input[name=staffNo]').val(row.staffNo);
            	$('#rest_from input[name=yearRestDay]').val(row.yearRestDay);
            	$('#rest_from input[name=keepRestDay]').val(row.keepRestDay);
        	}
        }
    });    
  //delete Row.
//  function tab_deleteRow() {
//      var rowIndx = getRowIndx();
//      if (rowIndx != null) {
//      	var row = $grid.pqGrid('getRowData', {rowIndx: rowIndx});
//      	var delMsg = '<form id="del_tab_from" class="form-horizontal" role="form"><input type="hidden" class="form-control input-xlarge" name="id" value="'+row.id+'"></form>';
//      	bootbox.dialog({
//      		  message: "您确定要删除这条信息么"+delMsg,
//      		  title: "删除角色",
//      		  buttons: {
//      		    success: {
//      		      label: "确定",
//      		      className: "btn btn-sm btn-success",
//      		      callback: function() {
//      		    	 var add_tab_data=$("#del_tab_from input[type=hidden]").map(function(){
// 	                		  return ($(this).attr("name")+'='+$(this).val());
// 	                		}).get().join("&") ;
//      		    	  $.ajax({
//	                		   type: "POST",
//	                		   url: "deleteOrg",
//	                		   data: add_tab_data,
//	                		   success: function(data){
//	                			   if('success' == data.type){
//	                				   $grid.pqGrid("deleteRow", { rowIndx: rowIndx });
//	                	               $grid.pqGrid("setSelection", { rowIndx: rowIndx });
//	                				   Lobibox.notify("success", {
//	                		        		icon: false,
//	                		        		height:'300px',
//	                		        		title: '删除提示',
//	                		        		msg: '删除成功'
//	                		        	});
//	                			   }else{
//	                				   Lobibox.notify("error", {
//	                		        		icon: false,
//	                		        		height:'300px',
//	                		        		title: '删除提示',
//	                		        		msg: '删除失败，请检查删除的数据'
//	                		        	});
//	                			   }
//	                			   $grid.pqGrid( "refreshDataAndView" )
//	                		   }
//	                		});
//      		      }
//      		    },
//      		    canle:{
// 	            	 label: "取消",
// 	                 className: "btn btn-sm btn-grey",
// 	                 callback: function () {
// 	                 }
//      		    }
//      		  }
//      		});
//      }
//  }