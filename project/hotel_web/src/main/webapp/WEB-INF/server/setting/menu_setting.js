$(function() {
	// 远程筛选
	$("#sel_menu3").select2({
		ajax : {
			url : "/Home/GetProvinces",
			dataType : 'json',
			delay : 250,
			data : function(params) {
				return {
					q : params.term, // search term
					page : params.page
				};
			},
			processResults : function(data, params) {
				params.page = params.page || 1;

				return {
					results : data.items,
					pagination : {
						more : (params.page * 10) < data.total_count
					}
				};
			},
			cache : true
		},
		escapeMarkup : function(markup) {
			return markup;
		}, // let our custom formatter work
		minimumInputLength : 1,
		templateResult : formatRepoProvince, // omitted for brevity, see the
												// source of this page
		templateSelection : formatRepoProvince
	// omitted for brevity, see the source of this page
	});
	
	function dynamicDataSource(openedParentData, callback) {
		  var childNodesArray = [];

		  // call API, posting options
		  $.ajax({
		    'type': 'post',
		    'url': '/tree/data',
		    'data': openedParentData  // first call with be an empty object
		  })
		  .done(function(data) {
		    // configure datasource
		    var childObjectsArray = data;

		    // pass an array with the key 'data' back to the tree
		    // [ {'name': [string], 'type': [string], 'attr': [object] } ]
		    callback({
		      data: childNodesArray
		    });

		  });
		}
	
	
	
		$('#tree2').ace_tree({
			dataSource: treeDataSource2 ,
			loadingHTML:'<div class="tree-loading"><i class="icon-refresh icon-spin blue"></i></div>',
			'open-icon' : 'icon-folder-open',
			'close-icon' : 'icon-folder-close',
			'selectable' : false,
			'selected-icon' : null,
			'unselected-icon' : null
		});



		/**
		$('#tree1').on('loaded', function (evt, data) {
		});

		$('#tree1').on('opened', function (evt, data) {
		});

		$('#tree1').on('closed', function (evt, data) {
		});

		$('#tree1').on('selected', function (evt, data) {
		});
		*/
});

function formatRepoProvince(repo) {
	if (repo.loading)
		return repo.text;
	var markup = "<div>" + repo.name + "</div>";
	return markup;
}

/*
 * 1、获取选中的值 var oMenuIcon = $("#txt_menuicon_web").select2({ placeholder:
 * "请选择菜单图标", templateResult: oInit.formatState, templateSelection:
 * oInit.formatState }); oMenuIcon.val();
 * 
 * 2、设置select2的选中值 var oMenuIcon = $("#txt_menuicon_web").select2({ placeholder:
 * "请选择菜单图标", templateResult: oInit.formatState, templateSelection:
 * oInit.formatState }); oMenuIcon.val("CA").trigger("change");
 */