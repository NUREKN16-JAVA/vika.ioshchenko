package ua.nure.kn16.ioshchenko.usermanagement.db;


import org.dbunit.Assertion;
import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.XmlDataSet;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.nure.kn16.ioshchenko.usermanagement.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

public class HsqlDBUserDaoTest extends DatabaseTestCase {
    private User user;
    private UserDAO dao;
    private static final String USER_SURNAME = "Ivan";
	private static final String USER_NAME = "Ivanov";
	private static final String DATE_OF_BIRTH_ETALON = "18-12-1998";
    private ConnectionFactory connectionFactory;
    private static final Long ID = 0L;

    @Before
    public void setUp() throws Exception {
        getConnection();
        user = new User("Ivan","Ivanov",new Date());
        dao = new HsqlDBUserDao(connectionFactory);
    }

    @After
    public void tearDown() throws Exception {

    }
    /**
     * Test for creating new user record in DB method
     * @throws DatabaseException
     */
    @Test
    public void testCreate() throws DatabaseException {
        assertNull(user.getId());
        User userResult = dao.create(user);
        assertNotNull(userResult);
        assertNotNull(userResult.getId());
        assertEquals(user.getFirstName(),userResult.getFirstName());
        assertEquals(user.getLastName(),userResult.getLastName());
        assertEquals(user.getDateofBirth(),userResult.getDateofBirth());
    }
    @Override
    protected IDatabaseConnection getConnection() throws Exception {
        connectionFactory = new ConnectionFactoryImpl("org.hsqldb.jdbcDriver",
                "jdbc:hsqldb:file:db/UserManagment","sa","");
        return new DatabaseConnection(connectionFactory.createConnection());
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        IDataSet dataSet = new XmlDataSet(getClass().getClassLoader()
                .getResourceAsStream("usersDataSet.xml"));
        return dataSet;
    }
    /**
     * Test method to find and return all user records in DB
     * @throws DatabaseException
     */
    @Test
    public void testFindAll() throws DatabaseException {
        User userResult = dao.create(user);
        int etalonSize = 1;
        Collection collection = dao.findAll();
        assertNotNull("Collection is null", collection);
        assertEquals("Collection size.", etalonSize, collection.size());
    }
    /**
     * Test for finding  user by ID
     * @throws DatabaseException
     */
    @Test
    public void testFind () throws DatabaseException {
        User testUser = dao.find(ID);
        assertNotNull(testUser);
        assertEquals( user.getFirstName(),testUser.getFirstName());
        assertEquals(user.getLastName(),testUser.getLastName());
    }  
    /**
     * Test for user deleting method
     * @throws DatabaseException
     */
    @Test
    public void testDelete() throws DatabaseException {
        User testUser = new User(ID, "Ivan", "Ivanov", new Date());
        dao.delete(testUser);
        assertNull(dao.find(ID));
    }
    
    /**
     * Test for updating user 
     * @throws DatabaseException
     */
    @Test
    public void testUpdate() {
		try {
			User user = new User();
			user.setFirstName(USER_NAME+"Update");
			user.setLastName(USER_SURNAME+"Update");
			user.setDateofBirth(new SimpleDateFormat("dd-MM-yyyy").parse(DATE_OF_BIRTH_ETALON));
			user.setId(1000L);
			dao.update(user);
			IDataSet databaseDataSet = getConnection().createDataSet();
			ITable actualTable = databaseDataSet.getTable("USERS");
			IDataSet expectedDataSet = new XmlDataSet(getClass().getResourceAsStream("/usersUpdateDataSet.xml"));
			ITable expectedTable = expectedDataSet.getTable("USERS");
			Assertion.assertEquals(expectedTable, actualTable);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}