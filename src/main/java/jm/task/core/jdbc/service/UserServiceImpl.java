package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDaoJDBCImpl serv;

    public UserServiceImpl() {
        serv = new UserDaoJDBCImpl();
    }

    public void createUsersTable() {
        serv.createUsersTable();
    }

    public void dropUsersTable() { serv.dropUsersTable(); }

    public void saveUser(String name, String lastName, byte age) {
        serv.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        serv.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return serv.getAllUsers();
    }

    public void cleanUsersTable() {
        serv.cleanUsersTable();
    }
}
