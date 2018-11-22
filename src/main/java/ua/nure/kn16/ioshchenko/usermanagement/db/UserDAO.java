package ua.nure.kn16.ioshchenko.usermanagement.db;

import java.util.Collection;
import java.util.List;

import ua.nure.kn16.ioshchenko.usermanagement.User;

public interface UserDAO {
    /**
     * Add user to DB table USER
     * @param user with null id field
     * @return user modified record exemplar with DB auto-generated id field
     */  
    public User create(final User user) throws DatabaseException;
    /**
     * Select user from DB table USERS 
     * @param id - id of user we want to find
     * @return user record that have such id
     * @throws DatabaseException 
     */
    public User find(final Long id) throws DatabaseException;
    /**
     * Select all users from DB table USERS
     * @return list of users records from DB table USERS
     */
    public Collection<User> findAll() throws DatabaseException;
    /**
     * Update user record in DB table USERS 
     * @param user
     * @throws DatabaseException 
     */
    public void update(final User user) throws DatabaseException;
    /**
     * Delete user record in DB table USERS 
     * @param user
     * @throws DatabaseException 
     */
    public void delete(final User user) throws DatabaseException;
    /**
    *
    * @param connectionFactory
    */
     void setConnectionFactory(ConnectionFactory connectionFactory);
}