/**
 * Copyright  2014, samsung All Rights Reserved.
 * Project: FTPScan
 * com.sbl.FtpScan.Object.XmlInfoObj.java
 * Create By: samsung
 * Create Date: 2014-1-20 下午3:28:27
 */
package com.sbl.FtpScan.Object;

/**
 * @author samsung 
 * @E-mail: cxxwode@163.com
 * @version 1.0 
 * @date 2014-1-20 下午3:28:27
 * @description TODO
 */
public class XmlInfObj {

	private String ip;
	private int port;
	private String tspath;
	private String startTD;
	private String EndTD;
	
	public XmlInfObj(String ip, int port, String tspath, String startTD,
			String endTD) {
		super();
		this.ip = ip;
		this.port = port;
		this.tspath = tspath;
		this.startTD = startTD;
		EndTD = endTD;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getTspath() {
		return tspath;
	}
	public void setTspath(String tspath) {
		this.tspath = tspath;
	}
	public String getStartTD() {
		return startTD;
	}
	public void setStartTD(String startTD) {
		this.startTD = startTD;
	}
	public String getEndTD() {
		return EndTD;
	}
	public void setEndTD(String endTD) {
		EndTD = endTD;
	}
	
	
}

