package com.yls.testMain;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.HTablePool;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.json.JSONException;

public class Main {
	public static void main(String[] args) throws IOException, JSONException, SQLException {
		//		String filePath = "F:/1101.json";
		//		File file = new File ( filePath ) ;
		//		String encoding="UTF-8";
		//		InputStreamReader read = new InputStreamReader(
		//				new FileInputStream(file),encoding);//���ǵ������ʽ
		//		BufferedReader reader = new BufferedReader(read);
		//		String tempString = null ;
		//		int i =1;
		//		while ( ( tempString = reader.readLine ( ) ) != null )
		//		{
		//			insert(tempString,conn);
		//			System.out.println("�ɹ����"+i);
		//			i++;
		//			
		//		}
		//		System.out.println("������");
		//		conn.close();
		//		reader.close();	
		
		System.out.println( getAllTables());
		 
	}
	public static void insert(String data,Connection conn){
		String sql = "insert into blogDataSTest(content) values(?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, data);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static Configuration configuration; 
	private static HBaseAdmin admin = null;  
	private static Connection conn;
	static { 
		//192.168.1.30
		configuration = HBaseConfiguration.create(); 
		configuration.set("hbase.zookeeper.property.clientPort", "2181"); 
		configuration.set("hbase.zookeeper.quorum", "192.168.1.30"); 
		configuration.set("hbase.master", "192.168.1.30:600000"); 
		try {
			admin = new HBaseAdmin(configuration);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
//		Properties properties=new Properties();  
//		InputStream is=blogData.class.getClassLoader().getResourceAsStream("db.properties");  
//		try {
//			properties.load(is);
//			Class.forName(properties.getProperty("driverClass"));
//			conn = DriverManager.getConnection(properties.getProperty("jdbcUrl"),
//					properties.getProperty("user"), properties.getProperty("password"));
//			System.out.println(conn);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  
	} 
	/** 
	 * ��ѯ�������� 
	 * @param tableName 
	 */  
	public static void QueryAll(String tableName) {  
		HTablePool pool = new HTablePool(configuration, 1000);  
		HTable table = (HTable) pool.getTable(tableName);  
		try {  
			ResultScanner rs = table.getScanner(new Scan());  
			for (Result r : rs) {  
				System.out.println("��õ�rowkey:" + new String(r.getRow()));  
				for (KeyValue keyValue : r.raw()) {  
					System.out.println("�У�" + new String(keyValue.getFamily())  
							+ "====ֵ:" + new String(keyValue.getValue()));  
				}  
			}  
		} catch (IOException e) {  
			e.printStackTrace();  
		}  
	}  
	// ����һ�ű�ָ������������  
	public void createTable(String tableName, String columnFarily)  
			throws Exception {  

		if (admin.tableExists(tableName)) {  
			System.out.println(tableName + "���ڣ�");  
			System.exit(0);  
		} else {  
			HTableDescriptor tableDesc = new HTableDescriptor(tableName);  
			tableDesc.addFamily(new HColumnDescriptor(columnFarily));  
			admin.createTable(tableDesc);  
			System.out.println("������ɹ���");  
		}  
	}  

	// Hbase��ȡ���еı���Ϣ  
	public static List getAllTables() {  
		List<String> tables = null;  
		if (admin != null) {  
			try {  
				HTableDescriptor[] allTable = admin.listTables();  
				if (allTable.length > 0)  
					tables = new ArrayList<String>();  
				for (HTableDescriptor hTableDescriptor : allTable) {  
					tables.add(hTableDescriptor.getNameAsString());  
					System.out.println(hTableDescriptor.getNameAsString());  
				}  
			} catch (IOException e) {  
				e.printStackTrace();  
			}  
		}  
		return tables;  
	}  

	// Hbase����ĳ���������һ����¼  
	public boolean addOneRecord(String table, String key, String family,  
			String col, byte[] dataIn) {  
		HTablePool tp = new HTablePool(configuration, 1000);  
		HTable tb = (HTable) tp.getTable(table);  
		Put put = new Put(key.getBytes());  
		put.add(family.getBytes(), col.getBytes(), dataIn);  
		try {  
			tb.put(put);  
			System.out.println("����������" + key + "�ɹ�������");  
			return true;  
		} catch (IOException e) {  
			System.out.println("����������" + key + "ʧ�ܣ�����");  
			return false;  
		}  
	}  

	// Hbase���м�¼��Ϣ�Ĳ�ѯ  
	public void getValueFromKey(String table, String key) {  
		HTablePool tp = new HTablePool(configuration, 1000);  
		HTable tb = (HTable) tp.getTable(table);  
		Get get = new Get(key.getBytes());  
		try {  
			Result rs = tb.get(get);  
			if (rs.raw().length == 0) {  
				System.out.println("�����ڹؼ���Ϊ" + key + "���У�!");  

			} else {  
				for (KeyValue kv : rs.raw()) {  
					System.out.println(new String(kv.getKey()) + " "  
							+ new String(kv.getValue()));  
				}  

			}  
		} catch (IOException e) {  
			e.printStackTrace();  
		}  
	}  

	// ��ʾ�������ݣ�ͨ��HTable Scan���ȡ���б����Ϣ  
	public void getAllData(String tableName) throws Exception {  
		HTable table = new HTable(configuration, tableName);  
		Scan scan = new Scan();  
		ResultScanner rs = table.getScanner(scan);  
		for (Result r : rs) {  
			for (KeyValue kv : r.raw()) {  
				System.out.println(new String(kv.getKey())  
						+ new String(kv.getValue()));  
			}  
		}  
	}  

	// Hbase���м�¼��Ϣ��ɾ��  
	public boolean deleteRecord(String table, String key) {  
		HTablePool tp = new HTablePool(configuration, 1000);  
		HTable tb = (HTable) tp.getTable(table);  
		Delete de = new Delete(key.getBytes());  
		try {  
			tb.delete(de);  
			return true;  
		} catch (IOException e) {  
			System.out.println("ɾ����¼" + key + "�쳣������");  
			return false;  
		}  
	}  

	// Hbase�б��ɾ��  
	public boolean deleteTable(String table) {  
		try {  
			if (admin.tableExists(table)) {  
				admin.disableTable(table);  
				admin.deleteTable(table);  
				System.out.println("ɾ����" + table + "!!!");  
			}  
			return true;  
		} catch (IOException e) {  
			System.out.println("ɾ����" + table + "�쳣!!!");  
			return false;  
		}  
	}  
}
