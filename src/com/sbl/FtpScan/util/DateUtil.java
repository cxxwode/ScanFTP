/**
 * Copyright  2014, samsung All Rights Reserved.
 * Project: FTPScan
 * com.sbl.FtpScan.util.DateUitl.java
 * Create By: samsung
 * Create Date: 2014-1-20 下午5:19:27
 */
package com.sbl.FtpScan.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author samsung 
 * @E-mail: cxxwode@163.com
 * @version 1.0 
 * @date 2014-1-20 下午5:19:27
 * @description TODO
 */
public class DateUtil {

	public static Date ConvertStrToDate(String source) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		//String dateStr = source.replace("_", "").replace("-", "").replace(":", "").replace(" ", "");
		String dateStr = source.replaceAll("[_ :-]", "");
		return sdf.parse(dateStr);
	}
}

