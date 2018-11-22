package ua.nure.kn16.ioshchenko.usermanagement.db;

import java.io.IOException;
import java.util.Properties;

public class DaoFactory {
    public static final String USER_DAO = "dao.ua.nure.kn16.ioshchenko.usermanagement.db.UserDAO";
private final Properties properties;
	
	private final static DaoFactory INSTANCE = new DaoFactory();
	
	/**  
	 * Create exactly one DaoFactory

	 */
	public static DaoFactory getInstance() {
		return INSTANCE;
	}
	
	private DaoFactory() {
		properties = new Properties();
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream("settings.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private ConnectionFactory getConnectionFactory() {
		String user = properties.getProperty("connection.user");
		String password = properties.getProperty("connection.password");
		String url = properties.getProperty("connection.url");
		String driver = properties.getProperty("connection.driver");
		return new ConnectionFactoryImpl(driver, url, user, password);
	}
	/**
	 * 
	 * @return UserDao 
	 */
	public UserDAO getUserDao() {
		UserDAO result = null;
		try {
			Class clazz = Class.forName(properties.getProperty(USER_DAO));
			result = (UserDAO) clazz.newInstance();
			result.setConnectionFactory(getConnectionFactory());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}
}