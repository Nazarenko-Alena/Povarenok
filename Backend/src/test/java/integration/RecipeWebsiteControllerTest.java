package integration;

import org.dbunit.dataset.SortedTable;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.ITable;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.junit.jupiter.Container;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import javax.sql.DataSource;

import ru.spbstu.povarenok.repository.RecipeWebsiteRepository;
import ru.spbstu.povarenok.service.RecipeWebsiteController;
import ru.spbstu.povarenok.model.*;

@Testcontainers
public class RecipeWebsiteControllerTest {

    private DataSource dataSource;
    private RecipeWebsiteRepository repository;
    private RecipeWebsiteController controller;
    private IDatabaseTester tester;

    private static LinkedList<Ingredient> ingredients;
    private static LinkedList<Recipe> recipes;
    private static LinkedList<User> users;
    private static LinkedList<LinkedList<Long>> savedRecipes;
    private static LinkedList<Category> categories;
    private static LinkedList<Cuisine> cuisines;

    @Container
    private PostgreSQLContainer postgres = new PostgreSQLContainer<>("postgres:12-alpine")
            .withInitScript("schema.sql");

    @BeforeAll
    public static void generateData() {
        ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(1L, 1L, "Свекла", 200.0));
        ingredients.add(new Ingredient(2L, 1L, "Морковь", 120.0));
        ingredients.add(new Ingredient(3L, 1L, "Мясо", 400.0));
        ingredients.add(new Ingredient(4L, 2L, "Капуста", 300.0));
        ingredients.add(new Ingredient(5L, 2L, "Морковь", 100.0));
        ingredients.add(new Ingredient(6L, 2L, "Мясо", 500.0));
        ingredients.add(new Ingredient(7L, 3L, "Молоко", 250.0));
        ingredients.add(new Ingredient(8L, 3L, "Яйцо", 120.0));
        ingredients.add(new Ingredient(9L, 4L, "Молоко", 250.0));
        ingredients.add(new Ingredient(10L, 4L, "Мука", 70.0));
        ingredients.add(new Ingredient(11L, 4L, "Шоколад горький", 100.0));
        ingredients.add(new Ingredient(12L, 5L, "Мясо", 320.0));
        ingredients.add(new Ingredient(13L, 6L, "Кокосовое молоко", 220.0));
        ingredients.add(new Ingredient(14L, 6L, "Креветки", 350.0));

        recipes = new LinkedList<>();

        LinkedList<Ingredient> ingredientsForRecipe = new LinkedList<>();
        ingredientsForRecipe.add(ingredients.get(0));
        ingredientsForRecipe.add(ingredients.get(1));
        ingredientsForRecipe.add(ingredients.get(2));

        recipes.add(new Recipe(1L, "polinafomina", "Борщ", "140.png", "2023-03-11",
                "Русская", "Супы", 120, ingredientsForRecipe,
                "Ароматный борщ с пампушками", "Порезать овощи и сварить"));

        ingredientsForRecipe = new LinkedList<>();
        ingredientsForRecipe.add(ingredients.get(3));
        ingredientsForRecipe.add(ingredients.get(4));
        ingredientsForRecipe.add(ingredients.get(5));

        recipes.add(new Recipe(2L, "polinafomina", "Щи", "141.png", "2023-03-12",
                "Русская", "Супы", 90, ingredientsForRecipe,
                "Вкусные щи с гренками", "Поджарить гренки и сварить щи"));

        ingredientsForRecipe = new LinkedList<>();
        ingredientsForRecipe.add(ingredients.get(6));
        ingredientsForRecipe.add(ingredients.get(7));

        recipes.add(new Recipe(3L, "povarverona", "Блины", "142.png", "2023-03-12",
                "Русская", "Выпечка и десерты", 20, ingredientsForRecipe,
                "Тонкие блинчики с маслом", "Приготовить тесто и пожарить блины"));

