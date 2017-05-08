package com.readexcel;
import java.io.File;
import java.io.IOException;

import org.apache.poi.ss.extractor.ExcelExtractor;


public class ReadExcel {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		try{
		File folder=new File("C:\\OperationCell\\mpui");
		File allFiles[]=folder.listFiles();
		File fileName=null;
		for(File file : allFiles)
		{
			if(file.getName().substring(0, 4).equalsIgnoreCase("mpui"))
			{
				fileName=file;
				break;
				
			}
		}
		if(fileName!=null){
			//fileName=new File("C:\\OperationCell\\mpui\\mpui_5.xls");
			System.out.println(fileName);
			new ExcelReader().readXlsx(fileName);
		}
		}
		catch(Exception e)
		{
			System.out.println("File entered is corrupt or not in format of xls. Please save as xls and try again..");
			
		}
		
		
		try{
			File folder=new File("C:\\OperationCell\\loadsurvey");
			File allFiles[]=folder.listFiles();
			File fileName=null;
			for(File file : allFiles)
			{
				if(file.getName().substring(0, 4).equalsIgnoreCase("Load") )
				{
					fileName=file;
					break;
					
				}
			}
			if(fileName!=null){
				//fileName=new File("C:\\OperationCell\\mpui\\mpui_5.xls");
				System.out.println(fileName);
				new ExcelReaderLoadSurvey().readExcel(fileName);
			}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("File entered is corrupt or not in format of xls. Please save as xls and try again..");
				
			}
			
			
		
		
		
	}

}
