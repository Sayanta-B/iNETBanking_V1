package com.intbanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	
	Properties pro;
	
	public ReadConfig() {
		
		File src = new File("./Configuration/config.properties");
		try {
			FileInputStream fis=new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);  //this will load complete file 
		}
		catch(Exception e) {
			System.out.println("Exception is "+ e.getMessage());
		}	
	}
	public String getApplicationURL() {
		String url=pro.getProperty("baseURL");
		return url;
	}
	public String getUserName() {
		String username=pro.getProperty("username");
		return username;
	}
	public String getPassword() {
		String password=pro.getProperty("password");
		return password;
	}
	public String getChromepath() {
		String chromepath=pro.getProperty("chromepath");
		return chromepath;
	}
	public String getFireFoxpath() {
		String firefoxpath=pro.getProperty("firefoxpath");
		return firefoxpath;
	}


}
