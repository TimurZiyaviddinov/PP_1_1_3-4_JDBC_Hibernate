package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static long id = 1L;
    Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() throws SQLException {

    }

    public void createUsersTable() {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("CREATE TABLE IF NOT EXISTS Users(id int, name varchar(45), lastName varchar(45), age int(0))");) {

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("DROP TABLE IF EXISTS Users");) {
            preparedStatement.execute();
            id = 1L;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO Users VALUES(?,?,?,?)");) {

            preparedStatement.setInt(1, (int) id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, lastName);
            preparedStatement.setInt(4, age);
            id++;
            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("delete from users where id = " + id + ";")) {

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * from users")) {

            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                list.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public void cleanUsersTable() {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("Delete from users")) {

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
