package icac.opsis.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

public class TestExcel {

	@Test
	public void testCreate() throws IOException {
		
		 String prototypeName = null;
		
		
		String thisFile = "D://try//prototype";
		String readExcelFile = null;
		
		File file = new File("D://try//prototype");

		String[] listAraay = file.list();

		for (String str : listAraay) {

			boolean isxls = str.endsWith(".xls");

			if (isxls) {
				
				prototypeName =	str.substring(0,3).toLowerCase();
				
				
				readExcelFile = thisFile+"//"+str;

				File newFile = new File("D://try//test");

				newFile.mkdir();

				String[] fileNameArray = str.split("[.]");

				String fileAbsolutePath = newFile.getAbsolutePath() + "\\"
						+ fileNameArray[0] + ".ftl";
				
				
				FileOutputStream fileOutputStream = new FileOutputStream(fileAbsolutePath);
				/*out.write("text".getBytes());*/

				File writename = new File(fileAbsolutePath);
				writename.createNewFile(); 
				FileWriter fileWriter = new FileWriter(writename);
				BufferedWriter out = new BufferedWriter(fileWriter);
				
				Workbook book = null; 
				
				//book = new org.apache.poi.xssf.usermodel.XSSFWorkbook(new FileInputStream(fileName));
				 
				book = new HSSFWorkbook(new FileInputStream(readExcelFile)); 
				
				Sheet sheet = book.getSheetAt(0);
				
				Cell cell = null;
				
				String cellValue = null;
				
				List<String> summaryList = new ArrayList<String>();
				
				for(int i = 0;i<=2;i++){
				
					cell= sheet.getRow(i).getCell(1);
					
					if(cell != null){
						
						cell.setCellType(Cell.CELL_TYPE_STRING);
						
						cellValue = cell.getStringCellValue();
						
					}else{
						cellValue = "";
					}
					
					summaryList.add(cellValue);
					
				}
				
				summaryList.add(prototypeName);
				
				String mainContentTopStr = WriteMainContent.mainContentTop(summaryList);
				
				
				
				
				
				
				String fieldStr  = "";
				
				int numberOfSheets =  book.getNumberOfSheets();
				
				String sheetName = null;
				
				for(int i=1;i<numberOfSheets;i++){
					
					
					 sheet = book.getSheetAt(i);
					 
					 if(sheet == null){
						 break;
					 }
					 
					 sheetName =  sheet.getSheetName();
					 
					 if(StringUtils.isNotBlank(sheetName)){
						 
						 switch (sheetName) {
						 
						 case "Fields":
							 
							//sheet = book.getSheetAt(1); 
							fieldStr  =	fieldStr + ReadFields.mainMethod(sheet);
								
							//out.write(fieldStr);
							
						 break;
						 
						 case "Buttons|Links":
							 
							 
						String buttonStr =	 ReadButtons.doRead(sheet);
								
							
						
						
							 break;
							 
						 case "Tabs":
								
							 break;
							 

						default:
							break;
						}
						 
					 }else{
						 break; 
					 }
					 
					
					
				}
				
				
				WriteMainContent.mainContentEnd(out);

				out.flush();

				out.close();

			}

		}

	}

}
