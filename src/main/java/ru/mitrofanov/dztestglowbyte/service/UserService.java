package ru.mitrofanov.dztestglowbyte.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.mitrofanov.dztestglowbyte.entity.User;
import ru.mitrofanov.dztestglowbyte.repository.UserRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void fillUsersTableFromFile(MultipartFile file) throws Exception {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            List<User> users = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                User user = new User();
                user.setName(data[0]);
                user.setAge(Integer.parseInt(data[1]));
                user.setCity(data[2]);
                users.add(user);
            }
            userRepository.saveAll(users);
        }
    }

    @Transactional
    public void fillUsersTableFromJSON(List<User> users) {
        userRepository.saveAll(users);
    }
}
