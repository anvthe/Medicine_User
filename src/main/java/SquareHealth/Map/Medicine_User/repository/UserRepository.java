package SquareHealth.Map.Medicine_User.repository;

import SquareHealth.Map.Medicine_User.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
Optional<User> findByEmail(String email);
}