        ingredientsForRecipe = new LinkedList<>();
        ingredientsForRecipe.add(ingredients.get(8));
        ingredientsForRecipe.add(ingredients.get(9));
        ingredientsForRecipe.add(ingredients.get(10));

        recipes.add(new Recipe(4L, "povarverona", "Вафли", "143.png", "2023-03-13",
                "Европейская", "Выпечка и десерты", 45, ingredientsForRecipe,
                "Пышные вафли с шоколадом", "Приготовить тесто, растопить шоколад"));

        ingredientsForRecipe = new LinkedList<>();
        ingredientsForRecipe.add(ingredients.get(11));

        recipes.add(new Recipe(5L, "povarverona", "Стейк", "144.png", "2023-03-14",
                "Французская", "Основные блюда", 20, ingredientsForRecipe,
                "Стейк Нью-Йорк", "Посолить и поперчить мясо, пожарить на гриле"));

        ingredientsForRecipe = new LinkedList<>();
        ingredientsForRecipe.add(ingredients.get(12));
        ingredientsForRecipe.add(ingredients.get(13));

        recipes.add(new Recipe(6L, "polinafomina", "Том ям", "145.png", "2023-03-15",
                "Тайская", "Супы", 70, ingredientsForRecipe,
                "Том ям на кокосовом молоке с крветками", "Почистить креветки добавить кокосове молоко"));

        users = new LinkedList<>();

        LinkedList<Recipe> addedRecipesForUser = new LinkedList<>();
        addedRecipesForUser.add(recipes.get(0));
        addedRecipesForUser.add(recipes.get(1));

        LinkedList<Recipe> savedRecipesForUser = new LinkedList<>();
        savedRecipesForUser.add(recipes.get(2));

        users.add(new User(1L, "polinafomina", "qwerty123",
                "fominapolia2001@yandex.ru", addedRecipesForUser, savedRecipesForUser));

        addedRecipesForUser = new LinkedList<>();
        addedRecipesForUser.add(recipes.get(2));
        addedRecipesForUser.add(recipes.get(3));
        addedRecipesForUser.add(recipes.get(4));

        savedRecipesForUser = new LinkedList<>();
        savedRecipesForUser.add(recipes.get(0));
        savedRecipesForUser.add(recipes.get(1));

        users.add(new User(2L, "povarverona", "veronaitaly",
                "povar.verona@gmail.com", addedRecipesForUser, savedRecipesForUser));

        users.add(new User(3L, "nikkita", "nik01nik",
                "nikkita2001@mail.ru", new LinkedList<>(), new LinkedList<>()));

        savedRecipes = new LinkedList<>();

        LinkedList<Long> list = new LinkedList<>();
        list.add(1L);
        list.add(3L);

        savedRecipes.add(list);

        list = new LinkedList<>();
        list.add(2L);
        list.add(1L);

        savedRecipes.add(list);

        list = new LinkedList<>();
        list.add(2L);
        list.add(2L);

        savedRecipes.add(list);

        list = new LinkedList<>();
        list.add(1L);
        list.add(4L);

        savedRecipes.add(list);

        categories = new LinkedList<>();
        categories.add(new Category(1L, "Бульоны"));
        categories.add(new Category(2L, "Выпечка и десерты"));
        categories.add(new Category(3L, "Завтраки"));
        categories.add(new Category(4L, "Закуски"));
        categories.add(new Category(5L, "Напитки"));
        categories.add(new Category(6L, "Основные блюда"));
        categories.add(new Category(7L, "Паста"));
        categories.add(new Category(8L, "Пиццы"));
        categories.add(new Category(9L, "Салаты"));
        categories.add(new Category(10L, "Супы"));
        categories.add(new Category(11L, "Соусы и маринады"));
        categories.add(new Category(12L, "Сэндвичи"));

