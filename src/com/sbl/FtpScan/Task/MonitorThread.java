/**
 * Copyright  2014, samsung All Rights Reserved.
 * Project: FTPScan
 * com.sbl.FtpScan.Task.MonitorThread.java
 * Create By: samsung
 * Create Date: 2014-1-21 下午2:24:21
 */
package com.sbl.FtpScan.Task;

import java.io.IOException;

/**
 * @author samsung 
 * @E-mail: cxxwode@163.com
 * @version 1.0 
 * @date 2014-1-21 下午2:24:21
 * @description TODO
 */
public class MonitorThread extends Thread {

	ScanTask scanTask = new ScanTask();
	
	@Override
	public void run() {
		try {
			scanTask.ScanFile();
			sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

