package ua.nure.kn16.ioshchenko.usermanagement.db;

import com.mockobjects.dynamic.Mock;

public class MockDaoFactory extends DaoFactory {
    private Mock mockUserDao;

    MockDaoFactory() {
        mockUserDao = new Mock(UserDAO.class);
    }

    public Mock getMockUserDao() {
        return mockUserDao;
    }

    @Override
    public UserDAO getUserDAO() throws ReflectiveOperationException {
        return (UserDAO) mockUserDao.proxy();
    }
}