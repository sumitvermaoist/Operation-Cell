package com.readexcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class ExcelReaderLoadSurvey {


	List<LoadSurveyData> listAll=new ArrayList<LoadSurveyData>();
	LoadSurveyData loadSurveyData;
	
	public void readExcel(File inputFile)
	{
		try {
			FileInputStream file = new FileInputStream(inputFile);
			System.out.println(inputFile.getName());
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			HSSFSheet sheet = workbook.getSheetAt(0);
			Cell cell = null;

			//Update the value of cell
			for(int row=1;row<sheet.getLastRowNum();row++)
			{
				for(int col=0;col<28;col++)
				{
					cell=sheet.getRow(row).getCell(col);
					if(col==0){
					System.out.println(cell.getStringCellValue());
					loadSurveyData.setMeterNo("For Column 1 " +cell.getStringCellValue());
					}
					else if(col==1)
					{loadSurveyData.setDate(cell.getDateCellValue());
					System.out.println("For Column 1 " +cell.getDateCellValue());
					
					
					}
					
					
				}
				System.out.println(loadSurveyData.getMeterNo());
				System.out.println(loadSurveyData.getDate());
			}
				
			file.close();
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
