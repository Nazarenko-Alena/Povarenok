package ru.spbstu.povorenok.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedList;

import ru.spbstu.povorenok.database.*;
import ru.spbstu.povorenok.model.*;

@RestController
@RequestMapping("/povorenok")
public class RecipeWebsiteController
{
    private final RecipeWebsiteRepository repository;

    public RecipeWebsiteController(RecipeWebsiteRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/authorization")
    public ResponseEntity<User> getUser(@RequestParam String login, @RequestParam String password)
    {
        if (login.length() > 30 || !login.matches("[a-zA-Zа-яА-Я0-9]+$")
        || password.length() < 8 || password.length() > 12 || !password.matches("[a-zA-Zа-яА-Я0-9]+$")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = repository.getUser(login, password);

        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/users/{login}")
    public ResponseEntity<User> getUser(@PathVariable(name = "login") String login)
    {
        if (login.length() > 30 || !login.matches("[a-zA-Zа-яА-Я0-9]+$")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = repository.getUser(login);

        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registerUser(@RequestBody User user)
    {
        if (user.getLogin().length() > 30 || !user.getLogin().matches("[a-zA-Zа-яА-Я0-9]+$")
                || user.getPassword().length() < 8 || user.getPassword().length() > 12
                || !user.getPassword().matches("[a-zA-Zа-яА-Я0-9]+$") || user.getEmail().length() > 30 ||
                !user.getEmail().matches("[a-zA-Zа-яА-Я0-9]+@[a-zA-Zа-яА-Я0-9]+$")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        repository.addUser(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/categories")
    public ResponseEntity<LinkedList<Category>> getCategories()
    {
        LinkedList<Category> categories = repository.getAllCategories();

        return categories != null
                ? new ResponseEntity<>(categories, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/cuisines")
    public ResponseEntity<LinkedList<Cuisine>> getCuisines()
    {
        LinkedList<Cuisine> cuisines = repository.getAllCuisines();

        return cuisines != null
                ? new ResponseEntity<>(cuisines, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
