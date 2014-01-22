/**
 * Copyright  2014, samsung All Rights Reserved.
 * Project: FTPScan
 * com.sbl.FtpScan.Test.fileOpTest.java
 * Create By: samsung
 * Create Date: 2014-1-21 上午11:00:43
 */
package com.sbl.FtpScan.Test;

import java.io.File;

import com.sbl.FtpScan.Config.ScanConfig;
import com.sbl.FtpScan.util.FileOperator;

/**
 * @author samsung 
 * @E-mail: cxxwode@163.com
 * @version 1.0 
 * @date 2014-1-21 上午11:00:43
 * @description TODO
 */
public class fileOpTest {

	
	
	public static void main(String[] args) {
		ScanConfig config = new ScanConfig();
		FileOperator fileOperator = new FileOperator();
		String strFileFrom = "D:\\logs\\app.log";
		String strFileTo = "D:\\app.log";
		fileOperator.copyFileFromLocalToLocal(strFileFrom, strFileTo);
		File f = new File("E:\\ScanTest\\gv1\\monitordir");
		File[] files = f.listFiles();
		System.out.println(files[0].getAbsolutePath());
		
		String MonitorDir = config.getPropertyByName("MonitorDir");
		System.out.println(MonitorDir);
		File f1 = new File(MonitorDir);
		File[] files1 = f1.listFiles();
		System.out.println(files1);
		System.out.println(files1[0].getAbsolutePath());
		
	}
}

