package day_18;

import java.awt.event.MouseWheelEvent;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;



/**
 * 
 * Title: JDBCTest
 * Description: ������һ������
 * Company: �����ŵ�������Ϣ�������޹�˾
 * ProjectName: day_18
 * @author fupengpeng
 * @date 2017��8��1�� ����1:54:12
 */
public class JDBCTest {
	
	/**
	 * 
	 * Description: Driver��һ���ӿڣ����ݿ⳧�̱����ṩʵ�ֵĽӿڣ��ܴ����л�ȡ���ݿ�����
	 * @throws SQLException 
	 * 
	 */
	@Test
	public void testDriver() throws SQLException{
		//1.����driverʵ����Ķ���
		Driver driver = new com.mysql.jdbc.Driver();
		//2.�������ݿ������Ϣ׼��
		String url = "jdbc:mysql://127.0.0.1:3306/shoppingmall";
		Properties info = new Properties();
		info.put("user", "root");
		info.put("password", "root");
		
		//3.����driver�ӿڵ�connect��url��info����ȡ���ݿ�����
		Connection connection = driver.connect(url, info);//�������ݿ�
		System.out.println(connection);
		
	}
	
	/**
	 * 
	 * Description: ͨ�õ����ݿ�����
	 * @return
	 * @throws Exception
	 */
	public Connection getConnection() throws Exception{
		String driverClass = null;
		String jdbcUrl = null;
		String user = null;
		String password = null;
		
		//��ȡ��·���µ�jdbc��properties�ļ�
		
		InputStream inputStream = 
				getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		Properties properties = new Properties();
		properties.load(inputStream);
		driverClass = properties.getProperty("driver");
		jdbcUrl = properties.getProperty("jdbcUrl");
		user = properties.getProperty("user");
		password = properties.getProperty("password");
		
		Driver driver = (Driver) Class.forName(driverClass).newInstance();
		Properties info = new Properties();
		info.put("user", user);
		info.put("password", password);
		Connection connection = driver.connect(jdbcUrl, info);
		
		return connection;
	}
	
	@Test
	public void testGetConnection() throws Exception{
		System.out.println(getConnection());
	}

}
