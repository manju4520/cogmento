package frameWork;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class DataUtil {
	
      public static Properties getpropertesFile(String propertiesfilepath) throws IOException {
    	 
    		  FileInputStream fis=new FileInputStream(propertiesfilepath);
        	  Properties props=new Properties();
        	  props.load(fis);
			return props;
       
      }
      
      
      public static HashMap<String, String> getTestCaseData(String filePath, String sheetName, String tcId){
  		HashMap<String, String> tcData = new HashMap<>();	
  		
  		try {
  			FileInputStream fis = new FileInputStream(filePath);			
  			XSSFWorkbook wb = new XSSFWorkbook(fis);
  			
  			try{
  				XSSFSheet sheet = wb.getSheet(sheetName);
  				XSSFRow headerRow = sheet.getRow(0);
  				XSSFRow testCaseRow = getTestCaseRow(sheet, tcId);
  				
  				for (int colNum = 0; colNum <= headerRow.getLastCellNum()-1; colNum++){
  					XSSFCell headerCell = headerRow.getCell(colNum);
  					XSSFCell dataCell = testCaseRow.getCell(colNum);
  					
  					if (dataCell == null){
  						tcData.put(headerCell.toString(), "");
  					} else {
  						tcData.put(headerCell.toString(), dataCell.toString());
  					}
  				}
  				
  			} catch(NullPointerException npe){
  				
  				System.out.println("Unable to read the data from data sheet. exception info: " + npe.getMessage());
  				
  			} finally{
  				wb.close();
  			}
  		
  			
  			
  			
  		} catch (FileNotFoundException e) {
  			System.out.println("Given Data file : " + filePath+ " is not available. Please check the path and re-run.");
  			System.exit(0);
  			
  		} catch (IOException e) {
  			System.out.println("Given Data file : " + filePath+ " is not a valid excel file. Please check the path and re-run.");
  			System.exit(0);
  		} 
  		
  		
  		
  		
  		
  		
  		
  		
  		
  		
  		return tcData;
  		
  		
  	}
  	
  	
  	public static int get_column_position(XSSFSheet sheet, String colHeader){
  		int colPos = -1;
  		XSSFRow headerRow = sheet.getRow(0);
  		int totalColumns = headerRow.getLastCellNum();
  		
  		for (int cellNum = 0; cellNum < totalColumns; cellNum++){
  			XSSFCell headerCell = headerRow.getCell(cellNum);
  			
  			if (headerCell.toString().trim().equalsIgnoreCase(colHeader)){
  				colPos = cellNum;
  				break;
  			}
  		}
  		
  		return colPos;
  		
  	}
  	
  	
  	
  	public static XSSFRow getTestCaseRow(XSSFSheet sheet, String tcId){
  		XSSFRow tcDataRow = null;
  		int totalRows = sheet.getLastRowNum();
  		int tcRowNum = -1;
  		int tcIdColNum = get_column_position(sheet, "TC_ID");
  		if (tcIdColNum >= 0)	{
  			for (int rNum = 1; rNum <= totalRows; rNum++){
  				try{	
  					XSSFRow row = sheet.getRow(rNum);
  					XSSFCell cell = row.getCell(tcIdColNum);				
  					if (cell.toString().trim().equalsIgnoreCase(tcId)){
  						tcRowNum = rNum;
  						break;
  					}				
  				} catch(NullPointerException npe){
  					
  				}
  			}
  			
  			if (tcRowNum == -1){
  				System.out.println("Given test case : " + tcId+ " is not found in the data file.");
  			} else {
  				tcDataRow = sheet.getRow(tcRowNum);
  			}
  		
  		} else {
  			System.out.println("TC_ID column is not found in the given data sheet.");
  		}
  			
  		
  		
  		return tcDataRow;
  	}
  	
  	
  	
      
}
