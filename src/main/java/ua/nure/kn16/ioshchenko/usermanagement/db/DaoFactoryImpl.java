package ua.nure.kn16.ioshchenko.usermanagement.db;

public class DaoFactoryImpl extends DaoFactory {
    @Override
    public UserDAO getUserDAO() throws ReflectiveOperationException {
        UserDAO userDAO = null;
        try {
            Class UserDOAClass = Class.forName(properties.getProperty("dao.ua.nure.kn16.ioshchenko.usermanagement.db.UserDAO"));
            userDAO = (UserDAO) UserDOAClass.newInstance();
            userDAO.setConnectionFactory(createConnection());
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new ReflectiveOperationException(e);
        }

        return userDAO;
    }
}
