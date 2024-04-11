package ru.mitrofanov.dztestglowbyte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mitrofanov.dztestglowbyte.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}