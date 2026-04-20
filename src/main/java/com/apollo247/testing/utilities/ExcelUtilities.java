
package com.apollo247.testing.utilities;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;

public class ExcelUtilities {

	public List<Map<String, String>> getExcelDataAsMap(String sheetName) throws Exception {

		List<Map<String, String>> list = new ArrayList<>();

		String FILE_PATH = "";

		FileInputStream fis = new FileInputStream(FILE_PATH);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheet(sheetName);

		Row header = sheet.getRow(0);

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {

			Row row = sheet.getRow(i);
			Map<String, String> map = new HashMap<>();

			for (int j = 0; j < row.getLastCellNum(); j++) {
				map.put(header.getCell(j).toString(), row.getCell(j).toString());
			}

			list.add(map);
		}

		return list;
	}

}
