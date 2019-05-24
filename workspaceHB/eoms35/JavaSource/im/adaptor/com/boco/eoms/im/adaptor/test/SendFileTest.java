package com.boco.eoms.im.adaptor.test;

import java.io.File;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.filetransfer.FileTransferManager;
import org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer;

public class SendFileTest {

	public static void main(String[] args)  throws InterruptedException {
		File file = new File("D:\\abc.txt");
		String filePath="";
		ConnectionConfiguration config = new ConnectionConfiguration(
				"10.131.62.239", 5222);
		XMPPConnection conn = new XMPPConnection(config);
		try {
			conn.connect();
			conn.login("webMaster", "111");
			FileTransferManager manager = new FileTransferManager(conn);
			OutgoingFileTransfer transfer = manager
					.createOutgoingFileTransfer("mengxianyu@eoms-server/spark");
			transfer.sendFile(file, "You won't believe this!");
		} catch (XMPPException e) {
			e.printStackTrace();
		} finally {
			// 让线程休眠 然后再关闭连接
			Thread.sleep(3600000);
		}

	}
}
