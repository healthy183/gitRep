package icac.opsis.util;

import icac.opsis.model.FieldModel;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ReadFields {
	
	
	
	public static String mainMethod(Sheet sheet){
		
		int numberOfRows = sheet.getPhysicalNumberOfRows();
		
		if(numberOfRows <= 0){
			return "";
		}

		Cell cell = null;
		
		String fieldName = "";
		
		boolean isEditable = false;
		String isEditableStr = "false";
		
		boolean isMandatory = false;
		String isMandatoryStr = "false";
		
		String remarks = "";
		
		FieldModel fieldModel  = null;
		
		//String thisField = "";
		
		//List<String> fieldlist = new ArrayList<String>();
		
		//String assginField = "";
		
		String assginMsg = "[#assign case=[ \n [ \n ";
		
		//fieldlist.add(assginMsg);
		
		for(int i=1;i<numberOfRows;i++){
			
			Row row = sheet.getRow(i);
			
			if(row == null){
				break;
			}
			
			//row.getPhysicalNumberOfCells();
			
			cell= sheet.getRow(i).getCell(0);
			
			if(cell != null){
				
				cell.setCellType(Cell.CELL_TYPE_STRING);
				
				fieldName = cell.getStringCellValue();
				
				//if(StringUtils.isNotBlank(fieldName)){	}

					//editable
					cell = sheet.getRow(i).getCell(1);
					
					if(cell != null){
						
						cell.setCellType(Cell.CELL_TYPE_STRING);
						
						isEditableStr = cell.getStringCellValue();
						
						if("Y".equals(isEditableStr)){
							isEditable = true;
						}
						
					}
					
					//mandatory
					cell= sheet.getRow(i).getCell(2);
					
					if(cell != null){
						
						cell.setCellType(Cell.CELL_TYPE_STRING);
						
						isMandatoryStr = cell.getStringCellValue();
						
						if("Y".equals(isMandatoryStr)){
							isMandatory = true;
						}
					}
					
					//remarks
					cell= sheet.getRow(i).getCell(3);
					
					if(cell != null){
						
						cell.setCellType(Cell.CELL_TYPE_STRING);
						remarks = cell.getStringCellValue();
					}else{
						remarks = "";
					}
					
			
				    fieldModel = new FieldModel(fieldName,isEditable,isMandatory,remarks);
					
					assginMsg = assginMsg + upStr(fieldModel);	
					//fieldlist.add(thisField);
				
					 fieldName = "";
					 isEditable = false;
					 isEditableStr = "false";
					 isMandatory = false;
					 isMandatoryStr = "false";
					 remarks = "";
				
			}else{
				break;
			}
			
			
			
			
		}
		
		String assginMsgEnd = "] \n ] \n ] \n"
				+ "[@ui.divComponents\n"
				+ "name=\"case\"\n"
				+ "elements=case\n "
				+"/]\n";
		
		assginMsg = assginMsg + assginMsgEnd;
		
		return assginMsg;
	}
	
	
	public static String upStr(FieldModel fieldModel){
	
		String tabStr ="\t  ";
		String thisField = null;
		
		String noWhiteieldName = fieldModel.getNoWhiteieldName();
		boolean isEditable = fieldModel.isEditable();
		boolean isMandatory = fieldModel.isMandatory();
		String fieldName = fieldModel.getFieldName();
		String remarks = fieldModel.getRemarks();;
		
		
		if(remarks.equals("")){
			
		thisField =	"\t{\n"+
				tabStr+"'type':\"text\",\n"+
				tabStr+"'name':\""+noWhiteieldName +"\",\n"+
				tabStr+"'className':\"\"\",\n"+
				tabStr+"'width':220\",\n"+
				tabStr+"'disabled':"+(!isEditable)+",\n"+
				tabStr+"'mandatory':"+isMandatory+",\n"+
				tabStr+"'label':{'width':80, 'value':\" "+fieldName+" \", 'className':\"\"},\n"+
				tabStr+"'fields': [{'width': 125, 'value': \"\", 'disabled': false, 'className': \"\"}]\n"+
				tabStr+"}\n";
		}
		
		
		return thisField;
	}

}
