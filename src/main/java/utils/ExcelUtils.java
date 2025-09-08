package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtils {
    private static Workbook workbook;
    private static Sheet sheet;
    public  void setExcelFile(String path, String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
    }

    public  String getCellData(int rowNum, int colNum) {
        Cell cell = sheet.getRow(rowNum).getCell(colNum);
        return cell.getStringCellValue();
    }
    
    public  int getRowCount() {
        return sheet.getPhysicalNumberOfRows();
    }
    
    public  Workbook getWorkbook() {
        return workbook;
    }
    
    public Sheet getSheet() {
        return sheet;
    }   
    
    public  void setCellData(String value, int rowNum, int colNum, String path) throws IOException {
        Row row = sheet.getRow(rowNum);
        if (row == null) {
            row = sheet.createRow(rowNum);
        }
        Cell cell = row.getCell(colNum);
        if (cell == null) {
            cell = row.createCell(colNum);
        }
        cell.setCellValue(value);

        // Save changes back to file
        FileOutputStream fos = new FileOutputStream(path);
        workbook.write(fos);
        fos.close();
    }
}