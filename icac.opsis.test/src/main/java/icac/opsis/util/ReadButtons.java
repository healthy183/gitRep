package icac.opsis.util;

import icac.opsis.model.ButtonModel;
import icac.opsis.model.FieldModel;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

public class ReadButtons {

	public static String doRead(Sheet sheet) {

		List<ButtonModel> btnList =new ArrayList<ButtonModel>();
		ButtonModel buttonModel = null;
		
		int numberOfRows = sheet.getPhysicalNumberOfRows();
		
		if(numberOfRows <= 0){
			return "";
		}

		Cell cell = null;
		
		
		 //String buttonName =  ""; 
		 String buttonLabel =  "";
		 boolean isSaveAndCancel= false;
		 String urlNamespace = "";
		 String actionName = "";
		 boolean isBack = false;
		 String showType = "";
		
		
		for(int i=1;i<numberOfRows;i++){
			
			
			cell= sheet.getRow(i).getCell(0);
			
			Object obj = PoiExcel.getCellValueForCell(cell);
			
			buttonLabel = obj.toString();
			
		    if(buttonLabel.equals("")){
		    	break;
		    }
		    
		    if(buttonLabel.toLowerCase().equals("save")){
		    	
		    	cell= sheet.getRow(i+1).getCell(0);
		    	
		    	Object nextCellobj = PoiExcel.getCellValueForCell(cell);
				
			    String nextbuttonName = obj.toString();
		    	
			    if(nextbuttonName.toLowerCase().equals("cancel")){
			    	
			    	isSaveAndCancel = true;
			    	
			    	i++;
			    }
			    
		    }
		    
		    cell = sheet.getRow(i+1).getCell(1);
		    
			actionName = PoiExcel.getCellValueForCell(cell).toString();
			
			urlNamespace =	actionName.substring(0,3).toLowerCase();
			
			urlNamespace = "/prototype/"+urlNamespace;
			
			
			if(actionName.equals("")){
				continue;
			}
			
			
			if(actionName.toLowerCase().equals("back")){

				isBack = true;
			}
		    
			 cell = sheet.getRow(i+1).getCell(1);
			 
			 showType = PoiExcel.getCellValueForCell(cell).toString();
			 
			 buttonModel = new ButtonModel( buttonLabel,
						 isSaveAndCancel,  urlNamespace,
						 actionName,  isBack,showType);
			 
			 
			 btnList.add(buttonModel);
			 
		}
		
		
		return upStr(btnList);
	}

	public static String upStr(List<ButtonModel> buttonModelList){
		
		String returnStr = "";
		
		String javaScriptStr = "<script>\n"
				+ "\"var moduleId = \"${moduleId}\"\n"
				+ "\"var currNavMenuLabel = \"${navigation}\"\n"
				+ "</script>\n"
				+ "$(function(){";
		
		String buttonStr = "";
		
		String urlStr = "";
		
		for(ButtonModel buttonModel : buttonModelList){
			
			String buttonName = buttonModel.getButtonName();
			
			
			if(buttonModel.isSaveAndCancel()){
				
				buttonStr = buttonStr + "[#assign saveButton=true /] \n";
				
				if(buttonModel.isBack()){
					
					javaScriptStr = javaScriptStr + "$(\"."+buttonName+"\").click(function(){ \n "+
							"window.history.back(); \n"+			
						"}); \n";
					
					if(buttonName.startsWith("save")){
						
						javaScriptStr = javaScriptStr + "$(\".saveBtn\").click(function(){ \n "+
								"window.history.back(); \n"+			
							"}); \n";
						
					}else{
						javaScriptStr = javaScriptStr + "$(\".cancelbtn\").click(function(){ \n "+
								"window.history.back(); \n"+			
							"}); \n";
					}
					
				};
				
			}else{
				
				buttonStr = buttonStr +	"[#assign customButtons=[{'name':\"searchBtn\", 'value':\"Search\"}] /]";
				
				if(buttonModel.isBack()){
					
					javaScriptStr = javaScriptStr + "$(\"#"+buttonName+"\").click(function(){ \n "+
							"window.history.back(); \n"+			
						"}); \n";
				}else if(buttonModel.getShowType().equals("popup")){
					
					urlStr = urlStr +
							"[@s.url var=\"  "+ buttonModel.getButtonVar()+" \" namespace="+buttonModel.getUrlNamespace()+" action="+buttonModel.getActionName()+" /]";
					
					javaScriptStr = javaScriptStr +	"$(\"#"+buttonName+"\").click(function(){ \n"+
						"var popupOptions = new Object(); \n"+
						"popupOptions.title = \"\"; \n"+
						"popupOptions.height = 250; \n"+
						"popupOptions.width = 400; \n"+
						"popupOptions.position = [\"center\", \"center\"]; \n"+
						"popupOptions.btnType = 3; \n"+
						"$.popup(\"${copyPersonParticularBtnAjax}\", popupOptions); \n"+	
						"}); \n";
					
					
				}
				
				
			}
			
		}
		
		
		return buttonStr + urlStr+	"<title>Screen ID : </title>\n <head>\n "
				+ "<link rel=\"stylesheet\" href=\"[@s.url value='/css/abc.css'/]\" />\n"+
				javaScriptStr	+ "}) \n </script>\n"; 
		
	
	}
	
	
	
}
