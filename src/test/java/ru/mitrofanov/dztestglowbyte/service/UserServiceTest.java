package ru.mitrofanov.dztestglowbyte.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import ru.mitrofanov.dztestglowbyte.entity.User;
import ru.mitrofanov.dztestglowbyte.repository.UserRepository;
import ru.mitrofanov.dztestglowbyte.service.UserService;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void fillUsersTableFromFileTest() throws Exception {
        String content = "Дмитрий;24;Калуга\nЮля;25;Москва";
        InputStream is = new ByteArrayInputStream(content.getBytes());
        MultipartFile file = new MockMultipartFile("file", "test.csv", "text/plain", is);

        userService.fillUsersTableFromFile(file);

        verify(userRepository, times(1)).saveAll(any(List.class));
    }

    @Test
    void fillUsersTableFromJSONTest() {
        User user1 = new User();
        user1.setName("Дмитрий");
        user1.setAge(24);
        user1.setCity("Калуга");

        User user2 = new User();
        user2.setName("Юля");
        user2.setAge(25);
        user2.setCity("Москва");

        List<User> users = Arrays.asList(user1, user2);

        userService.fillUsersTableFromJSON(users);

        verify(userRepository, times(1)).saveAll(users);
    }
}
