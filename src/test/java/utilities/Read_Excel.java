package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Read_Excel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		XSSFWorkbook ExcelWBook = null;
		XSSFSheet ExcelWSheet;
		// XSSFCell Cell;
		// XSSFRow Row;

		// Create an object of File class to open file
		File excelFile = new File("D:\\Convay_Automation\\AutomationTest\\TestData\\TestDataFile.xlsx");

		FileInputStream inputStream = null;

		// Create an object of FileInputstream to read data from file
		try {
			inputStream = new FileInputStream(excelFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Excel->workbook->Sheet->row->cell
		// Create object of XSSWorkbook to handle xlsx file
		try {
			ExcelWBook = new XSSFWorkbook(inputStream);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// To access workbook sheet
		ExcelWSheet = ExcelWBook.getSheetAt(0);

		// To get total row count
		int ttlRows = ExcelWSheet.getLastRowNum() + 1;

		// To get total cells count in a row
		int ttlCells = ExcelWSheet.getRow(0).getLastCellNum();

		for (int currentRow = 0; currentRow < ttlRows; currentRow++) {

			// To Launch chrome browser
			WebDriverManager.chromedriver().setup();
			WebDriver driver = new ChromeDriver();

			for (int currentCell = 0; currentCell < ttlCells; currentCell++) {
				System.out.print(ExcelWSheet.getRow(currentRow).getCell(currentCell).toString());
				System.out.print("\t");
			}
			System.out.println();
		}

		try {
			ExcelWBook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
