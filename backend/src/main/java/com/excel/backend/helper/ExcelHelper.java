package com.excel.backend.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.excel.backend.model.Tutorial;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ExcelHelper {
	static String SHEET = "Tutorials";

	public static List<Tutorial> excelToTutorial(InputStream is) {
		try {
			Workbook workbook = new XSSFWorkbook(is);

			Sheet sheet = workbook.getSheet(SHEET);
			Iterator<Row> rows = sheet.iterator();

			List<Tutorial> tutorials = new ArrayList<Tutorial>();

			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();

				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				Iterator<Cell> cellsInRow = currentRow.iterator();
				Tutorial tutorial = new Tutorial();

				int cellIdx = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();

					switch (cellIdx) {
					case 0:
						tutorial.setId(currentCell.getStringCellValue());
						break;

					case 1:
						tutorial.setTitle(currentCell.getStringCellValue());
						break;

					case 2:
						tutorial.setDescription(currentCell.getStringCellValue());
						break;

					case 3:
						tutorial.setPublished(currentCell.getBooleanCellValue());
						break;
					}
					cellIdx++;
				}
				tutorials.add(tutorial);
			}
			workbook.close();
			return tutorials;

		} catch (IOException e) {
			throw new RuntimeException("fail to parse Excel file:" + e.getMessage());
		}
	}
}
