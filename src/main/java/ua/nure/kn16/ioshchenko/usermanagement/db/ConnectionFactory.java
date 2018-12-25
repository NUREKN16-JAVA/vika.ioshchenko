package ua.nure.kn16.ioshchenko.usermanagement.db;
import java.sql.Connection;

/**
 * Interface to create connection with DB
 */
public interface ConnectionFactory {
    Connection createConnection () throws DatabaseException;
}