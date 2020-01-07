package frameWork;
	import java.util.List;
	import javax.management.remote.TargetedNotification;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.ui.UnexpectedTagNameException;

	public class webTable {
		
		private WebElement table;
		
		public void WebTable(WebElement tableElement){
			table = tableElement;
			String elemTagName = table.getTagName();
			if (!elemTagName.equalsIgnoreCase("table")){
				throw new  UnexpectedTagNameException("Table", elemTagName);
			}
		}
		

		public WebElement getTableRow(String columnName, String value){
			WebElement dataRow = null;
			int colPos = getColumnIndex( columnName);
			
			if (colPos >= 0){
				List<WebElement> allRows = table.findElements(By.tagName("tr"));
				
				for (int i = 1; i <= allRows.size()-1; i++){
					WebElement row = allRows.get(i);				
					List<WebElement> allCells  = row.findElements(By.tagName("td"));
					if (allCells.size() >= colPos){
						WebElement dataCell = allCells.get(colPos);				
						String cellText = dataCell.getText();
						
						if (cellText.trim().equalsIgnoreCase(value)){
							dataRow = row;
							break;
						}
					}
					
				}	
				
			} else{
				System.out.println("THe given column : " + columnName + " is not found.");
			}
			
		
			return dataRow;
			
			
			
			
		}
		
		/**
		 * searches for the column by given name in the table and gets the column index position/.
		 * @param columnName name of the column for which column the index to be retrieved.
		 * @return if column found returns position which is minimum 0, if column not found returns -1
		 */
		public int getColumnIndex(String columnName){
			int colIndex = -1;
			WebElement headerRow = table.findElement(By.tagName("tr"));
			
			List<WebElement> allHeaders = headerRow.findElements(By.tagName("th"));
			
			for (int i =0; i <= allHeaders.size()-1; i++ ){
				WebElement header = allHeaders.get(i);
				String headerText = header.getText();
				if (headerText.trim().equalsIgnoreCase(columnName)){
					colIndex = i;
					break;
				}
				
			}
			
			return colIndex;
			
		}
		
		
		

	}

