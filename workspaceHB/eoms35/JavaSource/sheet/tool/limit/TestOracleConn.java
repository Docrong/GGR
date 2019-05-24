import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class TestOracleConn implements Runnable{
	private List informixConnList=null;
	private String messageString=new String("\n---------------start----------------------\n");
	private String currentDir=System.getProperty("user.dir");
	private int connectionSize=10;
	private Connection connection = null;
	private ResultSet resultSet = null;
	private Statement statement= null;
	private String conURL = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=10.30.172.214)(PORT=1521))(ADDRESS=(PROTOCOL=TCP)(HOST=10.30.172.215)(PORT=1521))(LOAD_BALANCE=yes)(CONNECT_DATA=(SERVER=DEDICATED)(SERVICE_NAME=rac)(FAILOVER_MODE=(TYPE=SELECT)(METHOD=BASIC)(RETRIES=180)(DELAY=5))))";
    private String userName = "eomsv36";
    private String userPassword = "WdL4P#3s34sl34#ls2";
    private String className = "oracle.jdbc.driver.OracleDriver";
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");//设置日期格式
	
	public TestOracleConn() throws Exception {
		super();
		informixConnList = new ArrayList();
		Class.forName(className);
		//初始化10个数据库连接
		for(int i=1;i<=connectionSize;i++){
			try {
//				String jndiName = "jdbc/eoms";
//				Connection conn = null;
//				Hashtable env = new Hashtable();
//				env.put(Context.INITIAL_CONTEXT_FACTORY,
//						"com.ibm.websphere.naming.WsnInitialContextFactory");
//				InitialContext ic = new InitialContext(env);
//				DataSource ds = (DataSource) ic.lookup(jndiName);
//				conn = ds.getConnection();
//				informixConnList.add(conn);
				informixConnList.add(DriverManager.getConnection(conURL, userName, userPassword));
			} catch (SQLException e) {
				messageString=df.format(new Date())+"getConnection error!" + e.getMessage()+"\n";
				System.out.println(messageString);
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void releaseInformixConnList(){
		for(int i=0;i<connectionSize-1;i++){
			connection=(Connection)informixConnList.get(i);
			if(connection!=null){
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			connection=null;
		}
	}
	
	 /**
	  * 描述：writeFile
	  * 
	  * @param dir
	  * @param content
	  * @param flag
	  *            true：追加，false：覆盖
	  * @throws IOException
	  * @CreateOn Jul 28, 2008 3:58:07 PM
	  * @author chun_chang
	  */
	 public void writeFile(String dir, String content, boolean flag) throws IOException {
	  try {
	   // 文件的追加或修改
	   OutputStream fos = new FileOutputStream(dir, flag);
	   OutputStreamWriter osw = new OutputStreamWriter(fos);

	   osw.write(content, 0, content.length());
	   osw.flush();
	   fos.close();
	  } catch (FileNotFoundException e) {
	   // 文件不存在的时候
	   File write = new File(dir);
	   BufferedWriter bw = new BufferedWriter(new FileWriter(write, flag));
	   bw.write(content + "/r/n"); // 只适用Windows系统
	   bw.close();
	  }
	 }
	private void saveToDisk(){
		try {
			writeFile(currentDir+File.separator+"TestInformixConnection.log",messageString,true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			messageString=df.format(new Date())+"save logs error！！\n";
			System.out.println(messageString);
			releaseInformixConnList();
			e.printStackTrace();
		}
	}
	
	public  void getUsersForGroup() throws Exception
	{
		try {
			for(int i=1;i<=connectionSize;i++){
				int randConn=i-1;
				System.out.println("111111111111111111111111111"+informixConnList.get(randConn));
				statement = ((Connection)informixConnList.get(randConn)).createStatement();
				System.out.println("222222222222222222222222222"+statement);
				String sql = "select  1 as tabname  from dual";
				System.out.println("3333333333333333333333333333");
				resultSet = statement.executeQuery(sql);
				System.out.println("444444444444444444444444"+resultSet);
				resultSet.next();
				messageString=df.format(new Date())+"connection num=="+randConn+"，get connection success!\n";
				System.out.println("55555555555555555555555555555555"+messageString);
			}		
		} catch (Exception e) {
			// TODO: handle exception
//			throw new Exception("getUsersForGroup is error");
			messageString=df.format(new Date())+"get connection fail........\n";
			System.out.print(messageString);
		}finally{
			if(statement!=null){
				statement.close();
			}
			statement=null;
			if(resultSet!=null){
				resultSet.close();
			}
			resultSet=null;
		}
	}
	
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			saveToDisk();
			try {
				//睡眠5分钟
				Thread.sleep(5*60*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				messageString=df.format(new Date())+e.getMessage()+"\n";
				System.out.println(messageString);
				e.printStackTrace();
			}
			try {
				getUsersForGroup();
			} catch (Exception e) {
				// TODO Auto-generated catch block
//				messageString=df.format(new Date())+e.getMessage()+"\n";
//				System.out.println(messageString);
//				saveToDisk();
//				releaseInformixConnList();
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	public static void main(String[] args){
		TestOracleConn testInformixConn;
		try {
			testInformixConn = new TestOracleConn();
			new Thread(testInformixConn).start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
