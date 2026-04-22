
package com.apollo247.testing.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

public class ExcelUtilities {
	WebDriver driver;
	Workbook workbook;

	private static final String FILE_PATH = "./src/test/resources/Reader/Apollo247_TestData.xlsx";

	public ExcelUtilities(WebDriver driver) {
		this.driver = driver;
	}

	public static List<Map<String, String>> getSheetData(String sheetName) {

		List<Map<String, String>> dataList = new ArrayList<>();

		try (FileInputStream fis = new FileInputStream(FILE_PATH); Workbook workbook = new XSSFWorkbook(fis)) {

			Sheet sheet = workbook.getSheet(sheetName);
			if (sheet == null)
				return dataList;

			Row headerRow = sheet.getRow(0);
			if (headerRow == null)
				return dataList;

			DataFormatter formatter = new DataFormatter();

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {

				Row row = sheet.getRow(i);
				if (row == null)
					continue;

				Map<String, String> rowData = new HashMap<>();

				for (int j = 0; j < headerRow.getLastCellNum(); j++) {

					String key = formatter.formatCellValue(headerRow.getCell(j));
					String value = formatter.formatCellValue(row.getCell(j));

					rowData.put(key, value);
				}

				dataList.add(rowData);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return dataList;
	}
}