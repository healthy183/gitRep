package icac.opsis.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;


public class ExcelParser {
	public static void main(String[] args) {
		HashMap<String,Object> map =new HashMap<String,Object>();
		loadExcelByXsl(map,"F:\\opsis\\document\\02-Requirement_Analysis\\OPSIS_WS043\\OBJ230-010.xls");
		try {
			fileWriter("c:\\adb.ftl",map);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    public static void loadExcelByXsl(HashMap<String,Object> map,String fileName){  
        try {  
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(fileName));  
            readSheet(workbook,"Summary",map);
            readSheet(workbook,"Fields",map);
            readSheet(workbook,"Buttons|Links",map);
            //readSheet(workbook,"Table-Column Setting",map);
        } catch (FileNotFoundException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    
    private static void readSheet(HSSFWorkbook workbook,String sheetName,HashMap<String,Object> map) throws Exception{
        HSSFSheet sheet = workbook.getSheet(sheetName);  
        HSSFRow row =null;
        ArrayList<String> list=new ArrayList<String>();
        for (int i = 0; ; i++) {
        	 if(!"Summary".equals(sheetName)&&i==0)
        		 continue;
        	 row = sheet.getRow(i);
        	 if(row==null) break;
             if(row!=null){ 
            	 String key="";
            	 String value=" ";
            	if(row.getCell(0)!=null){
            		row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
            		key = row.getCell(0).getStringCellValue();
            	}
            	if(row.getCell(1)!=null){
            		row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
            		value = row.getCell(1).getStringCellValue();
            	}
                if("Buttons|Links".equals(sheetName)){
                	String remark=row.getCell(2)==null?" ":row.getCell(2).getStringCellValue();
                	list.add(key+"#"+value+"#"+remark);
                	continue;
                }
             	if(sheetName.startsWith("Table-")){
             		String value5=row.getCell(5)==null?" ":row.getCell(5).getStringCellValue();
                 	String value6=row.getCell(6)==null?" ":row.getCell(6).getStringCellValue();
                 	String value7=row.getCell(7)==null?" ":row.getCell(7).getStringCellValue();
                 	String value8=row.getCell(8)==null?" ":row.getCell(8).getStringCellValue();
                 	String key1 = value5;
                 	String value1 = value6+"#"+value7+"#"+value8;
                 	if("".equals(key1.trim())) continue;
                 	list.add(key1+": "+value1);
             	}
                 System.out.println(key+": "+value);  
                 map.put(key, value);
                 
              	if("Fields".equals(sheetName)){
                 	String value1=row.getCell(2)==null?" ":row.getCell(2).getStringCellValue();
                 	String value2=row.getCell(3)==null?" ":row.getCell(3).getStringCellValue();
                 	if("".equals(key)) continue;
             		value = value+"#"+value1+"#"+value2;
             		list.add(key+": "+value);
             	}
             }
		}
        if(sheetName.startsWith("Table-")){
           map.put("label", list);
        }
        if("Fields".equals(sheetName)){
            map.put("Fields", list);
         }
        if("Buttons|Links".equals(sheetName)){
            map.put("Links", list);
         }
    }
    
    public static void fileWriter(String fileName,HashMap<String,Object> map) throws IOException{
        //创建一个FileWriter对象
        FileWriter fw = new FileWriter(fileName);
        
        writeMainSummarycode(fw,map);
        
        if(map.get("Fields")!=null&&((ArrayList<String>)map.get("Fields")).size()>0){
        	writeDivComponentsFile(fw,(ArrayList<String>)map.get("Fields"));
        }
        
        if(map.get("label")!=null&&((ArrayList<String>)map.get("label")).size()>0){
        	writeTableList(fw,map);
        }
        
        //刷新缓冲区
        fw.flush();
        //关闭文件流对象
        fw.close();
    }
    
    public static void writeTableList(FileWriter fw,HashMap<String,Object> map) throws IOException{
    	fw.write("\n\t\t<div style=\"float:left\">");
    	String dataFileNm = (String) map.get("Data file");
    	String gridId = dataFileNm.substring(dataFileNm.lastIndexOf("-")+1, dataFileNm.length());
    	String gridData = gridId.substring(0, gridId.lastIndexOf("D"));
    	fw.write("\n\t\t\t[#assign "+gridId+"=[");
    	int k=0;
    	if(map.get("label")!=null&&((ArrayList<String>)map.get("label")).size()>0){
    		for (String string : (ArrayList<String>)map.get("label")) {
        		String [] keyValues =string.split(":");
    			String[] strs = keyValues[1].split("#");
        		String labelName = keyValues[0].trim();
        		boolean editable  = "Y".equals(strs[0].trim());
        		boolean mandatory  = "Y".equals(strs[1].trim());
        		String labelType = strs[2].trim();
        		wirteGridLabel(fw,labelType,labelName,editable,mandatory);
        		if(k++ !=((ArrayList<String>)map.get("label")).size()-1) 
        			fw.write(",");
             }
    	}
    	fw.write("\n\t\t\t   ]\n\t\t\t/]");
    	
    	String path = (String) map.get("Path");
    	String moduleIdPath = path.substring(path.lastIndexOf("/")-3,path.lastIndexOf("/"));
    	String gridDataUrl =(String) map.get("Data file");
    	fw.write("\n\t\t\t[@s.url var=\""+gridData+"\" namespace=\"/prototype/"+moduleIdPath+"\" action=\""+gridDataUrl+"\"/] ");
    	
    	fw.write("\n\t\t\t[@ui.datagrid");
    	fw.write("\n\t\t\t   id=\""+gridId+"\"\n\t\t\t   loadOnStart=true\n\t\t\t   sortname = \"null\"");
    	fw.write("\n\t\t\t   url=\"${"+gridData+"}\"");
    	fw.write("\n\t\t\t   className=\""+gridId+"\"");
    	fw.write("\n\t\t\t   elements=\""+gridId+"\"");
    	fw.write("\n\t\t\t   multiselect=\""+(String) map.get("multiselect")+"\"");
    	fw.write("\n\t\t\t   rowEdit=\""+(String) map.get("rowEdit")+"\"");
    	fw.write("\n\t\t\t   caption=\"\"");
    	fw.write("\n\t\t\t   rowNum=\""+(String) map.get("rowNum")+"\"");
    	fw.write("\n\t\t\t   addBtn=\""+(String) map.get("addBtn")+"\"");
    	fw.write("\n\t\t\t   delBtn=\""+(String) map.get("delBtn")+"\"");
    	fw.write("\n\t\t\t   editBtn=\""+(String) map.get("editBtn")+"\"");
    	fw.write("\n\t\t\t/]");
    	fw.write("\n\t\t</div>");
    }
    
    public static String string4Null(Object obj){
    	if(obj==null) return " ";
    	return (String)obj;
    }
    
    public static void writeMainSummarycode(FileWriter fw,HashMap<String,Object> map) throws IOException{
        fw.write("[#ftl]\n[#import \"/WEB-INF/view/components/componentsAPI.ftl\" as ui]\n");
        fw.write("[#assign navigation=\""+ string4Null(map.get("navigation"))+"\" /]\n");
        fw.write("[#assign moduleId=\""+ string4Null(map.get("moduleId"))+"\" /]\n");
        String path = (String) map.get("Path");
    	String moduleIdPath = path.substring(path.lastIndexOf("/")-3,path.lastIndexOf("/"));
    	String btnLinkHtmlFtl="";
		String btnLinkHtmlJS="";
    	if(map.get("Links")!=null&&((ArrayList<String>)map.get("Links")).size()>0){
        	Map<String,String> btnHtmls= writeBtnLink((ArrayList<String>)map.get("Links"),moduleIdPath);
        	btnLinkHtmlFtl = btnHtmls.get("ftl")==null?"":btnHtmls.get("ftl");
        	btnLinkHtmlJS = btnHtmls.get("js")==null?"":btnHtmls.get("js");
    	}
    	if(!"".equals(btnLinkHtmlFtl.trim())){
       	 fw.write(btnLinkHtmlFtl);
       }
        fw.write("[#-- [@s.url var=\"hereURL\" namespace=\"/prototype/"+moduleIdPath+" action=\"here\"/] --]\n");
        fw.write("[#-- [#assign customButtons=[{'name':\"hereBtn\", 'value':\"here\", 'url':\"${here}\"}] /] --]\n");	
        fw.write("[#-- [@ui.frame id=\"enquireDCPersonRecordFID\" contentClassName=\"\" collapsible=true active=true title=\"Search Criteria\" titlebarButtons=[{'name':\"searchBtn\", 'value':\"Search\"}]]    [/@ui.frame]--]\n");	
        fw.write("<title>Screen ID : "+ string4Null(map.get("Screen ID"))+"</title>\n");
        fw.write("<head>\n<script>\n\tvar moduleId = \"${moduleId}\";\n\tvar currNavMenuLabel = \"${navigation}\";\n");
        if(!"".equals(btnLinkHtmlJS.trim())){
        	 fw.write(btnLinkHtmlJS);
        }
        
        fw.write("</script>\n</head>\n<body>\n\t[#include \"../../decorators/navigation.ftl\"/]");
        fw.write("\n\t<div id=\"mainContent\"><!-- mainContent begin -->");
		fw.write("\n\t\t[#assign tableCase=[");
		fw.write("\n\t\t\t\t[\n\n\t\t\t\t]");
		fw.write("\n\t\t\t]\n\t\t]");
		fw.write("\n\t\t[@ui.divComponents");
		fw.write("\n\t\t  name=\"tableCase\"");
		fw.write("\n\t\t  elements=systemCase");
		fw.write("\n\t\t/]");
        fw.write("\n\n\n\t</div><!-- mainContent end -->\n</body>");
        
    }
    
    public static Map<String,String> writeBtnLink(ArrayList<String> list,String moduleIdPath){
    	HashMap<String,String> btnLinks =new HashMap<String,String>();
    	String btnLinkHtmlFtl="";
		String btnLinkHtmlJS="";
    	for (int i = 0; i < list.size(); i++) {
    		String [] btnAttrs = list.get(i).split("#");
    		String urlName ="";
    		String link ="";
    		String remarks ="";
    		for (int j = 0; j < btnAttrs.length; j++) {
				switch(j){
				case 0:
					  urlName = btnAttrs[j].trim();
					  break;
				case 1:
					link=btnAttrs[j].trim();
					  break;
				case 2:
					remarks=btnAttrs[j].trim();
					  break;
				}
			}
    		if("".equals(link)){
    			continue;
    		}
    		String urlNm = urlName.replaceAll("\\.|\\(|\\)|\\s|/", "");
			urlNm =urlNm.toLowerCase().substring(0, 1)+urlNm.substring(1,urlNm.length());
    		if("popup".equals(remarks)||"BACK".equals(link)){
    			String temp="";
    			if("popup".equals(remarks)){
    				temp = "\n\t\t$(\"#"+urlNm+"Btn\").click(function (){\n\t\t\tvar "+urlNm+"Options = new Object();\n\t\t\t"+urlNm+"Options.title = \"\";\n\t\t\t"+urlNm+"Options.height = 450;\n\t\t\t"+urlNm+"Options.width = 560;\n\t\t\t"+urlNm+"Options.position = [\"center\", \"center\"];\n\t\t\t$.popup(\"${"+urlNm+"URL}\", "+urlNm+"Options);";
    				btnLinkHtmlFtl +="[@s.url var=\""+urlNm+"URL\" namespace=\"/prototype/"+moduleIdPath+"\" action=\""+link+"\"/]\n";
    			}else{
    				temp = "\n\t\t$(\"."+urlNm+"Btn\").click(function(){\n\t\t\twindow.history.back();\n\t\t});";
    			}
    			btnLinkHtmlJS +=temp;
    		}else{
    			btnLinkHtmlFtl +="[@s.url var=\""+urlNm+"URL\" namespace=\"/prototype/"+moduleIdPath+"\" action=\""+link+"\"/]\n";
    		}
    		
		}
    	if(!"".equals(btnLinkHtmlFtl)){
    		btnLinks.put("ftl", btnLinkHtmlFtl);
    	}
    	if(!"".equals(btnLinkHtmlJS)){
    		btnLinkHtmlJS = "\n\t$(document).ready(function(){" +btnLinkHtmlJS +"\n\t})";
    		btnLinks.put("js", btnLinkHtmlJS);
    	}
    	return btnLinks;
    }
    public static void writeDivComponentsFile(FileWriter fw,ArrayList<String> list) throws IOException{

		int j =0;
    	for (String string : list) {
    		String [] keyValues =string.split(":");
			String[] strs = keyValues[1].split("#");
    		String fieldName = keyValues[0].trim();
    		
    		boolean editable  =false;
    		boolean mandatory  = false;
    		String divType = "";
    		for (int i = 0; i < strs.length; i++) {
				switch(i){
				case 0:
					  editable="Y".equals(strs[i].trim());
					  break;
				case 1:
					  mandatory="Y".equals(strs[i].trim());
					  break;
				case 2:
					divType=strs[i].trim();
					  break;
				}
			}
    		wirteDivComponent(fw,divType,fieldName,editable,mandatory);
    		if(j++ !=list.size()-1) 
    			fw.write(",");
         }
    }
    
    
    public static void wirteDivComponent(FileWriter fw,String divType,String fieldName,boolean editable,boolean mandatory) throws IOException{
    	editable = !editable;
    	fw.write("\n\t\t\t\t\t{");
		String fieldnm = fieldName.replaceAll("\\.|\\(|\\)|\\s|/", "");
		fieldnm =fieldnm.toLowerCase().substring(0, 1)+fieldnm.substring(1,fieldnm.length());
		
		if("".equals(divType.trim())||divType.toLowerCase().contains("textbox")){
			fw.write("\n\t\t\t\t\t\t'type':\"text\",");
			fw.write("\n\t\t\t\t\t\t'name':\""+fieldnm+"\",");
			fw.write("\n\t\t\t\t\t\t'className':\"\",");
			fw.write("\n\t\t\t\t\t\t'width':320,");
			if(mandatory){
				fw.write("\n\t\t\t\t\t\t'mandatory': true,");
			}
			fw.write("\n\t\t\t\t\t\t'disabled':"+editable+",");
			fw.write("\n\t\t\t\t\t\t'label':{'width':130, 'value':\""+fieldName+"\", 'className':\"\"},");
			fw.write("\n\t\t\t\t\t\t'fields':[{'width':150, 'value':\"\", 'className':\"\", 'disabled':"+editable+"}]");    		
		
		}else if(divType.toLowerCase().contains("combo")){
			String[] comboOpts=null; 
			if(divType.indexOf(":")>0){
				divType = divType.substring(divType.indexOf(":"), divType.length());
				comboOpts = divType.split(",");
			}
			fw.write("\n\t\t\t\t\t\t'type':\"combo\",");
			fw.write("\n\t\t\t\t\t\t'name':\""+fieldnm+"\",");
			fw.write("\n\t\t\t\t\t\t'className':\"\",");
			fw.write("\n\t\t\t\t\t\t'width':320,");
			fw.write("\n\t\t\t\t\t\t'disabled':"+editable+",");
			if(mandatory){
				fw.write("\n\t\t\t\t\t\t'mandatory': true,");
			}			
			fw.write("\n\t\t\t\t\t\t'label':{'width':130, 'value':\""+fieldName+"\", 'className':\"\"},");
			fw.write("\n\t\t\t\t\t\t'comboOpts':[{'width':150,'isSort':false, 'className':\"\", 'disabled':"+editable+","); 
			fw.write("\n\t\t\t\t\t\t   'value':{"); 
			if(comboOpts==null){
				fw.write("\n\t\t\t\t\t\t     '':\"\"");
			}else{
				int i =0;
				for (String str : comboOpts) {
					String[] comboOpt = str.split(":");
					if(i++==comboOpts.length-1){
						fw.write("\n\t\t\t\t\t\t     "+comboOpt[0]+":"+comboOpt[1]);
						break;
					}
					fw.write("\n\t\t\t\t\t\t     "+comboOpt[0]+":"+comboOpt[1]+",");
				}
			}
			fw.write("\n\t\t\t\t\t\t   }\n\t\t\t\t\t\t}]");
		}else if(divType.toLowerCase().contains("textarea")){

			fw.write("\n\t\t\t\t\t\t'type':\"textarea\",");
			fw.write("\n\t\t\t\t\t\t'name':\""+fieldnm+"\",");
			fw.write("\n\t\t\t\t\t\t'className':\"\",");
			fw.write("\n\t\t\t\t\t\t'width': 800,");
			fw.write("\n\t\t\t\t\t\t'disabled':"+editable+",");
			if(mandatory){
				fw.write("\n\t\t\t\t\t\t'mandatory': true,");
			}			
			fw.write("\n\t\t\t\t\t\t'label':{'width':130, 'value':\""+fieldName+"\", 'className':\"\"},");
			fw.write("\n\t\t\t\t\t\t'fields':[{'width': 314, 'height': 47,  'value':\"\", 'className':\"\", 'disabled':"+editable+"}]");
		
	    }else if(divType.toLowerCase().contains("date")){
	    	fw.write("\n\t\t\t\t\t\t'type':\"date\",");
			fw.write("\n\t\t\t\t\t\t'name':\""+fieldnm+"\",");
			fw.write("\n\t\t\t\t\t\t'className':\"\",");
			fw.write("\n\t\t\t\t\t\t'width': 320,");
			fw.write("\n\t\t\t\t\t\t'disabled':"+editable+",");
			if(mandatory){
				fw.write("\n\t\t\t\t\t\t'mandatory': true,");
			}			
			fw.write("\n\t\t\t\t\t\t'label':{'width':130, 'value':\""+fieldName+"\", 'className':\"\"},");
			fw.write("\n\t\t\t\t\t\t'date':{'width':85, 'value':\"\", 'className':\"\", 'disabled':"+editable+"}");
		}else if(divType.toLowerCase().contains("hkid")){
	    	fw.write("\n\t\t\t\t\t\t'type':\"hkid\",");
			fw.write("\n\t\t\t\t\t\t'name':\""+fieldnm+"\",");
			fw.write("\n\t\t\t\t\t\t'className':\"\",");
			fw.write("\n\t\t\t\t\t\t'width': 320,");
			fw.write("\n\t\t\t\t\t\t'disabled':"+editable+",");
			if(mandatory){
				fw.write("\n\t\t\t\t\t\t'mandatory': true,");
			}			
			fw.write("\n\t\t\t\t\t\t'label':{'width':130, 'value':\""+fieldName+"\", 'className':\"\"},");
			fw.write("\n\t\t\t\t\t\t'fields': [{'name': \"hkid_num\", 'width': 100, 'value': '', 'checkDigit': \"\", 'disabled': false, 'className': ''}]");
		}else if(divType.toLowerCase().contains("radio")||divType.toLowerCase().contains("checkbox")){
			if(divType.toLowerCase().contains("checkbox")){
				fw.write("\n\t\t\t\t\t\t'type':\"checkbox\",");
			}else{
				fw.write("\n\t\t\t\t\t\t'type':\"radio\",");
			}
			
			fw.write("\n\t\t\t\t\t\t'name':\""+fieldnm+"\",");
			fw.write("\n\t\t\t\t\t\t'className':\"\",");
			fw.write("\n\t\t\t\t\t\t'width':320,");
			fw.write("\n\t\t\t\t\t\t'disabled':"+editable+",");
			if(mandatory){
				fw.write("\n\t\t\t\t\t\t'mandatory': true,");
			}			
			fw.write("\n\t\t\t\t\t\t'label':{'position': \"right\",'width':90, 'value':\"\", 'className':\"\"},");
			fw.write("\n\t\t\t\t\t\t'opts': [{ 'label': \""+fieldName+"\", 'width': 100, 'position': \"right\", 'className': \"paddingBottom\"}]");  
			fw.write("\n\t\t\t\t\t\t\t'props': {'width':20, 'checked': false, 'value':  \""+fieldnm+"\"} }");   
			fw.write("\n\t\t\t\t\t\t]");   	
		}else if(divType.toLowerCase().contains("datetime")){
			fw.write("\n\t\t\t\t\t\t'type':\"datetime\",");
			fw.write("\n\t\t\t\t\t\t'name':\""+fieldnm+"\",");
			fw.write("\n\t\t\t\t\t\t'className':\"\",");
			fw.write("\n\t\t\t\t\t\t'width':320,");
			fw.write("\n\t\t\t\t\t\t'disabled':"+editable+",");
			if(mandatory){
				fw.write("\n\t\t\t\t\t\t'mandatory': true,");
			}			
			fw.write("\n\t\t\t\t\t\t'label':{'width':130, 'value':\""+fieldName+"\", 'className':\"\"},");
			fw.write("\n\t\t\t\t\t\t'date': {'width': 100,'className': \"\", 'value': \"\"},"); 	
			fw.write("\n\t\t\t\t\t\t'time': {'width': 50,'className': \"\", 'value': \"\"}");     			
		}
		fw.write("\n\t\t\t\t\t}");
    }
    
    public static void wirteGridLabel(FileWriter fw,String labelType,String fieldName,boolean editable,boolean mandatory) throws IOException{
    	
		String fieldnm = fieldName.replaceAll("\\.|\\(|\\)|\\s|/", "");
		fieldnm =fieldnm.toLowerCase().substring(0, 1)+fieldnm.substring(1,fieldnm.length());
    	String diffHtml ="";
        if(editable){
        	diffHtml += "'editable':true"; 
    	}
        if(mandatory){
        	diffHtml += ",'mandatory': true"; 
    	}
    	if(labelType.toLowerCase().contains("date")){
    		diffHtml = ", 'edittype': \"date\"";
    	}else if(labelType.toLowerCase().contains("datetime")){
    		diffHtml = ", 'edittype': \"datetime\"";
    	}else if(labelType.toLowerCase().contains("combo")){
    		diffHtml = ", 'edittype': \"select\", 'editoptions': \"{'value': \":&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp\"}";
    	}else if(labelType.toLowerCase().contains("checkbox")){
    		diffHtml = ", 'edittype':\"checkbox\", 'editoptions': \"{value: 'Y:N'}\"";
    	}else if(labelType.toLowerCase().contains("radio")){
    		diffHtml = ", 'edittype':\"radio\", 'editoptions': \"{value: 'Y:N'}\"";
    	}else if(labelType.toLowerCase().contains("textarea")){
    		diffHtml = ", 'edittype':\"textarea\", 'editoptions':'{rows:10,cols:40}'";
    	}
    	
    	String labelHtml = "{'label':\""+fieldName+"\", 'name':\""+fieldnm+"\", 'width': 100"+diffHtml+"}";
    	
    	fw.write("\n\t\t\t\t"+labelHtml);
    }
    
}

