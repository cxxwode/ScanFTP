/**
 * Copyright  2014, samsung All Rights Reserved.
 * Project: FTPScan
 * com.sbl.FtpScan.util.FileOperator.java
 * Create By: samsung
 * Create Date: 2014-1-20 下午6:01:38
 */
package com.sbl.FtpScan.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

/**
 * @author samsung 
 * @E-mail: cxxwode@163.com
 * @version 1.0 
 * @date 2014-1-20 下午6:01:38
 * @description TODO
 */
public class FileOperator {

	public static final Logger fileLog = Logger.getLogger("FileOperator");
	
	public int copyFileFromLocalToLocal(String strFileFrom, String strFileTo) {
		fileLog.debug("copyFileFromLocalToLocal...");
		fileLog.debug("源 - " + strFileFrom);
		fileLog.debug("目标 - " + strFileTo);
		int ret = -1;

		if (strFileFrom == null || strFileTo == null
				|| strFileFrom.equalsIgnoreCase("")
				|| strFileTo.equalsIgnoreCase("")) {
			fileLog.warn("输入参数为空。");
			ret = 1;
			return ret;
		}

		try {
			int last = 0;
			Long localreadbytes = Long.valueOf(0);
			byte[] bytes = new byte[1024];

			// 检查源文件是否存在
			if (checkFileExist(strFileFrom)) {
				// 检查目标路径是否存在
				fileLog.debug("检查目标路径...");
				checkLocalDir(strFileTo);

				// 检查目标文件是否存在，如果存在，则删除
				if (checkFileExist(strFileTo)) {
					// 目标文件已经存在，删除旧文件
					fileLog.debug("目标文件已经存在，删除旧文件...");
					deleteLocalFile(strFileTo);
				}

				File fileFrom = new File(strFileFrom); // 源文件
				File fileTo = new File(strFileTo); // 目标文件

				FileOutputStream fileStreamOut = new FileOutputStream(fileTo,
						true); // 输出文件
				FileInputStream fileStreamIn = new FileInputStream(fileFrom); // 输入文件

				while ((last = fileStreamIn.read(bytes)) != -1) {
					fileStreamOut.write(bytes, 0, last);
					localreadbytes += last; // 已传输字节数
				}
				fileStreamOut.flush();
				fileStreamOut.close();
				
				fileStreamIn.close();
				
				ret = 0;
				fileLog.info("复制文件成功 : " + strFileFrom + " --> " + strFileTo);
			} else {
				// 源文件不存在
			}
		} catch (IOException ex) {
			ret = 1;
			fileLog.error("复制文件失败 : " + strFileFrom + " --> " + strFileTo);
			fileLog.error("FileOperationImpl -> copyFileFromLocalToLocal - 异常："
							+ ex.getMessage());
		}

		fileLog.debug("copyFileFromLocalToLocal returns.");
		return ret;
	}
	
	public boolean checkFileExist(String filePath) {
		
		boolean exist = false;
		String head = "";
		String checkDir = "";

		if (filePath == null || filePath.equalsIgnoreCase("")) {
			fileLog.warn("checkFileExist - 输入参数为空，不操作。");
			exist = false;
		} else {
			try {
				File file = new File(filePath);
				if (file.exists()) {
					exist = true;
				} else {
					exist = false;
				}
			} catch (Exception e) {
				fileLog.error("checkFileExist - 异常："
						+ e.getMessage());
				exist = false;
			}
		}

		return exist;
	}
	
	// 删除本地文件
		public int deleteLocalFile(String strFile // 文件路径，格式：""
		) {
			// 删除文件
			int ireturn = -1;
			try {
				if (checkFileExist(strFile)) {
					File file = new File(strFile);
					file.delete();
					fileLog.debug("deleteLocalFile - 删除文件成功 : "
									+ strFile);
				} else {
					fileLog.debug("deleteLocalFile - 文件不存在，不需要删除 : "
									+ strFile);
				}
				ireturn = 0;
			} catch (Exception ex) {
				ireturn = 1;
				fileLog.error("deleteLocalFile - 删除文件失败 : "
								+ strFile);
				fileLog.error("deleteLocalFile - 异常："
						+ ex.getMessage());
			}

			return ireturn;
		}
		
		// 检查目录，如果不存在目录，则创建目录。以"/"结束
		public int checkLocalDir(String dir // 目录，""
		) {
			System.out.println(dir);
			String checkDir = "";
			// String newdir = "";
			dir = dir.replace('\\', '/');

			// head = dir.substring(0, 3);
			if (dir == null || dir.equalsIgnoreCase("")) {
				fileLog.warn("checkLocalDir - 输入参数为空，不操作。");
				return 1;
			}

			try {
				checkDir = dir.substring(0, dir.lastIndexOf("/") + 1);
				System.out.println(checkDir);
				File file = new File(checkDir);
				if (!file.exists()) {
					System.out.println(file + "不存在");
					file.mkdirs();
					fileLog.debug("checkLocalDir - 目录已经创建。"
									+ checkDir);
				} else {
					fileLog.debug("checkLocalDir - 目录已经存在，不操作。"
									+ checkDir);
				}
			} catch (Exception e) {
				fileLog.error("checkLocalDir - 异常："
						+ e.getMessage());
				return 1;
			}
			return 0;
		}
		
		
}

