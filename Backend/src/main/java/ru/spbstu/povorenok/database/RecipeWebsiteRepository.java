package ru.spbstu.povorenok.database;

import java.sql.*;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.spbstu.povorenok.model.*;

@Repository
public class RecipeWebsiteRepository
{
    @Value("${database.url}")
    public String DB_URL;

    @Value("${database.user}")
    public String DB_User;

    @Value("${database.password}")
    public String DB_Password;

    public Connection getConnection() {

        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            connection = DriverManager.getConnection(DB_URL, DB_User, DB_Password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return connection;
    }

    public User getUser(String login, String password) {

        User user = null;

        Connection connection = getConnection();

        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM users WHERE login = \'" +  login + "\' AND password = \'" + password + "\'";
            ResultSet result = statement.executeQuery(query);

            if (result.next()) {
                user = new User(result.getLong("id_user"), result.getString("login"),
                        result.getString("password"), result.getString("email"));
            }

            result.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return user;
    }

    public User getUser(String login) {

        User user = null;

        Connection connection = getConnection();

        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM users WHERE login = \'" +  login + "\'";
            ResultSet result = statement.executeQuery(query);

            if (result.next()) {
                user = new User(result.getLong("id_user"), result.getString("login"),
                        result.getString("password"), result.getString("email"));
            }

            result.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return user;
    }

    public void addUser(User user) {

        Connection connection = getConnection();

        try {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO users (login, password, email) VALUES (\'" + user.getLogin() + "\', \'" +
                    user.getPassword() + " \', \'" + user.getEmail() + "\')";
            statement.execute(query);

            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public LinkedList<Category> getAllCategories() {

        LinkedList<Category> categories = null;

        Connection connection = getConnection();

        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM categories";
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                categories.add(new Category(result.getLong("id_category"), result.getString("name")));
            }

            result.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return categories;
    }

    public LinkedList<Cuisine> getAllCuisines() {

        LinkedList<Cuisine> cuisines = null;

        Connection connection = getConnection();

        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM cuisines";
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                cuisines.add(new Cuisine(result.getLong("id_cuisine"), result.getString("name")));
            }

            result.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return cuisines;
    }
}
