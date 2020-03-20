package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import domain.User;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
	private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(UserServiceImpl.class);
    private static UserService userServiceImpl;
    private UserDao userDao;

    private UserServiceImpl (){
        try {
            userDao = new UserDaoImpl();
        } catch (ClassNotFoundException e) {
            logger.error(e);
        } catch (SQLException e) {
            logger.error(e);
        } catch (InstantiationException e) {
            logger.error(e);
        } catch (IllegalAccessException e) {
            logger.error(e);
        }
    }

    public static UserService getUserService(){
        if (userServiceImpl==null){
            userServiceImpl = new UserServiceImpl();
        }
        return userServiceImpl;
    }

    @Override
    public User create(User user) {
        return userDao.create(user);
    }

    @Override
    public User read(Integer id) {
        return userDao.read(id);
    }

    @Override
    public User update(Integer id, User user) {
        return userDao.update(id, user);
    }

    @Override
    public void delete(Integer id) {
        userDao.delete(id);
    }

    @Override
    public List<User> readAll() {
        return userDao.readAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }
}
