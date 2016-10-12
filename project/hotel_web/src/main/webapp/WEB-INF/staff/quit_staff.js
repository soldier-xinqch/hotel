    $(function () {
    	//首先定义表格选项
    	var tabId = $("#quit_staff_grid");
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
    	        		{ title: "员工编号",dataIndx:"staffNo", width: '8%', dataType: "string", align: "center"},
    	        		{ title: "员工姓名",dataIndx:"staffName", width: '8%', dataType: "string", align: "center" },
    	        		{ title: "部门名称",dataIndx:"orgName", width: '8%', dataType: "string", align: "center" },
    	        		{ title: "状态",dataIndx:"staffStatus", width: '8%', dataType: "string", align: "center" },
    	        		{ title: "联系手机",dataIndx:"telphone", width: '8%', dataType: "string", align: "center" },
    	        		{ title: "离职时间",dataIndx:"quitTime", width: '12%', dataType: "string", align: "center",
    	        			 render: function (ui) {
     	         	            var rowData=ui.rowData;
     	                     return formatDateTime(rowData.quitTime,"yyyy-MM-dd HH:mm:ss");
    	        			 }
    	        		},
    	        		{ title: "办理人",dataIndx:"quitCheckName", width: '10%', dataType: "date", align: "center" },
    	        		{ title: "离职原因",dataIndx:"quitDesc", width: '17%', dataType: "string", align: "center" },
    	        		{ title: "备注",dataIndx:"quitMemo", width: '20%', dataType: "string", align: "center" },
    	        	];
    	var tab_toolbar = {
                items: [
                    {
                        type: 'button',
                        label: "导出",
                        cls:'btn btn-sm',
                        listeners: [{"click": function (evt) {
                        	 tab_export();
                            }
                        }]
                    }
                ]
            };
        var obj = { 
//			width: '100%',
			height: "100%",
    		title: "离职员工列表",
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
          		    	  window.open("exportExcel?"+$("#export_from").serialize());
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
          		selectallText:'全选',
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
    });    
    