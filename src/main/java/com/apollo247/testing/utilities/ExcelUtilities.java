package com.apollo247.testing.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {

	Workbook workbook;

	private static final String FILE_PATH = "./src/test/resources/Reader/Apollo247_TestData.xlsx";

	// 🔥 Get data as STRING (reliable for all types)
	public String getExcelData(String sheetName, int rowNum, int colNum) {

		try (FileInputStream fis = new FileInputStream(FILE_PATH); Workbook wb = new XSSFWorkbook(fis)) {

			Sheet sheet = wb.getSheet(sheetName);
			Row row = sheet.getRow(rowNum);
			Cell cell = row.getCell(colNum);

			DataFormatter formatter = new DataFormatter();
			return formatter.formatCellValue(cell);

		} catch (IOException e) {
			throw new RuntimeException("Error reading Excel file: " + e.getMessage());
		}
	}
}