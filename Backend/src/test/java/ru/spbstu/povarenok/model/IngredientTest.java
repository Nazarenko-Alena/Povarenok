package ru.spbstu.povarenok.model;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class IngredientTest {

    @Test
    public void testGetId() {
        Long expectedId = 1L;
        Ingredient ingredient = new Ingredient(expectedId, 2L,
                "Капуста", 100.0);
        Long actualId = ingredient.getId();

        assertEquals(expectedId, actualId);
    }

    @Test
    public void testSetId() {
        Long expectedId = 2L;
        Ingredient ingredient = new Ingredient(1L, 2L,
                "Капуста", 100.0);
        ingredient.setId(expectedId);
        Long actualId = ingredient.getId();

        assertEquals(expectedId, actualId);
    }

    @Test
    public void testGetIdRecipe() {
        Long expectedIdRecipe = 2L;
        Ingredient ingredient = new Ingredient(1L, expectedIdRecipe,
                "Капуста", 100.0);
        Long actualIdRecipe = ingredient.getIdRecipe();

        assertEquals(expectedIdRecipe, actualIdRecipe);
    }

    @Test
    public void testSetIdRecipe() {
        Long expectedIdRecipe = 2L;
        Ingredient ingredient = new Ingredient(1L, 1L,
                "Капуста", 100.0);
        ingredient.setIdRecipe(expectedIdRecipe);
        Long actualIdRecipe = ingredient.getIdRecipe();

        assertEquals(expectedIdRecipe, actualIdRecipe);
    }

    @Test
    public void testGetName() {
        String expectedName = "Капуста";
        Ingredient ingredient = new Ingredient(1L, 2L,
                expectedName, 100.0);
        String actualName = ingredient.getName();

        assertEquals(expectedName, actualName);
    }

    @Test
    public void testSetName() {
        String expectedName = "Капуста";
        Ingredient ingredient = new Ingredient(1L, 2L,
                "Лук", 100.0);
        ingredient.setName(expectedName);
        String actualName = ingredient.getName();

        assertEquals(expectedName, actualName);
    }

    @Test
    public void testGetGrams() {
        Double expectedGrams = 100.0;
        Ingredient ingredient = new Ingredient(1L, 2L,
                "Капуста", expectedGrams);
        Double actualGrams = ingredient.getGrams();

        assertEquals(expectedGrams, actualGrams);
    }

    @Test
    public void testSetGrams() {
        Double expectedGrams = 100.0;
        Ingredient ingredient = new Ingredient(1L, 2L,
                "Капуста", 200.0);
        ingredient.setGrams(expectedGrams);
        Double actualGrams = ingredient.getGrams();

        assertEquals(expectedGrams, actualGrams);
    }
}