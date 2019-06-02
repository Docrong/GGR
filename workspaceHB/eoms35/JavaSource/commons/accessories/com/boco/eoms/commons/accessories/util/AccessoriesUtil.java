/*
 * Created on 2007-9-13
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.accessories.util;

import java.io.File;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AccessoriesUtil {
  public static void createFile(String filePath,String filter) {
  	String[] filePaths=filePath.split(filter);
  	String path=filePaths[0];
  	for(int i=1;i<filePaths.length;i++){
  		 path=path+filter+filePaths[i];
  		 File tempFile = new File(path);
		if (!tempFile.exists()) {
			tempFile.mkdir();
		}
  	} 
  }
}
