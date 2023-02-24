package ru.spbstu.povarenok.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import ru.spbstu.povarenok.model.*;
import ru.spbstu.povarenok.repository.RecipeWebsiteRepository;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RunWith(MockitoJUnitRunner.class)
public class RecipeWebsiteControllerTest {

    private RecipeWebsiteController controller;
    @Mock
    private RecipeWebsiteRepository repository;

    private LinkedList<User> correctUsers;
    private LinkedList<User> usersWithIncorrectLogin;
    private LinkedList<User> usersWithIncorrectPassword;
    private LinkedList<User> usersWithIncorrectEmail;

    private LinkedList<Category> categories;
    private LinkedList<Cuisine> cuisines;

    private LinkedList<Recipe> correctRecipes;
    private LinkedList<Recipe> recipesWithIncorrectName;
    private LinkedList<Recipe> recipesWithIncorrectNameOfIngredients;
    private Recipe recipeWithIncorrectGramsOfIngredients;
    private LinkedList<Recipe> recipesWithIncorrectDescription;
    private LinkedList<Recipe> recipesWithIncorrectRecipe;


    @Before
    public void setUp() throws IOException {
        controller = new RecipeWebsiteController(repository);

        // Создаем пользователей для тестирования

        // Корректные пользователи
        // Техники тест дизайна: граничные условия и попарное тестирование

        String loginLess30 = "polinafomina1";
        String login30 = "полинафоминаполинафоминаполина";

        String passwordMore8Less12 = "qwertyyyy";
        String password8 = "квертиии";
        String password12 = "Кwerty123456";

        String emailLess30 = "fominapolia20@яндекс.ru01";
        String email30 = "фоминаполинаандре@yandex01.ком";

        correctUsers = new LinkedList<>();
        correctUsers.add(new User(1L, loginLess30, passwordMore8Less12,
                emailLess30, new LinkedList<>(), new LinkedList<>()));
        correctUsers.add(new User(2L, loginLess30, password8,
                email30, new LinkedList<>(), new LinkedList<>()));
        correctUsers.add(new User(3L, login30, password8,
                emailLess30, new LinkedList<>(), new LinkedList<>()));
        correctUsers.add(new User(4L, login30, password12,
                emailLess30, new LinkedList<>(), new LinkedList<>()));
        correctUsers.add(new User(5L, login30, passwordMore8Less12,
                email30, new LinkedList<>(), new LinkedList<>()));
        correctUsers.add(new User(6L, loginLess30, password12,
                email30, new LinkedList<>(), new LinkedList<>()));
        correctUsers.add(new User(7L, loginLess30, password8,
                emailLess30, new LinkedList<>(), new LinkedList<>()));

        // Некорректные пользователи

        usersWithIncorrectLogin = new LinkedList<>();
        usersWithIncorrectLogin.add(new User(8L, "polinafominapolinafominapolinaf",
                "qwerty123", "fominapolia2001@yandex.ru", new LinkedList<>(),
                new LinkedList<>()));
        usersWithIncorrectLogin.add(new User(9L, "polinafomina!", "qwerty123",
                "fominapolia2001@yandex.ru", new LinkedList<>(), new LinkedList<>()));

        usersWithIncorrectPassword = new LinkedList<>();
        usersWithIncorrectPassword.add(new User(10L, "polinafomina", "qwerty1",
                "fominapolia2001@yandex.ru",
                new LinkedList<>(), new LinkedList<>()));
        usersWithIncorrectPassword.add(new User(11L, "polinafomina", "qwerty1234567",
                "fominapolia2001@yandex.ru",
                new LinkedList<>(), new LinkedList<>()));
        usersWithIncorrectPassword.add(new User(12L, "polinafomina", "qwe#rty",
                "fominapolia2001@yandex.ru",
                new LinkedList<>(), new LinkedList<>()));

        usersWithIncorrectEmail = new LinkedList<>();
        usersWithIncorrectEmail.add(new User(13L, "polinafomina", "qwerty123",
                "fominapoliapolina2001@yandex.ru", new LinkedList<>(), new LinkedList<>()));
        usersWithIncorrectEmail.add(new User(14L, "polinafomina", "qwerty123", "fomina#polia@yandex.ru",
                new LinkedList<>(), new LinkedList<>()));
        usersWithIncorrectEmail.add(new User(15L, "polinafomina", "qwerty123", "fominapolia2001.yandex.ru",
                new LinkedList<>(), new LinkedList<>()));
        usersWithIncorrectEmail.add(new User(16L, "polinafomina", "qwerty123", "fominapolia2001@!yandex.ru",
                new LinkedList<>(), new LinkedList<>()));
        usersWithIncorrectEmail.add(new User(17L, "polinafomina", "qwerty123", "fominapolia2001@yandexru",
                new LinkedList<>(), new LinkedList<>()));
        usersWithIncorrectEmail.add(new User(18L, "polinafomina", "qwerty123", "fominapolia2001@yandex.ru$",
                new LinkedList<>(), new LinkedList<>()));


        // Создаем категории для тестирования

        categories = new LinkedList<>();
        categories.add(new Category(1L, "Суп"));
        categories.add(new Category(2L, "Десерты"));


        // Создаем типы кухонь для тестирования

        cuisines = new LinkedList<>();
        cuisines.add(new Cuisine(1L, "Французская"));
        cuisines.add(new Cuisine(2L, "Русская"));
        cuisines.add(new Cuisine(3L, "Итальянская"));


        // Создаем рецепты для тестирования

        // Корректные рецепты
        // Техники тест дизайна: граничные условия и попарное тестирование

        String nameLess100 = "Борщ123";
        String name100 = "Борщ борщ борщ борщ борщ борщ борщ борщ борщ борщ борщ борщ борщ " +
                "борщ борщ борщ борщ борщ борщ борщ ";

        String ingredientNameLess100 = "Капуста12";
        String ingredientName100 = "Капуста капуста капуста капуста капуста капуста капуста " +
                "капуста капуста капуста капуста капуста капу";

        Double ingredientGramsLengthLess5 = 10.0;
//        Double ingredientGramsLength5 = 30000.0;
        Double ingredientGramsLength5 = 300.0;

//        String descriptionLess500 = "Ароматный борщ, который подаётся с пампушками";
        String descriptionLess500 = "Ароматный борщ который подаётся с пампушками";
        String description500 = FileUtils.readFileToString(new File("500.txt"), StandardCharsets.UTF_8);

//        String recipeLess5000 = "Порезать капусту и лук, сварить";
        String recipeLess5000 = "Порезать капусту и лук сварить";
        String recipe5000 = FileUtils.readFileToString(new File("5000.txt"), StandardCharsets.UTF_8);;

        LinkedList< Ingredient > ingredients1 = new LinkedList<>();
        ingredients1.add(new Ingredient(1L, 1L,
                ingredientName100, ingredientGramsLength5));

        LinkedList< Ingredient > ingredients2 = new LinkedList<>();
        ingredients1.add(new Ingredient(2L, 2L,
                ingredientNameLess100, ingredientGramsLengthLess5));

        LinkedList< Ingredient > ingredients3 = new LinkedList<>();
        ingredients1.add(new Ingredient(3L, 3L,
                ingredientNameLess100, ingredientGramsLength5));

        LinkedList< Ingredient > ingredients4 = new LinkedList<>();
        ingredients1.add(new Ingredient(4L, 4L,
                ingredientName100, ingredientGramsLengthLess5));

        correctRecipes = new LinkedList<>();
        correctRecipes.add(new Recipe(1L, "polinafomina", name100, "123.png",
                "2023-02-19", "Русская", "Суп", 140, ingredients1,
                description500, recipe5000));
        correctRecipes.add(new Recipe(2L, "polinafomina", name100, "123.png",
                "2023-02-19", "Русская", "Суп", 140, ingredients2,
                descriptionLess500, recipeLess5000));
        correctRecipes.add(new Recipe(3L, "polinafomina", nameLess100, "123.png",
                "2023-02-19", "Русская", "Суп", 140, ingredients3,
                descriptionLess500, recipe5000));
        correctRecipes.add(new Recipe(4L, "polinafomina", nameLess100, "123.png",
                "2023-02-19", "Русская", "Суп", 140, ingredients4,
                description500, recipeLess5000));

        // Некорректные рецепты

        recipesWithIncorrectName = new LinkedList<>();
        recipesWithIncorrectName.add(new Recipe(5L, "polinafomina", name100 + 'б',
                "123.png", "2023-02-19", "Русская", "Суп", 140,
                ingredients1, "Ароматный борщ с пампушками",
                "Порезать капусту и лук сварить"));
        recipesWithIncorrectName.add(new Recipe(6L, "polinafomina", "Борщ №",
                "123.png", "2023-02-19", "Русская", "Суп", 140,
                ingredients1, "Ароматный борщ с пампушками",
                "Порезать капусту и лук сварить"));

        LinkedList<Ingredient> incorrectIngredients1 = new LinkedList<>();
        incorrectIngredients1.add(new Ingredient(5L, 7L,
                ingredientName100 + 'л', 100.0));

        LinkedList<Ingredient> incorrectIngredients2 = new LinkedList<>();
        incorrectIngredients2.add(new Ingredient(6L, 8L, "Luk", 20.0));

        LinkedList<Ingredient> incorrectIngredients3 = new LinkedList<>();
        incorrectIngredients3.add(new Ingredient(7L, 9L, "Лук", 200000.0));

        recipesWithIncorrectNameOfIngredients = new LinkedList<>();
        recipesWithIncorrectNameOfIngredients.add(new Recipe(7L, "polinafomina", "Борщ",
                "123.png", "2023-02-19", "Русская", "Суп", 140,
                incorrectIngredients1, "Ароматный борщ с пампушками",
                "Порезать капусту и лук сварить"));
        recipesWithIncorrectNameOfIngredients.add(new Recipe(8L, "polinafomina", "Борщ",
                "123.png", "2023-02-19", "Русская", "Суп", 140,
                incorrectIngredients2, "Ароматный борщ с пампушками",
                "Порезать капусту и лук сварить"));
        recipeWithIncorrectGramsOfIngredients = new Recipe(9L, "polinafomina", "Борщ", "123.png",
                "2023-02-19", "Русская", "Суп", 140, incorrectIngredients3,
                "Ароматный борщ с пампушками", "Порезать капусту и лук сварить");

        recipesWithIncorrectDescription = new LinkedList<>();
        recipesWithIncorrectDescription.add(new Recipe(10L, "polinafomina", "Борщ",
                "123.png", "2023-02-19", "Русская", "Суп", 140,
                ingredients1, description500 + 'э', "Порезать капусту и лук сварить"));
        recipesWithIncorrectDescription.add(new Recipe(11L, "polinafomina", "Борщ",
                "123.png", "2023-02-19", "Русская", "Суп", 140,
                ingredients1, "Ароматный борщ @ с пампушками #",
                "Порезать капусту и лук сварить"));

        recipesWithIncorrectRecipe = new LinkedList<>();
        recipesWithIncorrectRecipe.add(new Recipe(12L, "polinafomina", "Борщ",
                "123.png", "2023-02-19", "Русская", "Суп", 140,
                ingredients1, "Ароматный борщ с пампушками", recipe5000 + 'г'));
        recipesWithIncorrectRecipe.add(new Recipe(13L, "polinafomina", "Борщ",
                "123.png", "2023-02-19", "Русская", "Суп", 140,
                ingredients1, "Ароматный борщ с пампушками", "Сварить %"));
    }
    @Test
    public void registerUserTest() {

        String expectedMessage = "Login must contain no more than 30 characters " +
                "and contain only numbers and Russian or English letters!";
        Exception exception;
        String actualMessage;

        for (User user : usersWithIncorrectLogin) {
            exception = assertThrows(ResponseStatusException.class, () -> {
                controller.registerUser(user);
            });
            actualMessage = exception.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }

        expectedMessage = "Password must contain at least 8 and no more 12 characters " +
                "and contain only numbers and Russian or English letters!";

        for (User user : usersWithIncorrectPassword) {
            exception = assertThrows(ResponseStatusException.class, () -> {
                controller.registerUser(user);
            });
            actualMessage = exception.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }

        expectedMessage = "Mail must contain no more than 30 characters " +
                "and match the template ***@***.*** (*** - any number of characters)!";

        for (User user : usersWithIncorrectEmail) {
            exception = assertThrows(ResponseStatusException.class, () -> {
                controller.registerUser(user);
            });
            actualMessage = exception.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }


        User correctUser = correctUsers.get(0);

        expectedMessage = "User with this login already exists!";

        when(repository.getUser(correctUser.getLogin())).thenReturn(correctUser);

        exception = assertThrows(ResponseStatusException.class, () -> {
            controller.registerUser(correctUser);
        });
        actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        expectedMessage = "User with this email already exists!";

        when(repository.getUser(correctUser.getLogin())).thenReturn(null);
        when(repository.getUserByEmail(correctUser.getEmail())).thenReturn(correctUser);

        exception = assertThrows(ResponseStatusException.class, () -> {
            controller.registerUser(correctUser);
        });
        actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));


        expectedMessage = "Failed to add user!";

        when(repository.getUserByEmail(correctUser.getEmail())).thenReturn(null);
        when(repository.addUser(correctUser)).thenReturn(false);

        exception = assertThrows(ResponseStatusException.class, () -> {
            controller.registerUser(correctUser);
        });
        actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));


        for (User user : correctUsers) {
            when(repository.addUser(user)).thenReturn(true);

            ResponseEntity<?> expectedResponse = new ResponseEntity<>(HttpStatus.CREATED);
            ResponseEntity<?> actualResponse = controller.registerUser(user);

            assertEquals(expectedResponse, actualResponse);
        }
    }

    @Test
    public void getUserTest() {

        String expectedMessage = "Login must contain no more than 30 characters " +
                "and contain only numbers and Russian or English letters!";
        Exception exception;
        String actualMessage;

        for (User user : usersWithIncorrectLogin) {
            exception = assertThrows(ResponseStatusException.class, () -> {
                controller.getUser(user.getLogin(), user.getPassword());
            });
            actualMessage = exception.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }

        expectedMessage = "Password must contain at least 8 and no more 12 characters " +
                "and contain only numbers and Russian or English letters!";

        for (User user : usersWithIncorrectPassword) {
            exception = assertThrows(ResponseStatusException.class, () -> {
                controller.getUser(user.getLogin(), user.getPassword());
            });
            actualMessage = exception.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }


        User correctUser = correctUsers.get(0);

        when(repository.getUser(correctUser.getLogin(), correctUser.getPassword())).thenReturn(null);

        ResponseEntity<?> expectedResponse = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        ResponseEntity<?> actualResponse = controller.getUser(correctUser.getLogin(), correctUser.getPassword());

        assertEquals(expectedResponse, actualResponse);


        for (User user : correctUsers) {
            when(repository.getUser(user.getLogin(), user.getPassword())).thenReturn(user);

            expectedResponse = new ResponseEntity<>(user, HttpStatus.OK);
            actualResponse = controller.getUser(user.getLogin(), user.getPassword());

            assertEquals(expectedResponse, actualResponse);
        }
    }

    @Test
    public void getUserByLoginTest() {

        String expectedMessage = "Login must contain no more than 30 characters " +
                "and contain only numbers and Russian or English letters!";
        Exception exception;
        String actualMessage;

        for (User user : usersWithIncorrectLogin) {
            exception = assertThrows(ResponseStatusException.class, () -> {
                controller.getUser(user.getLogin());
            });
            actualMessage = exception.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }


        User correctUser = correctUsers.get(0);

        when(repository.getUser(correctUser.getLogin())).thenReturn(null);

        ResponseEntity<?> expectedResponse = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        ResponseEntity<?> actualResponse = controller.getUser(correctUser.getLogin());

        assertEquals(expectedResponse, actualResponse);


        for (User user : correctUsers) {
            when(repository.getUser(user.getLogin())).thenReturn(user);

            expectedResponse = new ResponseEntity<>(user, HttpStatus.OK);
            actualResponse = controller.getUser(user.getLogin());

            assertEquals(expectedResponse, actualResponse);
        }
    }

    @Test
    public void getCategoriesTest() {

        when(repository.getAllCategories()).thenReturn(null);

        ResponseEntity<?> expectedResponse = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        ResponseEntity<?> actualResponse = controller.getCategories();

        assertEquals(expectedResponse, actualResponse);


        when(repository.getAllCategories()).thenReturn(categories);

        expectedResponse = new ResponseEntity<>(categories, HttpStatus.OK);
        actualResponse = controller.getCategories();

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void getCuisinesTest() {

        when(repository.getAllCuisines()).thenReturn(null);

        ResponseEntity<?> expectedResponse = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        ResponseEntity<?> actualResponse = controller.getCuisines();

        assertEquals(expectedResponse, actualResponse);


        when(repository.getAllCuisines()).thenReturn(cuisines);

        expectedResponse = new ResponseEntity<>(cuisines, HttpStatus.OK);
        actualResponse = controller.getCuisines();

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void addRecipeTest() {

        String expectedMessage = "The recipe name must contain no more than 100 characters " +
                "and contain only numbers and Russian letters!";
        Exception exception;
        String actualMessage;

        for (Recipe recipe : recipesWithIncorrectName) {
            exception = assertThrows(ResponseStatusException.class, () -> {
                controller.addRecipe(recipe);
            });
            actualMessage = exception.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }

        expectedMessage = "The ingredient name must contain no more than 100 characters " +
                "and contain only numbers and Russian letters!";

        for (Recipe recipe : recipesWithIncorrectNameOfIngredients) {
            exception = assertThrows(ResponseStatusException.class, () -> {
                controller.addRecipe(recipe);
            });
            actualMessage = exception.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }

        expectedMessage = "Grams must be a number with no more than 5 characters!";

        exception = assertThrows(ResponseStatusException.class, () -> {
            controller.addRecipe(recipeWithIncorrectGramsOfIngredients);
        });
        actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        expectedMessage = "The recipe description must contain no more than 500 characters " +
                "and contain only numbers and Russian letters!";

        for (Recipe recipe : recipesWithIncorrectDescription) {
            exception = assertThrows(ResponseStatusException.class, () -> {
                controller.addRecipe(recipe);
            });
            actualMessage = exception.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }

        expectedMessage = "The recipe must contain no more than 5000 characters " +
                "and contain only numbers and Russian letters!";

        for (Recipe recipe : recipesWithIncorrectRecipe) {
            exception = assertThrows(ResponseStatusException.class, () -> {
                controller.addRecipe(recipe);
            });
            actualMessage = exception.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }


        Recipe correctRecipe = correctRecipes.get(0);

        expectedMessage = "Recipe with this name already exists!";

        when(repository.getRecipe(correctRecipe.getName())).thenReturn(correctRecipe);

        exception = assertThrows(ResponseStatusException.class, () -> {
            controller.addRecipe(correctRecipe);
        });
        actualMessage = exception.getMessage();

        System.out.println(correctRecipe.getDescription().length());

        assertTrue(actualMessage.contains(expectedMessage));

        expectedMessage = "Recipe with this url already exists!";

        when(repository.getRecipe(correctRecipe.getName())).thenReturn(null);
        when(repository.getRecipeByUrl(correctRecipe.getImageUrl())).thenReturn(correctRecipe);

        exception = assertThrows(ResponseStatusException.class, () -> {
            controller.addRecipe(correctRecipe);
        });
        actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        expectedMessage = "Recipe with this description already exists!";

        when(repository.getRecipeByUrl(correctRecipe.getImageUrl())).thenReturn(null);
        when(repository.getRecipeByDescription(correctRecipe.getDescription())).thenReturn(correctRecipe);

        exception = assertThrows(ResponseStatusException.class, () -> {
            controller.addRecipe(correctRecipe);
        });
        actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        expectedMessage = "Recipe with this step-by-step recipe already exists!";

        when(repository.getRecipeByDescription(correctRecipe.getDescription())).thenReturn(null);
        when(repository.getRecipeByStepByStepRecipe(correctRecipe.getRecipe())).thenReturn(correctRecipe);

        exception = assertThrows(ResponseStatusException.class, () -> {
            controller.addRecipe(correctRecipe);
        });
        actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));


        expectedMessage = "Failed to add recipe!";

        when(repository.getRecipeByStepByStepRecipe(correctRecipe.getRecipe())).thenReturn(null);
        when(repository.addRecipe(correctRecipe)).thenReturn(false);

        exception = assertThrows(ResponseStatusException.class, () -> {
            controller.addRecipe(correctRecipe);
        });
        actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        for (Recipe recipe : correctRecipes) {
            when(repository.addRecipe(recipe)).thenReturn(true);

            ResponseEntity<?> expectedResponse = new ResponseEntity<>(HttpStatus.CREATED);
            ResponseEntity<?> actualResponse = controller.addRecipe(recipe);

            assertEquals(expectedResponse, actualResponse);
        }
    }

    @Test
    public void getRecipeTest() {

        String expectedMessage = "The recipe name must contain no more than 100 characters " +
                "and contain only numbers and Russian letters!";
        Exception exception;
        String actualMessage;

        for (Recipe recipe : recipesWithIncorrectName) {
            exception = assertThrows(ResponseStatusException.class, () -> {
                controller.getRecipe(recipe.getName());
            });
            actualMessage = exception.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }


        Recipe correctRecipe = correctRecipes.get(0);

        when(repository.getRecipe(correctRecipe.getName())).thenReturn(null);

        ResponseEntity<?> expectedResponse = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        ResponseEntity<?> actualResponse = controller.getRecipe(correctRecipe.getName());

        assertEquals(expectedResponse, actualResponse);


        for (Recipe recipe : correctRecipes) {
            when(repository.getRecipe(recipe.getName())).thenReturn(recipe);

            expectedResponse = new ResponseEntity<>(recipe, HttpStatus.OK);
            actualResponse = controller.getRecipe(recipe.getName());

            assertEquals(expectedResponse, actualResponse);
        }
    }

    @Test
    public void saveRecipeTest() {

        String expectedMessage = "Login must contain no more than 30 characters " +
                "and contain only numbers and Russian or English letters!";
        Exception exception;
        String actualMessage;

        Recipe correctRecipe = correctRecipes.get(0);

        for (User user : usersWithIncorrectLogin) {
            exception = assertThrows(ResponseStatusException.class, () -> {
                controller.saveRecipe(user.getLogin(), correctRecipe.getName());
            });
            actualMessage = exception.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }

        expectedMessage = "The recipe name must contain no more than 100 characters " +
                "and contain only numbers and Russian letters!";

        User correctUser = correctUsers.get(0);

        for (Recipe recipe : recipesWithIncorrectName) {
            exception = assertThrows(ResponseStatusException.class, () -> {
                controller.saveRecipe(correctUser.getLogin(), recipe.getName());
            });
            actualMessage = exception.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }


        expectedMessage = "There is no user with this login!";

        when(repository.getUser(correctUser.getLogin())).thenReturn(null);

        exception = assertThrows(ResponseStatusException.class, () -> {
            controller.saveRecipe(correctUser.getLogin(), correctRecipe.getName());
        });
        actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        expectedMessage = "There is no recipe with this name!";

        when(repository.getUser(correctUser.getLogin())).thenReturn(correctUser);
        when(repository.getRecipe(correctRecipe.getName())).thenReturn(null);

        exception = assertThrows(ResponseStatusException.class, () -> {
            controller.saveRecipe(correctUser.getLogin(), correctRecipe.getName());
        });
        actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));


        expectedMessage = "Failed to save recipe!";

        when(repository.getRecipe(correctRecipe.getName())).thenReturn(correctRecipe);
        when(repository.saveRecipe(correctUser.getLogin(), correctRecipe.getName())).thenReturn(false);

        exception = assertThrows(ResponseStatusException.class, () -> {
            controller.saveRecipe(correctUser.getLogin(), correctRecipe.getName());
        });
        actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));


        when(repository.getUser(correctUser.getLogin())).thenReturn(correctUser);
        for (Recipe recipe : correctRecipes) {
            when(repository.getRecipe(recipe.getName())).thenReturn(recipe);
            when(repository.saveRecipe(correctUser.getLogin(), recipe.getName())).thenReturn(true);

            ResponseEntity<?> expectedResponse = new ResponseEntity<>(HttpStatus.CREATED);
            ResponseEntity<?> actualResponse = controller.saveRecipe(correctUser.getLogin(), recipe.getName());

            assertEquals(expectedResponse, actualResponse);
        }


        when(repository.getRecipe(correctRecipe.getName())).thenReturn(correctRecipe);
        for (User user : correctUsers) {
            when(repository.getUser(user.getLogin())).thenReturn(user);
            when(repository.saveRecipe(user.getLogin(), correctRecipe.getName())).thenReturn(true);

            ResponseEntity<?> expectedResponse = new ResponseEntity<>(HttpStatus.CREATED);
            ResponseEntity<?> actualResponse = controller.saveRecipe(user.getLogin(), correctRecipe.getName());

            assertEquals(expectedResponse, actualResponse);
        }
    }

    @Test
    public void getRecipesByCountTest() {

        String expectedMessage = "The count of recipes can't be negative or equal to zero!";
        Exception exception;
        String actualMessage;

        exception = assertThrows(ResponseStatusException.class, () -> {
            controller.getRecipes(0);
        });
        actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        exception = assertThrows(ResponseStatusException.class, () -> {
            controller.getRecipes(-1);
        });
        actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));


        when(repository.getLastRecipes(3)).thenReturn(null);

        ResponseEntity<?> expectedResponse = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        ResponseEntity<?> actualResponse = controller.getRecipes(3);

        assertEquals(expectedResponse, actualResponse);


        when(repository.getLastRecipes(3)).thenReturn(correctRecipes);

        expectedResponse = new ResponseEntity<>(correctRecipes, HttpStatus.OK);
        actualResponse = controller.getRecipes(3);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void getRecipesTest() {

        when(repository.getRecipes("Суп", "Русская")).thenReturn(null);

        ResponseEntity<?> expectedResponse = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        ResponseEntity<?> actualResponse = controller.getRecipes("Суп", "Русская");

        assertEquals(expectedResponse, actualResponse);


        when(repository.getRecipes("Суп", "Русская")).thenReturn(correctRecipes);

        expectedResponse = new ResponseEntity<>(correctRecipes, HttpStatus.OK);
        actualResponse = controller.getRecipes("Суп", "Русская");

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void getRecipesByKeywordsTest() {

        when(repository.getRecipes("щи")).thenReturn(null);

        ResponseEntity<?> expectedResponse = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        ResponseEntity<?> actualResponse = controller.getRecipeByKeywords("щи");

        assertEquals(expectedResponse, actualResponse);


        when(repository.getRecipes("щи")).thenReturn(correctRecipes);

        expectedResponse = new ResponseEntity<>(correctRecipes, HttpStatus.OK);
        actualResponse = controller.getRecipeByKeywords("щи");

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void deleteRecipeTest() {

        String expectedMessage = "Login must contain no more than 30 characters " +
                "and contain only numbers and Russian or English letters!";
        Exception exception;
        String actualMessage;

        Recipe correctRecipe = correctRecipes.get(0);

        for (User user : usersWithIncorrectLogin) {
            exception = assertThrows(ResponseStatusException.class, () -> {
                controller.deleteRecipe(user.getLogin(), correctRecipe.getName());
            });
            actualMessage = exception.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }

        expectedMessage = "The recipe name must contain no more than 100 characters " +
                "and contain only numbers and Russian letters!";

        User correctUser = correctUsers.get(0);

        for (Recipe recipe : recipesWithIncorrectName) {
            exception = assertThrows(ResponseStatusException.class, () -> {
                controller.deleteRecipe(correctUser.getLogin(), recipe.getName());
            });
            actualMessage = exception.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }


        expectedMessage = "There is no user with this login!";

        when(repository.getUser(correctUser.getLogin())).thenReturn(null);

        exception = assertThrows(ResponseStatusException.class, () -> {
            controller.deleteRecipe(correctUser.getLogin(), correctRecipe.getName());
        });
        actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        expectedMessage = "There is no recipe with this name!";

        when(repository.getUser(correctUser.getLogin())).thenReturn(correctUser);
        when(repository.getRecipe(correctRecipe.getName())).thenReturn(null);

        exception = assertThrows(ResponseStatusException.class, () -> {
            controller.deleteRecipe(correctUser.getLogin(), correctRecipe.getName());
        });
        actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));


        expectedMessage = "Failed to save recipe!";

        when(repository.getRecipe(correctRecipe.getName())).thenReturn(correctRecipe);
        when(repository.deleteRecipe(correctUser.getLogin(), correctRecipe.getName())).thenReturn(false);

        exception = assertThrows(ResponseStatusException.class, () -> {
            controller.deleteRecipe(correctUser.getLogin(), correctRecipe.getName());
        });
        actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));


        when(repository.getUser(correctUser.getLogin())).thenReturn(correctUser);
        for (Recipe recipe : correctRecipes) {
            when(repository.getRecipe(recipe.getName())).thenReturn(recipe);
            when(repository.deleteRecipe(correctUser.getLogin(), recipe.getName())).thenReturn(true);

            ResponseEntity<?> expectedResponse = new ResponseEntity<>(HttpStatus.OK);
            ResponseEntity<?> actualResponse = controller.deleteRecipe(correctUser.getLogin(), recipe.getName());

            assertEquals(expectedResponse, actualResponse);
        }


        when(repository.getRecipe(correctRecipe.getName())).thenReturn(correctRecipe);
        for (User user : correctUsers) {
            when(repository.getUser(user.getLogin())).thenReturn(user);
            when(repository.deleteRecipe(user.getLogin(), correctRecipe.getName())).thenReturn(true);

            ResponseEntity<?> expectedResponse = new ResponseEntity<>(HttpStatus.OK);
            ResponseEntity<?> actualResponse = controller.deleteRecipe(user.getLogin(), correctRecipe.getName());

            assertEquals(expectedResponse, actualResponse);
        }
    }
}