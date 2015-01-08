package icac.opsis.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class WriteMainContent {
	
	
	public static String mainContentTop(List<String> summaryList) throws IOException{
		
		
		String mainContentTopStr  = "[#ftl]\n";
		
		mainContentTopStr = mainContentTopStr+ "[#import \"/WEB-INF/view/components/componentsAPI.ftl\" as ui]\n";
	    
	    mainContentTopStr = mainContentTopStr+"[#assign navigation=\""+summaryList.get(0)+"\" /]\n";
	    		
	    mainContentTopStr = mainContentTopStr+"[#assign moduleId=\""+summaryList.get(1)+"\"/]\n";	
	    
	   /* 
	  
	    
	  
	   
	    out.write("<body>\n");
	    
	    
	    out.write("[#include \"../../decorators/navigation.ftl\"/]\n");
	    
	    out.write("<div id=\"mainContent\">\n");*/
	    		
	    return mainContentTopStr;		
	   	
	}
	
	
	public static String mainContentButton(){
		
		
		
		return "";
	}
	
	
	public static  void mainContentEnd(BufferedWriter out) throws IOException{
		
		
		 out.write("</div>\n"); 		
 		
		 out.write("</body>\n");
		 
		 
	}

}
