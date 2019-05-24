package com.boco.eoms.workbench.netdisk.webapp.util;

import java.io.File;
import java.io.FilenameFilter;

/**
 * 
 * <p>
 * Title:文件过滤器
 * </p>
 * <p>
 * Description:用来获取某目录下所有文件，使用方法如下：File[] files = folder.listFiles(new
 * FileFilter());
 * </p>
 * <p>
 * Date:2008-5-8 9:05:27
 * </p>
 * 
 * @author 李秋野
 * @version 3.5.1
 * 
 */
public class FileFilter implements FilenameFilter {

	public boolean accept(File dir, String name) {
		return new File(dir + File.separator + name).isFile();
	}

}
