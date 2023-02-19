package ru.spbstu.povarenok.model;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class RecipeTest {

    @Test
    public void testGetId() {
        LinkedList<Ingredient> ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(1L, 1L,
                "Капуста", 100.0));
        ingredients.add(new Ingredient(2L, 1L,
                "Лук", 20.0));

        Long expectedId = 1L;
        Recipe recipe = new Recipe(expectedId, "polinafomina", "Борщ", "123.png",
                "2023-02-19", "Русская", "Суп", 140, ingredients,
                "Ароматный борщ, который подаётся с пампушками", "Порезать капусту и лук, сварить");
        Long actualId = recipe.getId();

        assertEquals(expectedId, actualId);
    }

    @Test
    public void testSetId() {
        LinkedList<Ingredient> ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(1L, 1L,
                "Капуста", 100.0));
        ingredients.add(new Ingredient(2L, 1L,
                "Лук", 20.0));

        Long expectedId = 1L;
        Recipe recipe = new Recipe(2L, "polinafomina", "Борщ", "123.png",
                "2023-02-19", "Русская", "Суп", 140, ingredients,
                "Ароматный борщ, который подаётся с пампушками", "Порезать капусту и лук, сварить");
        recipe.setId(expectedId);
        Long actualId = recipe.getId();

        assertEquals(expectedId, actualId);
    }

    @Test
    public void testGetUserLogin() {
        LinkedList<Ingredient> ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(1L, 1L,
                "Капуста", 100.0));
        ingredients.add(new Ingredient(2L, 1L,
                "Лук", 20.0));

        String expectedUserLogin = "polinafomina";
        Recipe recipe = new Recipe(1L, expectedUserLogin, "Борщ", "123.png",
                "2023-02-19", "Русская", "Суп", 140, ingredients,
                "Ароматный борщ, который подаётся с пампушками", "Порезать капусту и лук, сварить");
        String actualUserLogin = recipe.getUserLogin();

        assertEquals(expectedUserLogin, actualUserLogin);
    }

    @Test
    public void testSetUserLogin() {
        LinkedList<Ingredient> ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(1L, 1L,
                "Капуста", 100.0));
        ingredients.add(new Ingredient(2L, 1L,
                "Лук", 20.0));

        String expectedUserLogin = "polinafomina";
        Recipe recipe = new Recipe(1L, "verafomina", "Борщ", "123.png",
                "2023-02-19", "Русская", "Суп", 140, ingredients,
                "Ароматный борщ, который подаётся с пампушками", "Порезать капусту и лук, сварить");
        recipe.setUserLogin(expectedUserLogin);
        String actualUserLogin = recipe.getUserLogin();

        assertEquals(expectedUserLogin, actualUserLogin);
    }

    @Test
    public void testGetName() {
        LinkedList<Ingredient> ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(1L, 1L,
                "Капуста", 100.0));
        ingredients.add(new Ingredient(2L, 1L,
                "Лук", 20.0));

        String expectedName = "Борщ";
        Recipe recipe = new Recipe(1L, "polinafomina", expectedName, "123.png",
                "2023-02-19", "Русская", "Суп", 140, ingredients,
                "Ароматный борщ, который подаётся с пампушками", "Порезать капусту и лук, сварить");
        String actualName= recipe.getName();

        assertEquals(expectedName, actualName);
    }

    @Test
    public void testSetName() {
        LinkedList<Ingredient> ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(1L, 1L,
                "Капуста", 100.0));
        ingredients.add(new Ingredient(2L, 1L,
                "Лук", 20.0));

        String expectedName = "Борщ";
        Recipe recipe = new Recipe(1L, "polinafomina", "Щи", "123.png",
                "2023-02-19", "Русская", "Суп", 140, ingredients,
                "Ароматный борщ, который подаётся с пампушками", "Порезать капусту и лук, сварить");
        recipe.setName(expectedName);
        String actualName = recipe.getName();

        assertEquals(expectedName, actualName);
    }

    @Test
    public void testGetImageUrl() {
        LinkedList<Ingredient> ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(1L, 1L,
                "Капуста", 100.0));
        ingredients.add(new Ingredient(2L, 1L,
                "Лук", 20.0));

        String expectedImageUrl = "123.png";
        Recipe recipe = new Recipe(1L, "polinafomina", "Борщ", expectedImageUrl,
                "2023-02-19", "Русская", "Суп", 140, ingredients,
                "Ароматный борщ, который подаётся с пампушками", "Порезать капусту и лук, сварить");
        String actualImageUrl= recipe.getImageUrl();

        assertEquals(expectedImageUrl, actualImageUrl);
    }

    @Test
    public void testSetImageUrl() {
        LinkedList<Ingredient> ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(1L, 1L,
                "Капуста", 100.0));
        ingredients.add(new Ingredient(2L, 1L,
                "Лук", 20.0));

        String expectedImageUrl = "123.png";
        Recipe recipe = new Recipe(1L, "polinafomina", "Борщ", "456.png",
                "2023-02-19", "Русская", "Суп", 140, ingredients,
                "Ароматный борщ, который подаётся с пампушками", "Порезать капусту и лук, сварить");
        recipe.setImageUrl(expectedImageUrl);
        String actualImageUrl = recipe.getImageUrl();

        assertEquals(expectedImageUrl, actualImageUrl);
    }

    @Test
    public void testGetDateAdded() {
        LinkedList<Ingredient> ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(1L, 1L,
                "Капуста", 100.0));
        ingredients.add(new Ingredient(2L, 1L,
                "Лук", 20.0));

        String expectedDateAdded = "2023-02-19";
        Recipe recipe = new Recipe(1L, "polinafomina", "Борщ", "123.png",
                expectedDateAdded, "Русская", "Суп", 140, ingredients,
                "Ароматный борщ, который подаётся с пампушками", "Порезать капусту и лук, сварить");
        String actualDateAdded = recipe.getDateAdded();

        assertEquals(expectedDateAdded, actualDateAdded);
    }

    @Test
    public void testSetDateAdded() {
        LinkedList<Ingredient> ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(1L, 1L,
                "Капуста", 100.0));
        ingredients.add(new Ingredient(2L, 1L,
                "Лук", 20.0));

        String expectedDateAdded = "2023-02-19";
        Recipe recipe = new Recipe(1L, "polinafomina", "Борщ", "123.png",
                "2013-03-13", "Русская", "Суп", 140, ingredients,
                "Ароматный борщ, который подаётся с пампушками", "Порезать капусту и лук, сварить");
        recipe.setDateAdded(expectedDateAdded);
        String actualDateAdded = recipe.getDateAdded();

        assertEquals(expectedDateAdded, actualDateAdded);
    }

    @Test
    public void testGetCuisine() {
        LinkedList<Ingredient> ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(1L, 1L,
                "Капуста", 100.0));
        ingredients.add(new Ingredient(2L, 1L,
                "Лук", 20.0));

        String expectedCuisine = "Русская";
        Recipe recipe = new Recipe(1L, "polinafomina", "Борщ", "123.png",
                "2023-02-19", expectedCuisine, "Суп", 140, ingredients,
                "Ароматный борщ, который подаётся с пампушками", "Порезать капусту и лук, сварить");
        String actualCuisine = recipe.getCuisine();

        assertEquals(expectedCuisine, actualCuisine);
    }

    @Test
    public void testSetCuisine() {
        LinkedList<Ingredient> ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(1L, 1L,
                "Капуста", 100.0));
        ingredients.add(new Ingredient(2L, 1L,
                "Лук", 20.0));

        String expectedCuisine = "Русская";
        Recipe recipe = new Recipe(1L, "polinafomina", "Борщ", "123.png",
                "2023-02-19", "Французская", "Суп", 140, ingredients,
                "Ароматный борщ, который подаётся с пампушками", "Порезать капусту и лук, сварить");
        recipe.setCuisine(expectedCuisine);
        String actualCuisine = recipe.getCuisine();

        assertEquals(expectedCuisine, actualCuisine);
    }

    @Test
    public void testGetCategory() {
        LinkedList<Ingredient> ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(1L, 1L,
                "Капуста", 100.0));
        ingredients.add(new Ingredient(2L, 1L,
                "Лук", 20.0));

        String expectedCategory = "Суп";
        Recipe recipe = new Recipe(1L, "polinafomina", "Борщ", "123.png",
                "2023-02-19", "Русская", expectedCategory, 140, ingredients,
                "Ароматный борщ, который подаётся с пампушками", "Порезать капусту и лук, сварить");
        String actualCategory = recipe.getCategory();

        assertEquals(expectedCategory, actualCategory);
    }

    @Test
    public void testSetCategory() {
        LinkedList<Ingredient> ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(1L, 1L,
                "Капуста", 100.0));
        ingredients.add(new Ingredient(2L, 1L,
                "Лук", 20.0));

        String expectedCategory = "Суп";
        Recipe recipe = new Recipe(1L, "polinafomina", "Борщ", "123.png",
                "2023-02-19", "Русская", "Десерты", 140, ingredients,
                "Ароматный борщ, который подаётся с пампушками", "Порезать капусту и лук, сварить");
        recipe.setCategory(expectedCategory);
        String actualCategory = recipe.getCategory();

        assertEquals(expectedCategory, actualCategory);
    }

    @Test
    public void testGetCookingTime() {
        LinkedList<Ingredient> ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(1L, 1L,
                "Капуста", 100.0));
        ingredients.add(new Ingredient(2L, 1L,
                "Лук", 20.0));

        Integer expectedCookingTime = 140;
        Recipe recipe = new Recipe(1L, "polinafomina", "Борщ", "123.png",
                "2023-02-19", "Русская", "Суп", expectedCookingTime, ingredients,
                "Ароматный борщ, который подаётся с пампушками", "Порезать капусту и лук, сварить");
        Integer actualCookingTime = recipe.getCookingTime();

        assertEquals(expectedCookingTime, actualCookingTime);
    }

    @Test
    public void testSetCookingTime() {
        LinkedList<Ingredient> ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(1L, 1L,
                "Капуста", 100.0));
        ingredients.add(new Ingredient(2L, 1L,
                "Лук", 20.0));

        Integer expectedCookingTime = 140;
        Recipe recipe = new Recipe(1L, "polinafomina", "Борщ", "123.png",
                "2023-02-19", "Русская", "Суп", 250, ingredients,
                "Ароматный борщ, который подаётся с пампушками", "Порезать капусту и лук, сварить");
        recipe.setCookingTime(expectedCookingTime);
        Integer actualCookingTime = recipe.getCookingTime();

        assertEquals(expectedCookingTime, actualCookingTime);
    }

    @Test
    public void testGetIngredients() {
        LinkedList<Ingredient> expectedIngredients = new LinkedList<>();
        expectedIngredients.add(new Ingredient(1L, 1L,
                "Капуста", 100.0));
        expectedIngredients.add(new Ingredient(2L, 1L,
                "Лук", 20.0));

        Recipe recipe = new Recipe(1L, "polinafomina", "Борщ", "123.png",
                "2023-02-19", "Русская", "Суп", 140, expectedIngredients,
                "Ароматный борщ, который подаётся с пампушками", "Порезать капусту и лук, сварить");
        LinkedList<Ingredient> actualIngredients = recipe.getIngredients();

        assertEquals(expectedIngredients, actualIngredients);
    }

    @Test
    public void testSetIngredients() {
        LinkedList<Ingredient> ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(1L, 1L,
                "Капуста", 100.0));
        ingredients.add(new Ingredient(2L, 1L,
                "Лук", 20.0));

        LinkedList<Ingredient> expectedIngredients = new LinkedList<>();
        expectedIngredients.add(new Ingredient(3L, 1L,
                "Морковь", 110.0));
        expectedIngredients.add(new Ingredient(4L, 1L,
                "Свекла", 22.0));

        Recipe recipe = new Recipe(1L, "polinafomina", "Борщ", "123.png",
                "2023-02-19", "Русская", "Суп", 140, ingredients,
                "Ароматный борщ, который подаётся с пампушками", "Порезать капусту и лук, сварить");
        recipe.setIngredients(expectedIngredients);
        LinkedList<Ingredient> actualIngredients = recipe.getIngredients();

        assertEquals(expectedIngredients, actualIngredients);
    }

    @Test
    public void testGetDescription() {
        LinkedList<Ingredient> ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(1L, 1L,
                "Капуста", 100.0));
        ingredients.add(new Ingredient(2L, 1L,
                "Лук", 20.0));

        String expectedDescription = "Ароматный борщ, который подаётся с пампушками";
        Recipe recipe = new Recipe(1L, "polinafomina", "Борщ", "123.png",
                "2023-02-19", "Русская", "Суп", 140, ingredients,
                expectedDescription, "Порезать капусту и лук, сварить");
        String actualDescription = recipe.getDescription();

        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    public void testSetDescription() {
        LinkedList<Ingredient> ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(1L, 1L,
                "Капуста", 100.0));
        ingredients.add(new Ingredient(2L, 1L,
                "Лук", 20.0));

        String expectedDescription = "Ароматный борщ, который подаётся с пампушками";
        Recipe recipe = new Recipe(1L, "polinafomina", "Борщ", "123.png",
                "2023-02-19", "Русская", "Десерты", 140, ingredients,
                "Самый вкусный борщ", "Порезать капусту и лук, сварить");
        recipe.setDescription(expectedDescription);
        String actualDescription = recipe.getDescription();

        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    public void testGetRecipe() {
        LinkedList<Ingredient> ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(1L, 1L,
                "Капуста", 100.0));
        ingredients.add(new Ingredient(2L, 1L,
                "Лук", 20.0));

        String expectedRecipe = "Порезать капусту и лук, сварить";
        Recipe recipe = new Recipe(1L, "polinafomina", "Борщ", "123.png",
                "2023-02-19", "Русская", "Суп", 140, ingredients,
                "Ароматный борщ, который подаётся с пампушками", expectedRecipe);
        String actualRecipe = recipe.getRecipe();

        assertEquals(expectedRecipe, actualRecipe);
    }

    @Test
    public void testSetRecipe() {
        LinkedList<Ingredient> ingredients = new LinkedList<>();
        ingredients.add(new Ingredient(1L, 1L,
                "Капуста", 100.0));
        ingredients.add(new Ingredient(2L, 1L,
                "Лук", 20.0));

        String expectedRecipe = "Порезать капусту и лук, сварить";
        Recipe recipe = new Recipe(1L, "polinafomina", "Борщ", "123.png",
                "2023-02-19", "Русская", "Десерты", 140, ingredients,
                "Самый вкусный борщ", "Сварить суп");
        recipe.setRecipe(expectedRecipe);
        String actualRecipe = recipe.getRecipe();

        assertEquals(expectedRecipe, actualRecipe);
    }
}
