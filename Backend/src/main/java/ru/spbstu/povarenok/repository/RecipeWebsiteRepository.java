package ru.spbstu.povarenok.repository;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.stereotype.Repository;
import java.util.LinkedList;
import java.sql.*;

import ru.spbstu.povarenok.model.*;

@Repository
public class RecipeWebsiteRepository
{
    PGSimpleDataSource dataSource;

    public RecipeWebsiteRepository(PGSimpleDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean addUser(User user) {

        String query = "INSERT INTO users (login, password, email) VALUES (\'" + user.getLogin() + "\', \'" +
                user.getPassword() + "\', \'" + user.getEmail() + "\')";

        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();

            statement.execute(query);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public LinkedList<Ingredient> getIngredients(Long idRecipe) {
        LinkedList<Ingredient> ingredients = new LinkedList<>();

        String query = "SELECT * FROM ingredients WHERE id_recipe = " + idRecipe;

        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(query)) {

            while (result.next()) {
                ingredients.add(new Ingredient(result.getLong("id_ingredient"),
                        result.getLong("id_recipe"), result.getString("name"),
                        result.getDouble("grams")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return ingredients;
        }

        return ingredients;
    }

    public Recipe getRecipe(Long id) {

        Recipe recipe = null;

        LinkedList<Ingredient> ingredients = getIngredients(id);

        String query = "SELECT id_recipe, users.login, recipes.name, image_url, date_added, cuisines.name, " +
                "categories.name, cooking_time, description, recipe FROM recipes " +
                "JOIN users ON recipes.id_user = users.id_user " +
                "JOIN cuisines ON recipes.cuisine = cuisines.id_cuisine " +
                "JOIN categories ON recipes.category = categories.id_category " +
                "WHERE id_recipe = " + id;

        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(query)) {

            if (result.next()) {
                recipe = new Recipe(result.getLong("id_recipe"), result.getString("login"),
                        result.getString("name"), result.getString("image_url"),
                        result.getString("date_added"), result.getString(6),
                        result.getString(7), result.getInt("cooking_time"), ingredients,
                        result.getString("description"), result.getString("recipe"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return recipe;
    }

    public LinkedList<Recipe> getAddedRecipes(Long idUser) {
        LinkedList<Recipe> addedRecipes = new LinkedList<>();

        LinkedList<Long> idRecipes = new LinkedList<>();

        String query = "SELECT id_recipe FROM recipes WHERE id_user = " + idUser;

        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(query)) {

            while (result.next()) {
                idRecipes.add(result.getLong("id_recipe"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return addedRecipes;
        }

        for (Long id : idRecipes)
            addedRecipes.add(getRecipe(id));

        return addedRecipes;
    }

    public LinkedList<Recipe> getSavedRecipes(Long idUser) {
        LinkedList<Recipe> savedRecipes = new LinkedList<>();

        LinkedList<Long> idRecipes = new LinkedList<>();

        String query = "SELECT id_recipe FROM saved_recipes WHERE id_user = " + idUser;

        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(query)) {

            while (result.next()) {
                idRecipes.add(result.getLong("id_recipe"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return savedRecipes;
        }

        for (Long id : idRecipes)
            savedRecipes.add(getRecipe(id));

        return savedRecipes;
    }

    public User getUser(String login, String password) {

        Long idUser = null;
        String email = null;

        String query = "SELECT * FROM users WHERE login = \'" + login + "\' AND password = \'" + password + "\'";

        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(query)) {

            if (result.next()) {
                idUser = result.getLong("id_user");
                email = result.getString("email");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

        LinkedList<Recipe> addedRecipes = getAddedRecipes(idUser);
        LinkedList<Recipe> savedRecipes = getSavedRecipes(idUser);

        return new User(idUser, login, password, email, addedRecipes, savedRecipes);
    }

    public User getUser(String login) {

        String password = null;

        String query = "SELECT * FROM users WHERE login = \'" +  login + "\'";

        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(query)) {

            if (result.next()) {
                password = result.getString("password");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

        return getUser(login, password);
    }

    public User getUserByEmail(String email) {

        String login = null;
        String password = null;

        String query = "SELECT * FROM users WHERE email = \'" +  email + "\'";

        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(query)) {

            if (result.next()) {
                login = result.getString("login");
                password = result.getString("password");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

        return getUser(login, password);
    }

    public LinkedList<Category> getAllCategories() {

        LinkedList<Category> categories = new LinkedList<>();

        String query = "SELECT * FROM categories";

        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(query);) {

            while (result.next()) {
                categories.add(new Category(result.getLong("id_category"), result.getString("name")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return categories;
    }

    public Category getCategory(String name) {

        Category category = null;

        String query = "SELECT * FROM categories WHERE name = \'" +  name + "\'";

        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(query)) {

            if (result.next()) {
                category = new Category(result.getLong("id_category"), result.getString("name"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return category;
    }

    public LinkedList<Cuisine> getAllCuisines() {

        LinkedList<Cuisine> cuisines = new LinkedList<>();

        String query = "SELECT * FROM cuisines";

        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(query)) {

            while (result.next()) {
                cuisines.add(new Cuisine(result.getLong("id_cuisine"), result.getString("name")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return cuisines;
    }

    public Cuisine getCuisine(String name) {

        Cuisine cuisine = null;

        String query = "SELECT * FROM cuisines WHERE name = \'" +  name + "\'";

        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(query);) {

            if (result.next()) {
                cuisine = new Cuisine(result.getLong("id_cuisine"), result.getString("name"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return cuisine;
    }

    public boolean addIngredient(Ingredient ingredient) {

        String query = "INSERT INTO ingredients (id_recipe, name, grams) VALUES (" + ingredient.getIdRecipe() +
                ", \'" + ingredient.getName() + "\', " + ingredient.getGrams() + ")";

        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {

            statement.execute(query);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public boolean addRecipe(Recipe recipe) {

        Long idUser = getUser(recipe.getUserLogin()).getId();
        Long idCuisine = getCuisine(recipe.getCuisine()).getId();
        Long idCategory = getCategory(recipe.getCategory()).getId();

        String query = "INSERT INTO recipes (id_user, name, image_url, date_added, cuisine, category, cooking_time, " +
                "description, recipe) VALUES (" + idUser + ", \'" + recipe.getName() + "\', \'" + recipe.getImageUrl() +
                "\', \'" + recipe.getDateAdded() + "\', " + idCuisine + ", " + idCategory + ", " + recipe.getCookingTime() +
                ", \'" + recipe.getDescription() + "\', \'" + recipe.getRecipe() + "\')";

        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {

            statement.execute(query);

            Long idRecipe = getRecipe(recipe.getName()).getId();

            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.setIdRecipe(idRecipe);
                addIngredient(ingredient);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public Recipe getRecipe(String name) {

        Long idRecipe = null;

        String query = "SELECT * FROM recipes WHERE recipes.name = \'" + name + "\'";

        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(query)) {

            if (result.next()) {
                idRecipe = result.getLong("id_recipe");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

        return getRecipe(idRecipe);
    }

    public Recipe getRecipeByUrl(String url) {

        Long idRecipe = null;

        String query = "SELECT * FROM recipes WHERE recipes.image_url = \'" + url + "\'";

        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(query)) {

            if (result.next()) {
                idRecipe = result.getLong("id_recipe");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

        return getRecipe(idRecipe);
    }

    public Recipe getRecipeByDescription(String description) {

        Long idRecipe = null;

        String query = "SELECT * FROM recipes WHERE recipes.description = \'" + description + "\'";

        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(query)) {

            if (result.next()) {
                idRecipe = result.getLong("id_recipe");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

        return getRecipe(idRecipe);
    }

    public Recipe getRecipeByStepByStepRecipe(String stepByStepRecipe) {

        Long idRecipe = null;

        String query = "SELECT * FROM recipes WHERE recipes.recipe = \'" + stepByStepRecipe + "\'";

        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(query)) {

            if (result.next()) {
                idRecipe = result.getLong("id_recipe");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

        return getRecipe(idRecipe);
    }

    public boolean saveRecipe(String login, String name) {

        Long idUser = getUser(login).getId();
        Long idRecipe = getRecipe(name).getId();

        String query = "INSERT INTO saved_recipes (id_user, id_recipe) VALUES (" + idUser + ", " + idRecipe + ")";

        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {

            statement.execute(query);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public LinkedList<Recipe> getLastRecipes(Integer count) {

        LinkedList<Recipe> recipes = new LinkedList<>();

        LinkedList<Long> idRecipes = new LinkedList<>();

        String query = "SELECT id_recipe FROM recipes ORDER BY date_added DESC LIMIT " + count;

        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(query)) {

            while (result.next()) {
                idRecipes.add(result.getLong("id_recipe"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return recipes;
        }

        for (Long id : idRecipes)
            recipes.add(getRecipe(id));

        return recipes;
    }

    public LinkedList<Recipe> getRecipes(String category, String cuisine) {

        LinkedList<Recipe> recipes = new LinkedList<>();

        LinkedList<Long> idRecipes = new LinkedList<>();
        LinkedList<LinkedList<Ingredient>> ingredients = new LinkedList<>();

        String query = "SELECT id_recipe FROM recipes " +
                "JOIN cuisines ON recipes.cuisine = cuisines.id_cuisine " +
                "JOIN categories ON recipes.category = categories.id_category " +
                "WHERE cuisines.name = \'" + cuisine + "\' AND categories.name = \'" + category + "\' " +
                "ORDER BY date_added DESC";

        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(query)) {

            while (result.next()) {
                idRecipes.add(result.getLong("id_recipe"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return recipes;
        }

        for (Long id : idRecipes)
            recipes.add(getRecipe(id));

        return recipes;
    }

    public LinkedList<Recipe> getRecipes(String keywords) {

        LinkedList<Recipe> recipes = new LinkedList<>();

        LinkedList<Long> idRecipes = new LinkedList<>();
        LinkedList<LinkedList<Ingredient>> ingredients = new LinkedList<>();

        String query = "SELECT id_recipe FROM recipes " +
                "WHERE name LIKE \'%" + keywords + "%\' " +
                "ORDER BY date_added DESC";

        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(query)) {

            while (result.next()) {
                idRecipes.add(result.getLong("id_recipe"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return recipes;
        }

        for (Long id : idRecipes)
            recipes.add(getRecipe(id));

        return recipes;
    }

    public boolean deleteRecipe(String login, String name) {

        Long idUser = getUser(login).getId();
        Long idRecipe = getRecipe(name).getId();

        String query = "DELETE FROM saved_recipes WHERE id_user = " + idUser + " AND id_recipe = " + idRecipe;

        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {

            statement.execute(query);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }
}