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
 * Description: 给此类一个描述
 * Company: 济宁九点连线信息技术有限公司
 * ProjectName: day_18
 * @author fupengpeng
 * @date 2017年8月1日 下午1:54:12
 */
public class JDBCTest {
	
	/**
	 * 
	 * Description: Driver是一个接口，数据库厂商必须提供实现的接口，能从其中获取数据库连接
	 * @throws SQLException 
	 * 
	 */
	@Test
	public void testDriver() throws SQLException{
		//1.创建driver实现类的对象
		Driver driver = new com.mysql.jdbc.Driver();
		//2.连接数据库基本信息准备
		String url = "jdbc:mysql://127.0.0.1:3306/shoppingmall";
		Properties info = new Properties();
		info.put("user", "root");
		info.put("password", "root");
		
		//3.调用driver接口的connect（url，info）获取数据库连接
		Connection connection = driver.connect(url, info);//连接数据库
		System.out.println(connection);
		
	}
	
	/**
	 * 
	 * Description: 通用的数据库驱动
	 * @return
	 * @throws Exception
	 */
	public Connection getConnection() throws Exception{
		String driverClass = null;
		String jdbcUrl = null;
		String user = null;
		String password = null;
		
		//读取类路径下的jdbc。properties文件
		
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
