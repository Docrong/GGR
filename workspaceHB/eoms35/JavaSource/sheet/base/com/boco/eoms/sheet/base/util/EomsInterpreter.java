/*
 * Created on 2007-12-17
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.util;

import java.util.Hashtable;

import bsh.EvalError;
import bsh.Interpreter;

/**
 * @author Administrator
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class EomsInterpreter {
	public static boolean getbooleanFromExpression(String Expression) throws EvalError {
		Interpreter interpreter = new Interpreter();
		interpreter.set("boolean", interpreter.eval(Expression));
		return ((Boolean) interpreter.get("boolean")).booleanValue();
	}
	
	public static int getintFromExpression(String Expression) throws EvalError {
		Interpreter interpreter = new Interpreter();
		interpreter.set("int", interpreter.eval(Expression));
		return ((Integer) interpreter.get("int")).intValue();
	}
	
	public static Hashtable getParamNameFromCondition(String condition) throws EvalError {
		Hashtable hash = new Hashtable();
		while(!condition.equals("") && condition.indexOf("${")!=-1 && condition.indexOf("}")!=-1){
		    String key = condition.substring(condition.indexOf("${")+2,condition.indexOf("}"));
		    condition = condition.substring(condition.indexOf("}")+1,condition.length());
		    hash.put(key,"");
		}
		return hash;
	}
}
