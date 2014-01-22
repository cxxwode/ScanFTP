/**
 * Copyright  2014, samsung All Rights Reserved.
 * Project: FTPScan
 * com.sbl.FtpScan.Test.property.java
 * Create By: samsung
 * Create Date: 2014-1-22 下午12:07:45
 */
package com.sbl.FtpScan.Test;

import org.apache.log4j.PropertyConfigurator;

/**
 * @author samsung 
 * @E-mail: cxxwode@163.com
 * @version 1.0 
 * @date 2014-1-22 下午12:07:45
 * @description TODO
 */
public class property {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//PropertyConfigurator.configure(System.getProperty("user.dir") + "\\src\\log4j.properties"); 
		String file = System.getProperty("user.dir") ;  
		System.out.println(file);
	}

}

