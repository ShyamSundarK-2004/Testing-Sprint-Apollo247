package com.apollo247.testing.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

public class ExcelUtilities {
	WebDriver driver;
    Workbook workbook;

    public ExcelUtilities(WebDriver driver) throws EncryptedDocumentException, IOException {
        this.driver = driver;
  
    }
    public static String getExcelData(String sheet, int row, int col) {
        try {
        	FileInputStream fls = new FileInputStream("./src/test/resources/Readers/Config.xlsx");
            XSSFWorkbook wb = new XSSFWorkbook(fls);
            String data = wb.getSheet(sheet).getRow(row).getCell(col).toString();
            wb.close();
            return data;
        } catch (Exception e) {
            return "";
        }
    }
}