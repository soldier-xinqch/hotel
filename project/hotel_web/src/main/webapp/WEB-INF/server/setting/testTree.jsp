<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en" class="fuelux">
  <head>
    <meta charset="utf-8">
    <title>Fuel UX Test</title>
    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="http://www.fuelcdn.com/fuelux/3.7.3/css/fuelux.min.css" rel="stylesheet"/>
  </head>
  <body>
  
  
  	<ul id="myTree" class="tree tree-folder-select" role="tree" id="myTree">
  <li class="tree-branch hide" data-template="treebranch" role="treeitem" aria-expanded="false">
    <div class="tree-branch-header">
      <button class="glyphicon icon-caret glyphicon-play"><span class="sr-only">Open</span></button>
      <button class="tree-branch-name">
        <span class="glyphicon icon-folder glyphicon-folder-close"></span>
        <span class="tree-label"></span>
      </button>
    </div>
    <ul class="tree-branch-children" role="group"></ul>
    <div class="tree-loader" role="alert">Loading...</div>
  </li>
  <li class="tree-item hide" data-template="treeitem" role="treeitem">
    <button class="tree-item-name">
      <span class="glyphicon icon-item fueluxicon-bullet"></span>
      <span class="tree-label"></span>
    </button>
  </li>
</ul>

    <script type="text/javascript" src="/public/js/jquery-1.11.3.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="http://www.fuelcdn.com/fuelux/3.7.3/js/fuelux.min.js"></script>
    <script type="text/javascript">
    function staticDataSource(openedParentData, callback) {
    	  childNodesArray = [
    	    { "name": "Ascending and Descending", "type": "folder" },
    	    { "name": "Sky and Water I", "type": "item" },
    	    { "name": "Drawing Hands", "type": "folder" },
    	    { "name": "waterfall", "type": "item" },
    	    { "name": "Belvedere", "type": "folder" },
    	    { "name": "Relativity", "type": "item" },
    	    { "name": "House of Stairs", "type": "folder" },
    	    { "name": "Convex and Concave", "type": "item" }
    	  ];

    	  callback({
    	    data: childNodesArray
    	  });
    	}
    $(function() {
    	   $('#myTree').tree({
    	      dataSource: staticDataSource,
    	      multiSelect: false,
    	      folderSelect: true
    	    });
    	});
    </script>
  </body>
</html>