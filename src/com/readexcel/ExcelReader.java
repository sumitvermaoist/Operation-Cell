package com.readexcel;


import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelReader{
	
	
	List<MpuiData> listAll=new ArrayList<MpuiData>();
	MpuiData mpuiData;
	
	
	 void readXlsx(File inputFile)
	{
		HSSFWorkbook wb=null;
		HSSFSheet sheet =null;
		try {
	
			//Get the workbook instance for XLSX file
				wb=new HSSFWorkbook(new FileInputStream(inputFile));
				sheet =wb.getSheetAt(0);
	
				FormulaEvaluator formulaeEvaluator=wb.getCreationHelper().createFormulaEvaluator();
		
		int rowCounter=1;
		for(Row row:sheet)
		{	
			
			//System.out.println();
			if(rowCounter==1)
			{
				rowCounter++;
			continue;	
				
			}
			
			
		mpuiData=new MpuiData();
		int cellCounter=1;	
		for(Cell cell:row)
			{
				if(cellCounter==4)
				break;
					
				switch(formulaeEvaluator.evaluateInCell(cell).getCellType())
				{
				
				case Cell.CELL_TYPE_NUMERIC:
					//System.out.print("Numeric "+cell.getNumericCellValue()+"\t");
					
					if(cellCounter==2)
					{
						mpuiData.setBlockNumber( (int) cell.getNumericCellValue());
						
					}
					if(cellCounter==3){
						
						mpuiData.setFrequency(cell.getNumericCellValue());
					}
					
				break;
				
				case Cell.CELL_TYPE_STRING:
					//System.out.print("String "+cell.getStringCellValue()+"\t");
				
					if(cellCounter==1)
					mpuiData.setDate(cell.getStringCellValue());
					
					break;
					
				case Cell.CELL_TYPE_BLANK:
					//System.out.print("Blank "+" "+"\t");
				break;

				case Cell.CELL_TYPE_BOOLEAN:
					//System.out.print(cell.getBooleanCellValue()+"\t");
				break;
				
				case Cell.CELL_TYPE_FORMULA:
					//System.out.print(cell.getCellFormula()+"\t");
				break;
				
				case Cell.CELL_TYPE_ERROR:
					//System.out.print(cell.getErrorCellValue()+"\t");
				break;
				
				default:
					System.out.println(cell);
				
				}
				cellCounter++;
			}
		System.out.println(mpuiData.getDate()+" "+mpuiData.getBlockNumber()+" "+mpuiData.getFrequency());
		listAll.add(mpuiData);
		
		
		}
		
		
		
		new MpuiService().addAll(listAll);
		//new MpuiServiceBatch().addAll(listAll);
		
		
		
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("File entered is not in xls format. Save the file in xls and try again...");
	}
	catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}