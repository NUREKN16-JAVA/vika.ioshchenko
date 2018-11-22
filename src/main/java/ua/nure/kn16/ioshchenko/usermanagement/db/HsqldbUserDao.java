package ua.nure.kn16.ioshchenko.usermanagement.db;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.dbunit.database.DatabaseConnection;

import ua.nure.kn16.ioshchenko.usermanagement.User;

class HsqlDBUserDao implements UserDAO{
    public static final String FIND_ALL_QUERY = "SELECT id, firstname, lastname, dateofbirth FROM users";
    private static final String INSERT_QUERY = "INSERT INTO users (firstname,lastname,dateofbirth) VALUES (?,?,?)";
    private static final String FIND_BY_ID_QUERY = "SELECT id, firstname, lastname, dateofbirth FROM users WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE users SET firstname = ?, lastname = ?, dateofbirth = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM users WHERE id = ?";
    private ConnectionFactory connectionFactory;
  
    public HsqlDBUserDao(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public HsqlDBUserDao() {
    }
    /**
	 * @return the connectionFactory
	 */
    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public User create(User user) throws DatabaseException {
        Connection connection = connectionFactory.createConnection();
        try {
            PreparedStatement statement = connection
                    .prepareStatement(INSERT_QUERY);
            statement.setString(1,user.getFirstName());
            statement.setString(2,user.getLastName());
            statement.setDate(3,new Date(user.getDateofBirth().getTime()));
            int number = statement.executeUpdate();
            if(number != 1){
                throw new DatabaseException("Number of inserted raws: " + number);
            }
            CallableStatement callableStatement = connection
                    .prepareCall("call IDENTITY ()");
            ResultSet keys = callableStatement.executeQuery();
            if(keys.next()){
                user.setId(new Long(keys.getLong(1)));
            }
            keys.close();
            callableStatement.close();
            statement.close();
            return user;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public User find(Long id) throws DatabaseException {
    	User user;
        try {
            user = null;
            Connection connection = connectionFactory.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY);
            preparedStatement.setLong(1,id);
            ResultSet oneUserResultSet = preparedStatement.executeQuery();
           if (oneUserResultSet.next()){
                user = new User();
                user.setId(new Long(oneUserResultSet.getLong("ID")));
                user.setFirstName(oneUserResultSet.getString("FIRSTNAME"));
                user.setLastName(oneUserResultSet.getString("LASTNAME"));
                user.setDateofBirth(oneUserResultSet.getDate("DATEOFBIRTH"));
            }
            connection.close();
            preparedStatement.close();
            oneUserResultSet.close();
            return user;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }

    }

    @Override
    public Collection<User> findAll() throws DatabaseException {
        Collection<User> result = new LinkedList<>();
        try {
            Connection connection = connectionFactory.createConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_QUERY);
            while (resultSet.next()){
                User user = new User();
                user.setId(new Long(resultSet.getLong(1)));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setDateofBirth(resultSet.getDate(4));
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void update(User user) throws DatabaseException {
        Connection connection = connectionFactory.createConnection();
        try {
            PreparedStatement statement = connection
                    .prepareStatement(UPDATE_QUERY);
            statement.setString(1,user.getFirstName());
            statement.setString(2,user.getLastName());
            statement.setDate(3,new Date(user.getDateofBirth().getTime()));
            statement.setLong(4, user.getId());
            int number = statement.executeUpdate();
            if(number != 1){
                throw new DatabaseException("Number of updated raws: " + number);
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
    @Override
	public void delete(User user) throws DatabaseException {
		if(user.getId() == null) throw new DatabaseException();
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection
					.prepareStatement(DELETE_QUERY);
			statement.setLong(1, user.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
		
	}
}
