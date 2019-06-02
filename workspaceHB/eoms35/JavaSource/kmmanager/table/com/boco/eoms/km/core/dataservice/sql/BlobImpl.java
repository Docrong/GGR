package com.boco.eoms.km.core.dataservice.sql;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

public class BlobImpl implements Blob {

	private InputStream stream;
	private int length;
	private boolean needsReset = false;

	public BlobImpl(byte[] bytes) {
		this.stream = new ByteArrayInputStream(bytes);
		this.length = bytes.length;
	}

	public BlobImpl(InputStream stream, int length) {
		this.stream = stream;
		this.length = length;
	}

	public InputStream getBinaryStream() throws SQLException {
		try {
			if (this.needsReset)
				this.stream.reset();
		} catch (IOException ioe) {
			throw new SQLException("could not reset reader");
		}
		this.needsReset = true;
		return this.stream;
	}

	public byte[] getBytes(long pos, int length) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public long length() throws SQLException {
		return this.length;
	}

	public long position(byte[] pattern, long start) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	public long position(Blob pattern, long start) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	public OutputStream setBinaryStream(long pos) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public int setBytes(long pos, byte[] bytes) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	public int setBytes(long pos, byte[] bytes, int offset, int len)
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
