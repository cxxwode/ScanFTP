/**
 * Copyright  2014, samsung All Rights Reserved.
 * Project: FTPScan
 * com.sbl.FtpScan.Task.ScanTask.java
 * Create By: samsung
 * Create Date: 2014-1-20 下午12:34:41
 */
package com.sbl.FtpScan.Task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.sbl.FtpScan.Config.ScanConfig;
import com.sbl.FtpScan.Object.FtpInfObj;
import com.sbl.FtpScan.Object.XmlInfObj;
import com.sbl.FtpScan.XmlParse.XmlParse;
import com.sbl.FtpScan.util.DateUtil;
import com.sbl.FtpScan.util.FileOperator;
import com.sbl.FtpScan.util.FtpUtil;
/**
 * @author samsung 
 * @E-mail: cxxwode@163.com
 * @version 1.0 
 * @date 2014-1-20 下午12:34:41
 * @description TODO
 */
public class ScanTask {

	public static final Logger scanLog = Logger.getLogger("ScanTask");
	
	private ScanConfig config = new ScanConfig();
	
	private FileOperator fileOperator = new FileOperator();
	
	private String MonitorDir = config.getPropertyByName("MonitorDir");
	
	private String MonitorErrorDir = config.getPropertyByName("MonitorErrorDir");
	
	private String MonitorSuccessDir = config.getPropertyByName("MonitorSuccessDir");
	
	private String MonitorErrorInSuccessDir = config.getPropertyByName("MonitorErrorInSuccessDir");
	
	private String MonitorTemp = config.getPropertyByName("MonitorTemp");
	
	private String username = config.getPropertyByName("username");
	
	private String password = config.getPropertyByName("password");
	
	private static int i = 0;
	
	public void ScanFile() throws IOException{
		scanLog.info("ScanFile");
		
		FileSearch(MonitorDir);
		FileSearch(MonitorErrorInSuccessDir);
		FileSearch(MonitorTemp);
		System.out.println("第" + i++ + "次扫描！时间为：" + new Timestamp(new Date().getTime()) );
	}
	
	//需要考虑文件自己向相同目录拷贝失败情况
	public void FileSearch(String path) {
		File f = new File(path);
		File[] array = f.listFiles();
		
		if(null != array) {
			scanLog.info("扫描目录" + path);
			scanLog.info("目录不为空，扫描！");
			for (File g : array) {
				if(g.isDirectory()){
					//如果是文件夹则不进行任何操作
					scanLog.debug("不是xml文件，不进行操作:" + g.getPath());
					continue;
				} else {
					if (g.getName().endsWith(".xml")) {
						File inputXml = new File(g.getPath());
						XmlParse xmlParse = new XmlParse();
						XmlInfObj xmlInfObj= xmlParse.getXmlInfo(inputXml);
						
						if(xmlInfObj != null) {
							String result = CheckXmlTD(xmlInfObj);
									
							if(result != null && result.length() > 0) {
								String xmlRePath = g.getAbsolutePath();
								String separator = System.getProperties().getProperty("file.separator");
								if(result.equals("after")) {
									if(!fileOperator.checkFileExist(MonitorTemp + separator + g.getName())) {
										fileOperator.copyFileFromLocalToLocal(xmlRePath, MonitorTemp + separator + g.getName());
										System.out.println("没有相同文件"+ xmlRePath +"存在，不进行删除！");
										fileOperator.deleteLocalFile(g.getAbsolutePath());
									}
								}else if(result.equals("before")) {
									if(!fileOperator.checkFileExist(MonitorErrorDir + separator + g.getName())) {
										fileOperator.copyFileFromLocalToLocal(xmlRePath, MonitorErrorDir + separator + g.getName());
										System.out.println("没有相同文件"+ xmlRePath +"存在，不进行删除！");
										fileOperator.deleteLocalFile(g.getAbsolutePath());
									}
								}else if(result.equals("between")) {
									
									if(!fileOperator.checkFileExist(MonitorSuccessDir + separator + g.getName())) {
										fileOperator.copyFileFromLocalToLocal(xmlRePath, MonitorSuccessDir + separator + g.getName());
										System.out.println("没有相同文件"+ xmlRePath +"存在，不进行删除！");
										fileOperator.deleteLocalFile(g.getAbsolutePath());
									}
								}
							}
						} else {
							scanLog.error(g.getName() + "文件Xml解析出错，请检查该文件内容是否正确!");
						}
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {

		PropertyConfigurator.configure(ClassLoader.getSystemResource("log4j.properties"));
		MonitorThread monitor = new MonitorThread();
		while(true) {
			monitor.run();
		}
	}
	
	public String CheckXmlTD(XmlInfObj xmlInfObj) {
		
		FtpInfObj ftpInfObj = new FtpInfObj(xmlInfObj.getIp(), 
				xmlInfObj.getPort(), username, password);
		try {
			FTPClient ftpClient = FtpUtil.getFtpClient(ftpInfObj);
			FTPFile[] files = FtpUtil.getFtpFiles(ftpClient, xmlInfObj.getTspath());
			FtpUtil.disconnect(ftpClient);
			String result = CompareDate(xmlInfObj.getStartTD(), xmlInfObj.getEndTD(), files);
			System.out.println(result);
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String CompareDate(String startTD, String endTD, FTPFile[] files){
		
		try {
			Date start = DateUtil.ConvertStrToDate(startTD);
			Date end = DateUtil.ConvertStrToDate(endTD);
			Date min, max;
			min=max=DateUtil.ConvertStrToDate(files[0].getName().substring(0, files[0].getName().indexOf(".")));
			for(FTPFile f : files) {
				Date tsDate = DateUtil.ConvertStrToDate(f.getName().substring(0, f.getName().indexOf(".")));
				if(tsDate.before(min)) min = tsDate;
				if(tsDate.after(max)) max = tsDate;
			}
			
			if(min.after(start)) return "before";  //xml过期
			if(max.before(start) || (min.before(start) && max.before(end))) return "after";  //xml属于将来时刻
			if(min.before(start) && max.after(end)) return "between";  //xml属于时间期内
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}

