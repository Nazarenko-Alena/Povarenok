package ru.spbstu.povarenok.repository;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.Mock;
import org.postgresql.ds.PGSimpleDataSource;
import ru.spbstu.povarenok.model.*;

import java.sql.*;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RecipeWebsiteRepositoryTest {

    @Mock
    private PGSimpleDataSource dataSource;
    private RecipeWebsiteRepository repository;

    @Mock
    private Connection connection;

    @Mock
    private Statement statement;

    @Mock
    private ResultSet result;

    private User user;

    private Ingredient ingredient1;
    private Ingredient ingredient2;

    private LinkedList<Ingredient> ingredients;

    private Recipe recipe1;
    private Recipe recipe2;

    private LinkedList<Recipe> recipes;

    private LinkedList<Category> categories;
    private LinkedList<Cuisine> cuisines;

    @Before
    public void setUp() throws Exception {
        repository = new RecipeWebsiteRepository(dataSource);

        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(any(String.class))).thenReturn(result);

        // Создаём пользователя для тестирования

        user = new User(1L, "polinafomina", "qwerty123",
                "fominapolia2001@yandex.ru", new LinkedList<>(), new LinkedList<>());


        // Создаём ингредиенты для тестирования

        ingredient1 = new Ingredient(1L, 1L,
                "Капуста", 120.0);
        ingredient2 = new Ingredient(2L, 1L,
                "Лук", 20.0);

        ingredients = new LinkedList<>();
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);


        // Создаём рецепты для тестирования

        recipe1 = new Recipe(1L, "polinafomina", "Борщ", "123.png",
                "2023-02-19", "Русская", "Суп", 140, ingredients,
                "Ароматный борщ который подаётся с пампушками", "Порезать капусту и лук, сварить");
        recipe2 = new Recipe(2L, "polinafomina", "Блины", "143.png",
                "2023-02-18", "Русская", "Десерты", 40, ingredients,
                "Золотистые блины с маслом и мёдом", "Смешать яица и молоко");

        recipes = new LinkedList<>();
        recipes.add(recipe1);
        recipes.add(recipe2);


        // Создаем категории для тестирования

        categories = new LinkedList<>();
        categories.add(new Category(1L, "Суп"));
        categories.add(new Category(2L, "Десерты"));


        // Создаем типы кухонь для тестирования

        cuisines = new LinkedList<>();
        cuisines.add(new Cuisine(1L, "Французская"));
        cuisines.add(new Cuisine(2L, "Русская"));
    }

    @Test
    public void addUserTest() throws SQLException {

        boolean actualResult = repository.addUser(user);
        assertEquals(true, actualResult);


        SQLException ex = new SQLException();
        when(statement.execute(any(String.class))).thenThrow(ex);
        actualResult = repository.addUser(user);
        assertEquals(false, actualResult);
    }

    @Test
    public void getIngredientsTest() throws SQLException {

        when(result.next()).thenReturn(true).thenReturn(true).thenReturn(false);

        when(result.getLong("id_ingredient")).thenReturn(ingredient1.getId())
                .thenReturn(ingredient2.getId());
        when(result.getLong("id_recipe")).thenReturn(ingredient1.getIdRecipe())
                .thenReturn(ingredient2.getIdRecipe());
        when(result.getString("name")).thenReturn(ingredient1.getName())
                .thenReturn(ingredient2.getName());
        when(result.getDouble("grams")).thenReturn(ingredient1.getGrams())
                .thenReturn(ingredient2.getGrams());

        LinkedList<Ingredient> actualIngredients = repository.getIngredients(1L);
        assertEquals(ingredients.size(), actualIngredients.size());

        for (int i = 0; i < ingredients.size(); i++) {
            assertEquals(ingredients.get(i).getId(), actualIngredients.get(i).getId());
            assertEquals(ingredients.get(i).getIdRecipe(), actualIngredients.get(i).getIdRecipe());
            assertEquals(ingredients.get(i).getName(), actualIngredients.get(i).getName());
            assertEquals(ingredients.get(i).getGrams(), actualIngredients.get(i).getGrams());
        }

        SQLException ex = new SQLException();
        when(result.next()).thenThrow(ex);
        actualIngredients = repository.getIngredients(1L);
        assertEquals(new LinkedList<>(), actualIngredients);
    }

    @Test
    public void getRecipeTest() throws SQLException {

        when(result.next()).thenReturn(true).thenReturn(true).thenReturn(false).thenReturn(true);

        when(result.getLong("id_ingredient")).thenReturn(ingredient1.getId())
                .thenReturn(ingredient2.getId());
        when(result.getLong("id_recipe")).thenReturn(ingredient1.getIdRecipe())
                .thenReturn(ingredient2.getIdRecipe()).thenReturn(recipe1.getId());
        when(result.getString("login")).thenReturn(recipe1.getUserLogin());
        when(result.getString("name")).thenReturn(ingredient1.getName())
                .thenReturn(ingredient2.getName()).thenReturn(recipe1.getName());
        when(result.getString("image_url")).thenReturn(recipe1.getImageUrl());
        when(result.getString("date_added")).thenReturn(recipe1.getDateAdded());
        when(result.getString(6)).thenReturn(recipe1.getCuisine());
        when(result.getString(7)).thenReturn(recipe1.getCategory());
        when(result.getInt("cooking_time")).thenReturn(recipe1.getCookingTime());
        when(result.getString("description")).thenReturn(recipe1.getDescription());
        when(result.getString("recipe")).thenReturn(recipe1.getRecipe());
        when(result.getDouble("grams")).thenReturn(ingredient1.getGrams())
                .thenReturn(ingredient2.getGrams());

        Recipe actualRecipe = repository.getRecipe(1L);

        assertEquals(recipe1.getId(), actualRecipe.getId());
        assertEquals(recipe1.getUserLogin(), actualRecipe.getUserLogin());
        assertEquals(recipe1.getName(), actualRecipe.getName());
        assertEquals(recipe1.getImageUrl(), actualRecipe.getImageUrl());
        assertEquals(recipe1.getDateAdded(), actualRecipe.getDateAdded());
        assertEquals(recipe1.getCuisine(), actualRecipe.getCuisine());
        assertEquals(recipe1.getCategory(), actualRecipe.getCategory());
        assertEquals(recipe1.getCookingTime(), actualRecipe.getCookingTime());

        LinkedList<Ingredient> actualIngredients = actualRecipe.getIngredients();

        assertEquals(ingredients.size(), actualIngredients.size());

        for (int i = 0; i < recipe1.getIngredients().size(); i++) {
            assertEquals(ingredients.get(i).getId(), actualIngredients.get(i).getId());
            assertEquals(ingredients.get(i).getIdRecipe(), actualIngredients.get(i).getIdRecipe());
            assertEquals(ingredients.get(i).getName(), actualIngredients.get(i).getName());
            assertEquals(ingredients.get(i).getGrams(), actualIngredients.get(i).getGrams());
        }

        assertEquals(recipe1.getDescription(), actualRecipe.getDescription());
        assertEquals(recipe1.getRecipe(), actualRecipe.getRecipe());

        SQLException ex = new SQLException();
        when(result.next()).thenThrow(ex);
        actualRecipe = repository.getRecipe(1L);
        assertEquals(null, actualRecipe);
    }

    @Test
    public void getAddedRecipesTest() throws SQLException {

        when(result.next()).thenReturn(true).thenReturn(true).thenReturn(false)
                .thenReturn(true).thenReturn(true).thenReturn(false).thenReturn(true)
                .thenReturn(true).thenReturn(true).thenReturn(false).thenReturn(true);

        when(result.getLong("id_ingredient")).thenReturn(ingredient1.getId())
                .thenReturn(ingredient2.getId()).thenReturn(ingredient1.getId())
                .thenReturn(ingredient2.getId());
        when(result.getLong("id_recipe")).thenReturn(recipe1.getId()).thenReturn(recipe2.getId())
                .thenReturn(ingredient1.getIdRecipe()).thenReturn(ingredient2.getIdRecipe())
                .thenReturn(recipe1.getId()).thenReturn(ingredient1.getIdRecipe())
                .thenReturn(ingredient2.getIdRecipe()).thenReturn(recipe2.getId());
        when(result.getString("login")).thenReturn(recipe1.getUserLogin())
                .thenReturn(recipe2.getUserLogin());
        when(result.getString("name")).thenReturn(ingredient1.getName())
                .thenReturn(ingredient2.getName()).thenReturn(recipe1.getName())
                .thenReturn(ingredient1.getName()).thenReturn(ingredient2.getName())
                .thenReturn(recipe2.getName());
        when(result.getString("image_url")).thenReturn(recipe1.getImageUrl())
                .thenReturn(recipe2.getImageUrl());
        when(result.getString("date_added")).thenReturn(recipe1.getDateAdded())
                .thenReturn(recipe2.getDateAdded());
        when(result.getString(6)).thenReturn(recipe1.getCuisine()).thenReturn(recipe2.getCuisine());
        when(result.getString(7)).thenReturn(recipe1.getCategory()).thenReturn(recipe2.getCategory());
        when(result.getInt("cooking_time")).thenReturn(recipe1.getCookingTime())
                .thenReturn(recipe2.getCookingTime());
        when(result.getString("description")).thenReturn(recipe1.getDescription())
                .thenReturn(recipe2.getDescription());
        when(result.getString("recipe")).thenReturn(recipe1.getRecipe())
                .thenReturn(recipe2.getRecipe());
        when(result.getDouble("grams")).thenReturn(ingredient1.getGrams())
                .thenReturn(ingredient2.getGrams()).thenReturn(ingredient1.getGrams())
                .thenReturn(ingredient2.getGrams());

        LinkedList<Recipe> actualRecipes = repository.getAddedRecipes(user.getId());

        assertEquals(recipes.size(), actualRecipes.size());

        for (int i = 0; i < recipes.size(); i++) {

            Recipe actualRecipe = actualRecipes.get(i);
            Recipe recipe = recipes.get(i);

            assertEquals(recipe.getId(), actualRecipe.getId());
            assertEquals(recipe.getUserLogin(), actualRecipe.getUserLogin());
            assertEquals(recipe.getName(), actualRecipe.getName());
            assertEquals(recipe.getImageUrl(), actualRecipe.getImageUrl());
            assertEquals(recipe.getDateAdded(), actualRecipe.getDateAdded());
            assertEquals(recipe.getCuisine(), actualRecipe.getCuisine());
            assertEquals(recipe.getCategory(), actualRecipe.getCategory());
            assertEquals(recipe.getCookingTime(), actualRecipe.getCookingTime());

            LinkedList<Ingredient> actualIngredients = actualRecipe.getIngredients();

            assertEquals(ingredients.size(), actualIngredients.size());

            for (int j = 0; j < recipe1.getIngredients().size(); j++) {
                assertEquals(ingredients.get(j).getId(), actualIngredients.get(j).getId());
                assertEquals(ingredients.get(j).getIdRecipe(), actualIngredients.get(j).getIdRecipe());
                assertEquals(ingredients.get(j).getName(), actualIngredients.get(j).getName());
                assertEquals(ingredients.get(j).getGrams(), actualIngredients.get(j).getGrams());
            }

            assertEquals(recipe.getDescription(), actualRecipe.getDescription());
            assertEquals(recipe.getRecipe(), actualRecipe.getRecipe());
        }


        SQLException ex = new SQLException();
        when(result.next()).thenThrow(ex);
        actualRecipes = repository.getAddedRecipes(user.getId());
        assertEquals(new LinkedList<>(), actualRecipes);
    }

    @Test
    public void getSavedRecipesTest() throws SQLException {

        when(result.next()).thenReturn(true).thenReturn(true).thenReturn(false)
                .thenReturn(true).thenReturn(true).thenReturn(false).thenReturn(true)
                .thenReturn(true).thenReturn(true).thenReturn(false).thenReturn(true);

        when(result.getLong("id_ingredient")).thenReturn(ingredient1.getId())
                .thenReturn(ingredient2.getId()).thenReturn(ingredient1.getId())
                .thenReturn(ingredient2.getId());
        when(result.getLong("id_recipe")).thenReturn(recipe1.getId()).thenReturn(recipe2.getId())
                .thenReturn(ingredient1.getIdRecipe()).thenReturn(ingredient2.getIdRecipe())
                .thenReturn(recipe1.getId()).thenReturn(ingredient1.getIdRecipe())
                .thenReturn(ingredient2.getIdRecipe()).thenReturn(recipe2.getId());
        when(result.getString("login")).thenReturn(recipe1.getUserLogin())
                .thenReturn(recipe2.getUserLogin());
        when(result.getString("name")).thenReturn(ingredient1.getName())
                .thenReturn(ingredient2.getName()).thenReturn(recipe1.getName())
                .thenReturn(ingredient1.getName()).thenReturn(ingredient2.getName())
                .thenReturn(recipe2.getName());
        when(result.getString("image_url")).thenReturn(recipe1.getImageUrl())
                .thenReturn(recipe2.getImageUrl());
        when(result.getString("date_added")).thenReturn(recipe1.getDateAdded())
                .thenReturn(recipe2.getDateAdded());
        when(result.getString(6)).thenReturn(recipe1.getCuisine()).thenReturn(recipe2.getCuisine());
        when(result.getString(7)).thenReturn(recipe1.getCategory()).thenReturn(recipe2.getCategory());
        when(result.getInt("cooking_time")).thenReturn(recipe1.getCookingTime())
                .thenReturn(recipe2.getCookingTime());
        when(result.getString("description")).thenReturn(recipe1.getDescription())
                .thenReturn(recipe2.getDescription());
        when(result.getString("recipe")).thenReturn(recipe1.getRecipe())
                .thenReturn(recipe2.getRecipe());
        when(result.getDouble("grams")).thenReturn(ingredient1.getGrams())
                .thenReturn(ingredient2.getGrams()).thenReturn(ingredient1.getGrams())
                .thenReturn(ingredient2.getGrams());

        LinkedList<Recipe> actualRecipes = repository.getSavedRecipes(user.getId());

        assertEquals(recipes.size(), actualRecipes.size());

        for (int i = 0; i < recipes.size(); i++) {

            Recipe actualRecipe = actualRecipes.get(i);
            Recipe recipe = recipes.get(i);

            assertEquals(recipe.getId(), actualRecipe.getId());
            assertEquals(recipe.getUserLogin(), actualRecipe.getUserLogin());
            assertEquals(recipe.getName(), actualRecipe.getName());
            assertEquals(recipe.getImageUrl(), actualRecipe.getImageUrl());
            assertEquals(recipe.getDateAdded(), actualRecipe.getDateAdded());
            assertEquals(recipe.getCuisine(), actualRecipe.getCuisine());
            assertEquals(recipe.getCategory(), actualRecipe.getCategory());
            assertEquals(recipe.getCookingTime(), actualRecipe.getCookingTime());

            LinkedList<Ingredient> actualIngredients = actualRecipe.getIngredients();

            assertEquals(ingredients.size(), actualIngredients.size());

            for (int j = 0; j < recipe1.getIngredients().size(); j++) {
                assertEquals(ingredients.get(j).getId(), actualIngredients.get(j).getId());
                assertEquals(ingredients.get(j).getIdRecipe(), actualIngredients.get(j).getIdRecipe());
                assertEquals(ingredients.get(j).getName(), actualIngredients.get(j).getName());
                assertEquals(ingredients.get(j).getGrams(), actualIngredients.get(j).getGrams());
            }

            assertEquals(recipe.getDescription(), actualRecipe.getDescription());
            assertEquals(recipe.getRecipe(), actualRecipe.getRecipe());
        }


        SQLException ex = new SQLException();
        when(result.next()).thenThrow(ex);
        actualRecipes = repository.getSavedRecipes(user.getId());
        assertEquals(new LinkedList<>(), actualRecipes);
    }

    @Test
    public void getAllCategoriesTest() throws SQLException {

        when(result.next()).thenReturn(true).thenReturn(true).thenReturn(false);

        when(result.getLong("id_category")).thenReturn(categories.get(0).getId())
                .thenReturn(categories.get(1).getId());
        when(result.getString("name")).thenReturn(categories.get(0).getName())
                .thenReturn(categories.get(1).getName());

        LinkedList<Category> actualCategories = repository.getAllCategories();
        assertEquals(categories.size(), actualCategories.size());

        for (int i = 0; i < categories.size(); i++) {
            assertEquals(categories.get(i).getId(), actualCategories.get(i).getId());
            assertEquals(categories.get(i).getName(), actualCategories.get(i).getName());
        }

        SQLException ex = new SQLException();
        when(result.next()).thenThrow(ex);
        actualCategories = repository.getAllCategories();
        assertEquals(new LinkedList<>(), actualCategories);
    }

    @Test
    public void getCategoryTest() throws SQLException {

        when(result.next()).thenReturn(true);

        for (Category category : categories) {
            when(result.getLong("id_category")).thenReturn(category.getId());
            when(result.getString("name")).thenReturn(category.getName());

            Category actualCategory = repository.getCategory(category.getName());

            assertEquals(category.getId(), actualCategory.getId());
            assertEquals(category.getName(), actualCategory.getName());
        }

        SQLException ex = new SQLException();
        when(result.next()).thenThrow(ex);
        Category actualCategory = repository.getCategory(categories.get(0).getName());
        assertEquals(null, actualCategory);
    }

    @Test
    public void getAllCuisinesTest() throws SQLException {

        when(result.next()).thenReturn(true).thenReturn(true).thenReturn(false);

        when(result.getLong("id_cuisine")).thenReturn(cuisines.get(0).getId())
                .thenReturn(cuisines.get(1).getId());
        when(result.getString("name")).thenReturn(cuisines.get(0).getName())
                .thenReturn(cuisines.get(1).getName());

        LinkedList<Cuisine> actualCuisines = repository.getAllCuisines();
        assertEquals(cuisines.size(), actualCuisines.size());

        for (int i = 0; i < cuisines.size(); i++) {
            assertEquals(cuisines.get(i).getId(), actualCuisines.get(i).getId());
            assertEquals(cuisines.get(i).getName(), actualCuisines.get(i).getName());
        }

        SQLException ex = new SQLException();
        when(result.next()).thenThrow(ex);
        actualCuisines = repository.getAllCuisines();
        assertEquals(new LinkedList<>(), actualCuisines);
    }

    @Test
    public void getCuisineTest() throws SQLException {

        when(result.next()).thenReturn(true);

        for (Cuisine cuisine : cuisines) {
            when(result.getLong("id_cuisine")).thenReturn(cuisine.getId());
            when(result.getString("name")).thenReturn(cuisine.getName());

            Cuisine actualCuisine = repository.getCuisine(cuisine.getName());

            assertEquals(cuisine.getId(), actualCuisine.getId());
            assertEquals(cuisine.getName(), actualCuisine.getName());
        }

        SQLException ex = new SQLException();
        when(result.next()).thenThrow(ex);
        Cuisine actualCuisine = repository.getCuisine(cuisines.get(0).getName());
        assertEquals(null, actualCuisine);
    }

    @Test
    public void addIngredientTest() throws SQLException {

        for (Ingredient ingredient : ingredients) {
            boolean actualResult = repository.addIngredient(ingredient);
            assertEquals(true, actualResult);
        }


        SQLException ex = new SQLException();
        when(statement.execute(any(String.class))).thenThrow(ex);
        boolean actualResult = repository.addIngredient(ingredient1);
        assertEquals(false, actualResult);
    }

    @Test
    public void getRecipeByNameTest() throws SQLException {

        when(result.next()).thenReturn(true).thenReturn(true).thenReturn(true)
                .thenReturn(false).thenReturn(true);

        when(result.getLong("id_ingredient")).thenReturn(ingredient1.getId())
                .thenReturn(ingredient2.getId());
        when(result.getLong("id_recipe")).thenReturn(recipe1.getId())
                .thenReturn(ingredient1.getIdRecipe()).thenReturn(ingredient2.getIdRecipe())
                .thenReturn(recipe1.getId());
        when(result.getString("login")).thenReturn(recipe1.getUserLogin());
        when(result.getString("name")).thenReturn(ingredient1.getName())
                .thenReturn(ingredient2.getName()).thenReturn(recipe1.getName());
        when(result.getString("image_url")).thenReturn(recipe1.getImageUrl());
        when(result.getString("date_added")).thenReturn(recipe1.getDateAdded());
        when(result.getString(6)).thenReturn(recipe1.getCuisine());
        when(result.getString(7)).thenReturn(recipe1.getCategory());
        when(result.getInt("cooking_time")).thenReturn(recipe1.getCookingTime());
        when(result.getString("description")).thenReturn(recipe1.getDescription());
        when(result.getString("recipe")).thenReturn(recipe1.getRecipe());
        when(result.getDouble("grams")).thenReturn(ingredient1.getGrams())
                .thenReturn(ingredient2.getGrams());

        Recipe actualRecipe = repository.getRecipe(recipe1.getName());

        assertEquals(recipe1.getId(), actualRecipe.getId());
        assertEquals(recipe1.getUserLogin(), actualRecipe.getUserLogin());
        assertEquals(recipe1.getName(), actualRecipe.getName());
        assertEquals(recipe1.getImageUrl(), actualRecipe.getImageUrl());
        assertEquals(recipe1.getDateAdded(), actualRecipe.getDateAdded());
        assertEquals(recipe1.getCuisine(), actualRecipe.getCuisine());
        assertEquals(recipe1.getCategory(), actualRecipe.getCategory());
        assertEquals(recipe1.getCookingTime(), actualRecipe.getCookingTime());

        LinkedList<Ingredient> actualIngredients = actualRecipe.getIngredients();

        assertEquals(ingredients.size(), actualIngredients.size());

        for (int i = 0; i < recipe1.getIngredients().size(); i++) {
            assertEquals(ingredients.get(i).getId(), actualIngredients.get(i).getId());
            assertEquals(ingredients.get(i).getIdRecipe(), actualIngredients.get(i).getIdRecipe());
            assertEquals(ingredients.get(i).getName(), actualIngredients.get(i).getName());
            assertEquals(ingredients.get(i).getGrams(), actualIngredients.get(i).getGrams());
        }

        assertEquals(recipe1.getDescription(), actualRecipe.getDescription());
        assertEquals(recipe1.getRecipe(), actualRecipe.getRecipe());

        when(result.next()).thenReturn(false);
        actualRecipe = repository.getRecipe(recipe1.getName());
        assertEquals(null, actualRecipe);

        SQLException ex = new SQLException();
        when(result.next()).thenThrow(ex);
        actualRecipe = repository.getRecipe(recipe1.getName());
        assertEquals(null, actualRecipe);
    }

    @Test
    public void getRecipeByUrlTest() throws SQLException {

        when(result.next()).thenReturn(true).thenReturn(true).thenReturn(true)
                .thenReturn(false).thenReturn(true);

        when(result.getLong("id_ingredient")).thenReturn(ingredient1.getId())
                .thenReturn(ingredient2.getId());
        when(result.getLong("id_recipe")).thenReturn(recipe1.getId())
                .thenReturn(ingredient1.getIdRecipe()).thenReturn(ingredient2.getIdRecipe())
                .thenReturn(recipe1.getId());
        when(result.getString("login")).thenReturn(recipe1.getUserLogin());
        when(result.getString("name")).thenReturn(ingredient1.getName())
                .thenReturn(ingredient2.getName()).thenReturn(recipe1.getName());
        when(result.getString("image_url")).thenReturn(recipe1.getImageUrl());
        when(result.getString("date_added")).thenReturn(recipe1.getDateAdded());
        when(result.getString(6)).thenReturn(recipe1.getCuisine());
        when(result.getString(7)).thenReturn(recipe1.getCategory());
        when(result.getInt("cooking_time")).thenReturn(recipe1.getCookingTime());
        when(result.getString("description")).thenReturn(recipe1.getDescription());
        when(result.getString("recipe")).thenReturn(recipe1.getRecipe());
        when(result.getDouble("grams")).thenReturn(ingredient1.getGrams())
                .thenReturn(ingredient2.getGrams());

        Recipe actualRecipe = repository.getRecipeByUrl(recipe1.getImageUrl());

        assertEquals(recipe1.getId(), actualRecipe.getId());
        assertEquals(recipe1.getUserLogin(), actualRecipe.getUserLogin());
        assertEquals(recipe1.getName(), actualRecipe.getName());
        assertEquals(recipe1.getImageUrl(), actualRecipe.getImageUrl());
        assertEquals(recipe1.getDateAdded(), actualRecipe.getDateAdded());
        assertEquals(recipe1.getCuisine(), actualRecipe.getCuisine());
        assertEquals(recipe1.getCategory(), actualRecipe.getCategory());
        assertEquals(recipe1.getCookingTime(), actualRecipe.getCookingTime());

        LinkedList<Ingredient> actualIngredients = actualRecipe.getIngredients();

        assertEquals(ingredients.size(), actualIngredients.size());

        for (int i = 0; i < recipe1.getIngredients().size(); i++) {
            assertEquals(ingredients.get(i).getId(), actualIngredients.get(i).getId());
            assertEquals(ingredients.get(i).getIdRecipe(), actualIngredients.get(i).getIdRecipe());
            assertEquals(ingredients.get(i).getName(), actualIngredients.get(i).getName());
            assertEquals(ingredients.get(i).getGrams(), actualIngredients.get(i).getGrams());
        }

        assertEquals(recipe1.getDescription(), actualRecipe.getDescription());
        assertEquals(recipe1.getRecipe(), actualRecipe.getRecipe());

        when(result.next()).thenReturn(false);
        actualRecipe = repository.getRecipeByUrl(recipe1.getImageUrl());
        assertEquals(null, actualRecipe);

        SQLException ex = new SQLException();
        when(result.next()).thenThrow(ex);
        actualRecipe = repository.getRecipeByUrl(recipe1.getImageUrl());
        assertEquals(null, actualRecipe);
    }

    @Test
    public void getRecipeByDescriptionTest() throws SQLException {

        when(result.next()).thenReturn(true).thenReturn(true).thenReturn(true)
                .thenReturn(false).thenReturn(true);

        when(result.getLong("id_ingredient")).thenReturn(ingredient1.getId())
                .thenReturn(ingredient2.getId());
        when(result.getLong("id_recipe")).thenReturn(recipe1.getId())
                .thenReturn(ingredient1.getIdRecipe()).thenReturn(ingredient2.getIdRecipe())
                .thenReturn(recipe1.getId());
        when(result.getString("login")).thenReturn(recipe1.getUserLogin());
        when(result.getString("name")).thenReturn(ingredient1.getName())
                .thenReturn(ingredient2.getName()).thenReturn(recipe1.getName());
        when(result.getString("image_url")).thenReturn(recipe1.getImageUrl());
        when(result.getString("date_added")).thenReturn(recipe1.getDateAdded());
        when(result.getString(6)).thenReturn(recipe1.getCuisine());
        when(result.getString(7)).thenReturn(recipe1.getCategory());
        when(result.getInt("cooking_time")).thenReturn(recipe1.getCookingTime());
        when(result.getString("description")).thenReturn(recipe1.getDescription());
        when(result.getString("recipe")).thenReturn(recipe1.getRecipe());
        when(result.getDouble("grams")).thenReturn(ingredient1.getGrams())
                .thenReturn(ingredient2.getGrams());

        Recipe actualRecipe = repository.getRecipeByDescription(recipe1.getDescription());

        assertEquals(recipe1.getId(), actualRecipe.getId());
        assertEquals(recipe1.getUserLogin(), actualRecipe.getUserLogin());
        assertEquals(recipe1.getName(), actualRecipe.getName());
        assertEquals(recipe1.getImageUrl(), actualRecipe.getImageUrl());
        assertEquals(recipe1.getDateAdded(), actualRecipe.getDateAdded());
        assertEquals(recipe1.getCuisine(), actualRecipe.getCuisine());
        assertEquals(recipe1.getCategory(), actualRecipe.getCategory());
        assertEquals(recipe1.getCookingTime(), actualRecipe.getCookingTime());

        LinkedList<Ingredient> actualIngredients = actualRecipe.getIngredients();

        assertEquals(ingredients.size(), actualIngredients.size());

        for (int i = 0; i < recipe1.getIngredients().size(); i++) {
            assertEquals(ingredients.get(i).getId(), actualIngredients.get(i).getId());
            assertEquals(ingredients.get(i).getIdRecipe(), actualIngredients.get(i).getIdRecipe());
            assertEquals(ingredients.get(i).getName(), actualIngredients.get(i).getName());
            assertEquals(ingredients.get(i).getGrams(), actualIngredients.get(i).getGrams());
        }

        assertEquals(recipe1.getDescription(), actualRecipe.getDescription());
        assertEquals(recipe1.getRecipe(), actualRecipe.getRecipe());

        when(result.next()).thenReturn(false);
        actualRecipe = repository.getRecipeByDescription(recipe1.getDescription());
        assertEquals(null, actualRecipe);

        SQLException ex = new SQLException();
        when(result.next()).thenThrow(ex);
        actualRecipe = repository.getRecipeByDescription(recipe1.getDescription());
        assertEquals(null, actualRecipe);
    }

    @Test
    public void getRecipeByStepByStepRecipeTest() throws SQLException {

        when(result.next()).thenReturn(true).thenReturn(true).thenReturn(true)
                .thenReturn(false).thenReturn(true);

        when(result.getLong("id_ingredient")).thenReturn(ingredient1.getId())
                .thenReturn(ingredient2.getId());
        when(result.getLong("id_recipe")).thenReturn(recipe1.getId())
                .thenReturn(ingredient1.getIdRecipe()).thenReturn(ingredient2.getIdRecipe())
                .thenReturn(recipe1.getId());
        when(result.getString("login")).thenReturn(recipe1.getUserLogin());
        when(result.getString("name")).thenReturn(ingredient1.getName())
                .thenReturn(ingredient2.getName()).thenReturn(recipe1.getName());
        when(result.getString("image_url")).thenReturn(recipe1.getImageUrl());
        when(result.getString("date_added")).thenReturn(recipe1.getDateAdded());
        when(result.getString(6)).thenReturn(recipe1.getCuisine());
        when(result.getString(7)).thenReturn(recipe1.getCategory());
        when(result.getInt("cooking_time")).thenReturn(recipe1.getCookingTime());
        when(result.getString("description")).thenReturn(recipe1.getDescription());
        when(result.getString("recipe")).thenReturn(recipe1.getRecipe());
        when(result.getDouble("grams")).thenReturn(ingredient1.getGrams())
                .thenReturn(ingredient2.getGrams());

        Recipe actualRecipe = repository.getRecipeByStepByStepRecipe(recipe1.getRecipe());

        assertEquals(recipe1.getId(), actualRecipe.getId());
        assertEquals(recipe1.getUserLogin(), actualRecipe.getUserLogin());
        assertEquals(recipe1.getName(), actualRecipe.getName());
        assertEquals(recipe1.getImageUrl(), actualRecipe.getImageUrl());
        assertEquals(recipe1.getDateAdded(), actualRecipe.getDateAdded());
        assertEquals(recipe1.getCuisine(), actualRecipe.getCuisine());
        assertEquals(recipe1.getCategory(), actualRecipe.getCategory());
        assertEquals(recipe1.getCookingTime(), actualRecipe.getCookingTime());

        LinkedList<Ingredient> actualIngredients = actualRecipe.getIngredients();

        assertEquals(ingredients.size(), actualIngredients.size());

        for (int i = 0; i < recipe1.getIngredients().size(); i++) {
            assertEquals(ingredients.get(i).getId(), actualIngredients.get(i).getId());
            assertEquals(ingredients.get(i).getIdRecipe(), actualIngredients.get(i).getIdRecipe());
            assertEquals(ingredients.get(i).getName(), actualIngredients.get(i).getName());
            assertEquals(ingredients.get(i).getGrams(), actualIngredients.get(i).getGrams());
        }

        assertEquals(recipe1.getDescription(), actualRecipe.getDescription());
        assertEquals(recipe1.getRecipe(), actualRecipe.getRecipe());

        when(result.next()).thenReturn(false);
        actualRecipe = repository.getRecipeByStepByStepRecipe(recipe1.getRecipe());
        assertEquals(null, actualRecipe);

        SQLException ex = new SQLException();
        when(result.next()).thenThrow(ex);
        actualRecipe = repository.getRecipeByStepByStepRecipe(recipe1.getRecipe());
        assertEquals(null, actualRecipe);
    }

    @Test
    public void getLastRecipesTest() throws SQLException {

        when(result.next()).thenReturn(true).thenReturn(true).thenReturn(false)
                .thenReturn(true).thenReturn(true).thenReturn(false).thenReturn(true)
                .thenReturn(true).thenReturn(true).thenReturn(false).thenReturn(true);

        when(result.getLong("id_ingredient")).thenReturn(ingredient1.getId())
                .thenReturn(ingredient2.getId()).thenReturn(ingredient1.getId())
                .thenReturn(ingredient2.getId());
        when(result.getLong("id_recipe")).thenReturn(recipe1.getId()).thenReturn(recipe2.getId())
                .thenReturn(ingredient1.getIdRecipe()).thenReturn(ingredient2.getIdRecipe())
                .thenReturn(recipe1.getId()).thenReturn(ingredient1.getIdRecipe())
                .thenReturn(ingredient2.getIdRecipe()).thenReturn(recipe2.getId());
        when(result.getString("login")).thenReturn(recipe1.getUserLogin())
                .thenReturn(recipe2.getUserLogin());
        when(result.getString("name")).thenReturn(ingredient1.getName())
                .thenReturn(ingredient2.getName()).thenReturn(recipe1.getName())
                .thenReturn(ingredient1.getName()).thenReturn(ingredient2.getName())
                .thenReturn(recipe2.getName());
        when(result.getString("image_url")).thenReturn(recipe1.getImageUrl())
                .thenReturn(recipe2.getImageUrl());
        when(result.getString("date_added")).thenReturn(recipe1.getDateAdded())
                .thenReturn(recipe2.getDateAdded());
        when(result.getString(6)).thenReturn(recipe1.getCuisine()).thenReturn(recipe2.getCuisine());
        when(result.getString(7)).thenReturn(recipe1.getCategory()).thenReturn(recipe2.getCategory());
        when(result.getInt("cooking_time")).thenReturn(recipe1.getCookingTime())
                .thenReturn(recipe2.getCookingTime());
        when(result.getString("description")).thenReturn(recipe1.getDescription())
                .thenReturn(recipe2.getDescription());
        when(result.getString("recipe")).thenReturn(recipe1.getRecipe())
                .thenReturn(recipe2.getRecipe());
        when(result.getDouble("grams")).thenReturn(ingredient1.getGrams())
                .thenReturn(ingredient2.getGrams()).thenReturn(ingredient1.getGrams())
                .thenReturn(ingredient2.getGrams());

        LinkedList<Recipe> actualRecipes = repository.getLastRecipes(2);

        assertEquals(recipes.size(), actualRecipes.size());

        for (int i = 0; i < recipes.size(); i++) {

            Recipe actualRecipe = actualRecipes.get(i);
            Recipe recipe = recipes.get(i);

            assertEquals(recipe.getId(), actualRecipe.getId());
            assertEquals(recipe.getUserLogin(), actualRecipe.getUserLogin());
            assertEquals(recipe.getName(), actualRecipe.getName());
            assertEquals(recipe.getImageUrl(), actualRecipe.getImageUrl());
            assertEquals(recipe.getDateAdded(), actualRecipe.getDateAdded());
            assertEquals(recipe.getCuisine(), actualRecipe.getCuisine());
            assertEquals(recipe.getCategory(), actualRecipe.getCategory());
            assertEquals(recipe.getCookingTime(), actualRecipe.getCookingTime());

            LinkedList<Ingredient> actualIngredients = actualRecipe.getIngredients();

            assertEquals(ingredients.size(), actualIngredients.size());

            for (int j = 0; j < recipe1.getIngredients().size(); j++) {
                assertEquals(ingredients.get(j).getId(), actualIngredients.get(j).getId());
                assertEquals(ingredients.get(j).getIdRecipe(), actualIngredients.get(j).getIdRecipe());
                assertEquals(ingredients.get(j).getName(), actualIngredients.get(j).getName());
                assertEquals(ingredients.get(j).getGrams(), actualIngredients.get(j).getGrams());
            }

            assertEquals(recipe.getDescription(), actualRecipe.getDescription());
            assertEquals(recipe.getRecipe(), actualRecipe.getRecipe());
        }


        SQLException ex = new SQLException();
        when(result.next()).thenThrow(ex);
        actualRecipes = repository.getLastRecipes(2);
        assertEquals(new LinkedList<>(), actualRecipes);
    }

    @Test
    public void getRecipesTest() throws SQLException {

        when(result.next()).thenReturn(true).thenReturn(true).thenReturn(false)
                .thenReturn(true).thenReturn(true).thenReturn(false).thenReturn(true)
                .thenReturn(true).thenReturn(true).thenReturn(false).thenReturn(true);

        when(result.getLong("id_ingredient")).thenReturn(ingredient1.getId())
                .thenReturn(ingredient2.getId()).thenReturn(ingredient1.getId())
                .thenReturn(ingredient2.getId());
        when(result.getLong("id_recipe")).thenReturn(recipe1.getId()).thenReturn(recipe2.getId())
                .thenReturn(ingredient1.getIdRecipe()).thenReturn(ingredient2.getIdRecipe())
                .thenReturn(recipe1.getId()).thenReturn(ingredient1.getIdRecipe())
                .thenReturn(ingredient2.getIdRecipe()).thenReturn(recipe2.getId());
        when(result.getString("login")).thenReturn(recipe1.getUserLogin())
                .thenReturn(recipe2.getUserLogin());
        when(result.getString("name")).thenReturn(ingredient1.getName())
                .thenReturn(ingredient2.getName()).thenReturn(recipe1.getName())
                .thenReturn(ingredient1.getName()).thenReturn(ingredient2.getName())
                .thenReturn(recipe2.getName());
        when(result.getString("image_url")).thenReturn(recipe1.getImageUrl())
                .thenReturn(recipe2.getImageUrl());
        when(result.getString("date_added")).thenReturn(recipe1.getDateAdded())
                .thenReturn(recipe2.getDateAdded());
        when(result.getString(6)).thenReturn(recipe1.getCuisine()).thenReturn(recipe2.getCuisine());
        when(result.getString(7)).thenReturn(recipe1.getCategory()).thenReturn(recipe2.getCategory());
        when(result.getInt("cooking_time")).thenReturn(recipe1.getCookingTime())
                .thenReturn(recipe2.getCookingTime());
        when(result.getString("description")).thenReturn(recipe1.getDescription())
                .thenReturn(recipe2.getDescription());
        when(result.getString("recipe")).thenReturn(recipe1.getRecipe())
                .thenReturn(recipe2.getRecipe());
        when(result.getDouble("grams")).thenReturn(ingredient1.getGrams())
                .thenReturn(ingredient2.getGrams()).thenReturn(ingredient1.getGrams())
                .thenReturn(ingredient2.getGrams());

        LinkedList<Recipe> actualRecipes = repository.getRecipes("Суп", "Русская");

        assertEquals(recipes.size(), actualRecipes.size());

        for (int i = 0; i < recipes.size(); i++) {

            Recipe actualRecipe = actualRecipes.get(i);
            Recipe recipe = recipes.get(i);

            assertEquals(recipe.getId(), actualRecipe.getId());
            assertEquals(recipe.getUserLogin(), actualRecipe.getUserLogin());
            assertEquals(recipe.getName(), actualRecipe.getName());
            assertEquals(recipe.getImageUrl(), actualRecipe.getImageUrl());
            assertEquals(recipe.getDateAdded(), actualRecipe.getDateAdded());
            assertEquals(recipe.getCuisine(), actualRecipe.getCuisine());
            assertEquals(recipe.getCategory(), actualRecipe.getCategory());
            assertEquals(recipe.getCookingTime(), actualRecipe.getCookingTime());

            LinkedList<Ingredient> actualIngredients = actualRecipe.getIngredients();

            assertEquals(ingredients.size(), actualIngredients.size());

            for (int j = 0; j < recipe1.getIngredients().size(); j++) {
                assertEquals(ingredients.get(j).getId(), actualIngredients.get(j).getId());
                assertEquals(ingredients.get(j).getIdRecipe(), actualIngredients.get(j).getIdRecipe());
                assertEquals(ingredients.get(j).getName(), actualIngredients.get(j).getName());
                assertEquals(ingredients.get(j).getGrams(), actualIngredients.get(j).getGrams());
            }

            assertEquals(recipe.getDescription(), actualRecipe.getDescription());
            assertEquals(recipe.getRecipe(), actualRecipe.getRecipe());
        }


        SQLException ex = new SQLException();
        when(result.next()).thenThrow(ex);
        actualRecipes = repository.getRecipes("Суп", "Русская");
        assertEquals(new LinkedList<>(), actualRecipes);
    }

    @Test
    public void getRecipesByKeywordsTest() throws SQLException {

        when(result.next()).thenReturn(true).thenReturn(true).thenReturn(false)
                .thenReturn(true).thenReturn(true).thenReturn(false).thenReturn(true)
                .thenReturn(true).thenReturn(true).thenReturn(false).thenReturn(true);

        when(result.getLong("id_ingredient")).thenReturn(ingredient1.getId())
                .thenReturn(ingredient2.getId()).thenReturn(ingredient1.getId())
                .thenReturn(ingredient2.getId());
        when(result.getLong("id_recipe")).thenReturn(recipe1.getId()).thenReturn(recipe2.getId())
                .thenReturn(ingredient1.getIdRecipe()).thenReturn(ingredient2.getIdRecipe())
                .thenReturn(recipe1.getId()).thenReturn(ingredient1.getIdRecipe())
                .thenReturn(ingredient2.getIdRecipe()).thenReturn(recipe2.getId());
        when(result.getString("login")).thenReturn(recipe1.getUserLogin())
                .thenReturn(recipe2.getUserLogin());
        when(result.getString("name")).thenReturn(ingredient1.getName())
                .thenReturn(ingredient2.getName()).thenReturn(recipe1.getName())
                .thenReturn(ingredient1.getName()).thenReturn(ingredient2.getName())
                .thenReturn(recipe2.getName());
        when(result.getString("image_url")).thenReturn(recipe1.getImageUrl())
                .thenReturn(recipe2.getImageUrl());
        when(result.getString("date_added")).thenReturn(recipe1.getDateAdded())
                .thenReturn(recipe2.getDateAdded());
        when(result.getString(6)).thenReturn(recipe1.getCuisine()).thenReturn(recipe2.getCuisine());
        when(result.getString(7)).thenReturn(recipe1.getCategory()).thenReturn(recipe2.getCategory());
        when(result.getInt("cooking_time")).thenReturn(recipe1.getCookingTime())
                .thenReturn(recipe2.getCookingTime());
        when(result.getString("description")).thenReturn(recipe1.getDescription())
                .thenReturn(recipe2.getDescription());
        when(result.getString("recipe")).thenReturn(recipe1.getRecipe())
                .thenReturn(recipe2.getRecipe());
        when(result.getDouble("grams")).thenReturn(ingredient1.getGrams())
                .thenReturn(ingredient2.getGrams()).thenReturn(ingredient1.getGrams())
                .thenReturn(ingredient2.getGrams());

        LinkedList<Recipe> actualRecipes = repository.getRecipes("щи");

        assertEquals(recipes.size(), actualRecipes.size());

        for (int i = 0; i < recipes.size(); i++) {

            Recipe actualRecipe = actualRecipes.get(i);
            Recipe recipe = recipes.get(i);

            assertEquals(recipe.getId(), actualRecipe.getId());
            assertEquals(recipe.getUserLogin(), actualRecipe.getUserLogin());
            assertEquals(recipe.getName(), actualRecipe.getName());
            assertEquals(recipe.getImageUrl(), actualRecipe.getImageUrl());
            assertEquals(recipe.getDateAdded(), actualRecipe.getDateAdded());
            assertEquals(recipe.getCuisine(), actualRecipe.getCuisine());
            assertEquals(recipe.getCategory(), actualRecipe.getCategory());
            assertEquals(recipe.getCookingTime(), actualRecipe.getCookingTime());

            LinkedList<Ingredient> actualIngredients = actualRecipe.getIngredients();

            assertEquals(ingredients.size(), actualIngredients.size());

            for (int j = 0; j < recipe1.getIngredients().size(); j++) {
                assertEquals(ingredients.get(j).getId(), actualIngredients.get(j).getId());
                assertEquals(ingredients.get(j).getIdRecipe(), actualIngredients.get(j).getIdRecipe());
                assertEquals(ingredients.get(j).getName(), actualIngredients.get(j).getName());
                assertEquals(ingredients.get(j).getGrams(), actualIngredients.get(j).getGrams());
            }

            assertEquals(recipe.getDescription(), actualRecipe.getDescription());
            assertEquals(recipe.getRecipe(), actualRecipe.getRecipe());
        }


        SQLException ex = new SQLException();
        when(result.next()).thenThrow(ex);
        actualRecipes = repository.getRecipes("щи");
        assertEquals(new LinkedList<>(), actualRecipes);
    }
}