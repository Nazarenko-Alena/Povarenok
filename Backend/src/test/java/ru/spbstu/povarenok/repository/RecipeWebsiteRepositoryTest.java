//package ru.spbstu.povarenok.repository;
//
//import org.junit.Test;
//import org.junit.Before;
//import org.junit.runner.RunWith;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Value;
//import ru.spbstu.povarenok.model.Ingredient;
//import ru.spbstu.povarenok.model.Recipe;
//import ru.spbstu.povarenok.model.User;
//
//import javax.sql.DataSource;
//import java.io.PrintWriter;
//import java.sql.*;
//import java.util.LinkedList;
//import java.util.logging.Logger;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
////import static org.junit.Assert.*;
////import static org.mockito.Matchers.*;
////import static org.mockito.Mockito.*;
////
////import java.sql.Connection;
////import java.sql.PreparedStatement;
////import java.sql.ResultSet;
////
////import javax.sql.DataSource;
////
////import org.anvard.introtojava.Person;
////import org.junit.Before;
////import org.junit.Test;
////import org.junit.runner.RunWith;
////import org.mockito.Mock;
////import org.mockito.runners.MockitoJUnitRunner;
//
//@RunWith(MockitoJUnitRunner.class)
//public class RecipeWebsiteRepositoryTest {
//
//    @Value("${database.url}")
//    public String DB_URL;
//
//    @Value("${database.user}")
//    public String DB_USER;
//
//    @Value("${database.password}")
//    public String DB_PASSWORD;
//
//    @Mock
//    private Connection connection;
//
//    @Mock
//    private Statement statement;
//
//    @Mock
//    private ResultSet result;
//
////    private Person p;
//
//    @Before
//    public void setUp() throws Exception {
//        when(DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)).thenReturn(connection);
//        when(connection.createStatement()).thenReturn(statement);
//        when(statement.executeQuery(any(String.class))).thenReturn(result);
//
////        p = new Person();
////        p.setId(1);
////        p.setFirstName("Johannes");
////        p.setLastName("Smythe");
//
////        when(result.next()).thenReturn(true);
////        when(rs.getInt(1)).thenReturn(1);
////        when(rs.getString(2)).thenReturn(p.getFirstName());
////        when(rs.getString(3)).thenReturn(p.getLastName());
////        when(stmt.executeQuery()).thenReturn(rs);
//    }
//
//    @Test
//    public void addUserTest() {
//        LinkedList<Ingredient> ingredients = new LinkedList<>();
//        ingredients.add(new Ingredient(1L, 1L,
//                "Капуста", 100.0));
//        ingredients.add(new Ingredient(2L, 1L,
//                "Лук", 20.0));
//
//        Recipe addedRecipe = new Recipe(1L, "polinafomina", "Щи", "123.png",
//                "2023-02-11", "Русская", "Суп", 90, ingredients,
//                "Ароматные щи с зеленью", "Порезать капусту и лук, сварить");
//
//        LinkedList<Recipe> addedRecipes = new LinkedList<>();
//        addedRecipes.add(addedRecipe);
//
//        ingredients = new LinkedList<>();
//        ingredients.add(new Ingredient(3L, 2L,
//                "Морковь", 220.0));
//        ingredients.add(new Ingredient(4L, 2L,
//                "Свёкла", 543.0));
//
//        Recipe savedRecipe1 = new Recipe(2L, "polinafomina", "Борщ", "456.png",
//                "2023-02-19", "Украинская", "Суп", 140, ingredients,
//                "Ароматный борщ, который подаётся с пампушками", "Порезать свёклу, потереть морковь, сварить");
//
//        ingredients = new LinkedList<>();
//        ingredients.add(new Ingredient(4L, 3L,
//                "Капуста", 140.0));
//        ingredients.add(new Ingredient(5L, 3L,
//                "Сосиски", 120.0));
//
//        Recipe savedRecipe2 = new Recipe(3L, "polinafomina", "Тушеная капуста", "789.png",
//                "2023-03-19", "Русская", "Основные блюда", 40, ingredients,
//                "Сочная тушеная капуста с сосисками", "Порезать капусту и сосиски, потушить");
//
//        LinkedList<Recipe> savedRecipes = new LinkedList<>();
//        savedRecipes.add(savedRecipe1);
//        savedRecipes.add(savedRecipe2);
//
//        User user = new User(1L, "polinafomina", "qwerty","fominapolia2001@yandex.ru",
//                addedRecipes, savedRecipes);
//
//        assertEquals(true, new RecipeWebsiteRepository().addUser(user));
//    }
//
////        @Test(expected=IllegalArgumentException.class)
////        public void nullCreateThrowsException() {
////            new PersonDao(ds).create(null);
////        }
////
////        @Test
////        public void createPerson() {
////            new PersonDao(ds).create(p);
////        }
////
////        @Test
////        public void createAndRetrievePerson() throws Exception {
////            PersonDao dao = new PersonDao(ds);
////            dao.create(p);
////            Person r = dao.retrieve(1);
////            assertEquals(p, r);
////        }
//}
