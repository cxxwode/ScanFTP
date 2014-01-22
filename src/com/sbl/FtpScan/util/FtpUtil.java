package com.sbl.FtpScan.util;

import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

import com.sbl.FtpScan.Object.FtpInfObj;


public class FtpUtil {

	private static final Logger logger = Logger.getLogger(FtpUtil.class);
	/**
	 * get FtpClient with ftpKey，if not exist,create it
	 * 
	 * @param ftpKey
	 * @return
	 * @throws Exception
	 */
	public static FTPClient getFtpClient(FtpInfObj ftpInfObj)
			throws Exception {
		FTPClient ftpClient = null;
		String ftpKey = ftpInfObj.getUsername() + ":" + ftpInfObj.getPassword()
				+ "@" + ftpInfObj.getHostname() + ":" + ftpInfObj.getPort();
		// if ftpKey does not exist,create it
		logger.info(ftpKey);
		ftpClient = new FTPClient();

		int connectFlag = 0;
		for (int i = 0; i < 5; i++) {
			try {
				ftpClient.connect(ftpInfObj.getHostname(), ftpInfObj.getPort());
				ftpClient.setControlEncoding("UTF-8");
				if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
					if (ftpClient.login(ftpInfObj.getUsername(), ftpInfObj
							.getPassword())) {
						// 设置PassiveMode传输
						ftpClient.enterLocalPassiveMode();

						// 设置以二进制流的方式传输
						ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
						ftpClient.pasv();
					} else {
						logger.debug("fpt登陆错误，请检查登陆配置是否正确！");
					}
				}
			} catch (Exception e) {
				continue;
			}
			connectFlag = 1;
			break;
		}
		if(connectFlag == 1) return ftpClient;
		else {
		//	System.out.println("连接FTP服务器失败！");
			logger.debug("连接FTP服务器失败！");
			return null;
		}
	}

	public static void disconnect(FTPClient f) throws IOException {
		if (f.isConnected()) {
			f.logout();
			f.disconnect();
		}
		logger.debug("断开ftp");
	}
	
	public static FTPFile[] getFtpFiles(FTPClient ftpClient, String ftpPath) {
		if(ftpClient.isConnected()) {
			try {
				if(ftpClient.changeWorkingDirectory("/" + ftpPath)){
					FTPFile[] files = ftpClient.listFiles();
					return files;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}
