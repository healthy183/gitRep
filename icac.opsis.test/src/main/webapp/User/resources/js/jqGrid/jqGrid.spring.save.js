/****
 * @author : felix
 * @date   : 2013-12-09
 * @remarks: jqGrid client side with server side json data save modules
 * 
 ****/


	/******************************************************************************************************
	 * This is the jqgrid hidden values tree. gridid >> operRoot >> operParent >> operChild >> Input tags *
	 ******************************************************************************************************/
	// set debug to true to enter into debug mode
	var debug = false;
	
	 // generate rowid for adding new records with negative value id
	function generateRowId(gridid){
		var operRoot = gridid + "_cmd_root"; // root of the oper tree
		var operParent = gridid + "_add";
		var operParent2 = gridid + "_del";
		var length = $("#" + operRoot + " #" + operParent + " div").length + $("#" + operRoot + " #" + operParent2 + " div").length;
		return "a" + length;
	}
	
	// find the list index of the record and set it into name attribute 
	function getListIndex(gridid, rowid){
		var operRoot = gridid + "_cmd_root"; // root of the oper tree
		var index = $("#" + operRoot + " div div").size();
		var oper = ["add","edit","del"];
		$.each( oper, function( key, value ) {
			var operChildid = gridid + "_" + value + "_" + rowid;
			if ( $("#" + operChildid).length){
				index = $("#" + operChildid).attr("submitmodelindex");
				return false;
			}
		});
		return index;
	}
	
	// generate the hidden div root and the parent, children div
	function generateOperDivs(gridid, oper, rowid){
		var operParent = gridid + "_" + oper;
		var operChild = gridid + "_" + oper + "_" + rowid;
		var operRoot = gridid + "_cmd_root"; // root of the oper tree
		var index = getListIndex(gridid, rowid);
		if($("#" + operParent).length <= 0){ // oper div not exist
			if ($("#" + operRoot).length <=0) {
				// create root of oper
				if (debug){
					$("#" + gridid).append("<div id='" + operRoot + "' ' ></div>");
				} else {
					$("#" + gridid).append("<div id='" + operRoot + "' style='display:none' ></div>");
				}
			}
			// create parent + child
			$("#" + operRoot).append("<div id='" + operParent + "'><div id='" + operChild + "' submitmodelindex='" + index + "'></div></div>");
		}
		// create child
		else if( $("#" + operChild).length <= 0){ // oper div exist
			$("#" + operParent).append("<div id='" + operChild + "' submitmodelindex='" + index + "'></div>");
		}
		return operChild;
	}
	
	// record the values changes from the grid
	function generateHiddenChanges(gridid, oper, rowid, name, value, keycolumnmap, submitModel){
		var operChild = generateOperDivs(gridid, oper, rowid);
		var listIndex = $("#" + operChild).attr("submitmodelindex");
		var	elementid = gridid + "_" + rowid + "_" + name.replace(/\./g,'_');
		if ( $("#" + elementid).length > 0 ){ //exist (name is found)
			// change the value of the element
			$("#" + elementid).attr("value",value);
		}
		else { //new field
			var changedProperty = "";
			if (value) {
				changedProperty = $("<input type='text' id='" + elementid + "'/>");
				$(changedProperty).attr("name", submitModel + "[" + listIndex + "]." + name);
//				$(changedProperty).val(value);
				$(changedProperty).attr("value", value);
				$(changedProperty).attr("columnname", name);
				if (!debug) {
					$(changedProperty).hide();
				}
			}
			
			/*
			if (debug){
				var changedProperty = "<input type='text' id='" + elementid + "' name='" + submitModel + "[" + listIndex + "]." + name + "' value='" + value + "' columnname='" + name + "'/>";
			} else {
				var changedProperty = "<input type='hidden' id='" + elementid + "' name='" + submitModel + "[" + listIndex + "]." + name + "' value='" + value + "' columnname='" + name + "'/>";
			}
			if (!value){
				changedProperty = "";
			}
			
			//var keyPropertys = "";
			// generate key columns value under the grid
		    $.each( keycolumnmap, function( key, value ) {
	        	var keyid = gridid + "_" + rowid + "_" + key.replace(/\./g,'_');
	        	// for delete, remove key properties under "add" or "edit", and add them to "del"
	        	if (oper == "del"){
	        		$("#" + gridid + "_add_" + rowid).remove();
	        		$("#" + gridid + "_" + rowid + "_" + key).remove();
	        	}
	        	else if ($("#" + keyid).length > 0){
	        		return false;
	        	}
				if (debug){
					keyPropertys = keyPropertys + "<input type='text' key='true' keycolumnname='"+ key + "' id='" + gridid + "_" + rowid + "_" + key + "' name='" + submitModel + "[" + listIndex + "]." + key + "' value='" + value + "'/>";
				} else {
					keyPropertys = keyPropertys + "<input type='hidden' key='true' keycolumnname='"+ key + "' id='" + gridid + "_" + rowid + "_" + key + "' name='" + submitModel + "[" + listIndex + "]." + key + "' value='" + value + "'/>";
				}
				
	        	
	        	$("#" + operChild).append(keyPropertys);
		    });*/
		    // generate key columns value with element
			//$("#" + operChild).append(keyPropertys + changedProperty);
		    $("#" + operChild).append(changedProperty);
		}
	}
	
	// reload data from div
	function reloadrecordfromdiv(gridid, rowid){
		var	reloaddiv = gridid + "_edit_" + rowid;
		if ($("#" + reloaddiv).length > 0){
			$("#" + reloaddiv + " input[columnname]").each(function() {
				// get values
				var name = $(this).attr('columnname');
				var value = $(this).attr('value');
				// set values
				var td = $("#" + gridid + " tr[id=" + rowid + "] td[aria-describedby='" + gridid + "-table_" + name + "']");
				td.attr('title', value);
				var editcolumn = $("#" + gridid + "-table").getColProp(name);
				// for combo box
				if (editcolumn.edittype == 'select' ){
				    td.text(getValue(value, editcolumn.editoptions.value));
				}
				else {
					td.text(value);
				}
			});
		}
	}
	
	// mark row to modify
	function markmodify(gridid, rowid){
		var	modifydiv = gridid + "_edit_" + rowid;
		if ($("#" + modifydiv).length > 0){
			var tr = $("#" + gridid + " tr[id=" + rowid + "]");
			tr.addClass("ui-row-edit");
		}
	}
	
	// mark row to delete
	function markdelete(gridid, rowid){
		var	deletediv = gridid + "_del_" + rowid;
		if ($("#" + deletediv).length > 0){
			var tr = $("#" + gridid + " tr[id=" + rowid + "]");
			tr.addClass("ui-row-delete");
		}
	}
	
	// mark row to add
	function markadd(gridid, rowid){
		var	adddiv = gridid + "_add_" + rowid;
		if ($("#" + adddiv).length > 0){
			var tr = $("#" + gridid + " tr[id=" + rowid + "]");
			tr.addClass("ui-row-add");
		}
	}
	
	// update the pager in jqGrid with both server json and client data
	function updatepagingforcreate(gridid, paging, currentpageserverrecords){
		// clientRecords is the number of newly created records 
		paging['clientRecords'] = $("#" + gridid + "_add div").length;
		// clientRecords is the number of records loaded from server
		paging['serverRecords'] = paging['records'];
		paging['total'] = Math.ceil((paging['clientRecords'] + paging['serverRecords'])/paging['rows']);
		// if user is browsing last page and delete all new created records in the last page
		// , it will reload and redirect back to the new last page(last page - 1)
		if (paging['currentPage'] > paging['total'] && paging['total']!=0){
			paging['currentPage'] = paging['total'];
			// reload the grid after loadcomplete
			paging['reload'] = true;
			setTimeout(function(){
		    	$("#" + gridid + "-table").trigger("reloadGrid");
			}, 1);
		}
		paging['records'] = paging['clientRecords'] + paging['serverRecords'];
		if( paging['offset'] + paging['rows'] > paging['serverRecords'] ){
			// display last page records
			if ( paging['currentPage'] == paging['total']){
				paging['records'] = paging['offset'] + currentpageserverrecords ;
			}
			// display mid page records
			else {
				paging['records'] = paging['records'] - ( paging['rows'] - currentpageserverrecords );
			}
		}
	}
	
	// get the records of current page of jqGrid
	function getcurrentpagerecords(gridid, paging){
		var currentPage = paging['currentPage'], 
			rows = paging['rows'], 
			serverRecords = paging['serverRecords'],
			clientRecords = paging['clientRecords'],
			offset = paging['offset'],
			// if any clientside records in this page (count from first client record to the end of current page)
			difference = currentPage * rows - serverRecords, 
			remainder = difference%rows,
			quotient = Math.floor(difference/rows);
		var start = 1,
			end = 0;
		if (difference <= rows){
			// set remainder to rows if remainder is a mulitple of rows 
			if (remainder == 0){
				remainder=rows;
			}
			// display first n records
			if (clientRecords <= remainder){
				end = clientRecords;
			}
			else {
				end = remainder;
			}
		} else { // difference > rows
			var start = quotient * rows - (rows - remainder ) + 1,
				end = start + rows -1;
			if ((currentPage == paging['total']) && ((clientRecords - remainder)%rows >0)){
				end = start + (clientRecords - remainder)%rows -1;
			}
		}
		for (var i = start; i <= end ; i++){
			getcreaterecordfromdiv(gridid, i);
		}
	}
	
	// get records from the hidden div
	function getcreaterecordfromdiv(gridid, index){
		var adddiv = $("#" + gridid + "_add div:nth-child(" + index + ")");
		/*
		var createdatamap = '{';
		if ($(adddiv).children().length>0){	
			$(adddiv.children()).each(function() {
				// get values
				var name = $(this).attr('columnname');
				if ($(this).attr("key")){
					name = $(this).attr('keycolumnname');
				}
				var value = $(this).attr('value');
				// set values
				createdatamap = createdatamap + '"' + name + '":"' + value + '",';			
			});
			createdatamap = createdatamap.substring(0,createdatamap.length-1) + '}';
			$("#"+gridid + "-table").addRowData(adddiv.children()[0].value, JSON.parse(createdatamap), "last");	
		}
		*/
		var createdatamap = {};
		if ($(adddiv).children().length>0) {
			$(adddiv.children()).each(function() {
				// get values
				var name = $(this).attr('columnname');
				if ($(this).attr("key")){
					name = $(this).attr('keycolumnname');
				}
				var value = $(this).attr('value');
				// set values
				createdatamap[name] = value;
			});
			$("#"+gridid + "-table").addRowData(adddiv.children()[0].value, createdatamap, "last");	
		}
	}
	
	// save or other submit button click, trigger this function before submit to server
	function beforeFormSubmit(gridid, submitModel){
		// remove all fake key columns from create
		$("#" + gridid + "_add input[key='true']").remove();
		// clear records which are being deleted under the create and modify div
		$.each( $("#" + gridid + "_del div"), function() {
			$("#" + gridid + "_edit div[submitModelIndex='" + $(this).attr("submitModelIndex") + "']").remove();
			$("#" + gridid + "_del div[id^='" + gridid + "_del_a']").remove();
		});
		
		var hiddenEle = $("#" + gridid + "_cmd_root div div");
        $.each( hiddenEle, function(idx, value) {
            $(this).attr("index", idx );
            var children = $(this).children("input");
            $.each(children, function(i, child) {
            	var name = $(this).attr('columnname');
                if ($(this).attr("key")){
                	name = $(this).attr('keycolumnname');
                }
            	$(this).attr("name", submitModel + "[" + idx + "]." + name );
            });
        });
	}
