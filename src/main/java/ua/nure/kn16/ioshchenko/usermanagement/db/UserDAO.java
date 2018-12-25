package ua.nure.kn16.ioshchenko.usermanagement.db;
import ua.nure.kn16.ioshchenko.usermanagement.User;
import java.util.Collection;

public interface UserDAO {
    User create (User user) throws DatabaseException;
    void update (User user) throws DatabaseException;
    void delete (User user) throws DatabaseException;
    User find (Long id) throws DatabaseException;
    Collection findAll () throws DatabaseException;
    void setConnectionFactory(ConnectionFactory connectionFactory);
}