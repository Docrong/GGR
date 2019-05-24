/*
 * Created on 2008-1-24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.base.util.ant;

import java.io.File;

import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

import com.boco.eoms.base.util.StaticMethod;

/**
 * @author IBM
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AntHolder {
	private static AntHolder instance = null;
	private static Project project = null;
	
	public static AntHolder getInstance(){
		if(instance==null){
			instance = new AntHolder();
		}
		return instance;
	}
	public AntHolder(){
		String filePath = "classpath:com/boco/eoms/base/util/ant/antXML/build.xml";
		File buildFile = new File(StaticMethod.getFilePath(filePath));
		project = new Project();
		
		DefaultLogger consoleLogger = new DefaultLogger();
		consoleLogger.setErrorPrintStream(System.err);
		consoleLogger.setOutputPrintStream(System.out);
		consoleLogger.setMessageOutputLevel(Project.MSG_INFO);
		project.addBuildListener(consoleLogger);

		
		project.init();
		ProjectHelper helper = ProjectHelper.getProjectHelper();
		helper.parse(project, buildFile);
	}
	
	public void execute(String targetName){
		
		project.executeTarget(targetName);

	}
}
