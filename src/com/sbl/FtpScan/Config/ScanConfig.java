package com.sbl.FtpScan.Config;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;


public class ScanConfig {
	
	private static String fileName = "/ScanConfig.properties";
	private static String log4j = "/log4j.properties";
	
	private Properties p = new Properties();
//	private Properties log = new Properties();
	
	public ScanConfig() {
		
//		InputStream lin = ScanConfig.class.getResourceAsStream(log4j);
		InputStream in = ScanConfig.class.getResourceAsStream(fileName);
		
		try {
//			log.load(lin);
//			PropertyConfigurator.configure(log4j); 
//			InputStream in = new BufferedInputStream(new FileInputStream(fileName));
			p.load(in);
			in.close();
		} catch (IOException e) {
			System.out.println("ScanConfig配置文件读取异常：" + e.getMessage());
			System.out.println("ScanConfig配置文件：" + fileName);
			e.printStackTrace();
		}
	}
	
	public String getPropertyByName(String name) {
		String property = "";
		if (p.containsKey(name)) {
			property = p.getProperty(name);
		} else {
			System.out.println("ScanConfig配置文件读取不存在的字段，字段名：" + name);
		}
		return property;
	}
	
	public static void main(String[] args) throws IOException {
		ScanConfig scanConfig = new ScanConfig();
		String value = scanConfig.getPropertyByName("ScanHostAddress");
		System.out.println(value);
	}
}
