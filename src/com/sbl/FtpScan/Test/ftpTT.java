package com.sbl.FtpScan.Test;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.PropertyConfigurator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.sbl.FtpScan.Object.FtpInfObj;
import com.sbl.FtpScan.Task.ScanTask;
import com.sbl.FtpScan.util.FtpUtil;

public class ftpTT {
	
	public static void main(String[] args) throws Exception {
		PropertyConfigurator.configure("log4j.properties");//加载.properties文件
		FtpInfObj ftpInfObj = new FtpInfObj("127.0.0.1", 21, "1d", "12");
		FTPClient f = FtpUtil.getFtpClient(ftpInfObj);
		f.changeWorkingDirectory("/CH01");
		FTPFile[] files = f.listFiles();
		System.out.println(files.length);
	}
}
