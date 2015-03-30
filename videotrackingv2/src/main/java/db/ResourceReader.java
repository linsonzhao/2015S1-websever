package db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ResourceReader {
	String _err;
	
	public ResourceReader(){
	}

	public String getFileString(String fpath){	
		String line = null;
		StringBuilder sb = new StringBuilder();
		
		try{			
			FileReader fr = new FileReader(new File(fpath));
			BufferedReader bf = new BufferedReader(fr);
			while((line=bf.readLine())!=null){
				sb.append(line);
				sb.append("\n");
			}
			
		}catch(IOException e){
			e.printStackTrace();
			_err = e.getMessage();
		}
		
		return sb.toString();
	}
	
	
	
}
