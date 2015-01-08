<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
 <link rel="stylesheet" type="text/css" media="screen" href="/User/resources/js/jqGrid/jquery-ui.css" />
  <link rel="stylesheet" type="text/css" media="screen" href="/User/resources/js/jqGrid/css/ui.jqgrid.css" />
    <script src="/User/resources/js/jquery-1.7.1.min.js" type="text/javascript"></script>
	<script src="/User/resources/js/jqGrid/jquery.jqGrid.src.js" type="text/javascript"></script>
	<script src="/User/resources/js/jqGrid/i18n/grid.locale-en.js" type="text/javascript"></script>
	<script src="/User/resources/js/jqGrid/jqGrid.spring.save.js" type="text/javascript"></script>
	<script>
	var lastSel = -1;
$(document).ready(function(){
	$("#abcList").jqGrid(
			{
				datatype : "local",
				height : "100%",
				shrinkToFit:false,
				caption:"Overpayment Record",
				editurl:"www.baidu.com",
				rowedit: true,
				autoScroll: true,
				autowidth :true,
				colNames : ['Name','Status','S/O Ind'],
				colModel : [
				        {name : 'name',index :'name',width :117, editable:true,edittype:'text'},
						{name : 'status',index : 'status',width : 117, editable:true,edittype:'custom',editoptions:{custom_element: myelem, custom_value:myValue,value:{'3':'perfect','4':'terrible'}},formatter: myFormatter,unformat: myUnformat},
						{name : 'soInd',index : 'soInd',width : 91, editable:true,edittype:'custom',editoptions:{custom_element: myelem, custom_value:myValue,value:{'1':'one','2':'two'}},formatter: myFormatter,unformat: myUnformat},
					],
					onSelectRow: function(id){
					     if(id && id!==lastSel){ 
					        jQuery('#abcList').restoreRow(lastSel); 
					        lastSel=id; 
					     }
					     jQuery('#abcList').editRow(id, true); 
					   },
					rowNum : 5,
					multiselect : false,
					pager: 'abcPager'
					});
	
	$("#abcList").jqGrid('navGrid', '#abcPager',{edit:true,add:true,del:false},{
		
		afterShowForm: function(form){
			var $parent=$('label',$('#FrmGrid_abcList')).parent().parent().parent();
			$parent.each(function(i){
			  cleanWhitespace(this);		
			 });
		}
		
	});
	var listData = [
	   	                {
	   	                	name :'david lin',
	   	                	status : "3",
	   	                	soInd : "1"
	   	                },
	   	                {	
	   	                	name :'david lin',
	   	                	status : "4",
	   	                	soInd : "2"
	   	                }
	   	                ];
	$("#abcList").jqGrid('clearGridData', false);
		for(var i=0;i<listData.length;i++){
			$("#abcList").jqGrid('addRowData',i+1,listData[i]);
			
		}
});		

function myelem(value, options){
	/*var valueOptions =options['value'];
	var htmlRadios='';
	for(var one in valueOptions){
		var key = options['name'].replace(/\./g,'');
		var isChecked=false;
		if(value== one){
			isChecked =true;
		}
		htmlRadios += "<label for='"+key+"'><input type='radio' id='"+key+"' value='"+one+"' checked="+isChecked+" name='"+key+"'/>"+valueOptions[one]+" </label><br/>";
	}
	
	$('input[type="radio"]',$(htmlRadios)).removeAttr("checked").prop("checked", false);
	$('input[type="radio"][value='+value+']',$(htmlRadios)).attr("checked", "checked").prop("checked", true);
	console.log("myelem>>",$(htmlRadios));
	return $(htmlRadios);
	*/
	var valueOptions =options['value'];
	var root=document.createElement('span');  
	console.log("myelem>>",valueOptions);
	for(var one in valueOptions){
		var key = options['name'].replace(/\./g,'');
		var newChild=document.createElement('label');
		var newRadio =document.createElement('input');
		newRadio.type='radio';
		newRadio.id=key;
		newRadio.name=key;
		newRadio.value=one;
		var isChecked=false;
		if(value== one){
			isChecked =true;
		}
		console.log("myelem>>",value,one,isChecked);
		newRadio.checked =isChecked;
		var newText =document.createTextNode(valueOptions[one]);
		newChild.appendChild(newRadio);
		newChild.appendChild(newText);
		var newBr =document.createElement('br');
		root.appendChild(newChild);
		root.appendChild(newBr);
	}
	return root;
}
function cleanWhitespace(element) 
{ 
  for(var i=0; i<element.childNodes.length; i++) { 
      var node = element.childNodes[i]; 
     if(node.nodeType == 3 && !/&nbsp;/.test(node.nodeValue)) { 
      node.parentNode.removeChild(node); 
     } 
  }
}
function myValue(elem,oper,value){
	console.log("myValue>>",oper);
	if(oper=='get'){
		console.log("myValue>>get>>",$(elem).find(':checked').val());
		return $(elem).find(':checked').val();
	}
	if(oper=='set'){
		$('input[type="radio"]',elem).removeAttr("checked").prop("checked", false);
		$('input[type="radio"][value='+value+']',elem).attr("checked", "checked").prop("checked", true);
		console.log("myValue>>set>>",value);
	}
}

function myFormatter(cellvalue, options, rowObject){
	console.log("myFormatter>>",options['colModel']['editoptions']['value'][cellvalue]);
	return options['colModel']['editoptions']['value'][cellvalue];
}

function myUnformat(cellvalue, options, cell){
	
	var valueOptions =options['colModel']['editoptions']['value'];
	var value="";
	for(var one in valueOptions){
		if(valueOptions[one] == cellvalue){
			value = one;
			break;
		}
	}
	console.log("myUnformat>>",value);
	return value;
}
	</script>
</head>
<body>
	<h1>Struts 2 Hello World Example</h1>
 
			<table id="abcList"></table>
			<div id="abcPager"></div>
</body>
</html>