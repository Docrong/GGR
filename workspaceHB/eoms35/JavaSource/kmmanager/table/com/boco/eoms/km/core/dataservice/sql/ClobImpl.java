package com.boco.eoms.km.core.dataservice.sql;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.sql.Clob;
import java.sql.SQLException;

public class ClobImpl implements Clob {

	private Reader reader;
	private int length;
	private boolean needsReset = false;

	public ClobImpl(String string) {
		this.reader = new StringReader(string);
		this.length = string.length();
	}

	public ClobImpl(Reader reader, int length) {
		this.reader = reader;
		this.length = length;
	}

	public InputStream getAsciiStream() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Reader getCharacterStream() throws SQLException {
		if (this.needsReset) {
			try {
				this.reader.reset();
			} catch (IOException e) {
				throw new SQLException("could not reset reader");
			}
		}
		this.needsReset = true;
		return this.reader;
	}

	public String getSubString(long pos, int length) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public long length() throws SQLException {
		return this.length;
	}

	public long position(String searchstr, long start) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	public long position(Clob searchstr, long start) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	public OutputStream setAsciiStream(long pos) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Writer setCharacterStream(long pos) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public int setString(long pos, String str) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	public int setString(long pos, String str, int offset, int len)
			throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	public void truncate(long len) throws SQLException {
		// TODO Auto-generated method stub

	}

	public String toString() {
		return "A Binary Large Object, length=" + this.length;
	}

}
