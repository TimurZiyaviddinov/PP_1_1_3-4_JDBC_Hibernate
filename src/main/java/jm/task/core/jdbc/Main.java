package jm.task.core.jdbc;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService service = new UserServiceImpl();
        service.createUsersTable();
        service.saveUser("Person1", "Surname1", (byte) 20);
        service.saveUser("Person2", "Surname2", (byte) 25);
        service.saveUser("Person3", "Surname3", (byte) 32);
        service.saveUser("Person4", "Surname4", (byte) 26);
        service.getAllUsers().forEach(user -> System.out.println(user.toString()));
        service.cleanUsersTable();
        service.dropUsersTable();
        // реализуйте алгоритм здесь
    }
}
