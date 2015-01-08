package icac.opsis.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
/**
 * Excel�����ࡣ
 * �����ṩ�˼򵥵Ļ�ȡָ��cell��ָ��cell������ݵķ�����
 *
 */
public class PoiExcel {
	//private static final Log log = LogFactory.getLog(PoiExcel.class);
	String filePath;
	HSSFWorkbook wb;
	private final static SimpleDateFormat fullTimeFmt = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private final static SimpleDateFormat fullDateFmt = new SimpleDateFormat(
			"yyyy-MM-dd");

	public PoiExcel(InputStream is) {
		POIFSFileSystem fs;
		try {
			fs = new POIFSFileSystem(is);
			wb = new HSSFWorkbook(fs);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public PoiExcel() {
		wb = new HSSFWorkbook();
	}

	public PoiExcel(String filePath) {
		this.filePath = filePath;
		POIFSFileSystem fs;
		try {
			fs = new POIFSFileSystem(new FileInputStream(filePath));
			wb = new HSSFWorkbook(fs);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e.getMessage() + filePath, e);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage() + filePath, e);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage() + filePath, e);
		}
	}

	class Point {
		public Point(String cellPositionStr) {
			char[] chars = cellPositionStr.toCharArray();
			int i = 0;
			for (; i < chars.length; i++) {
				if (Character.isDigit(chars[i])) {
					break;
				}
			}
			row = Integer.parseInt(cellPositionStr.substring(i)) - 1;
			col = cellNumStr2Int(cellPositionStr.substring(0, i));
		}

		public Point(String colStr, int row) {
			col = cellNumStr2Int(colStr);
			this.row = row;
		}

		int row;
		int col;
	}

	/**
	 * ��ȡsheet��Ŀ��
	 * 
	 * @return
	 */
	public int getSheetCnt() {
		return this.wb.getNumberOfSheets();
	}

	/**
	 * ��Excel�е�ĳ��sheet��ĳ����Ԫ��ֵ��
	 * 
	 * @param cellPositionStr
	 *            λ�ò�����A12��ʾA�У�12�С�
	 * @param sheetNo
	 * @param v
	 * @return
	 */
	public HSSFCell setCellValue(String cellPositionStr, int sheetNo, Object v) {
		Point p = new Point(cellPositionStr);
		return setCellValue(p, sheetNo, v);
	}

	public HSSFCell setCellValue(String cellPositionStr, Object v) {
		Point p = new Point(cellPositionStr);
		return setCellValue(p, 0, v);
	}

	/**
	 * ��Excel�е�ĳ��sheet��ĳ����Ԫ��ֵ��
	 * 
	 * @param colNumStr
	 *            ��һ��
	 * @param rowNum
	 * @param sheetNo
	 * @param v
	 * @return
	 */
	public HSSFCell setCellValue(String colNumStr, int rowNum, int sheetNo,
			Object v) {
		Point p = new Point(colNumStr, rowNum);
		return setCellValue(p, sheetNo, v);
	}

	public HSSFCell setCellValue(Point p, int sheetNo, Object v) {
		return setCellValue(p.col, p.row, sheetNo, v);
	}

	/**
	 * ��Excel�е�ĳ��sheet��ĳ����Ԫ��ֵ��
	 * 
	 * @param colNum
	 * @param rowNum
	 *            ��0��ʼ��
	 * @param sheetNo
	 *            ��0��ʼ��
	 * @param v
	 * @return
	 */
	public HSSFCell setCellValue(int colNum, int rowNum, int sheetNo, Object v) {
		HSSFCell cell = this.getCell(colNum, rowNum, sheetNo);
		if (v == null) {
			cell.setCellValue(new HSSFRichTextString(""));// TODO
															// ��ӵ�ֵ���Ե�Ԫ���ʽΪ׼����������������Ϊ׼��
			return cell;
		}
		if (v.getClass() == Boolean.class) {
			cell.setCellValue((Boolean) v);
		} else if (v.getClass() == Integer.class) {
			cell.setCellValue((Integer) v);
		} else if (v.getClass() == Double.class) {
			cell.setCellValue((Double) v);
		} else if (v.getClass() == Float.class) {
			cell.setCellValue((Float) v);
		} else if (v.getClass() == BigDecimal.class) {
			cell.setCellValue(((BigDecimal) v).doubleValue());
		} else if (v instanceof Date) {
			cell.setCellValue(new HSSFRichTextString(fullTimeFmt
					.format((Date) v)));// TODO Ȩ��֮��
			// }else if(v instanceof oracle.sql.TIMESTAMP){
			// oracle.sql.TIMESTAMP vx = (oracle.sql.TIMESTAMP)v;
			// try {
			// cell.setCellValue(new
			// HSSFRichTextString(fullTimeFmt.format(vx.timestampValue())));
			// } catch (SQLException e) {
			// throw new RuntimeException(e.getMessage(),e);
			// }
		} else if (v.getClass() == String.class) {
			String cellStr = (String) v;
			if (cellStr.length() >= 32766) {
				cellStr = cellStr.substring(0, 32765);
				/*if (log.isWarnEnabled()) {
					log.warn("str has been substring(0, 32765) for cell ("
							+ sheetNo + "," + rowNum + "," + colNum + ")");
				}*/
			}
			cell.setCellValue(new HSSFRichTextString(cellStr));
		} else {
			cell.setCellValue(new HSSFRichTextString(v.toString()));
		}
		return cell;
	}

	/**
	 * ����ָ�����к�sheet��ȡ��Ԫ��
	 * 
	 * @param rowNum
	 * @param cellNum
	 * @param sheetNo
	 * @return
	 */
	public HSSFCell getCell(int colNum, int rowNum, int sheetNo) {
		HSSFRow row = getRow(rowNum, sheetNo);
		HSSFCell cell = row.getCell(colNum);
		if (cell == null)
			cell = row.createCell(colNum);
		return cell;
	}

	public HSSFCell getCell(String colNumStr, int rowNum, int sheetNo) {
		int colNum = cellNumStr2Int(colNumStr);
		return getCell(colNum, rowNum, sheetNo);
	}

	public HSSFCell getCell(String cellPositionStr, int sheetNo) {
		Point p = new Point(cellPositionStr);
		return getCell(p.col, p.row, sheetNo);
	}

	public HSSFSheet getSheetAt(int num) {
		return wb.getSheetAt(num);
	}

	/**
	 * �ϲ���
	 * 
	 * @param sheetNum
	 * @param firstRow
	 * @param lastRow
	 * @param firstCol
	 * @param lastCol
	 */
	public void addMergedRegion(int sheetNum, int firstRow, int lastRow,
			int firstCol, int lastCol) {
		HSSFSheet sheet = getSheetAt(sheetNum);
		sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol,
				lastCol));// ָ���ϲ�����
	}

	/**
	 * ��ȡĳһ�С�
	 * 
	 * @param rowNum
	 * @param sheetNo
	 * @return
	 */
	public HSSFRow getRow(int rowNum, int sheetNo) {
		HSSFSheet sheet = null;
		if (sheetNo >= wb.getNumberOfSheets()) {
			sheet = wb.createSheet("sheet-" + sheetNo);
		} else {
			sheet = wb.getSheetAt(sheetNo);
		}
		HSSFRow row = sheet.getRow(rowNum);
		if (row == null)
			row = sheet.createRow(rowNum);
		return row;
	}

	/**
	 * ���е�����ת��Ϊ���֡�
	 * 
	 * @param cellNumStr
	 * @return
	 */
	private static int cellNumStr2Int(String cellNumStr) {
		cellNumStr = cellNumStr.toLowerCase();
		int cellNum = 0;
		char[] chars = cellNumStr.toCharArray();
		int j = 0;
		for (int i = chars.length - 1; i >= 0; i--) {
			cellNum += (chars[i] - 'a' + 1) * Math.pow(26, j);
			j++;
		}
		return cellNum - 1;
	}

	public static String cellNumIntToStr(int colNum) {
		String colName = "";
		// for(int i=0;i<colNum/26+1;i++){
		// char c = (char)(colNum%26+'a'-i);
		// colName = c + colName;
		// colNum = colNum/26;
		// }
		// colNum++;
		do {
			char c = (char) (colNum % 26 + 'A');
			colName = c + colName;
			colNum = colNum / 26 - 1;
		} while (colNum >= 0);
		return colName;
	}

	/**
	 * ��excelд�뵽ĳ��������С�
	 * 
	 * @param out
	 */
	public void write(OutputStream out) {
		try {
			wb.write(out);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void save(String filePath) {
		try {
			OutputStream out = new FileOutputStream(new File(filePath));
			write(out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * ��ȡĳ����Ԫ���ֵ������һ���������жϡ�
	 * 
	 * @param cell
	 * @return
	 */
	public  Object getCellValue(HSSFCell cell) {
		Object value = null;
		if (cell != null) {
			int cellType = cell.getCellType();
			HSSFCellStyle style = cell.getCellStyle();
			short format = style.getDataFormat();
			switch (cellType) {
			case HSSFCell.CELL_TYPE_NUMERIC:
				double numTxt = cell.getNumericCellValue();
				if (format == 22 || format == 14)
					value = HSSFDateUtil.getJavaDate(numTxt);
				else
					value = numTxt;
				break;
			case HSSFCell.CELL_TYPE_BOOLEAN:
				boolean booleanTxt = cell.getBooleanCellValue();
				value = booleanTxt;
				break;
			case HSSFCell.CELL_TYPE_BLANK:
				value = null;
				break;
			case HSSFCell.CELL_TYPE_FORMULA:
				HSSFFormulaEvaluator eval = new HSSFFormulaEvaluator(
						(HSSFWorkbook) wb);
				eval.evaluateInCell(cell);
				value = getCellValue(cell);
				break;
			case HSSFCell.CELL_TYPE_STRING:
				HSSFRichTextString rtxt = cell.getRichStringCellValue();
				if (rtxt == null) {
					break;
				}
				String txt = rtxt.getString();
				value = txt;
				break;
			default:
				// System.out.println(cell.getColumnIndex()+" col cellType="+cellType);
			}
		}
		return value;

	}

	public static interface CellCallback {
		public void handler(HSSFCell cell);
	}

	/**
	 * �������еĵ�Ԫ��
	 * 
	 * @param callback
	 * @param sheetNo
	 */
	public void iterator(CellCallback callback, int sheetNo) {
		HSSFSheet sheet = wb.getSheetAt(sheetNo);
		if (sheet == null)
			return;
		int firstRowNum = sheet.getFirstRowNum();
		int lastRowNum = sheet.getLastRowNum();
		for (int i = firstRowNum; i <= lastRowNum; i++) {
			HSSFRow row = sheet.getRow(i);
			if (row == null)
				continue;
			for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
				HSSFCell cell = row.getCell(j);
				callback.handler(cell);
			}
		}
	}

	/**
	 * ��ȡĳ��excel��Ȼ����ת��ΪList��List��
	 * 
	 * @param source
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public List<List> excelToListList(int sheetNo) {
		// �����ǽ�excel�����ݶ��룬Ȼ����ݵ��뵽�����ݿ�Ľṹ��excel�Ľṹ��������δ���

		HSSFSheet sheet = wb.getSheetAt(sheetNo);
		int firstRowNum = sheet.getFirstRowNum();
		int lastRowNum = sheet.getLastRowNum();
		List rows = new ArrayList();
		for (int i = firstRowNum; i <= lastRowNum; i++) {
			HSSFRow row = sheet.getRow(i);
			if (row == null) {
				System.out.println("Excel.excelToListList()" + i + " filePath="
						+ filePath);
				continue;
			}
			List cellList = new ArrayList();
			for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
				Object value = null;
				HSSFCell cell = row.getCell(j);
				if (cell != null)
					value = getCellValue(cell);

				cellList.add(value);

			}
			rows.add(cellList);
		}
		return rows;
	}

	/**
	 * ��excelת����List<Map>��ʽ
	 * 
	 * @param sheetNo
	 *            ��Ҫת�����������ڵ�sheet�����(0,1,2...n)
	 * @return
	 */
	public List<Map> excelToMapList(int sheetNo) {
		HSSFSheet sheet = this.wb.getSheetAt(sheetNo);
		int firstRowNum = sheet.getFirstRowNum();
		return excelToMapList(sheetNo, firstRowNum, firstRowNum + 1);
	}

	/**
	 * ��excelת����List<Map>��ʽ
	 * 
	 * @param sheetNo��Ҫת�����������ڵ�sheet�����
	 *            (0,1,2...n)
	 * @param keyRowNo
	 *            ��Ϊkey���к� ��0,1,2...n��
	 * @param dataStartRowNo��һ�����ݵ��к�
	 *            ��1,2...n��
	 * @return
	 */
	public List<Map> excelToMapList(int sheetNo, int keyRowNo,
			int dataStartRowNo) {
		return excelToMapList(sheetNo, keyRowNo, keyRowNo, dataStartRowNo);
	}

	/**
	 * ����Ӷ��н��кϲ��õ���
	 * 
	 * @param sheetNo
	 * @param keyRowNoFrom
	 * @param keyRowNoTo
	 * @param dataStartRowNo
	 * @return
	 */
	public List<Map> excelToMapList(int sheetNo, int keyRowNoFrom,
			int keyRowNoTo, int dataStartRowNo) {
		HSSFSheet sheet = this.wb.getSheetAt(sheetNo);
		List rowMapList = new ArrayList();
		String[] keyList = new String[200];
		for (int i = keyRowNoFrom; i <= keyRowNoTo; i++) {
			HSSFRow mapKeyRow = sheet.getRow(i);
			String lstKey = null;
			for (int j = mapKeyRow.getFirstCellNum(); j < mapKeyRow
					.getLastCellNum(); j++) {
				HSSFCell col = mapKeyRow.getCell(j);
				String key = col.getRichStringCellValue().getString();
				String keyx = keyList[j];
				if (key == null) {
					key = keyx;
				} else if (keyx != null)
					key = keyx + key;

				if (key == null || "".equals(key)) {
					key = lstKey;
				}
				lstKey = key;

				keyList[j] = key;
			}
		}
		int lastRowNum = sheet.getLastRowNum();
		for (int i = dataStartRowNo; i <= lastRowNum; ++i) {
			HSSFRow dataRow = sheet.getRow(i);
			if (dataRow == null)
				continue;
			Map rowMap = new HashMap();
			for (int j = dataRow.getFirstCellNum(); j < dataRow
					.getLastCellNum(); ++j) {
				String key = keyList[j];
				if (key == null || key.equals("")) {
					continue;
				}
				Object value = getCellValue(dataRow.getCell(j));
				rowMap.put(key, value);
			}
			rowMapList.add(rowMap);
		}
		return rowMapList;
	}

	static interface RowCallBack {
		void handler(Map m);
	}

	public void iterateRows(HSSFSheet sheet, RowCallBack callBack,
			int keyRowNoFrom, int keyRowNoTo, int dataStartRowNo) {
		List rowMapList = new ArrayList();
		String[] keyList = new String[200];
		for (int i = keyRowNoFrom; i <= keyRowNoTo; i++) {
			HSSFRow mapKeyRow = sheet.getRow(i);
			String lstKey = null;
			for (int j = mapKeyRow.getFirstCellNum(); j < mapKeyRow
					.getLastCellNum(); j++) {
				HSSFCell col = mapKeyRow.getCell(j);
				String key = col.getRichStringCellValue().getString();
				String keyx = keyList[j];
				if (key == null) {
					key = keyx;
				} else if (keyx != null)
					key = keyx + key;

				if (key == null || "".equals(key)) {
					key = lstKey;
				}
				lstKey = key;

				keyList[j] = key;
			}
		}
		int lastRowNum = sheet.getLastRowNum();
		for (int i = dataStartRowNo; i <= lastRowNum; ++i) {
			HSSFRow dataRow = sheet.getRow(i);
			if (dataRow == null)
				continue;
			Map rowMap = new HashMap();
			for (int j = dataRow.getFirstCellNum(); j < dataRow
					.getLastCellNum(); ++j) {
				String key = keyList[j];
				if (key == null || key.equals("")) {
					continue;
				}
				Object value = getCellValue(dataRow.getCell(j));
				rowMap.put(key, value);
			}
			callBack.handler(rowMap);
		}
	}

	public void mapListToExcel(PoiExcel excel, List<Map> rs, Iterator it) {

	}

	/**
	 * ����srcRowNum��Ȼ����targetRowNum�����һ�С�
	 * 
	 * @param srcRowNum
	 * @return
	 */
	public HSSFRow createRow(int srcRowNum) {
		HSSFSheet sheet = wb.getSheetAt(0);
		int targetRowNum = sheet.getLastRowNum() + 1;
		return createRow(sheet, sheet, srcRowNum, targetRowNum);
	}

	/**
	 * ����srcRowNum��Ȼ����targetRowNum�����һ�С�
	 * 
	 * @param srcRowNum
	 * @param targetRowNum
	 * @return
	 */
	public HSSFRow createRow(int srcRowNum, int targetRowNum) {
		HSSFSheet sheet = wb.getSheetAt(0);
		return createRow(sheet, sheet, srcRowNum, targetRowNum);
	}

	/**
	 * ����srcRowNum��Ȼ����targetRowNum�����һ�С�
	 * 
	 * @param sheet
	 * @param srcRowNum
	 * @param targetRowNum
	 * @return
	 */
	public HSSFRow createRow(HSSFSheet srcSheet, HSSFSheet targetSheet,
			int srcRowNum, int targetRowNum) {
		HSSFRow srcRow = srcSheet.getRow(srcRowNum);
		HSSFRow newRow = targetSheet.createRow(targetRowNum);
		newRow.setHeight(srcRow.getHeight());
		int i = 0;
		for (Iterator<Cell> cit = srcRow.cellIterator(); cit.hasNext();) {
			Cell hssfCell = cit.next();
			// HSSFCell�е�һЩ����ת�Ƶ�Cell��
			HSSFCell cell = newRow.createCell(i++);
			CellStyle s = hssfCell.getCellStyle();
			cell.setCellStyle(hssfCell.getCellStyle());
		}
		return newRow;
	}

	public void deleteRow(int rowNum) {
		deleteRow(0, rowNum);
	}

	public void deleteRow(int sheetNo, int rowNum) {
		HSSFSheet sheet = wb.getSheetAt(sheetNo);
		sheet.shiftRows(rowNum, sheet.getLastRowNum(), -1);
	}

	/**
	 * ������ճ����ָ��λ�á�
	 * 
	 * @param sheet
	 * @param srcRow
	 * @param rowNum
	 * @return
	 */
	public HSSFRow copyAndInsertRow(HSSFSheet sheet, HSSFRow srcRow,
			int targetRowNum) {
		sheet.shiftRows(targetRowNum, sheet.getLastRowNum(), 1);
		HSSFRow newRow = sheet.getRow(targetRowNum);
		newRow.setHeight(srcRow.getHeight());
		int j = 0;
		for (Iterator<Cell> cit = srcRow.cellIterator(); cit.hasNext();) {
			Cell hssfCell = cit.next();
			// HSSFCell�е�һЩ����ת�Ƶ�Cell��
			HSSFCell cell = newRow.createCell(j++);
			cell.setCellStyle(hssfCell.getCellStyle());
		}
		for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
			CellRangeAddress s = null;
			s.getFirstColumn();
			CellRangeAddress region = sheet.getMergedRegion(i);
			if (region.getFirstRow() == srcRow.getRowNum()
					&& region.getLastRow() == region.getFirstRow()) {
				sheet.addMergedRegion(new CellRangeAddress(targetRowNum, region
						.getFirstColumn(), targetRowNum, region.getLastColumn()));
			}
		}
		return newRow;
	}

	public HSSFRow copyAndInsertRow(int sheetNo, int fromRowNum,
			int targetRowNum) {
		HSSFSheet sheet = wb.getSheetAt(sheetNo);
		HSSFRow srcRow = sheet.getRow(fromRowNum);
		return copyAndInsertRow(sheet, srcRow, targetRowNum);
	}

	public HSSFRow copyAndInsertRow(int fromRowNum, int targetRowNum) {
		return copyAndInsertRow(0, fromRowNum, targetRowNum);
	}

	public HSSFWorkbook getWb() {
		return wb;
	}

	public void setWb(HSSFWorkbook wb) {
		this.wb = wb;
	}

	public void setForceFormulaRecalculation(boolean v) {
		setForceFormulaRecalculation(wb.getSheetAt(0), v);
	}

	public void setForceFormulaRecalculation(HSSFSheet sheet, boolean v) {
		sheet.setForceFormulaRecalculation(v);
	}

	public static void main(String[] args) throws ParseException,
			FileNotFoundException, IOException {

		Map<String, PoiExcel> excelMap = new HashMap();
		File root = new File("F:\\�������������ҵ��Ϣ");
		File[] fs = root.listFiles();
		for (int i = 0; i < fs.length; i++) {
			File[] fss = fs[i].listFiles();
			if (fss == null)
				continue;
			for (int j = 0; j < fss.length; j++) {
				File f = fss[j];
				String fileName = f.getName();
				if (!fileName.toLowerCase().endsWith(".xls"))
					continue;
				PoiExcel excel = new PoiExcel(f.getAbsolutePath());
				List<List> l = excel.excelToListList(0);
				PoiExcel rootExcel = excelMap.get(fileName);
				if (rootExcel == null) {
					rootExcel = new PoiExcel();
					List header = l.get(0);
					// for (int k = 0; k < header.size(); k++) {
					// rootExcel.setCellValue(k, 0, 0, header.get(k));
					// }
					excelMap.put(fileName, rootExcel);
				}
				int lstRowNum = 0;
				try {
					lstRowNum = rootExcel.getSheetAt(0).getLastRowNum() + 1;
				} catch (Exception e) {
				}
				for (int k = 0; k < l.size(); k++) {
					List row = l.get(k);
					for (int kk = 0; kk < row.size(); kk++) {
						rootExcel.setCellValue(kk, k + lstRowNum, 0,
								row.get(kk));
					}
				}
			}
		}
		Iterator<String> it = excelMap.keySet().iterator();
		while (it.hasNext()) {
			String fileName = it.next();
			PoiExcel excel = excelMap.get(fileName);
			excel.save("d:/prjx/" + fileName);
		}

	}
	
	
	//��ȡ��Ԫ���ֵ 
	public static  Object getCellValue(HSSFCell cell,HSSFWorkbook wb) {
		Object value = null;
		if (cell != null) {
			int cellType = cell.getCellType();
			HSSFCellStyle style = cell.getCellStyle();
			short format = style.getDataFormat();
			switch (cellType) {
			case HSSFCell.CELL_TYPE_NUMERIC: // ����  
				double numTxt = cell.getNumericCellValue();
				if (format == 22 || format == 14)
					value = HSSFDateUtil.getJavaDate(numTxt);
				else
					value = numTxt;
				break;
			case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean 
				boolean booleanTxt = cell.getBooleanCellValue();
				value = booleanTxt;
				break;
			case HSSFCell.CELL_TYPE_BLANK: // ��ֵ  
				value = null;
				break;
			case HSSFCell.CELL_TYPE_FORMULA: // ��ʽ   http://blog.csdn.net/chosen0ne/article/details/5140816
				HSSFFormulaEvaluator eval = new HSSFFormulaEvaluator(
						(HSSFWorkbook) wb);
				eval.evaluateInCell(cell);
				value = getCellValue(cell,wb);
				break;
			case HSSFCell.CELL_TYPE_STRING: // �ַ���  
				HSSFRichTextString rtxt = cell.getRichStringCellValue();
				if (rtxt == null) {
					break;
				}
				String txt = rtxt.getString();
				value = txt;
				break;
			default:
			}
		}
		return value;

	}

	public static Object getCellValueForCell(Cell cell) {
		Object value = null;
		if (cell != null) {
			int cellType = cell.getCellType();
			//int style = cell.getCellStyle();
			//short format = style.getDataFormat();
			switch (cellType) {
			
			   case Cell.CELL_TYPE_FORMULA:  
                 //  value = "FORMULA value=" + cell.getCellFormula();  
                   value = cell.getCellFormula();  
                   break;  

               case Cell.CELL_TYPE_NUMERIC:  
                   if(HSSFDateUtil.isCellDateFormatted(cell)){  
                      // value = "DATE value="   + cell.getDateCellValue();  
                	   value = cell.getDateCellValue(); 
                   }else{  
                      // value = "NUMERIC value="   + cell.getNumericCellValue();  
                	   value = cell.getNumericCellValue();  
                   }  
                     
                   break;  

               case Cell.CELL_TYPE_STRING:  
                  // value = "STRING value="  + cell.getStringCellValue();  
            	  value = cell.getStringCellValue();  
                   break;  
                     
               case Cell.CELL_TYPE_BOOLEAN:  
                  // value = "BOOLEAN value="   + cell.getBooleanCellValue();  
            	   value = cell.getBooleanCellValue();  
            	   
                   cell.getDateCellValue();  
                     
                   break;  

               default:
			}
		}
		return new Object();

	}
	
	
	
	
	
}
