/**
 * Copyright  2014, samsung All Rights Reserved.
 * Project: FTPScan
 * com.sbl.FtpScan.Test.scanTest.java
 * Create By: samsung
 * Create Date: 2014-1-21 下午2:49:53
 */
package com.sbl.FtpScan.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URL;

import org.apache.log4j.PropertyConfigurator;

import com.sbl.FtpScan.Task.ScanTask;

/**
 * @author samsung 
 * @E-mail: cxxwode@163.com
 * @version 1.0 
 * @date 2014-1-21 下午2:49:53
 * @description TODO
 */
public class scanTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//PropertyConfigurator.configure("conf/log4j.properties");
		PropertyConfigurator.configure(ClassLoader.getSystemResource("log4j.properties"));
		ScanTask scan = new ScanTask();
		try {
			scan.ScanFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

