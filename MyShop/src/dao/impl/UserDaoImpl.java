package dao.impl;

import dao.UserDao;
import domain.User;
import utils.ConnectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;


public class UserDaoImpl implements UserDao {
	private static String READ_ALL = "select * from users";
    private static String CREATE = "insert into users(`email`,`first_name`, `last_name`, `role`, `password`) values (?,?,?,?,?)";
    private static String READ_BY_ID = "select * from users where id =?";
    private static String UPDATE_BY_ID = "update users set email=?, first_name = ?, last_name = ?, role=?, password = ?  where id = ?";
    private static String DELETE_BY_ID = "delete from users where id=?";
    private static String READ_BY_EMAIL = "select * from users where email = ?";

    private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(UserDaoImpl.class);

    private Connection connection;
    private PreparedStatement preparedStatement;

    public UserDaoImpl() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        connection = ConnectionUtils.openConnection();
    }

    @Override
    public User create(User user) {
        try {
            preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            user.setId(rs.getInt(1));
        } catch (SQLException e) {
            logger.error(e);
        }
        return user;
    }

    @Override
    public User read(Integer id) {
        User user = null;
        try {
            preparedStatement = connection.prepareStatement(READ_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();

            int userId = rs.getInt("id");
            String email = rs.getString("email");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String role = rs.getString("role");
            String password = rs.getString("password");

            user = new User(userId, email, firstName, lastName, role, password);
        } catch (SQLException e) {
            logger.error(e);
        }
        return user;
    }

    @Override
    public User update(Integer id, User user) {
        try {
            preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
            preparedStatement.setInt(6, id);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error(e);
        }
        return user;
    }

    @Override
    public void delete(Integer id) {
        try {
            preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Override
    public List<User> readAll() {
        List<User> listOfUsers = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(READ_ALL);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int userId = rs.getInt("id");
                String email = rs.getString("email");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String role = rs.getString("role");
                String password = rs.getString("password");
                listOfUsers.add(new User(userId, email, firstName, lastName, role, password));
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return listOfUsers;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = null;
        try {
            preparedStatement = connection.prepareStatement(READ_BY_EMAIL);
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();

            int userId = rs.getInt("id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String role = rs.getString("role");
            String password = rs.getString("password");

            user = new User(userId, email, firstName, lastName, role, password);
        } catch (SQLException e) {
            logger.error(e);
        }
        return user;
    }
}
