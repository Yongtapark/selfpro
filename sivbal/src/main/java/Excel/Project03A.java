package Excel;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class Project03A {
public static void main(String[]args) {
	String fileName="src/main/java/Excel/bookList.xls";
	List<ExcelVO> data=new ArrayList<ExcelVO>();
	try(FileInputStream fis=new FileInputStream(fileName)) {
		HSSFWorkbook workbook=new HSSFWorkbook(fis);//실제 booklist를 가르키는 가상의 엑셀
		HSSFSheet sheet =workbook.getSheetAt(0);
		Iterator<Row> rows =sheet.rowIterator();//row를 열거형으로 가지고옴
		rows.next();//내용으로 이동하기위해서 한줄 건너뜀
		String[] imsi=new String[5];
		while(rows.hasNext()) {
			HSSFRow row=(HSSFRow) rows.next();
			Iterator<Cell>cells =row.cellIterator();
			int i=0;
			while(cells.hasNext()) {
				HSSFCell cell=(HSSFCell) cells.next();
				imsi[i]=cell.toString();
				i++;
			}
			//묶고(VO)->담고(List)
			ExcelVO vo=new ExcelVO(imsi[0],imsi[1],imsi[2],imsi[3],imsi[4]);
			data.add(vo);
		}
		showExcelData(data);
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
}
public static void showExcelData(List<ExcelVO> data) {
	for(ExcelVO vo:data){
		System.out.println(vo);
	}
}
}
