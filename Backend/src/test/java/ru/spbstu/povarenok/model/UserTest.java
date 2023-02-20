package ru.spbstu.povarenok.model;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class UserTest {

    @Test
    public void testGetId() {
        LinkedList<Ingredient> ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(1L, 1L,
                "Капуста", 100.0));
        ingredients.add(new Ingredient(2L, 1L,
                "Лук", 20.0));

        Recipe addedRecipe = new Recipe(1L, "polinafomina", "Щи", "123.png",
                "2023-02-11", "Русская", "Суп", 90, ingredients,
                "Ароматные щи с зеленью", "Порезать капусту и лук, сварить");

        LinkedList<Recipe> addedRecipes = new LinkedList<>();
        addedRecipes.add(addedRecipe);

        ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(3L, 2L,
                "Морковь", 220.0));
        ingredients.add(new Ingredient(4L, 2L,
                "Свёкла", 543.0));

        Recipe savedRecipe1 = new Recipe(2L, "polinafomina", "Борщ", "456.png",
                "2023-02-19", "Украинская", "Суп", 140, ingredients,
                "Ароматный борщ, который подаётся с пампушками", "Порезать свёклу, потереть морковь, сварить");

        ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(4L, 3L,
                "Капуста", 140.0));
        ingredients.add(new Ingredient(5L, 3L,
                "Сосиски", 120.0));

        Recipe savedRecipe2 = new Recipe(3L, "polinafomina", "Тушеная капуста", "789.png",
                "2023-03-19", "Русская", "Основные блюда", 40, ingredients,
                "Сочная тушеная капуста с сосисками", "Порезать капусту и сосиски, потушить");

        LinkedList<Recipe> savedRecipes = new LinkedList<>();
        savedRecipes.add(savedRecipe1);
        savedRecipes.add(savedRecipe2);

        Long expectedId = 1L;
        User user = new User(expectedId, "polinafomina", "qwerty", "fominapolia2001@yandex.ru",
                addedRecipes, savedRecipes);
        Long actualId = user.getId();

        assertEquals(expectedId, actualId);
    }

    @Test
    public void testSetId() {
        LinkedList<Ingredient> ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(1L, 1L,
                "Капуста", 100.0));
        ingredients.add(new Ingredient(2L, 1L,
                "Лук", 20.0));

        Recipe addedRecipe = new Recipe(1L, "polinafomina", "Щи", "123.png",
                "2023-02-11", "Русская", "Суп", 90, ingredients,
                "Ароматные щи с зеленью", "Порезать капусту и лук, сварить");

        LinkedList<Recipe> addedRecipes = new LinkedList<>();
        addedRecipes.add(addedRecipe);

        ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(3L, 2L,
                "Морковь", 220.0));
        ingredients.add(new Ingredient(4L, 2L,
                "Свёкла", 543.0));

        Recipe savedRecipe1 = new Recipe(2L, "polinafomina", "Борщ", "456.png",
                "2023-02-19", "Украинская", "Суп", 140, ingredients,
                "Ароматный борщ, который подаётся с пампушками", "Порезать свёклу, потереть морковь, сварить");

        ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(4L, 3L,
                "Капуста", 140.0));
        ingredients.add(new Ingredient(5L, 3L,
                "Сосиски", 120.0));

        Recipe savedRecipe2 = new Recipe(3L, "polinafomina", "Тушеная капуста", "789.png",
                "2023-03-19", "Русская", "Основные блюда", 40, ingredients,
                "Сочная тушеная капуста с сосисками", "Порезать капусту и сосиски, потушить");

        LinkedList<Recipe> savedRecipes = new LinkedList<>();
        savedRecipes.add(savedRecipe1);
        savedRecipes.add(savedRecipe2);

        Long expectedId = 1L;
        User user = new User(2L, "polinafomina", "qwerty","fominapolia2001@yandex.ru",
                addedRecipes, savedRecipes);
        user.setId(expectedId);
        Long actualId = user.getId();

        assertEquals(expectedId, actualId);
    }

    @Test
    public void testGetLogin() {
        LinkedList<Ingredient> ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(1L, 1L,
                "Капуста", 100.0));
        ingredients.add(new Ingredient(2L, 1L,
                "Лук", 20.0));

        Recipe addedRecipe = new Recipe(1L, "polinafomina", "Щи", "123.png",
                "2023-02-11", "Русская", "Суп", 90, ingredients,
                "Ароматные щи с зеленью", "Порезать капусту и лук, сварить");

        LinkedList<Recipe> addedRecipes = new LinkedList<>();
        addedRecipes.add(addedRecipe);

        ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(3L, 2L,
                "Морковь", 220.0));
        ingredients.add(new Ingredient(4L, 2L,
                "Свёкла", 543.0));

        Recipe savedRecipe1 = new Recipe(2L, "polinafomina", "Борщ", "456.png",
                "2023-02-19", "Украинская", "Суп", 140, ingredients,
                "Ароматный борщ, который подаётся с пампушками", "Порезать свёклу, потереть морковь, сварить");

        ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(4L, 3L,
                "Капуста", 140.0));
        ingredients.add(new Ingredient(5L, 3L,
                "Сосиски", 120.0));

        Recipe savedRecipe2 = new Recipe(3L, "polinafomina", "Тушеная капуста", "789.png",
                "2023-03-19", "Русская", "Основные блюда", 40, ingredients,
                "Сочная тушеная капуста с сосисками", "Порезать капусту и сосиски, потушить");

        LinkedList<Recipe> savedRecipes = new LinkedList<>();
        savedRecipes.add(savedRecipe1);
        savedRecipes.add(savedRecipe2);

        String expectedLogin = "polinafomina";
        User user = new User(1L, expectedLogin, "qwerty", "fominapolia2001@yandex.ru",
                addedRecipes, savedRecipes);
        String actualLogin = user.getLogin();

        assertEquals(expectedLogin, actualLogin);
    }

    @Test
    public void testSetLogin() {
        LinkedList<Ingredient> ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(1L, 1L,
                "Капуста", 100.0));
        ingredients.add(new Ingredient(2L, 1L,
                "Лук", 20.0));

        Recipe addedRecipe = new Recipe(1L, "polinafomina", "Щи", "123.png",
                "2023-02-11", "Русская", "Суп", 90, ingredients,
                "Ароматные щи с зеленью", "Порезать капусту и лук, сварить");

        LinkedList<Recipe> addedRecipes = new LinkedList<>();
        addedRecipes.add(addedRecipe);

        ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(3L, 2L,
                "Морковь", 220.0));
        ingredients.add(new Ingredient(4L, 2L,
                "Свёкла", 543.0));

        Recipe savedRecipe1 = new Recipe(2L, "polinafomina", "Борщ", "456.png",
                "2023-02-19", "Украинская", "Суп", 140, ingredients,
                "Ароматный борщ, который подаётся с пампушками", "Порезать свёклу, потереть морковь, сварить");

        ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(4L, 3L,
                "Капуста", 140.0));
        ingredients.add(new Ingredient(5L, 3L,
                "Сосиски", 120.0));

        Recipe savedRecipe2 = new Recipe(3L, "polinafomina", "Тушеная капуста", "789.png",
                "2023-03-19", "Русская", "Основные блюда", 40, ingredients,
                "Сочная тушеная капуста с сосисками", "Порезать капусту и сосиски, потушить");

        LinkedList<Recipe> savedRecipes = new LinkedList<>();
        savedRecipes.add(savedRecipe1);
        savedRecipes.add(savedRecipe2);

        String expectedLogin = "polinafomina";
        User user = new User(1L, "verafomina", "qwerty","fominapolia2001@yandex.ru",
                addedRecipes, savedRecipes);
        user.setLogin(expectedLogin);
        String actualLogin = user.getLogin();

        assertEquals(expectedLogin, actualLogin);
    }

    @Test
    public void testGetPassword() {
        LinkedList<Ingredient> ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(1L, 1L,
                "Капуста", 100.0));
        ingredients.add(new Ingredient(2L, 1L,
                "Лук", 20.0));

        Recipe addedRecipe = new Recipe(1L, "polinafomina", "Щи", "123.png",
                "2023-02-11", "Русская", "Суп", 90, ingredients,
                "Ароматные щи с зеленью", "Порезать капусту и лук, сварить");

        LinkedList<Recipe> addedRecipes = new LinkedList<>();
        addedRecipes.add(addedRecipe);

        ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(3L, 2L,
                "Морковь", 220.0));
        ingredients.add(new Ingredient(4L, 2L,
                "Свёкла", 543.0));

        Recipe savedRecipe1 = new Recipe(2L, "polinafomina", "Борщ", "456.png",
                "2023-02-19", "Украинская", "Суп", 140, ingredients,
                "Ароматный борщ, который подаётся с пампушками", "Порезать свёклу, потереть морковь, сварить");

        ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(4L, 3L,
                "Капуста", 140.0));
        ingredients.add(new Ingredient(5L, 3L,
                "Сосиски", 120.0));

        Recipe savedRecipe2 = new Recipe(3L, "polinafomina", "Тушеная капуста", "789.png",
                "2023-03-19", "Русская", "Основные блюда", 40, ingredients,
                "Сочная тушеная капуста с сосисками", "Порезать капусту и сосиски, потушить");

        LinkedList<Recipe> savedRecipes = new LinkedList<>();
        savedRecipes.add(savedRecipe1);
        savedRecipes.add(savedRecipe2);

        String expectedPassword = "qwerty";
        User user = new User(1L, "polinafomina", expectedPassword, "fominapolia2001@yandex.ru",
                addedRecipes, savedRecipes);
        String actualPassword = user.getPassword();

        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    public void testSetPassword() {
        LinkedList<Ingredient> ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(1L, 1L,
                "Капуста", 100.0));
        ingredients.add(new Ingredient(2L, 1L,
                "Лук", 20.0));

        Recipe addedRecipe = new Recipe(1L, "polinafomina", "Щи", "123.png",
                "2023-02-11", "Русская", "Суп", 90, ingredients,
                "Ароматные щи с зеленью", "Порезать капусту и лук, сварить");

        LinkedList<Recipe> addedRecipes = new LinkedList<>();
        addedRecipes.add(addedRecipe);

        ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(3L, 2L,
                "Морковь", 220.0));
        ingredients.add(new Ingredient(4L, 2L,
                "Свёкла", 543.0));

        Recipe savedRecipe1 = new Recipe(2L, "polinafomina", "Борщ", "456.png",
                "2023-02-19", "Украинская", "Суп", 140, ingredients,
                "Ароматный борщ, который подаётся с пампушками", "Порезать свёклу, потереть морковь, сварить");

        ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(4L, 3L,
                "Капуста", 140.0));
        ingredients.add(new Ingredient(5L, 3L,
                "Сосиски", 120.0));

        Recipe savedRecipe2 = new Recipe(3L, "polinafomina", "Тушеная капуста", "789.png",
                "2023-03-19", "Русская", "Основные блюда", 40, ingredients,
                "Сочная тушеная капуста с сосисками", "Порезать капусту и сосиски, потушить");

        LinkedList<Recipe> savedRecipes = new LinkedList<>();
        savedRecipes.add(savedRecipe1);
        savedRecipes.add(savedRecipe2);

        String expectedPassword = "qwerty";
        User user = new User(1L, "polinafomina", "12345","fominapolia2001@yandex.ru",
                addedRecipes, savedRecipes);
        user.setPassword(expectedPassword);
        String actualPassword = user.getPassword();

        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    public void testGetEmail() {
        LinkedList<Ingredient> ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(1L, 1L,
                "Капуста", 100.0));
        ingredients.add(new Ingredient(2L, 1L,
                "Лук", 20.0));

        Recipe addedRecipe = new Recipe(1L, "polinafomina", "Щи", "123.png",
                "2023-02-11", "Русская", "Суп", 90, ingredients,
                "Ароматные щи с зеленью", "Порезать капусту и лук, сварить");

        LinkedList<Recipe> addedRecipes = new LinkedList<>();
        addedRecipes.add(addedRecipe);

        ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(3L, 2L,
                "Морковь", 220.0));
        ingredients.add(new Ingredient(4L, 2L,
                "Свёкла", 543.0));

        Recipe savedRecipe1 = new Recipe(2L, "polinafomina", "Борщ", "456.png",
                "2023-02-19", "Украинская", "Суп", 140, ingredients,
                "Ароматный борщ, который подаётся с пампушками", "Порезать свёклу, потереть морковь, сварить");

        ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(4L, 3L,
                "Капуста", 140.0));
        ingredients.add(new Ingredient(5L, 3L,
                "Сосиски", 120.0));

        Recipe savedRecipe2 = new Recipe(3L, "polinafomina", "Тушеная капуста", "789.png",
                "2023-03-19", "Русская", "Основные блюда", 40, ingredients,
                "Сочная тушеная капуста с сосисками", "Порезать капусту и сосиски, потушить");

        LinkedList<Recipe> savedRecipes = new LinkedList<>();
        savedRecipes.add(savedRecipe1);
        savedRecipes.add(savedRecipe2);

        String expectedEmail = "fominapolia2001@yandex.ru";
        User user = new User(1L, "polinafomina", "qwerty", expectedEmail,
                addedRecipes, savedRecipes);
        String actualEmail = user.getEmail();

        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    public void testSetEmail() {
        LinkedList<Ingredient> ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(1L, 1L,
                "Капуста", 100.0));
        ingredients.add(new Ingredient(2L, 1L,
                "Лук", 20.0));

        Recipe addedRecipe = new Recipe(1L, "polinafomina", "Щи", "123.png",
                "2023-02-11", "Русская", "Суп", 90, ingredients,
                "Ароматные щи с зеленью", "Порезать капусту и лук, сварить");

        LinkedList<Recipe> addedRecipes = new LinkedList<>();
        addedRecipes.add(addedRecipe);

        ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(3L, 2L,
                "Морковь", 220.0));
        ingredients.add(new Ingredient(4L, 2L,
                "Свёкла", 543.0));

        Recipe savedRecipe1 = new Recipe(2L, "polinafomina", "Борщ", "456.png",
                "2023-02-19", "Украинская", "Суп", 140, ingredients,
                "Ароматный борщ, который подаётся с пампушками", "Порезать свёклу, потереть морковь, сварить");

        ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(4L, 3L,
                "Капуста", 140.0));
        ingredients.add(new Ingredient(5L, 3L,
                "Сосиски", 120.0));

        Recipe savedRecipe2 = new Recipe(3L, "polinafomina", "Тушеная капуста", "789.png",
                "2023-03-19", "Русская", "Основные блюда", 40, ingredients,
                "Сочная тушеная капуста с сосисками", "Порезать капусту и сосиски, потушить");

        LinkedList<Recipe> savedRecipes = new LinkedList<>();
        savedRecipes.add(savedRecipe1);
        savedRecipes.add(savedRecipe2);

        String expectedEmail = "fominapolia2001@yandex.ru";
        User user = new User(1L, "polinafomina", "qwerty","fomina01@gmail.com",
                addedRecipes, savedRecipes);
        user.setEmail(expectedEmail);
        String actualEmail = user.getEmail();

        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    public void testGetAddedRecipes() {
        LinkedList<Ingredient> ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(1L, 1L,
                "Капуста", 100.0));
        ingredients.add(new Ingredient(2L, 1L,
                "Лук", 20.0));

        Recipe addedRecipe = new Recipe(1L, "polinafomina", "Щи", "123.png",
                "2023-02-11", "Русская", "Суп", 90, ingredients,
                "Ароматные щи с зеленью", "Порезать капусту и лук, сварить");

        LinkedList<Recipe> expectedAddedRecipes = new LinkedList<>();
        expectedAddedRecipes.add(addedRecipe);

        ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(3L, 2L,
                "Морковь", 220.0));
        ingredients.add(new Ingredient(4L, 2L,
                "Свёкла", 543.0));

        Recipe savedRecipe1 = new Recipe(2L, "polinafomina", "Борщ", "456.png",
                "2023-02-19", "Украинская", "Суп", 140, ingredients,
                "Ароматный борщ, который подаётся с пампушками", "Порезать свёклу, потереть морковь, сварить");

        ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(4L, 3L,
                "Капуста", 140.0));
        ingredients.add(new Ingredient(5L, 3L,
                "Сосиски", 120.0));

        Recipe savedRecipe2 = new Recipe(3L, "polinafomina", "Тушеная капуста", "789.png",
                "2023-03-19", "Русская", "Основные блюда", 40, ingredients,
                "Сочная тушеная капуста с сосисками", "Порезать капусту и сосиски, потушить");

        LinkedList<Recipe> savedRecipes = new LinkedList<>();
        savedRecipes.add(savedRecipe1);
        savedRecipes.add(savedRecipe2);

        User user = new User(1L, "polinafomina", "qwerty", "fominapolia2001@yandex.ru",
                expectedAddedRecipes, savedRecipes);
        LinkedList<Recipe> actualAddedRecipes = user.getAddedRecipes();

        assertEquals(expectedAddedRecipes, actualAddedRecipes);
    }

    @Test
    public void testSetAddedRecipes() {
        LinkedList<Ingredient> ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(1L, 1L,
                "Капуста", 100.0));
        ingredients.add(new Ingredient(2L, 1L,
                "Лук", 20.0));

        Recipe addedRecipe = new Recipe(1L, "polinafomina", "Щи", "123.png",
                "2023-02-11", "Русская", "Суп", 90, ingredients,
                "Ароматные щи с зеленью", "Порезать капусту и лук, сварить");

        LinkedList<Recipe> expectedAddedRecipes = new LinkedList<>();
        expectedAddedRecipes.add(addedRecipe);

        ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(3L, 2L,
                "Морковь", 220.0));
        ingredients.add(new Ingredient(4L, 2L,
                "Свёкла", 543.0));

        Recipe savedRecipe1 = new Recipe(2L, "polinafomina", "Борщ", "456.png",
                "2023-02-19", "Украинская", "Суп", 140, ingredients,
                "Ароматный борщ, который подаётся с пампушками", "Порезать свёклу, потереть морковь, сварить");

        ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(4L, 3L,
                "Капуста", 140.0));
        ingredients.add(new Ingredient(5L, 3L,
                "Сосиски", 120.0));

        Recipe savedRecipe2 = new Recipe(3L, "polinafomina", "Тушеная капуста", "789.png",
                "2023-03-19", "Русская", "Основные блюда", 40, ingredients,
                "Сочная тушеная капуста с сосисками", "Порезать капусту и сосиски, потушить");

        LinkedList<Recipe> savedRecipes = new LinkedList<>();
        savedRecipes.add(savedRecipe1);
        savedRecipes.add(savedRecipe2);

        User user = new User(1L, "polinafomina", "qwerty", "fominapolia2001@yandex.ru",
                savedRecipes, savedRecipes);
        user.setAddedRecipes(expectedAddedRecipes);
        LinkedList<Recipe> actualAddedRecipes = user.getAddedRecipes();

        assertEquals(expectedAddedRecipes, actualAddedRecipes);
    }

    @Test
    public void testGetSavedRecipes() {
        LinkedList<Ingredient> ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(1L, 1L,
                "Капуста", 100.0));
        ingredients.add(new Ingredient(2L, 1L,
                "Лук", 20.0));

        Recipe addedRecipe = new Recipe(1L, "polinafomina", "Щи", "123.png",
                "2023-02-11", "Русская", "Суп", 90, ingredients,
                "Ароматные щи с зеленью", "Порезать капусту и лук, сварить");

        LinkedList<Recipe> addedRecipes = new LinkedList<>();
        addedRecipes.add(addedRecipe);

        ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(3L, 2L,
                "Морковь", 220.0));
        ingredients.add(new Ingredient(4L, 2L,
                "Свёкла", 543.0));

        Recipe savedRecipe1 = new Recipe(2L, "polinafomina", "Борщ", "456.png",
                "2023-02-19", "Украинская", "Суп", 140, ingredients,
                "Ароматный борщ, который подаётся с пампушками", "Порезать свёклу, потереть морковь, сварить");

        ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(4L, 3L,
                "Капуста", 140.0));
        ingredients.add(new Ingredient(5L, 3L,
                "Сосиски", 120.0));

        Recipe savedRecipe2 = new Recipe(3L, "polinafomina", "Тушеная капуста", "789.png",
                "2023-03-19", "Русская", "Основные блюда", 40, ingredients,
                "Сочная тушеная капуста с сосисками", "Порезать капусту и сосиски, потушить");

        LinkedList<Recipe> expectedSavedRecipes = new LinkedList<>();
        expectedSavedRecipes.add(savedRecipe1);
        expectedSavedRecipes.add(savedRecipe2);

        User user = new User(1L, "polinafomina", "qwerty", "fominapolia2001@yandex.ru",
                addedRecipes, expectedSavedRecipes);
        LinkedList<Recipe> actualSavedRecipes = user.getSavedRecipes();

        assertEquals(expectedSavedRecipes, actualSavedRecipes);
    }

    @Test
    public void testSetSavedRecipes() {
        LinkedList<Ingredient> ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(1L, 1L,
                "Капуста", 100.0));
        ingredients.add(new Ingredient(2L, 1L,
                "Лук", 20.0));

        Recipe addedRecipe = new Recipe(1L, "polinafomina", "Щи", "123.png",
                "2023-02-11", "Русская", "Суп", 90, ingredients,
                "Ароматные щи с зеленью", "Порезать капусту и лук, сварить");

        LinkedList<Recipe> addedRecipes = new LinkedList<>();
        addedRecipes.add(addedRecipe);

        ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(3L, 2L,
                "Морковь", 220.0));
        ingredients.add(new Ingredient(4L, 2L,
                "Свёкла", 543.0));

        Recipe savedRecipe1 = new Recipe(2L, "polinafomina", "Борщ", "456.png",
                "2023-02-19", "Украинская", "Суп", 140, ingredients,
                "Ароматный борщ, который подаётся с пампушками", "Порезать свёклу, потереть морковь, сварить");

        ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(4L, 3L,
                "Капуста", 140.0));
        ingredients.add(new Ingredient(5L, 3L,
                "Сосиски", 120.0));

        Recipe savedRecipe2 = new Recipe(3L, "polinafomina", "Тушеная капуста", "789.png",
                "2023-03-19", "Русская", "Основные блюда", 40, ingredients,
                "Сочная тушеная капуста с сосисками", "Порезать капусту и сосиски, потушить");

        LinkedList<Recipe> expectedSavedRecipes = new LinkedList<>();
        expectedSavedRecipes.add(savedRecipe1);
        expectedSavedRecipes.add(savedRecipe2);

        User user = new User(1L, "polinafomina", "qwerty", "fominapolia2001@yandex.ru",
                addedRecipes, addedRecipes);
        user.setSavedRecipes(expectedSavedRecipes);
        LinkedList<Recipe> actualSavedRecipes = user.getSavedRecipes();

        assertEquals(expectedSavedRecipes, actualSavedRecipes);
    }
}