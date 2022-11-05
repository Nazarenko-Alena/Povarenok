package ru.spbstu.povarenok.repository;

import java.sql.*;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.spbstu.povarenok.model.*;

@Repository
public class RecipeWebsiteRepository
{
    @Value("${database.url}")
    public String DB_URL;

    @Value("${database.user}")
    public String DB_USER;

    @Value("${database.password")
    public String DB_PASSWORD;

    public Connection getConnection() {

        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return connection;
    }

    public void addUser(User user) {

        Connection connection = getConnection();

        try {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO users (login, password, email) VALUES (\'" + user.getLogin() + "\', \'" +
                    user.getPassword() + "\', \'" + user.getEmail() + "\')";
            statement.execute(query);

            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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

    public LinkedList<Category> getAllCategories() {

        LinkedList<Category> categories = new LinkedList<>();

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

    public Category getCategory(String name) {

        Category category = null;

        Connection connection = getConnection();

        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM categories WHERE name = \'" +  name + "\'";
            ResultSet result = statement.executeQuery(query);

            if (result.next()) {
                category = new Category(result.getLong("id_category"), result.getString("name"));
            }

            result.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return category;
    }

    public LinkedList<Cuisine> getAllCuisines() {

        LinkedList<Cuisine> cuisines = new LinkedList<>();

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

    public Cuisine getCuisine(String name) {

        Cuisine cuisine = null;

        Connection connection = getConnection();

        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM cuisines WHERE name = \'" +  name + "\'";
            ResultSet result = statement.executeQuery(query);

            if (result.next()) {
                cuisine = new Cuisine(result.getLong("id_cuisine"), result.getString("name"));
            }

            result.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return cuisine;
    }

    public void addIngredient(Ingredient ingredient) {

        Connection connection = getConnection();

        try {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO ingredients (id_recipe, name, grams) VALUES (" + ingredient.getIdRecipe() +
                    ", \'" + ingredient.getName() + "\', " + ingredient.getGrams() + ")";
            statement.execute(query);

            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addRecipe(Recipe recipe) {

        Connection connection = getConnection();

        Long idUser = getUser(recipe.getUserLogin()).getId();
        Long idCuisine = getCuisine(recipe.getCuisine()).getId();
        Long idCategory = getCategory(recipe.getCategory()).getId();

        try {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO recipes (id_user, name, image_url, date_added, cuisine, category, cooking_time, " +
                    "description, recipe) VALUES (" + idUser + ", \'" + recipe.getName() + "\', \'" + recipe.getImageUrl() +
                    "\', \'" + recipe.getDateAdded() + "\', " + idCuisine + ", " + idCategory + ", " + recipe.getCookingTime() +
                    ", \'" + recipe.getDescription() + "\', \'" + recipe.getRecipe() + "\')";
            statement.execute(query);

            Long idRecipe = getRecipeByName(recipe.getName()).getId();

            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.setIdRecipe(idRecipe);
                addIngredient(ingredient);
            }

            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Recipe getRecipeByName(String name) {

        Recipe recipe = null;
        LinkedList<Ingredient> ingredients = new LinkedList<>();

        Connection connection = getConnection();

        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM ingredients " +
                    "JOIN recipes ON ingredients.id_recipe = recipes.id_recipe " +
                    "WHERE recipes.name = \'" + name + "\'";
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                ingredients.add(new Ingredient(result.getLong("id_ingredient"),
                        result.getLong("id_recipe"), result.getString("name"),
                        result.getDouble("grams")));
            }

            statement = connection.createStatement();
            query = "SELECT id_recipe, users.login, recipes.name, image_url, date_added, cuisines.name, " +
                    "categories.name, cooking_time, description, recipe FROM recipes " +
                    "JOIN users ON recipes.id_user = users.id_user " +
                    "JOIN cuisines ON recipes.cuisine = cuisines.id_cuisine " +
                    "JOIN categories ON recipes.category = categories.id_category " +
                    "WHERE recipes.name = \'" + name + "\'";
            result = statement.executeQuery(query);

            if (result.next()) {
                recipe = new Recipe(result.getLong("id_recipe"), result.getString("login"),
                        result.getString("name"), result.getString("image_url"),
                        result.getString("date_added"), result.getString(6),
                        result.getString(7), result.getInt("cooking_time"), ingredients,
                        result.getString("description"),  result.getString("recipe"));
            }

            result.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return recipe;
    }

    public void saveRecipe(String login, String name) {

        Connection connection = getConnection();

        Long idUser = getUser(login).getId();
        Long idRecipe = getRecipeByName(name).getId();

        try {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO saved_recipes (id_user, id_recipe) VALUES (" + idUser + ", " + idRecipe + ")";
            statement.execute(query);

            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}