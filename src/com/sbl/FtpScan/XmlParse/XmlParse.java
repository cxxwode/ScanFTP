/**
 * Copyright  2014, samsung All Rights Reserved.
 * Project: FTPScan
 * com.sbl.FtpScan.XmlParse.xmlParse.java
 * Create By: samsung
 * Create Date: 2014-1-20 下午1:31:50
 */
package com.sbl.FtpScan.XmlParse;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.sbl.FtpScan.Object.FtpInfObj;
import com.sbl.FtpScan.Object.XmlInfObj;

/**
 * @author samsung 
 * @E-mail: cxxwode@163.com
 * @version 1.0 
 * @date 2014-1-20 下午1:31:50
 * @description TODO
 */
public class XmlParse {

	public XmlInfObj getXmlInfo(File file) {
		
		String ip, port, tspath, startTD = null, endTD = null;
		
		SAXReader saxReader = new SAXReader();
		try{
			Document document = saxReader.read(file);
			Element rootElement = document.getRootElement();
			ip = rootElement.attributeValue("IP");
			port = rootElement.attributeValue("Port");
			tspath = rootElement.attributeValue("Path");
			for ( Iterator i = rootElement.elementIterator(); i.hasNext(); ) {
			       Element element = (Element) i.next();
			       startTD = element.attributeValue("StartTD");
			       System.out.println(element.attributeValue("StartTD"));
			       endTD = element.attributeValue("EndTD");
			}
			if(ip != null && port != null && tspath != null && startTD != null && endTD != null){
				return new XmlInfObj(ip, Integer.parseInt(port), tspath, startTD, endTD);
			}
		}catch(Exception e) {
			
		}
		return null;
	}
}

