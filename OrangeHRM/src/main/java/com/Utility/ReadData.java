package com.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadData {
String data=null;
XSSFWorkbook workbook=null;
FileInputStream fis=null;

public String getData(String sheetName,int rowNo,int cellNo){
File file=new File("C:\\Users\\Gaurav-Admin\\Desktop\\ExcelFilereading\\RetreeDeta.xlsx");

try {
	fis=new FileInputStream(file);
} catch (FileNotFoundException e) {
	e.printStackTrace();
}

try {
	workbook=new XSSFWorkbook(fis);
} catch (IOException e) {
	
	e.printStackTrace();
}
XSSFSheet sheet=workbook.getSheet(sheetName);

DataFormatter dataFormat=new DataFormatter();

data=dataFormatCellValue(sheet.getRow(rowNo).getCell(cellNo));

return data;



	
}

private String dataFormatCellValue(XSSFCell cell) {
	return null;
}
}
