package ru.spbstu.povarenok.model;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class CuisineTest {

    @Test
    public void testGetId() {
        Long expectedId = 1L;
        Cuisine cuisine = new Cuisine(expectedId, "Русская");
        Long actualId = cuisine.getId();

        assertEquals(expectedId, actualId);
    }

    @Test
    public void testSetId() {
        Long expectedId = 2L;
        Cuisine cuisine = new Cuisine(1L, "Русская");
        cuisine.setId(expectedId);
        Long actualId = cuisine.getId();

        assertEquals(expectedId, actualId);
    }

    @Test
    public void testGetName() {
        String expectedName = "Русская";
        Cuisine cuisine = new Cuisine(1L, expectedName);
        String actualName = cuisine.getName();

        assertEquals(expectedName, actualName);
    }

    @Test
    public void testSetName() {
        String expectedName = "Русская";
        Cuisine cuisine = new Cuisine(1L, "Французская");
        cuisine.setName(expectedName);
        String actualName = cuisine.getName();

        assertEquals(expectedName, actualName);
    }
}