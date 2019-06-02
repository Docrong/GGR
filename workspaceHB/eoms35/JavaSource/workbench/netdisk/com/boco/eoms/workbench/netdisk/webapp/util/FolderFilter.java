package com.boco.eoms.workbench.netdisk.webapp.util;

import java.io.File;
import java.io.FilenameFilter;

/**
 * 
 * <p>
 * Title:文件夹过滤器
 * </p>
 * <p>
 * Description:用来获取子文件夹，使用方法如下：File[] subFolderNames = file.listFiles(new
 * FolderFilter());
 * </p>
 * <p>
 * Date:2008-5-6 16:59:06
 * </p>
 * 
 * @author 李秋野
 * @version 3.5.1
 * 
 */
public class FolderFilter implements FilenameFilter {

	public boolean accept(File dir, String name) {
		return new File(dir + File.separator + name).isDirectory();
	}

}
