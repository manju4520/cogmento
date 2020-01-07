package frameWork;

import java.io.File;

public class FileUtils {
      public static boolean checkForFile(String filepath) {
    	  File f=new File(filepath);
    	  if (f.exists()) {
			return true;
		}else {
			return false;
		}
      }
      
      public static String getFileExtension(String filepath) {
    	 String extension="";
    	  if (checkForFile(filepath)) {
    		  File f = new File(filepath);
    		  if (f.isFile()) {
    			  String fileName=f.getName();
    			  String[] name=fileName.split("\\.");
    			  extension=name[name.length-1];
    			  
    		  }else {
    			  System.out.println("Given path:"+filepath+"");
    		
				
			} 
    			  System.out.println("");
			}
		return extension;
      }
			
		
}  