        cuisines = new LinkedList<>();
        cuisines.add(new Cuisine(1L, "Американская"));
        cuisines.add(new Cuisine(2L, "Армянская"));
        cuisines.add(new Cuisine(3L, "Белорусская"));
        cuisines.add(new Cuisine(4L, "Британская"));
        cuisines.add(new Cuisine(5L, "Вьетнамская"));
        cuisines.add(new Cuisine(6L, "Греческая"));
        cuisines.add(new Cuisine(7L, "Грузинская"));
        cuisines.add(new Cuisine(8L, "Европейская"));
        cuisines.add(new Cuisine(9L, "Индийская"));
        cuisines.add(new Cuisine(10L, "Испанская"));
        cuisines.add(new Cuisine(11L, "Итальянская"));
        cuisines.add(new Cuisine(12L, "Китайская"));
        cuisines.add(new Cuisine(13L, "Корейская"));
        cuisines.add(new Cuisine(14L, "Мексиканская"));
        cuisines.add(new Cuisine(15L, "Паназиатская"));
        cuisines.add(new Cuisine(16L, "Русская"));
        cuisines.add(new Cuisine(17L, "Средиземноморская"));
        cuisines.add(new Cuisine(18L, "Тайская"));
        cuisines.add(new Cuisine(19L, "Узбекская"));
        cuisines.add(new Cuisine(20L, "Украинская"));
        cuisines.add(new Cuisine(21L, "Французская"));
        cuisines.add(new Cuisine(22L, "Японская"));
    }
    @BeforeEach
    public void setUp() throws ClassNotFoundException {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(postgres.getJdbcUrl());
        dataSourceBuilder.username(postgres.getUsername());
        dataSourceBuilder.password(postgres.getPassword());

        dataSource = dataSourceBuilder.build();
        repository = new RecipeWebsiteRepository(dataSource);
        controller = new RecipeWebsiteController(repository);

        tester = new JdbcDatabaseTester(postgres.getDriverClassName(), postgres.getJdbcUrl(),
                postgres.getUsername(), postgres.getPassword());

        postgres.start();
    }

    @Test
    public void registerUserTest() throws Exception {
        assertTrue(postgres.isRunning());

        HttpStatus expectedStatus = HttpStatus.CREATED;
        HttpStatus actualStatus = controller.registerUser(users.get(2)).getStatusCode();
        assertEquals(expectedStatus, actualStatus);

        ITable actualTable = new SortedTable(tester.getConnection().createDataSet().getTable("users"));
        assertEquals(users.size(), actualTable.getRowCount());

        for (int i = 0; i < actualTable.getRowCount(); i++) {
            assertEquals(Long.valueOf(users.get(i).getId()).intValue(), actualTable.getValue(i, "id_user"));
            assertEquals(users.get(i).getLogin(), actualTable.getValue(i, "login"));
            assertEquals(users.get(i).getPassword(), actualTable.getValue(i, "password"));
            assertEquals(users.get(i).getEmail(), actualTable.getValue(i, "email"));
        }
    }

    @Test
    public void getUserTest() {
        assertTrue(postgres.isRunning());

        ResponseEntity<User> response = controller.getUser(users.get(1).getLogin(), users.get(1).getPassword());

        HttpStatus expectedStatus = HttpStatus.OK;
        HttpStatus actualStatus = response.getStatusCode();
        assertEquals(expectedStatus, actualStatus);

        User expectedUser = users.get(1);
        User actualUser = response.getBody();

        assertEquals(expectedUser, actualUser);

        response = controller.getUser(users.get(2).getLogin(), users.get(2).getPassword());

        expectedStatus = HttpStatus.NOT_FOUND;
        actualStatus = response.getStatusCode();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void getUserByLoginTest() {
        assertTrue(postgres.isRunning());

        ResponseEntity<User> response = controller.getUser(users.get(1).getLogin());

        HttpStatus expectedStatus = HttpStatus.OK;
        HttpStatus actualStatus = response.getStatusCode();
        assertEquals(expectedStatus, actualStatus);

        User expectedUser = users.get(1);
        User actualUser = response.getBody();

        assertEquals(expectedUser, actualUser);

        response = controller.getUser(users.get(2).getLogin());

        expectedStatus = HttpStatus.NOT_FOUND;
        actualStatus = response.getStatusCode();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void getCategoriesTest() {
        assertTrue(postgres.isRunning());

        ResponseEntity<LinkedList<Category>> response = controller.getCategories();

        HttpStatus expectedStatus = HttpStatus.OK;
        HttpStatus actualStatus = response.getStatusCode();
        assertEquals(expectedStatus, actualStatus);

        LinkedList<Category> expectedCategories = categories;
        LinkedList<Category> actualCategories = response.getBody();

        assertEquals(expectedCategories, actualCategories);
    }

    @Test
    public void getCuisinesTest() {
        assertTrue(postgres.isRunning());

        ResponseEntity<LinkedList<Cuisine>> response = controller.getCuisines();

        HttpStatus expectedStatus = HttpStatus.OK;
        HttpStatus actualStatus = response.getStatusCode();
        assertEquals(expectedStatus, actualStatus);

        LinkedList<Cuisine> expectedCuisines = cuisines;
        LinkedList<Cuisine> actualCuisines = response.getBody();

        assertEquals(expectedCuisines, actualCuisines);
    }

    @Test
    public void addRecipeTest() throws Exception {
        assertTrue(postgres.isRunning());

        HttpStatus expectedStatus = HttpStatus.CREATED;
        HttpStatus actualStatus = controller.addRecipe(recipes.get(5)).getStatusCode();
        assertEquals(expectedStatus, actualStatus);

        ITable actualTable = new SortedTable(tester.getConnection().createDataSet().getTable("recipes"));
        assertEquals(recipes.size(), actualTable.getRowCount());

        for (int i = 0; i < actualTable.getRowCount(); i++) {
            assertEquals(Long.valueOf(recipes.get(i).getId()).intValue(), actualTable.getValue(i, "id_recipe"));
            assertEquals(Long.valueOf(controller.getUser(recipes.get(i).getUserLogin()).getBody().getId()).intValue(),
                    actualTable.getValue(i, "id_user"));
            assertEquals(recipes.get(i).getName(), actualTable.getValue(i, "name"));
            assertEquals(recipes.get(i).getImageUrl(), actualTable.getValue(i, "image_url"));
            assertEquals(recipes.get(i).getDateAdded(), actualTable.getValue(i, "date_added").toString());

            Long idCuisine = null;
            for (Cuisine cuisine : cuisines) {
                if (cuisine.getName() == recipes.get(i).getCuisine())
                    idCuisine = cuisine.getId();
            }

            Long idCategory = null;
            for (Category category : categories) {
                if (category.getName() == recipes.get(i).getCategory())
                    idCategory = category.getId();
            }

            assertEquals(Long.valueOf(idCuisine).intValue(), actualTable.getValue(i, "cuisine"));
            assertEquals(Long.valueOf(idCategory).intValue(), actualTable.getValue(i, "category"));
            assertEquals(recipes.get(i).getCookingTime(), actualTable.getValue(i, "cooking_time"));
            assertEquals(recipes.get(i).getDescription(), actualTable.getValue(i, "description"));
            assertEquals(recipes.get(i).getRecipe(), actualTable.getValue(i, "recipe"));
        }
    }

    @Test
    public void getRecipeTest() {
        assertTrue(postgres.isRunning());

        ResponseEntity<Recipe> response = controller.getRecipe(recipes.get(1).getName());

        HttpStatus expectedStatus = HttpStatus.OK;
        HttpStatus actualStatus = response.getStatusCode();
        assertEquals(expectedStatus, actualStatus);

        Recipe expectedRecipe = recipes.get(1);
        Recipe actualRecipe = response.getBody();

        assertEquals(expectedRecipe, actualRecipe);

        response = controller.getRecipe(recipes.get(5).getName());

        expectedStatus = HttpStatus.NOT_FOUND;
        actualStatus = response.getStatusCode();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void saveRecipeTest() throws Exception {
        assertTrue(postgres.isRunning());

        ResponseEntity<?> response = controller.saveRecipe(users.get(0).getLogin(), recipes.get(3).getName());

        HttpStatus expectedStatus = HttpStatus.CREATED;
        HttpStatus actualStatus = response.getStatusCode();
        assertEquals(expectedStatus, actualStatus);

        ITable actualTable = new SortedTable(tester.getConnection().createDataSet().getTable("saved_recipes"));
        assertEquals(savedRecipes.size(), actualTable.getRowCount());

        for (int i = 0; i < actualTable.getRowCount(); i++) {
            assertEquals(Long.valueOf(savedRecipes.get(i).get(0)).intValue(), actualTable.getValue(i, "id_user"));
            assertEquals(Long.valueOf(savedRecipes.get(i).get(1)).intValue(), actualTable.getValue(i, "id_recipe"));
        }
    }

    @Test
    public void getRecipesByCountTest() {
        assertTrue(postgres.isRunning());

        ResponseEntity<LinkedList<Recipe>> response = controller.getRecipes(4);

        HttpStatus expectedStatus = HttpStatus.OK;
        HttpStatus actualStatus = response.getStatusCode();
        assertEquals(expectedStatus, actualStatus);

        LinkedList<Recipe> expectedRecipes = new LinkedList<>();
        expectedRecipes.add(recipes.get(4));
        expectedRecipes.add(recipes.get(3));
        expectedRecipes.add(recipes.get(1));
        expectedRecipes.add(recipes.get(2));
        LinkedList<Recipe> actualRecipes = response.getBody();

        assertEquals(expectedRecipes, actualRecipes);
    }

    @Test
    public void getRecipesTest() {
        assertTrue(postgres.isRunning());

        ResponseEntity<LinkedList<Recipe>> response = controller.getRecipes("Супы", "Русская");

        HttpStatus expectedStatus = HttpStatus.OK;
        HttpStatus actualStatus = response.getStatusCode();
        assertEquals(expectedStatus, actualStatus);

        LinkedList<Recipe> expectedRecipes = new LinkedList<>();
        expectedRecipes.add(recipes.get(1));
        expectedRecipes.add(recipes.get(0));
        LinkedList<Recipe> actualRecipes = response.getBody();

        assertEquals(expectedRecipes, actualRecipes);
    }

    @Test
    public void getRecipesByKeywordsTest() {
        assertTrue(postgres.isRunning());

        ResponseEntity<LinkedList<Recipe>> response = controller.getRecipesByKeywords("Борщ");

        HttpStatus expectedStatus = HttpStatus.OK;
        HttpStatus actualStatus = response.getStatusCode();
        assertEquals(expectedStatus, actualStatus);

        LinkedList<Recipe> expectedRecipes = new LinkedList<>();
        expectedRecipes.add(recipes.get(0));
        LinkedList<Recipe> actualRecipes = response.getBody();

        assertEquals(expectedRecipes, actualRecipes);
    }

    @Test
    public void deleteRecipeTest() throws Exception {
        assertTrue(postgres.isRunning());

        ResponseEntity<?> response = controller.deleteRecipe(users.get(0).getLogin(), recipes.get(3).getName());

        HttpStatus expectedStatus = HttpStatus.OK;
        HttpStatus actualStatus = response.getStatusCode();
        assertEquals(expectedStatus, actualStatus);

        ITable actualTable = new SortedTable(tester.getConnection().createDataSet().getTable("saved_recipes"));
        assertEquals(savedRecipes.size() - 1, actualTable.getRowCount());

        for (int i = 0; i < actualTable.getRowCount(); i++) {
            assertEquals(Long.valueOf(savedRecipes.get(i).get(0)).intValue(), actualTable.getValue(i, "id_user"));
            assertEquals(Long.valueOf(savedRecipes.get(i).get(1)).intValue(), actualTable.getValue(i, "id_recipe"));
        }
    }
}