package ru.spbstu.povarenok.model;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class CategoryTest {

    @Test
    public void testGetId() {
        Long expectedId = 1L;
        Category category = new Category(expectedId, "Суп");
        Long actualId = category.getId();

        assertEquals(expectedId, actualId);
    }

    @Test
    public void testSetId() {
        Long expectedId = 2L;
        Category category = new Category(1L, "Суп");
        category.setId(expectedId);
        Long actualId = category.getId();

        assertEquals(expectedId, actualId);
    }

    @Test
    public void testGetName() {
        String expectedName = "Суп";
        Category category = new Category(1L, expectedName);
        String actualName = category.getName();

        assertEquals(expectedName, actualName);
    }

    @Test
    public void testSetName() {
        String expectedName = "Суп";
        Category category = new Category(1L, "Десерты");
        category.setName(expectedName);
        String actualName = category.getName();

        assertEquals(expectedName, actualName);
    }
}