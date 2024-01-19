package epicode.u2w2d5BEdispositivi.repositories;

import epicode.u2w2d5BEdispositivi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDAO extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
