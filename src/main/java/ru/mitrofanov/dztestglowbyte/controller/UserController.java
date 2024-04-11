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
    public ResponseEntity<String> fillUsersTableFromFile(@RequestParam("file") MultipartFile file) {
        try {
            userService.fillUsersTableFromFile(file);
            return ResponseEntity.ok("Юзеры успешно добавлены из файла.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка: " + e.getMessage());
        }
    }

    @PostMapping("/fillUsersTableFromJSON")
    public ResponseEntity<String> fillUsersTableFromJSON(@RequestBody List<User> users) {
        userService.fillUsersTableFromJSON(users);
        return ResponseEntity.ok("Юзеры успешно добавлены из JSON.");
    }
}
