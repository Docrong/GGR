package com.boco.eoms.commons.statistic.base.excelutil.dbexcel.flopper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.Hashtable;


/**
 * Title:        
 * Description:  
 * Copyright:    Copyright (c) 2002
 * Company:      UNC
 * @author      zhouj@unc.com.cn
 * @version     1.0
 */
abstract class DictFlopper extends DBFlopper {
	private Hashtable hUri = new Hashtable(5);
	private FileReader fr = null;
	private BufferedReader br = null;
	private StringTokenizer ster = null;
	private Hashtable alst = null;

	String findForWhat(String uri, String find) {
		if (uri == null)
			return find;
		String what = null;
		if (hUri.containsKey(uri)) {
			//System.out.println("Retrieving from existent..." + uri);
			alst = (Hashtable) hUri.get(uri);
			what = (String) alst.get(find);
		} else {
			String line = null;
			//Read file  
			try {
				fr = new FileReader(uri);
				br = new BufferedReader(fr);
				alst = new Hashtable(20);
				while ((line = br.readLine()) != null) {
					ster = new StringTokenizer(line);
					String _find = null;
					String _what = null;
					try {
						_find = ster.nextToken();
						_what = ster.nextToken();
					} catch (NoSuchElementException nsee) {
					}
					System.err.println("Adding data..." + _find + " " + _what);
					if (_find.equals(find))
						what = _what;
					alst.put(_find, _what);
				}
				br.close();
				fr.close();
				hUri.put(uri, alst);
			} catch (FileNotFoundException fnfe) {
				System.err.println("File_not_found");
			} catch (IOException ioe) {
				System.err.println("IO_reading_error");
			}
		}
		return what;
	}

}
