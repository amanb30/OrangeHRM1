package com.Utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadProertiesData {
public String data=null;
public FileInputStream fis=null;

public String getObjectRepoData(String key){
	

	try {
		fis=new FileInputStream("C:\\Users\\Gaurav-Admin\\git\\OrangeHRM1\\OrangeHRM\\src\\main\\java\\com\\TestBase\\ObjectRepository.properties");
	} catch (FileNotFoundException e) {
		
		e.printStackTrace();
	}
	
	Properties prop=new Properties();
	
	try {
		prop.load(fis);
	} catch (IOException e) {
		e.printStackTrace();
	}

	data=prop.getProperty(key);
	
return data;
	
}
public String getGlobalRepoData(String key){
	
	
	try {
		fis=new FileInputStream("C:\\Users\\Gaurav-Admin\\git\\OrangeHRM1\\OrangeHRM\\src\\main\\java\\com\\TestBase\\GlobalRepository.properties");
		
	} catch (FileNotFoundException e) {
		
		e.printStackTrace();
	}
	
	Properties prop=new Properties();
	
	try {
		prop.load(fis);
	} catch (IOException e) {
		e.printStackTrace();
	}

	data=prop.getProperty(key);
	
return data;
	
}
}






