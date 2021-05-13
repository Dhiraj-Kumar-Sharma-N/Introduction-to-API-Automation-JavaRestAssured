package Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import io.restassured.path.json.JsonPath;

public class ReusableUtilities {

	public static JsonPath ParseStringtoJSON(String StringData) {

		// JSON PATH CLASS

		JsonPath jsobj = new JsonPath(StringData);

		return jsobj;
	}

	public static String GenerateStringFromResource(String path) throws IOException {

		return new String(Files.readAllBytes(Paths.get(path)));

	}

	public ArrayList<String> RetrieveExcelData(String SheetName, String BookName) throws IOException {
		ArrayList<String> inputarr = new ArrayList<String>();
		int rownum = 0;
		FileInputStream FISObj = new FileInputStream("./src/InpuOutputFiles/AddBookExcelData.xlsx");
		XSSFWorkbook WBObj = new XSSFWorkbook(FISObj);
		int TotalSheetNum = WBObj.getNumberOfSheets();

		for (int i = 0; i < TotalSheetNum; i++) {

			XSSFSheet sheet = WBObj.getSheetAt(i);

			if (sheet.getSheetName().equalsIgnoreCase(SheetName)) {

				WBObj.getSheetAt(i);

				Iterator<Row> row = sheet.iterator();
				Row FirstRow = row.next();
				Iterator<Cell> cell = FirstRow.iterator();

				int k = 0;
				while (cell.hasNext()) {

					Cell Columnname = cell.next();

					if (Columnname.getStringCellValue().equalsIgnoreCase("name")) {
						rownum = k;
					}
					k++;
				}
				while (row.hasNext()) {

					Row rows = row.next();
					if (rows.getCell(rownum).getStringCellValue().equalsIgnoreCase(BookName)) {

						Iterator<Cell> cellval = rows.cellIterator();
						while (cellval.hasNext()) {
							Cell cv = cellval.next();
							DataFormatter formatter = new DataFormatter();
							String CellValue = formatter.formatCellValue(cv);

							inputarr.add(CellValue);

						}

						System.out.println(inputarr);
					}

				}
			}
			WBObj.close();
		}

		return inputarr;

	}
}
