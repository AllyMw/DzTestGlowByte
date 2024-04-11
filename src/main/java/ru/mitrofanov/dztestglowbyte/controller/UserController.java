package ru.mitrofanov.dztestglowbyte.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.mitrofanov.dztestglowbyte.entity.User;
import ru.mitrofanov.dztestglowbyte.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/fillUsersTableFromFile")
    public String fillUsersTableFromFile(@RequestParam("file") MultipartFile file) {
        try {
            userService.fillUsersTableFromFile(file);
            return "Юзеры успешно добавлены из файла.";
        } catch (Exception e) {
            return "Произошла ошибка: " + e.getMessage();
        }
    }

    @PostMapping("/fillUsersTableFromJSON")
    public String fillUsersTableFromJSON(@RequestBody List<User> users) {
        try {
            userService.fillUsersTableFromJSON(users);
            return "Юзеры успешно добавлены из JSON.";
        }
        catch (Exception e) {
            return "Произошла ошибка: " + e.getMessage();
        }
    }
}
